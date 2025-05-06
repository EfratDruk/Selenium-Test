package finalTest.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class BookPage {

    @FindBy(how = How.CLASS_NAME, using ="form-control" )
    private WebElement searchBox;

    //cheack if it return list or just one
    @FindBy(how = How.CLASS_NAME, using ="pic" )
    private WebElement book;

    @FindBy(how = How.CLASS_NAME, using ="rt-noData" )
    private WebElement message;

    public void searchBook(String bookName) {
        searchBox.sendKeys(bookName);
    }
    public List<WebElement> currentBooks() {
        return book.findElements(By.tagName("pic"));
    }
    public int numberOfBooks() {
        return book.findElements(By.tagName("pic")).size();
    }
    public WebElement getMessage() {
        return message;
    }
}
