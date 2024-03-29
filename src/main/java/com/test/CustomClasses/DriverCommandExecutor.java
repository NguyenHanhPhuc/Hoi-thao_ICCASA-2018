package com.test.CustomClasses;



import com.google.common.base.Throwables;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.Response;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Map;
import java.util.Objects;

/**
 * A specialized {@link HttpCommandExecutor} that will use a {@link DriverService} that lives
 * and dies with a single WebDriver session. The service will be restarted upon each new session
 * request and shutdown after each quit command.
 */
public class DriverCommandExecutor extends HttpCommandExecutor {

    private final DriverService service;

    /**
     * Creates a new DriverCommandExecutor which will communicate with the driver as configured
     * by the given {@code service}.
     *
     * @param service The DriverService to send commands to.
     */
    public DriverCommandExecutor(DriverService service) {
        super(Objects.requireNonNull(service.getUrl(), "DriverService is required"));
        this.service = service;
    }

    /**
     * Creates an {@link DriverCommandExecutor} that supports non-standard
     * {@code additionalCommands} in addition to the standard.
     *
     * @param service driver server
     * @param additionalCommands additional commands the remote end can process
     */
    protected DriverCommandExecutor(
            DriverService service, Map<String, CommandInfo> additionalCommands) {
        super(additionalCommands, service.getUrl());
        this.service = service;
    }

    /**
     * Sends the {@code command} to the driver server for execution. The server will be started
     * if requesting a new session. Likewise, if terminating a session, the server will be shutdown
     * once a response is received.
     *
     * @param command The command to execute.
     * @return The command response.
     * @throws IOException If an I/O error occurs while sending the command.
     */
    @Override
    public Response execute(Command command) throws IOException {
        if (DriverCommand.NEW_SESSION.equals(command.getName())) {
            service.start();
        }

        try {
            return super.execute(command);
        } catch (Throwable t) {
            Throwable rootCause = Throwables.getRootCause(t);
            if (rootCause instanceof ConnectException &&
                    "Connection refused".equals(rootCause.getMessage()) &&
                    !service.isRunning()) {
                throw new WebDriverException("The driver server has unexpectedly died!", t);
            }
            Throwables.throwIfUnchecked(t);
            throw new WebDriverException(t);
        } finally {
            if (DriverCommand.QUIT.equals(command.getName())) {
                service.stop();
            }
        }
    }
}
