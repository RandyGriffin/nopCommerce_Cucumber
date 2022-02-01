package stepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.eo.Se;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoginSteps extends BaseClass{



    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {
        logger= Logger.getLogger("nopCommerce"); //Added logger
        PropertyConfigurator.configure("log4j.properties");
        System.setProperty("webdriver.chrome.driver","C://Users//justs/Downloads/chromedriver_win32/chromedriver.exe");
        driver=new ChromeDriver();
        logger.info("***** Launching Browser *****");
        lp=new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {
        driver.get(url);
        logger.info("***** Opening URL *****");

        driver.manage().window().maximize();
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
        logger.info("***** Providing login details *****");

        lp.setUserName(email);
        lp.setPassword(password);
    }

    @When("Click on Login")
    public void click_on_login() throws InterruptedException {
        logger.info("***** started login process *****");

        lp.clickLogin();
        Thread.sleep(3000);
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String title) throws InterruptedException {

        if(driver.getPageSource().contains("Login was unsuccessful.")){
            driver.close();
            logger.info("***** Login passed *****");
            Assert.assertTrue(false);
        } else {
            logger.info("***** Login failed *****");
            Assert.assertEquals(title, driver.getTitle());
        }
        Thread.sleep(3000);
    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link() throws InterruptedException {
        logger.info("***** Logging out *****");

        lp.clickLogout();
        Thread.sleep(3000);
    }

    @Then("close browser")
    public void close_browser(){
        logger.info("***** Closing Browser *****");
        driver.close();
    }

    //*********Customer feature step definitions **********
    @When("User open URL {string}")
    public void user_open_url(String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {

        addCust=new AddcustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());

    }
    @When("User click on customers Menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        Thread.sleep(3000);
        addCust.clickOnCustomersMenu();

    }
    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() throws InterruptedException {
        Thread.sleep(2000);
        addCust.clickOnCustomersMenuItem();
    }
    @When("click on Add new button")
    public void click_on_add_new_button() throws InterruptedException {
        addCust.clickOnAddnew();
        Thread.sleep(2000);

    }
    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration",addCust.getPageTitle());  //Expected title of page
    }
    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        logger.info("***** Adding new customer *****");
        logger.info("*****  Providing customer details *****");
        String email= randomstring()+"@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword("test123");
        //Registered - default
        //The customer cannot be in both 'Guests' and 'Registered' customer roles
        //Add the customer to 'Guests' or 'Registered' customer role
        addCust.setCustomerRoles("Guest");
        Thread.sleep(3000);

        addCust.setManagerOfVendor("Vendor 2");
        addCust.setGender("Male");
        addCust.setFirstName("Randy");
        addCust.setLastName("Griffin");
        addCust.setDob("8/24/1999"); // Format: D/MM/YYYY
        addCust.setCompanyName("busyQA");
        addCust.setAdminContent("This is for testing............");
    }
    @When("click on Save button")
    public void click_on_save_button() throws InterruptedException {
        logger.info("***** Saving customer data *****");
        addCust.clickOnSave();
        Thread.sleep(2000);
    }
    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String msg) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully."));
    }
//Steps for searching a customer using Email ID**********
    @When("Enter customer Email")
    public void enter_customer_email() {
        logger.info("***** Searching customer by using emailID *****");

        searchCust = new SearchCustomerPage(driver);

        searchCust.setEmail("victoria_victoria@nopCommerce.com");
    }
    @When("Click on search button")
    public void click_on_search_button() throws InterruptedException {
        searchCust.clickSearch();
        Thread.sleep(3000);
    }
    @Then("User should find Email in the search table")
    public void user_should_find_email_in_the_search_table() {
       boolean status= searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");

       Assert.assertEquals(true,status);
    }

    //Steps for searching a customer by using First Name & Last Name
    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
        logger.info("***** Searching customer by name *****");

        searchCust=new SearchCustomerPage(driver);
        searchCust.setFirstName("Victoria");
    }
    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        searchCust.setLastName("Terces");
    }
    @Then("User should find Name in the search table")
    public void user_should_find_name_in_the_search_table() {
        boolean status= searchCust.searchCustomerByName("Victoria Terces");
        Assert.assertEquals(true, status);
    }
}
