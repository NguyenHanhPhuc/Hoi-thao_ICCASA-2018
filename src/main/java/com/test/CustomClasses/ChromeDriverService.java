package com.test.CustomClasses;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriverException;


public class ChromeDriverService extends DriverService {
    public static final String CHROME_DRIVER_EXE_PROPERTY = "webdriver.chrome.driver";
    public static final String CHROME_DRIVER_LOG_PROPERTY = "webdriver.chrome.logfile";
    public static final String CHROME_DRIVER_VERBOSE_LOG_PROPERTY = "webdriver.chrome.verboseLogging";
    public static final String CHROME_DRIVER_SILENT_OUTPUT_PROPERTY = "webdriver.chrome.silentOutput";
    public static final String CHROME_DRIVER_WHITELISTED_IPS_PROPERTY = "webdriver.chrome.whitelistedIps";

    public ChromeDriverService(File executable, int port, ImmutableList<String> args, ImmutableMap<String, String> environment) throws IOException {
        super(executable, port, args, environment);
    }

    public static ChromeDriverService createDefaultService() {
        return (ChromeDriverService)((ChromeDriverService.Builder)(new ChromeDriverService.Builder()).usingAnyFreePort()).build();
    }

    public static class Builder extends DriverService.Builder<ChromeDriverService, ChromeDriverService.Builder> {
        private boolean verbose = Boolean.getBoolean("webdriver.chrome.verboseLogging");
        private boolean silent = Boolean.getBoolean("webdriver.chrome.silentOutput");
        private String whitelistedIps = System.getProperty("webdriver.chrome.whitelistedIps");

        public Builder() {
        }

        public ChromeDriverService.Builder withVerbose(boolean verbose) {
            this.verbose = verbose;
            return this;
        }

        public ChromeDriverService.Builder withSilent(boolean silent) {
            this.silent = silent;
            return this;
        }

        public ChromeDriverService.Builder withWhitelistedIps(String whitelistedIps) {
            this.whitelistedIps = whitelistedIps;
            return this;
        }

        protected File findDefaultExecutable() {
            return ChromeDriverService.findExecutable("chromedriver", "webdriver.chrome.driver", "https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver", "http://chromedriver.storage.googleapis.com/index.html");
        }

        protected ImmutableList<String> createArgs() {
            if (this.getLogFile() == null) {
                String logFilePath = System.getProperty("webdriver.chrome.logfile");
                if (logFilePath != null) {
                    this.withLogFile(new File(logFilePath));
                }
            }

            com.google.common.collect.ImmutableList.Builder<String> argsBuilder = ImmutableList.builder();
            argsBuilder.add(String.format("--port=%d", this.getPort()));
            if (this.getLogFile() != null) {
                argsBuilder.add(String.format("--log-path=%s", this.getLogFile().getAbsolutePath()));
            }

            if (this.verbose) {
                argsBuilder.add("--verbose");
            }

            if (this.silent) {
                argsBuilder.add("--silent");
            }

            if (this.whitelistedIps != null) {
                argsBuilder.add(String.format("--whitelisted-ips=%s", this.whitelistedIps));
            }

            return argsBuilder.build();
        }

        protected ChromeDriverService createDriverService(File exe, int port, ImmutableList<String> args, ImmutableMap<String, String> environment) {
            try {
                return new ChromeDriverService(exe, port, args, environment);
            } catch (IOException var6) {
                throw new WebDriverException(var6);
            }
        }
    }
}
