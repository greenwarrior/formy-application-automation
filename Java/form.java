//Form.java

//	Basic Form

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class Form {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:/webdriver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://formy-project.herokuapp.com/form");

        //driver.findElement(By.id("")).sendKeys("");

        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Wick");
        driver.findElement(By.id("job-title")).sendKeys("QA Engineer");
        driver.findElement(By.id("radio-button-2")).click();
        driver.findElement(By.id("checkbox-2")).sendKeys();
        driver.findElement(By.cssSelector("option[value='1']")).click();
        driver.findElement(By.id("datepicker")).sendKeys("08/20/2019");
        driver.findElement(By.id("datepicker")).sendKeys(Keys.RETURN);
        driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary")).click();

        //Add explicit wait
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
        //Add assertion to confirm a successfull submission

        String alertText = alert.getText();

        assertEquals("The form was successfully submitted!", alertText);
        driver.quit();
    }
}

//Form01
// Methods:
// 	public static void submitForm(WebDriver driver)
//	public static void waitForAlertBanner(WebDriver driver)
//	public static String getAlertBannerText(WebDriver driver)

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class Form {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:/webdriver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://formy-project.herokuapp.com/form");

        submitForm(driver);
        waitForAlertBanner(driver);

        assertEquals("The form was successfully submitted!", getAlertBannerText(driver));
        driver.quit();
    }

    public static void submitForm(WebDriver driver)
    {
        //Submit the form
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Wick");
        driver.findElement(By.id("job-title")).sendKeys("QA Engineer");
        driver.findElement(By.id("radio-button-2")).click();
        driver.findElement(By.id("checkbox-2")).sendKeys();
        driver.findElement(By.cssSelector("option[value='1']")).click();
        driver.findElement(By.id("datepicker")).sendKeys("08/20/2019");
        driver.findElement(By.id("datepicker")).sendKeys(Keys.RETURN);
        driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary")).click();
    }

    public static void waitForAlertBanner(WebDriver driver)
    {
        //Add explicit wait
        WebDriverWait wait = new WebDriverWait(driver,10);
        //Remove the alert variable
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
    }

    public static String getAlertBannerText(WebDriver driver)
    {
        return driver.findElement(By.className("alert")).getText();
    }
}


// PAGE OBJECT PATTERN

//Form.java
// Two separate classes are created:
//	- FormPage() class
//	- ConfirmationPage() class
// Steps
// 	1. Create new directory under the main project e.g. pages
//  2. Under the pages directory create a new class called FormPage
//  3. Remove the submitForm(WebDriver) method from the test class and include it on the FormPage() class
//  4. In the test test class (Form() class), create an object instance of the class FormPage(class)
//  5. Call the submitForm(WebDriver) method  through the instance object
//  6. Create a new class called ConfirmationPage() class and include the following methods:
//		waitForAlertBanner(WebDriver) and getAlertBannerText(WebDriver)
//  7. Create an instance object of the ConfirmationPage() class
//  8. Call the methods 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

public class Form {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:/webdriver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://formy-project.herokuapp.com/form");

        //Instance of the FormPage()class
		FormPage formPage = new FormPage();
        formPage.submitForm(driver);

        //Instance of the ConfirmationPage class
		ConfirmationPage confirmationPage = new ConfirmationPage();
        confirmationPage.waitForAlertBanner(driver);

        assertEquals("The form was successfully submitted!",confirmationPage.getAlertBannerText(driver));
        driver.quit();
    }
}

//FormPage() class
//FormPage.java
// Includes the SubmitForm(WebDriver) method

public class FormPage {
    public static void submitForm(WebDriver driver)
    {
        //Submit the form
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Wick");
        driver.findElement(By.id("job-title")).sendKeys("QA Engineer");
        driver.findElement(By.id("radio-button-2")).click();
        driver.findElement(By.id("checkbox-2")).sendKeys();
        driver.findElement(By.cssSelector("option[value='1']")).click();
        driver.findElement(By.id("datepicker")).sendKeys("08/20/2019");
        driver.findElement(By.id("datepicker")).sendKeys(Keys.RETURN);
        driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary")).click();
    }
}

//ConfirmationPage() class
//Confirmation.java
//
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage {
    public static void waitForAlertBanner(WebDriver driver)
    {
        //Add explicit wait
        WebDriverWait wait = new WebDriverWait(driver,10);
        //Remove the alert variable
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
    }

    public static String getAlertBannerText(WebDriver driver)
    {
        return driver.findElement(By.className("alert")).getText();
    }
}

