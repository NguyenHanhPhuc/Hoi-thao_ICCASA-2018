package testcase;

import com.test.CustomClasses.ChromeDriver;
import com.test.constant.PropertyConstant;
import com.test.until.ConfigurationUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

public class Core {
    String un="mngr53532";
    String pw="abc123!";

    ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    ConfigurationUtil props = ConfigurationUtil.getInstance();

    public Core() throws IOException {
    }

    @BeforeMethod
    public void beforeMethod(){
        String chromeDriverPath = System.getProperty("user.dir") + props.getProperty(PropertyConstant.CHROME_PATH);
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        drivers.set(new ChromeDriver(new ChromeOptions()));
    }

    @AfterMethod
    public void afterMethod(){
        drivers.get().close();
    }

    public WebDriver getDriver(){
        return drivers.get();
    }

    public static void takeSnapShot(WebDriver webdriver, String filePath) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(filePath);
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
