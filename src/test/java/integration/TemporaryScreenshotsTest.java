package integration;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.isHtmlUnit;
import static org.junit.Assume.assumeFalse;

import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TemporaryScreenshotsTest extends IntegrationTest {

  @Test(expected = RasterFormatException.class)
  public void originalIssueTest() throws IOException {
    assumeFalse(isHtmlUnit());
    open("https://www.baidu.com");
    $(By.id("kw")).val("selenide");
    $(By.id("su")).click();
    $(byText("Loading")).waitUntil(disappears, 300000);
    byte[] screen = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    BufferedImage tmp = ImageIO.read(new ByteArrayInputStream(screen));
    SelenideElement element = $("#content_left");
    Point elementLocation = element.getLocation();
    Dimension elementSize = element.getSize();
    tmp.getSubimage(elementLocation.getX(), elementLocation.getY(),
                    elementSize.getWidth(), elementSize.getHeight());
  }
}
