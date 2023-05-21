package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Main {

    WebDriver driver = null;
    String expectedSuggestionValue = "United States (USA)";
    String alertText = "Usman";
    String expectedAlertMessage = "Hello " + alertText + ", share this practice page and share your knowledge";


    @Test
    public void testGoogle() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        driver.findElement(By.id("APjFqb")).sendKeys("Java course for beginners");
        driver.findElement(By.id("jZ2SBf")).click();
        driver.close();
    }

    @Test
    public void testAlert() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.cssSelector("#name")).sendKeys(alertText);
        driver.findElement(By.id(("alertbtn"))).click();
        Thread.sleep(2000);
        String actualAlertMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertMessage, expectedAlertMessage);
        driver.switchTo().alert().accept();
        driver.close();
    }

    @Test
    public void testRadioButton() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.xpath("//input[@value='radio1']")).click();
        driver.findElement(By.xpath("//input[@value='radio2']")).click();
        driver.findElement(By.xpath("//input[@value='radio3']")).click();
        driver.close();
    }

    @Test
    public void testSuggestionClass() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.cssSelector(".inputs.ui-autocomplete-input")).sendKeys("United");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@class='ui-menu-item']/div[text()='United States (USA)']")).click();
        String actualSuggestionText = driver.findElement(By.cssSelector("#autocomplete")).getAttribute("value");
        Assert.assertEquals(actualSuggestionText, expectedSuggestionValue);
        driver.close();
    }

    @Test
    public void handMouseHover() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        Actions actions = new Actions(driver);
        ((JavascriptExecutor) driver).executeScript("scroll(0,900)");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.id("mousehover"));
        Thread.sleep(2000);
        actions.moveToElement(element).build().perform();
        WebElement element1 = driver.findElement(By.linkText("Top"));
        element1.click();
        ((JavascriptExecutor) driver).executeScript("scroll(0,900)");
        actions.moveToElement(element).build().perform();
        WebElement element2 = driver.findElement(By.linkText("Reload"));
        element2.click();
        driver.close();
    }


    @Test
    public void testIframe() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        ((JavascriptExecutor) driver).executeScript("scroll(0,1700)");
        Thread.sleep(2300);
        driver.switchTo().frame("courses-iframe");
        driver.findElement(By.cssSelector(".btn.btn-theme.btn-sm.btn-min-block")).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        ((JavascriptExecutor) driver).executeScript("scroll(0,-700)");
        driver.findElement(By.id("alertbtn")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        driver.close();
    }




}