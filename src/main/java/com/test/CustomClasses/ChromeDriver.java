package com.test.CustomClasses;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteLocationContext;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import org.openqa.selenium.remote.mobile.RemoteNetworkConnection;

public class ChromeDriver extends RemoteWebDriver implements LocationContext, WebStorage, HasTouchScreen, NetworkConnection {
    private RemoteLocationContext locationContext;
    private RemoteWebStorage webStorage;
    private TouchScreen touchScreen;
    private RemoteNetworkConnection networkConnection;

    public ChromeDriver() {
        this(ChromeDriverService.createDefaultService(), new ChromeOptions());
    }

    public ChromeDriver(ChromeDriverService service) {
        this(service, new ChromeOptions());
    }

    /** @deprecated */
    @Deprecated
    public ChromeDriver(Capabilities capabilities) {
        this(ChromeDriverService.createDefaultService(), capabilities);
    }

    public ChromeDriver(ChromeOptions options) {
        this(ChromeDriverService.createDefaultService(), options);
    }

    public ChromeDriver(ChromeDriverService service, ChromeOptions options) {
        this(service, (Capabilities)options);
    }

    /** @deprecated */
    @Deprecated
    public ChromeDriver(ChromeDriverService service, Capabilities capabilities) {
        super(new ChromeDriverCommandExecutor(service), capabilities);
        this.locationContext = new RemoteLocationContext(this.getExecuteMethod());
        this.webStorage = new RemoteWebStorage(this.getExecuteMethod());
        this.touchScreen = new RemoteTouchScreen(this.getExecuteMethod());
        this.networkConnection = new RemoteNetworkConnection(this.getExecuteMethod());
    }

    public void setFileDetector(FileDetector detector) {
        throw new WebDriverException("Setting the file detector only works on remote webdriver instances obtained via RemoteWebDriver");
    }

    public LocalStorage getLocalStorage() {
        return this.webStorage.getLocalStorage();
    }

    public SessionStorage getSessionStorage() {
        return this.webStorage.getSessionStorage();
    }

    public Location location() {
        return this.locationContext.location();
    }

    public void setLocation(Location location) {
        this.locationContext.setLocation(location);
    }

    public TouchScreen getTouch() {
        return this.touchScreen;
    }

    public ConnectionType getNetworkConnection() {
        return this.networkConnection.getNetworkConnection();
    }

    public ConnectionType setNetworkConnection(ConnectionType type) {
        return this.networkConnection.setNetworkConnection(type);
    }

    public void launchApp(String id) {
        this.execute("launchApp", ImmutableMap.of("id", id));
    }
}
