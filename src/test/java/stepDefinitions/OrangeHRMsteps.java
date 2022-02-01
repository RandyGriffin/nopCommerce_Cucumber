package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrangeHRMsteps {

    WebDriver driver;
    @Given("I launched chrome browser")
    public void i_launched_chrome_browser() {
       System.setProperty("webdriver.chrome.driver", "C://Users//justs/Downloads/chromedriver_win32/chromedriver.exe");
       driver=new ChromeDriver(); // Launch chrome browser
    }

    @When("I open orange hrm homepage")
    public void i_open_orange_hrm_homepage() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @Then("I verify that the logo is present on page")
    public void i_verify_that_the_logo_is_present_on_page() {
        boolean status = driver.findElement(By.xpath("//div[@id='divLogo']//img")).isDisplayed();
        Assert.assertEquals(true, status);
    }

    @And("close browsers")
    public void close_browsers() {
        driver.quit();
    }
}
