package finalTest.PageObject;

import finalTest.Book;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class BookPage {
    Book[] arrayBooks = new Book[numberOfBooks()];

    @FindBy(how = How.CLASS_NAME, using = "form-control")
    private WebElement searchBox;

//    @FindBy(how = How.XPATH, using = "//div[@class=\"rt-tbody\"]/div/div/div/img")
//    private List<WebElement> currentBooks;

    @FindBy(how = How.CLASS_NAME, using = "rt-noData")
    private WebElement message;

    @FindBy(how = How.CLASS_NAME, using = "rt-tbody")
    public WebElement table;

    @FindBy(how = How.XPATH, using = "//div[@class=\"rt-td\"][2]")
    private WebElement title;

    @FindBy(how = How.XPATH, using = "//div[@class=\"rt-td\"][3]")
    private WebElement author;

    @FindBy(how = How.XPATH, using = "//div[@class=\"rt-td\"][4]")
    private WebElement publisher;

    public void searchBook(String bookName) {
        searchBox.sendKeys(bookName);
    }


    public int numberOfBooks() {
        return table.findElements(By.xpath("//div[@class=\"rt-tr-group\"]/div/div/img")).size();
    }

    public WebElement getMessage() {
        return message;
    }

    public void insertBook() {

        for (int i = 0; i < numberOfBooks(); i++) {
            arrayBooks[i] = new Book(title.getText(), author.getText(), publisher.getText());
        }

    }

    public void printBooks(){
        for (int i = 0; i < numberOfBooks(); i++) {
            System.out.println(arrayBooks[i]);
        }
    }


}

