import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.time.Clock;
import java.util.List;

public class MainPartsTest {

    AndroidDriver dispatcher;

    @BeforeTest
    public void connectDevice() throws IOException {

        Dotenv env = Dotenv.configure().filename("./src/test/java/environment/capabilities.env").load();

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName", env.get("device"));
        caps.setCapability("platformName", env.get("platform"));
        caps.setCapability("platformVersion", env.get("version"));
        caps.setCapability("app",
                new File(".").getCanonicalPath().replaceAll("\\\\" , "/") +
                "/src/test/java/environment/speedtest.apk");
        caps.setCapability("appPackage","org.zwanoo.android.speedtest");
        caps.setCapability("appActivity","com.ookla.mobile4.screens.main.MainActivity");
        caps.setCapability("autoGrantPermissions","true");

        dispatcher = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),caps);


    }

    @BeforeClass
    public void startOoklaSpeedTest(){
        WebDriverWait wait = new WebDriverWait(dispatcher, 5);

        try {

            WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.Button[@text=\"Next\"]")));

            wait.until(ExpectedConditions.visibilityOf(nextButton));

            nextButton.click();

            WebElement continueButton = dispatcher.findElementByXPath("//android.widget.Button[@text=\"Continue\"]");
            continueButton.click();

            WebElement progress = dispatcher.findElementByXPath("//android.widget.ProgressBar");

            wait.until(ExpectedConditions.invisibilityOf(progress));

        //If start page would not enabled it will be another case to go to test
        }catch (TimeoutException t){
            t.fillInStackTrace();
        }

        WebElement name = dispatcher.findElementByXPath("//android.widget.TextView[@text=\"SPEEDTEST\"]");
        name.click();

        WebElement go  = dispatcher.findElement(By.xpath("//android.view.View[@content-desc=\"Start a Speedtest\"]"));
        go.click();

    }

    @Test
    public void networkTestCompletedUpToTime(){
        WebDriverWait wait = new WebDriverWait(dispatcher,40);
        WebElement go  = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc=\"Start a Speedtest\"]")));

        Assert.assertTrue(go.isDisplayed());

    }

    @Test
    public void checkDownloadValue(){
        //This instruction need to be duplicated, because it save possibility to run tests in parallel
        WebDriverWait wait = new WebDriverWait(dispatcher,40);
        WebElement go  = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc=\"Start a Speedtest\"]")));

        WebElement download_frame  = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.FrameLayout[@content-desc=\"DOWNLOAD\"]")));

        Assert.assertTrue(download_frame.isDisplayed());

        WebElement download_text  = download_frame.findElement(
                By.xpath("//*[@resource-id=\"org.zwanoo.android.speedtest:id/txt_test_result_value\"]"));

        Assert.assertTrue(download_text.isDisplayed());

        System.out.println(download_text.getText());

    }

    @Test
    public void checkUploadValue(){
        //This instruction need to be duplicated, because it save possibility to run tests in parallel
        WebDriverWait wait = new WebDriverWait(dispatcher,30);
        WebElement go  = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc=\"Start a Speedtest\"]")));

        WebElement download_frame  = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.FrameLayout[@content-desc=\"UPLOAD\"]")));

        Assert.assertTrue(download_frame.isDisplayed());

        WebElement upload_text  = download_frame.findElement(
                By.xpath("//*[@resource-id=\"org.zwanoo.android.speedtest:id/txt_test_result_value\"]"));

        Assert.assertTrue(upload_text.isDisplayed());

        System.out.println(upload_text.getText());

    }

    @Test
    public void checkPingValue(){
        //This instruction need to be duplicated, because it save possibility to run tests in parallel
        WebDriverWait wait = new WebDriverWait(dispatcher,40);
        WebElement go  = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc=\"Start a Speedtest\"]")));

        WebElement download_frame  = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.ViewGroup[@content-desc=\"Ping\"]")));

        Assert.assertTrue(download_frame.isDisplayed());

        WebElement download_text  = download_frame.findElement(
                By.xpath("//*[@resource-id=\"org.zwanoo.android.speedtest:id/txt_test_result_value\"]"));

        Assert.assertTrue(download_text.isDisplayed());

        System.out.println(download_text.getText());

    }

    @Test
    public void checkJitterValue(){
        //This instruction need to be duplicated, because it save possibility to run tests in parallel
        WebDriverWait wait = new WebDriverWait(dispatcher,40);
        WebElement go  = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc=\"Start a Speedtest\"]")));

        WebElement download_frame  = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.ViewGroup[@content-desc=\"Jitter\"]")));

        Assert.assertTrue(download_frame.isDisplayed());

        WebElement download_text  = download_frame.findElement(
                By.xpath("//*[@resource-id=\"org.zwanoo.android.speedtest:id/txt_test_result_value\"]"));

        Assert.assertTrue(download_text.isDisplayed());

        System.out.println(download_text.getText());

    }

    @Test
    public void checkLossValue(){
        //This instruction need to be duplicated, because it save possibility to run tests in parallel
        WebDriverWait wait = new WebDriverWait(dispatcher,40);

        WebElement go  = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.View[@content-desc=\"Start a Speedtest\"]")));

        WebElement description  = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.ViewGroup[@resource-id=\"org.zwanoo.android.speedtest:id/test_result_item_packet_loss\"]")));


        Assert.assertTrue(description.isDisplayed());

        WebElement value = description.findElement(
                By.xpath("//*[@resource-id=\"org.zwanoo.android.speedtest:id/txt_test_result_value\"]"));

        Assert.assertTrue(value.isDisplayed());

        System.out.println(value.getText());

    }

    @AfterTest(alwaysRun = true)
    public void closeDriver(){

        dispatcher.quit();

    }

}
