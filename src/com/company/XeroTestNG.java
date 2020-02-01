package com.company;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class XeroTestNG  {
    //Driver variable
    protected static WebDriver driver;
    // Logger Variables
    protected static ExtentReports reports;
    static ExtentTest logger;
    static String reportFolder = "C:\\Pallavi\\Selenium-Utility\\XeroTestCaseReports\\";
    //File Path
    static String filePath = "C:\\Pallavi\\Selenium-Utility\\XeroDataFile.txt";
    static String imgPath = "C:\\Users\\Pallavi\\Pictures\\sundar.jpg";

    /* @BeforeSuite
     public void setup() throws InterruptedException {
         InitializeDriver (null, "https://www.xero.com/us");
     }  */

     @BeforeClass
     public void loadReportPorperty()
     {
         InitializeReport();
     }
     @AfterClass
     public void closeReport()
     {
         reports.flush();
     }
     @BeforeTest
     public void waitBeforeAllTest() throws InterruptedException {
         Thread.sleep(10000);
     }

   /*  @AfterMethod
     public void closeDriver()
     {
         reports.endTest(logger);
         driver.close();
     }
*/
    @Test (priority=1)
    private static void TC01_successfulLogin() throws IOException, InterruptedException {
         logger = reports.startTest("TC01_successfulLogin");
         InitializeDriver (null, "https://www.xero.com/us");
         Thread.sleep(2000);
         //Login Btn
         WebElement loginBtn = driver.findElement(By.xpath("//a[@class='btn btn-tertiary-alt global-ceiling-bar-btn']"));
         clickElement(loginBtn,"Login Button");
         logger.log(LogStatus.INFO, "Xero Login Page is Loaded");
         Thread.sleep(2000);
         //Reading the file to get login
         String[][] data = ReadWriteTextFile.readTextFile(filePath);
         //Send value to email
         WebElement email_txt = driver.findElement(By.id("email"));
         enterText(email_txt, data[0][1], "Email");
         //Send value to Password textbox
         WebElement passwd_txt = driver.findElement(By.id("password"));
         enterText(passwd_txt, data[1][1], "Password");
         //Click Login Button
         WebElement login_btn = driver.findElement(By.id("submitButton"));
         clickElement(login_btn, "Login Button");
         Thread.sleep(2000L);
         logger.log(LogStatus.PASS, "User Logged on Successfully");
         logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
     }
    @Test (priority=2)
    private static void TC01B_IncorrectPassword() throws IOException, InterruptedException {
         logger = reports.startTest("TC01B_IncorrectPassword");
         InitializeDriver (null, "https://www.xero.com/us");
         Thread.sleep(2000);
         //Login Btn
         WebElement loginBtn = driver.findElement(By.xpath("//a[@class='btn btn-tertiary-alt global-ceiling-bar-btn']"));
         clickElement(loginBtn,"Login Button");
         logger.log(LogStatus.INFO, "Xero Login Page is Loaded");
         Thread.sleep(2000);
         //Reading the file to get login
         String[][] data = ReadWriteTextFile.readTextFile(filePath);
         //Send value to email
         WebElement email_txt = driver.findElement(By.id("email"));
         enterText(email_txt, data[0][1], "Email");
         //Send value to Password textbox
         WebElement passwd_txt = driver.findElement(By.id("password"));
         enterText(passwd_txt, "Password", "Password");
         //Click Login Button
         WebElement login_btn = driver.findElement(By.id("submitButton"));
         clickElement(login_btn, "Login Button");
         Thread.sleep(2000L);
         logger.log(LogStatus.PASS, "Message - Your email or password is incorrect is displayed");
         logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
        }
    @Test (priority=3)
    private static void TC01C_IncorrectEmail() throws IOException, InterruptedException {
         InitializeDriver(null, "https://www.xero.com/us");
         Thread.sleep(1000);
         logger = reports.startTest("TC01C_IncorrectEmail");
         //Login Btn
         WebElement loginBtn = driver.findElement(By.xpath("//a[@class='btn btn-tertiary-alt global-ceiling-bar-btn']"));
         clickElement(loginBtn,"Login Button");
         logger.log(LogStatus.INFO, "Xero Login Page is Loaded");
         Thread.sleep(2000);
         //Reading the file to get login
         String[][] data = ReadWriteTextFile.readTextFile(filePath);
         //Send value to email
         WebElement email_txt = driver.findElement(By.id("email"));
         enterText(email_txt, "pallavi@gmail.com", "Email");
         //Send value to Password textbox
         WebElement passwd_txt = driver.findElement(By.id("password"));
         enterText(passwd_txt, data[1][1], "Password");
         //Click Login Button
         WebElement login_btn = driver.findElement(By.id("submitButton"));
         clickElement(login_btn, "Login Button");
         Thread.sleep(2000L);
         logger.log(LogStatus.PASS, "Message - Your email or password is incorrect is displayed ");
         logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
}
    @Test (priority=4)
    private static void TC01D_NavigateToXERO() throws IOException, InterruptedException {
         InitializeDriver(null, "https://login.xero.com/");
         Thread.sleep(1000);
         String[][] data = ReadWriteTextFile.readTextFile(filePath);
         logger = reports.startTest("TC01D_NavigateToXERO");
         //Click Forgot Password
         WebElement forgotPasswd = driver.findElement(By.xpath("//a[@class='forgot-password-advert']"));
         clickElement(forgotPasswd, "Forgot Password Link");
         Thread.sleep(2000L);
         logger.log(LogStatus.INFO, "Reset Password Page has been Loaded");
         logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
         //Entering Email ID
         WebElement resetEmail = driver.findElement(By.id("UserName"));
         enterText(resetEmail,data[0][1],"Email sent");
         //Click on send Link
         WebElement sendLink = driver.findElement(By.xpath("//span[@class='text']"));
         clickElement(sendLink, "Send Link Button");
         Thread.sleep(2000L);
         logger.log(LogStatus.PASS, "User Logged on Successfully");
         logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
}
    @Test (priority=5)
    private static void TC02A_SignUpToXERO() throws IOException, InterruptedException {
         InitializeDriver("firefox", "https://www.xero.com/us/");
         logger = reports.startTest("TC02A_SignUpToXERO");
         //Click Free Trial Btn
         WebElement freeTrialBtn = driver.findElement(By.linkText("Free trial"));
         clickElement(freeTrialBtn, "Free Trial Button");
         Thread.sleep(1000);
         //First Name
         WebElement firstName = driver.findElement(By.name("FirstName"));
         enterText(firstName,"Pallavi", "First Name");
         //Last Name
         WebElement lastName = driver.findElement(By.xpath("//input[@name='LastName']"));
         enterText(lastName,"Manikandan", "Last Name");
         //Email
         WebElement email = driver.findElement(By.xpath("//input[@name='EmailAddress']"));
         enterText(email,"ManikandanSJ@gmail.com", "Email Address");
         //Phone Number
         WebElement phNumber = driver.findElement(By.xpath("//input[@name='PhoneNumber']"));
         enterText(phNumber,"53456756", "Email Address");
         //Country
         WebElement country = driver.findElement(By.xpath("//select[@name='LocationCode']"));
         Thread.sleep(2000);
         dropDownByVisibleText(country,"Angola","LocationCode - US");
        // Thread.sleep(5000);
        /* //I am not robot Check box - Location Code
         WebElement iframeRobot = driver.findElement(By.xpath("//iframe[@name='a-6ruxk58bozfp']"));
         driver.switchTo().frame(iframeRobot);
         Thread.sleep(1000);
         WebElement checkBoxRobot = driver.findElement(By.xpath("//div[@class='g-recaptcha form-group invalid']"));
         clickElement(checkBoxRobot,"I am not Robot CheckBox");
         driver.switchTo().defaultContent(); */
        Thread.sleep(50000);
        /*  Selecting the captcha images manually can't be automated */
        //Terms Accepted
        WebElement termAccepted = driver.findElement(By.xpath("//input[@name='TermsAccepted']"));
        clickElement(termAccepted,"Accepting Terms");
        Thread.sleep(5000);
        logger.log(LogStatus.INFO, "Values has been entered");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
        //Get Started Button
        WebElement getStartedBtn = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        clickElement(getStartedBtn,"Get Started Button");
        Thread.sleep(2000);
        logger.log(LogStatus.FAIL, "Inbox Page is not displayed");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
}
    @Test (priority=6)
    private static void TC02B_SignUpToXERO() throws IOException, InterruptedException {
        InitializeDriver(null, "https://www.xero.com/us/");
        logger = reports.startTest("TC02B_SignUpToXERO");
        //Click Forgot Password
        WebElement freeTrialBtn = driver.findElement(By.linkText("Free trial"));
        clickElement(freeTrialBtn, "Free Trial Button");
        Thread.sleep(1000);
        //Get Started Button  TC -  Step 1 check for mandatory fields
        WebElement getStartedBtn = driver.findElement(By.xpath("//span[@class='g-recaptcha-submit']"));
        clickElement(getStartedBtn,"Get Started Button");
        Thread.sleep(2000);
        logger.log(LogStatus.INFO, "Error messages are displayed to fill the fields");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
        Thread.sleep(5000);
        //First Name  TC -  Step 2 Check for valid email
        WebElement firstName = driver.findElement(By.name("FirstName"));
        enterText(firstName,"Pallavi", "First Name");
        //Last Name
        WebElement lastName = driver.findElement(By.xpath("//input[@name='LastName']"));
        enterText(lastName,"Manikandan", "Last Name");
        //Email
        WebElement email = driver.findElement(By.xpath("//input[@name='EmailAddress']"));
        enterText(email,"ManikandanSJgmail.com", "Email Address");
        Thread.sleep(3000);
        logger.log(LogStatus.INFO, "Email address is Invalid shown");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
        //First Name  TC - Step 3 Check for terms accepted shows error
        // WebElement firstName = driver.findElement(By.name("FirstName"));
        firstName.clear();
        enterText(firstName,"Pallavi", "First Name");
        //Last Name
        // WebElement lastName = driver.findElement(By.xpath("//input[@name='LastName']"));
        lastName.clear();
        enterText(lastName,"Manikandan", "Last Name");
        //Email
        //  WebElement email1 = driver.findElement(By.xpath("//input[@name='EmailAddress']"));
        email.clear();
        enterText(email,"ManikandanSJ@gmail.com", "Email Address");
        //Phone Number
        WebElement phNumber = driver.findElement(By.xpath("//input[@name='PhoneNumber']"));
        enterText(phNumber,"53456756", "Email Address");
        //Get Started Button
        WebElement getStartedBtn1 = driver.findElement(By.xpath("//span[@class='g-recaptcha-submit']"));
        clickElement(getStartedBtn1,"Get Started Button");
        Thread.sleep(5000);
        logger.log(LogStatus.PASS, "Error messages are displayed as expected");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));

    }
    @Test (priority=7)
    private static void TC02C_SignUpToXERO() throws IOException, InterruptedException {
        InitializeDriver(null, "https://www.xero.com/us/");
        logger = reports.startTest("TC02C_SignUpToXERO");
        //Click Forgot Password
        WebElement freeTrialBtn = driver.findElement(By.linkText("Free trial"));
        clickElement(freeTrialBtn, "Free Trial Button");
        Thread.sleep(1000);
        //Terms page
        WebElement termsPage = driver.findElement(By.xpath("//a[contains(text(),'terms')]"));
        clickElement(termsPage,"Terms Page");
        Thread.sleep(2000);
        logger.log(LogStatus.INFO, "Terms page is displayed");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
        Thread.sleep(3000);
        closeNewWindow("Terms Page");
        Thread.sleep(2000);

        //Privacy Policy page
        WebElement privacyPolicyPage = driver.findElement(By.xpath("//a[contains(text(),'privacy')]"));
        clickElement(privacyPolicyPage,"Privacy Policy Page");
        Thread.sleep(3000);
        logger.log(LogStatus.INFO, "Privacy Policy is displayed");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
        Thread.sleep(2000);
        closeNewWindow("Terms Page");
        Thread.sleep(2000);

    }
    @Test (priority=8)
    private static void TC02D_SignUpToXERO() throws IOException, InterruptedException {
        InitializeDriver(null, "https://www.xero.com/us/");
        logger = reports.startTest("TC02D_SignUpToXERO");
        //Click Forgot Password
        WebElement freeTrialBtn = driver.findElement(By.linkText("Free trial"));
        clickElement(freeTrialBtn, "Free Trial Button");
        Thread.sleep(1000);
        //Offer Details page
        WebElement termsPage = driver.findElement(By.linkText("offer details"));
        clickElement(termsPage,"Offer Details Page");
        Thread.sleep(5000);
        logger.log(LogStatus.PASS, "Offer Details page is displayed");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));

    }
    @Test (priority=9)
    private static void TC02E_SignUpToXERO() throws IOException, InterruptedException {
        InitializeDriver(null, "https://www.xero.com/us/");
        logger = reports.startTest("TC02E_SignUpToXERO");
        //Click Forgot Password
        WebElement freeTrialBtn = driver.findElement(By.linkText("Free trial"));
        clickElement(freeTrialBtn, "Free Trial Button");
        Thread.sleep(1000);
        //accountant or bookkeeper page
        WebElement accBookkeeperPage = driver.findElement(By.linkText("accountant or bookkeeper"));
        clickElement(accBookkeeperPage,"accountant or bookkeeper Page");
        Thread.sleep(5000);
        logger.log(LogStatus.PASS, "accountant or bookkeeper page is displayed");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));

    }
    @Test (priority=10)
    private static void TC03A_TabsPage() throws IOException, InterruptedException {
        InitializeDriver(null, "https://login.xero.com/");
        logger = reports.startTest("TC03A_TabsPage");
        LoginWebpage(filePath);
        logger.log(LogStatus.INFO, "User Successfully Logged In");
        //Dashboard Tab
        WebElement dashboard = driver.findElement(By.linkText("Dashboard"));
        clickElement(dashboard,"Dashboard Tab");
        Thread.sleep(4000);
        logger.log(LogStatus.INFO, "Dashboard Tab Page has been displayed");
        //Business Tab
        WebElement busi = driver.findElement(By.xpath("//button[contains(text(),'Business')]"));
        clickElement(busi,"Business Tab");
        Thread.sleep(4000);
        logger.log(LogStatus.INFO, "Business Tab Page has been displayed");
        //Accounting Tab
        WebElement accTab = driver.findElement(By.xpath("//button[contains(text(),'Accounting')]"));
        clickElement(accTab,"Accounting Tab");
        Thread.sleep(4000);
        logger.log(LogStatus.INFO, "Accounting Tab Page has been displayed");
        //Contacts Tab
        WebElement contactTab = driver.findElement(By.xpath("//button[contains(text(),'Contacts')]"));
        clickElement(contactTab,"Contacts Tab");
        Thread.sleep(4000);
        logger.log(LogStatus.INFO, "Contacts Tab Page has been displayed");
        //Create New SVG
        WebElement createNEW = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/ol[2]/li[1]/button[1]/div[1]/*"));
        clickElement(createNEW,"Create New Icon");
        Thread.sleep(2000);
        logger.log(LogStatus.INFO, "Create New Drop down page displayed");
        // Search
        WebElement search = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/ol[2]/li[2]/button[1]/div[1]"));
        clickElement(search,"Search Icon");
        Thread.sleep(2000);
        logger.log(LogStatus.INFO, "Search Drop down page displayed");
        //Notification
        WebElement notification = driver.findElement(By.xpath("//li[3]//button[1]//div[1]"));
        clickElement(notification,"Notification Icon");
        Thread.sleep(2000);
        logger.log(LogStatus.INFO, "Notification Drop down page displayed");
        //Help Icon
        WebElement help = driver.findElement(By.xpath("//li[4]//button[1]//div[1]"));
        clickElement(help,"Help Icon");
        Thread.sleep(2000);
        logger.log(LogStatus.INFO, "Help Drop down page displayed");
        //Help Menu
        WebElement helpMenu = driver.findElement(By.xpath("//input[@id='menu_help']"));
        enterText(helpMenu,"Daily Report","Help Menu");
        logger.log(LogStatus.PASS, "Help search text has been displayed");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));

    }
    @Test (priority=11)
    private static void TC04A_LogoutFunction() throws IOException, InterruptedException {
        InitializeDriver(null, "https://login.xero.com/");
        logger = reports.startTest("TC04A_LogoutFunction");
        String[][] data = ReadWriteTextFile.readTextFile(filePath);
        //email Login
        WebElement email = driver.findElement(By.id("email"));
        enterText(email,data[0][1], "Email");
        //Password
        WebElement password = driver.findElement(By.id("password"));
        enterText(password,data[1][1],"Password");
        //Login Btn
        WebElement loginBtn = driver.findElement(By.id("submitButton"));
        clickElement(loginBtn,"LoginBtn");
        Thread.sleep(4000);
        logger.log(LogStatus.INFO, "User Successfully Logged In");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
        //Profile Tab
        WebElement profile = driver.findElement(By.xpath("//abbr[@class='xrh-avatar xrh-avatar-color-3']"));
        clickElement(profile,"Profile");
        Thread.sleep(1000);
        //Click Logout
        WebElement logOut = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/ol[2]/li[5]/div[1]/div[2]/div[1]/ol[1]/li[5]/a[1]"));
        clickElement(logOut,"LogOut");
        Thread.sleep(4000);
        logger.log(LogStatus.FAIL, "Log Out function is working, but could see the remember me checkbox");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));

    }
    @Test (priority=12)
    private static void TC06A_UploadProfileImage() throws IOException, InterruptedException {
        InitializeDriver(null, "https://login.xero.com/");
        logger = reports.startTest("TC06A_UploadProfileImage");
        LoginWebpage(filePath);
        logger.log(LogStatus.INFO, "User Successfully Logged In");
        Thread.sleep(2000);
        //Profile Tab
        WebElement profile = driver.findElement(By.xpath("//abbr[@class='xrh-avatar xrh-avatar-color-3']"));
        clickElement(profile,"Profile");
        Thread.sleep(1000);
        //Click editProfile
        WebElement editProfile = driver.findElement(By.xpath("//li[@class='xrh-addon xrh-addon-lastvisible']//li[1]//a[1]"));
        clickElement(editProfile,"Edit Profile");
        Thread.sleep(2000);
        //UploadImageBtn
        WebElement uploadImageBtn = driver.findElement(By.xpath("//div[@id='button-1041']"));
        clickElement(uploadImageBtn,"Upload Image Button");
        Thread.sleep(5000);
        //Browse Button
        WebElement browseBtnVal = driver.findElement(By.id("filefield-1174-button-fileInputEl"));
        browseBtnVal.sendKeys(imgPath);
        Thread.sleep(2000);
        //UploadImageBtn1
        WebElement uploadImageBtn1 = driver.findElement(By.xpath("//span[@id='button-1178-btnInnerEl']"));
        clickElement(uploadImageBtn1,"Upload Image Button 1");
        Thread.sleep(2000);
        logger.log(LogStatus.PASS, "Image is uploaded and displayed on the profile page");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));

    }
    @Test
    private static void TC08A_AddOrganizationTrail() throws IOException, InterruptedException {
        InitializeDriver(null, "https://login.xero.com/");
        logger = reports.startTest("TC08A_AddOrganizationTrail");
        LoginWebpage(filePath);
        logger.log(LogStatus.INFO, "User Successfully Logged In");
        Thread.sleep(1000);
        //Organization Tab
        WebElement organi = driver.findElement(By.xpath("//span[@class='xrh-appbutton--text']"));
        clickElement(organi,"Organization");
        Thread.sleep(1000);
        //Add New Organization
        WebElement  newOrgani= driver.findElement(By.linkText("Add a new organization"));
        clickElement(newOrgani,"Add New Organization");
        Thread.sleep(3000);
        //Org Name
        // WebElement orgName = driver.findElement(By.xpath("//input[@id='11697ad1-e07a-43c7-842c-d6a313df370e-control']"));
        WebElement orgName = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/input[1]"));
        enterText(orgName,"Self","Organization Name");
        Thread.sleep(2000);
        //PayTaxes
        //PayTaxes is a dynamic listbox so did it in 2 steps
        WebElement payTaxDropBtn = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]/div[1]"));
        Thread.sleep(1000);
        clickElement(payTaxDropBtn,"PayTax Drop Down");
        Thread.sleep(2000);
        WebElement payTaxCountryList = driver.findElement(By.xpath("//li[@id='CNTRY/US']//span[@class='xui-pickitem--text']"));
        payTaxCountryList.click();
        Thread.sleep(2000);
        //TimeZone
        WebElement timeZonedropBtn = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]/div[1]"));
        Thread.sleep(1000);
        clickElement(timeZonedropBtn,"TimeZone Drop Down");
        Thread.sleep(2000);
        WebElement timeZonelist = driver.findElement(By.xpath("//span[contains(text(),'(UTC-08:00) Pacific Time (US & Canada)')]"));
        clickElement(timeZonelist,"TimeZone List");
        Thread.sleep(2000);
        //Organizaton Do
        WebElement orgDo = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[4]/div[1]/div[1]/div[1]/div[2]/div[1]/input[1]"));
        Thread.sleep(4000);
       // enterText(orgDo,"Marine Engineering","Organization Do");
        orgDo.sendKeys("Marine Engineering");
        Thread.sleep(7000);
        //start Trial
        WebElement startTrail = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[8]/div[1]/button[1]"));
        clickElement(startTrail,"Start Trail Button");
        Thread.sleep(5000);
        logger.log(LogStatus.PASS, "Organization start up page has been done successfully");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));

    }
    @Test
    private static void TC08B_AddOrganizationPaid() throws IOException, InterruptedException {
        InitializeDriver(null, "https://login.xero.com/");
        logger = reports.startTest("TC08B_AddOrganizationPaid");
        //Paid Login details
        //Send value to email
        WebElement email_txt = driver.findElement(By.id("email"));
        enterText(email_txt, "gopala.anumanchipalli@gmail.com", "Email");
        //Send value to Password textbox
        WebElement passwd_txt = driver.findElement(By.id("password"));
        enterText(passwd_txt,"password12", "Password");
        //Click Login Button
        WebElement login_btn = driver.findElement(By.id("submitButton"));
        clickElement(login_btn, "Login Button");
        Thread.sleep(2000L);
        logger.log(LogStatus.INFO, "User Gopala Successfully Logged In");
        Thread.sleep(4000);
        //Organization Tab
        WebElement organi = driver.findElement(By.xpath("//div[@class='xrh-appbutton--body']"));
        clickElement(organi,"Organization");
        Thread.sleep(1000);
        //Add New Organization
        WebElement  newOrgani= driver.findElement(By.linkText("Add a new organization"));
        clickElement(newOrgani,"Add New Organization");
        Thread.sleep(5000);
        //Organization Name
        WebElement orgName = driver.findElement(By.xpath("//form[1]/div[1]/div[1]/div[1]/input[1]"));
        enterText(orgName,"Self","Organization Name");
        Thread.sleep(1000);
        //PayTaxes is a dynamic listbox so did it in 2 steps
        WebElement payTaxDropBtn = driver.findElement(By.xpath("//form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]/div[1]"));
        Thread.sleep(1000);
        clickElement(payTaxDropBtn,"PayTax Drop Down");
        Thread.sleep(2000);
        WebElement payTax = driver.findElement(By.xpath("//li[@id='CNTRY/US']//span[@class='xui-pickitem--text']"));
        payTax.click();
        Thread.sleep(2000);
        //Time Zone
        WebElement timeZonedropBtn = driver.findElement(By.xpath("//form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]/div[1]"));
        Thread.sleep(1000);
        clickElement(timeZonedropBtn,"TimeZone Drop Down");
        Thread.sleep(2000);
        WebElement timeZonelist = driver.findElement(By.xpath("//span[contains(text(),'(UTC-08:00) Pacific Time (US & Canada)')]"));
        clickElement(timeZonelist,"TimeZone List");
        Thread.sleep(3000);
        //Organizaton Do textbox
        driver.findElement(By.xpath("//form/div[4]/div/div/div/div[2]/div/input")).sendKeys("engineering");
        Thread.sleep(3000);
        //if you change the li[num] value, you can get some dii. option
        driver.findElement(By.xpath("//div[@class='xui-dropdown--scrollable-content']/child::ul/child::li[3]")).click();
        Thread.sleep(2000);
        //Buy now button
        driver.findElement(By.xpath("//button[@class='xui-button xui-actions--secondary xui-button-standard xui-button-medium']")).click();
        logger.log(LogStatus.FAIL, "After Buynow button click, automatically logging out not showing the 'purchase plan page', but once we logged-in manually, able to see the organization name change in the start up page");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));

    }
    @Test
    private static void TC08C_AddOrganizationWithStarterPlan() throws IOException, InterruptedException {
        InitializeDriver(null, "https://login.xero.com/");
        logger = reports.startTest("TC08F_AddOrganizationWithQuickBookUser");
        LoginWebpage(filePath);
        logger.log(LogStatus.INFO, "User Successfully Logged In");
        Thread.sleep(4000);
        //Organization Tab
        WebElement organi = driver.findElement(By.xpath("//div[@class='xrh-appbutton--body']"));
        clickElement(organi,"Organization");
        Thread.sleep(1000);
        //My Xero List item
        WebElement  myXero= driver.findElement(By.linkText("My Xero"));
        clickElement(myXero,"myXero");
        Thread.sleep(10000);
        switchNewWindow("Add Organization");
        Thread.sleep(5000);
        //Add an Organization
        driver.findElement(By.cssSelector("#ext-gen1043")).click();
        Thread.sleep(2000);
        //Organization Name
        WebElement orgName = driver.findElement(By.xpath("//form[1]/div[1]/div[1]/div[1]/input[1]"));
        enterText(orgName,"Self","Organization Name");
        Thread.sleep(1000);
        //PayTaxes is a dynamic listbox so did it in 2 steps
        WebElement payTaxDropBtn = driver.findElement(By.xpath("//form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]/div[1]"));
        Thread.sleep(1000);
        clickElement(payTaxDropBtn,"PayTax Drop Down");
        Thread.sleep(2000);
        WebElement payTax = driver.findElement(By.xpath("//li[@id='CNTRY/US']//span[@class='xui-pickitem--text']"));
        payTax.click();
        Thread.sleep(2000);
        //Time Zone
        WebElement timeZonedropBtn = driver.findElement(By.xpath("//form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]/div[1]"));
        Thread.sleep(1000);
        clickElement(timeZonedropBtn,"TimeZone Drop Down");
        Thread.sleep(2000);
        WebElement timeZonelist = driver.findElement(By.xpath("//span[contains(text(),'(UTC-08:00) Pacific Time (US & Canada)')]"));
        clickElement(timeZonelist,"TimeZone List");
        Thread.sleep(3000);
        //Organizaton Do textbox
        driver.findElement(By.xpath("//form/div[4]/div/div/div/div[2]/div/input")).sendKeys("engineering");
        Thread.sleep(3000);
        //if you change the li[num] value, you can get some diff. option
        driver.findElement(By.xpath("//div[@class='xui-dropdown--scrollable-content']/child::ul/child::li[1]")).click();
        Thread.sleep(2000);
        //Start Trial button
        driver.findElement(By.xpath("//button[@class='xui-button xui-actions--secondary xui-button-main xui-button-medium']")).click();
        Thread.sleep(2000);




    }
    @Test
    private static void TC08F_AddOrganizationWithQuickBookUser() throws IOException, InterruptedException {
        InitializeDriver(null, "https://login.xero.com/");
        logger = reports.startTest("TC08F_AddOrganizationWithQuickBookUser");
        //Paid Login details
        //Send value to email
        WebElement email_txt = driver.findElement(By.id("email"));
        enterText(email_txt, "gopala.anumanchipalli@gmail.com", "Email");
        //Send value to Password textbox
        WebElement passwd_txt = driver.findElement(By.id("password"));
        enterText(passwd_txt,"password12", "Password");
        //Click Login Button
        WebElement login_btn = driver.findElement(By.id("submitButton"));
        clickElement(login_btn, "Login Button");
        Thread.sleep(2000L);
        logger.log(LogStatus.INFO, "User Gopala Successfully Logged In");
        Thread.sleep(4000);
        //Organization Tab
        WebElement organi = driver.findElement(By.xpath("//div[@class='xrh-appbutton--body']"));
        clickElement(organi,"Organization");
        Thread.sleep(5000);
        //My Xero List item
        WebElement  myXero= driver.findElement(By.linkText("My Xero"));
        clickElement(myXero,"myXero");
        Thread.sleep(7000);
        //Add an Organization
        WebElement addOrg= driver.findElement(By.xpath("/html[1]/body[1]/div[4]/form[1]/div[1]/section[2]/div[1]"));
        clickElement(addOrg,"Add an Organization");
        Thread.sleep(7000);
        //Organization Name
        WebElement orgName = driver.findElement(By.xpath("//form[1]/div[1]/div[1]/div[1]/input[1]"));
        enterText(orgName,"Self","Organization Name");
        Thread.sleep(1000);
        //PayTaxes is a dynamic listbox so did it in 2 steps
        WebElement payTaxDropBtn = driver.findElement(By.xpath("//form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]/div[1]"));
        Thread.sleep(1000);
        clickElement(payTaxDropBtn,"PayTax Drop Down");
        Thread.sleep(2000);
        WebElement payTax = driver.findElement(By.xpath("//li[@id='CNTRY/US']//span[@class='xui-pickitem--text']"));
        payTax.click();
        Thread.sleep(2000);
        //Time Zone
        WebElement timeZonedropBtn = driver.findElement(By.xpath("//form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]/div[1]"));
        Thread.sleep(1000);
        clickElement(timeZonedropBtn,"TimeZone Drop Down");
        Thread.sleep(2000);
        WebElement timeZonelist = driver.findElement(By.xpath("//span[contains(text(),'(UTC-08:00) Pacific Time (US & Canada)')]"));
        clickElement(timeZonelist,"TimeZone List");
        Thread.sleep(3000);
        //Organizaton Do textbox
        driver.findElement(By.xpath("//form/div[4]/div/div/div/div[2]/div/input")).sendKeys("engineering");
        Thread.sleep(3000);
        //if you change the li[num] value, you can get some dii. option
        driver.findElement(By.xpath("//div[@class='xui-dropdown--scrollable-content']/child::ul/child::li[5]")).click();
        Thread.sleep(2000);
        //QuickBook User
        driver.findElement(By.xpath("//div[@class='xui-text-label']")).click();
        Thread.sleep(1000);
        //Click the 'yes' check box
        driver.findElement(By.xpath("//div[@class='xui-styledcheckboxradio--checkbox xui-styledcheckboxradio--checkbox-small']")).click();
        Thread.sleep(2000);
        logger.log(LogStatus.INFO, "Able to see the 'Start Trial' button disabled and able to click on continue button");
        logger.log(LogStatus.INFO,logger.addScreenCapture(takeScreenshot()));
        //Continue button
        driver.findElement(By.xpath("//button[@class='xui-button xui-actions--secondary xui-button-main xui-button-medium']")).click();
        Thread.sleep(2000);




    }


 //Reusable Methods Starts From Here

    //InitializeReport
    public static void InitializeReport () {
        reports = new ExtentReports(reportFolder + new SimpleDateFormat("'XeroReports_'YYYYMMddHHmm'.html'").format(new Date()));
    }
    //InitializeDriver
    public static void InitializeDriver (String driver_type, String url) throws InterruptedException {
        if (driver_type == "firefox")  // For firefox browser
        {
            System.setProperty("webdriver.gecko.driver", "C:\\Pallavi\\Selenium-Utility\\geckodriver-v0.26.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();            }
        else if (driver_type == "IE")
        {
            System.setProperty("webdriver.ie.driver", "C:\\Pallavi\\Selenium-Utility\\IEDriverServer_x64_3.4.0\\IEDriverServer.exe");
            // DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
            // caps.setCapability("ignoreZoomSetting", true);
            driver = new InternetExplorerDriver();

        }
        else // For Chrome browser
        {
            System.setProperty("webdriver.chrome.driver", "C:\\Pallavi\\Selenium-Utility\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();            }

        // Opens Xero login page
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }
    //Login
    public static void LoginWebpage (String filePath) throws IOException, InterruptedException {
        String[][] data = ReadWriteTextFile.readTextFile(filePath);
        //Send value to email
        WebElement email_txt = driver.findElement(By.id("email"));
        enterText(email_txt, data[0][1], "Email");
        //Send value to Password textbox
        WebElement passwd_txt = driver.findElement(By.id("password"));
        enterText(passwd_txt, data[1][1], "Password");
        //Click Login Button
        WebElement login_btn = driver.findElement(By.id("submitButton"));
        clickElement(login_btn, "Login Button");
        Thread.sleep(2000L);

    }
    //send values to the Text elements
    public static void enterText(WebElement ele, String txtValue, String objectName) {
        if (ele.isDisplayed()) {
            ele.sendKeys(txtValue);
            System.out.println(txtValue + " has been successfully entered into " + objectName);
        } else {
            System.out.println(objectName + " is not displayed on the web page.");
        }
    }
    //Click event
    public static void clickElement(WebElement ele, String ObjectName) {
        if (ele.isDisplayed()) {
            ele.click();
            System.out.println(ObjectName + " has been successfully clicked");
        } else {
            System.out.println(ObjectName + " is not displayed in the web page.");
        }
    }
    // Take ScreeShots
    public static String takeScreenshot() throws IOException {
        TakesScreenshot srcShot = ((TakesScreenshot) driver);
        File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
        String imagePath = reportFolder + "ScreenShots\\" + new SimpleDateFormat("'Image_'YYYYMMddHHmmss'.png'").format(new Date());
        File destFile = new File(imagePath);
        FileUtils.copyFile(srcFile,destFile);
        return imagePath;
    }
    //DropDown
    public static void dropDownByVisibleText(WebElement ele, String visibleText, String ObjectName) {
        Select sel = new Select(ele);
        sel.selectByVisibleText(visibleText);
        System.out.println(ObjectName + visibleText+ " DropDown Selection by Visible Text has been done successfully");
    }
   // List Item
    public static void listBoxByIDVisibleText(WebElement ele, String visibleText, String listItemID, String ObjectName) {
        System.out.println(visibleText + "," +listItemID );
        List<WebElement> options = ele.findElements(By.id(listItemID));  // By.id , use xpath, tagname
        for (WebElement option : options) {
            if(visibleText.equals(option.getText())) {
                option.click();
                System.out.println(ObjectName + visibleText + " ListItem by Visible Text and ID has been done successfully");
            }
        }

    }
    //close New Window
    public static void closeNewWindow(String ObjectName) {
        String parentWindow = driver.getWindowHandle();
        //Switch to new window opened we dont know how many windows are opened so handling it thru for each loop
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        // Perform the actions on new window
        driver.close(); //this will close new opened window
        //switch back to main window using this code
        driver.switchTo().window(parentWindow);
    }
        //Switch to new window
    public static void switchNewWindow(String ObjectName) {
    String parentWindow = driver.getWindowHandle();
    //Switch to new window opened we dont know how many windows are opened so handling it thru for each loop
    for (String winHandle : driver.getWindowHandles()) {
        driver.switchTo().window(winHandle);
    }
    System.out.println("Switched to the new Page " +ObjectName);
}


}
