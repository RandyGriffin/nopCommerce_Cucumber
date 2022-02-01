package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import utilities.WaitHelper;

import java.util.List;

public class SearchCustomerPage {

    public WebDriver ldriver;

    WaitHelper waitHelper;
    public SearchCustomerPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(ldriver,this);
        waitHelper=new WaitHelper(ldriver);
    }

    @FindBy(how = How.ID, using = "SearchEmail")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(how = How.ID, using = "SearchFirstName")
    @CacheLookup
    WebElement txtFirstName;

    @FindBy(how = How.ID, using = "SearchLastName")
    @CacheLookup
    WebElement txtLastName;


    @FindBy(how = How.ID, using = "search-customers")
    @CacheLookup
    WebElement btnSearch;

    @FindBy(how = How.XPATH, using = "//table[@role='grid']")
    @CacheLookup
    WebElement tblSearchResults;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
    @CacheLookup
    WebElement table;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
    @CacheLookup
    List<WebElement> tableRows;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
    @CacheLookup
    List<WebElement> tableColumns;

    //Action method
    public void setEmail(String email)
    {
        waitHelper.WaitForElement(txtEmail,30);
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setFirstName(String fname) {
        waitHelper.WaitForElement(txtFirstName, 30);
        txtFirstName.clear();
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        waitHelper.WaitForElement(txtFirstName, 30);
        txtLastName.clear();
        txtLastName.sendKeys(lname);
    }

    public void clickSearch() {
        btnSearch.click();
        waitHelper.WaitForElement(btnSearch, 30);
    }

    public int getNoOfRows() {
        return(tableRows.size());
    }

    public int getNoOfColumns() {
        return(tableColumns.size());
    }

    public boolean searchCustomerByEmail(String email)
    {
        boolean flag=false;

        for(int i=1;i<=getNoOfRows();i++)
        {
            String emailid=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();

            System.out.println(emailid);

            if(emailid.equals(email))
            {
                flag=true;
            }
        }

        return flag;
    }

    public boolean searchCustomerByName(String Name)
    {
        boolean flag=false;

        for(int i=1;i<=getNoOfRows();i++)
        {
            String name=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();

            String names[]=name.split(" "); //Separating fname & lname

            if(names[0].equals("Victoria") && names[1].equals("Terces"))
            {
                flag=true;
            }
        }

        return flag; }
}
