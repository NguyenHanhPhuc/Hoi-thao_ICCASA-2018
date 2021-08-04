package com.test.CustomClasses;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.http.HttpMethod;



class ChromeDriverCommandExecutor extends DriverCommandExecutor {
    private static final ImmutableMap<String, CommandInfo> CHROME_COMMAND_NAME_TO_URL;

    public ChromeDriverCommandExecutor(DriverService service) {
        super(service, CHROME_COMMAND_NAME_TO_URL);
    }

    static {
        CHROME_COMMAND_NAME_TO_URL = ImmutableMap.of("launchApp", new CommandInfo("/session/:sessionId/chromium/launch_app", HttpMethod.POST));
    }
}
