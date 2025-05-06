package finalTest;

import finalTest.PageObject.BookPage;
import finalTest.PageObject.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.w3c.dom.Document;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;


public class Testing {


    WebDriver driver;
    LoginPage loginPage;
    BookPage bookPage;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(getData("URL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);

    }

    @BeforeMethod
    public void loginAction() {
        try {
            loginPage.loginAction(getData("username"), getData("password"));
        } catch (Exception e) {
            System.out.println("Failed to login " + e);
        }
        driver.findElement(By.id("gotoStore")).click();
        WebElement bookRow = new WebDriverWait(driver,10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"rt-tbody\"]")));
        bookPage = PageFactory.initElements(driver, BookPage.class);
    }

    @Test
    public void test01() {
        bookPage.searchBook("git Pocket");
        try {
            Assert.assertEquals(bookPage.numberOfBooks(), 1);
        } catch (AssertionError e) {
            System.out.println("failed " + e);
        }
    }

    @Test
    public void test02() {
        bookPage.searchBook("VeriSoft");
        try {
            Assert.assertEquals(bookPage.numberOfBooks(), 0);
            Assert.assertTrue(bookPage.getMessage().isDisplayed());
        } catch (AssertionError e) {
            System.out.println("failed " + e);
        }
    }

    @Test
    public void insertAndPrint() {
        bookPage.insertBook();
        bookPage.printBooks();
    }


    @AfterClass
    public void endSession() {
        driver.quit();
    }

    @AfterMethod
    public void logout() {
        loginPage.logoutAction();
    }

    public String getData(String tagName) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fxml = new File("./configuration.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fxml);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(tagName).item(0).getTextContent();
    }
}


