package finalTest.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
    @FindBy(how = How.ID, using = "userName")
    private WebElement userName;

    @FindBy(how = How.ID, using = "password")
    private WebElement password;

    @FindBy(how = How.ID, using = "login")
    private WebElement submit;
    @FindBy(how = How.ID, using = "submit")
    private WebElement logout;


    public void loginAction(String username, String pass) {
        userName.sendKeys(username);
        password.sendKeys(pass);
        submit.click();
    }
    public void logoutAction() {
        logout.click();
    }


}
