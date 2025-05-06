package finalTest;

import finalTest.PageObject.BookPage;
import finalTest.PageObject.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.w3c.dom.Document;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Testing {


    WebDriver driver;
    LoginPage loginPage;
    BookPage bookPage;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        bookPage = PageFactory.initElements(driver, BookPage.class);

    }

    @BeforeMethod
    public void loginAction() {
        try {
            loginPage.loginAction(getData("username"), getData("password"));
        } catch (Exception e) {
            System.out.println("Failed to lofin " + e);
        }
        driver.findElement(By.id("gotoStore")).click();
    }

    @Test
    public void test01() {
        List<WebElement> gitPocketBooks = driver.findElements(By.partialLinkText("git Pocket"));
        Assert.assertEquals(gitPocketBooks.size(), 1);
    }

    @Test
    public void test02() {
        bookPage.searchBook("VeriSoft");
        List<WebElement> books = bookPage.currentBooks();
        System.out.println(books.size() + "------------------------");
        Assert.assertEquals(books.size(), 0);
        Assert.assertTrue(bookPage.getMessage().isDisplayed());
    }

    @AfterClass
    public void endSession() {
        driver.quit();
    }

    @AfterMethod
    public void logout() {

    }

    public String getData(String tagName){
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


