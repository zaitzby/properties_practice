package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentHelper.*;

public class BaseTest {
    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.browser = System.getProperty("web.browser", "chrome");
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
        String remoteDriver = System.getProperty("remote.webdriver");
        if (remoteDriver != null)
            Configuration.remote = remoteDriver;
    }

    @AfterEach
    void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        if(System.getProperty("video.storage")!= null)
            attachVideo();
        closeWebDriver();
    }
}
