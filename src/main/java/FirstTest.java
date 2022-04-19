import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstTest {

   @Test
   public void checkTopUpMobile(){

       System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
       WebDriver driver = new ChromeDriver();
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

       //page elements variable initialization
       By phoneCode = By.xpath("//button[@data-qa-node='phone-code']");
       By phoneCodeInp = By.xpath("//input[@placeholder='Пошук']");
       By phoneNumber = By.xpath("//input[@data-qa-node='phone-number']");
       By transferAmount = By.xpath("//input[@data-qa-node='amount']");
       By cardNum = By.xpath("//input[@data-qa-node='numberdebitSource']");
       By cardExpDat = By.xpath("//input[@data-qa-node='expiredebitSource']");
       By cardCvvCode = By.xpath("//input[@data-qa-node='cvvdebitSource']");
       By firstName = By.xpath("//input[@data-qa-node='firstNamedebitSource']");
       By lastName = By.xpath("//input[@data-qa-node='lastNamedebitSource']");
       By submit = By.xpath("//button[@data-qa-node='submit']");

       //Filling out the form
       driver.get("https://next.privat24.ua/mobile");
       driver.findElement(phoneCode).click();
       driver.findElement(phoneCodeInp).sendKeys("+380");
       driver.findElement(phoneNumber).click();
       driver.findElement(phoneNumber).sendKeys("934861370");
       driver.findElement(transferAmount).sendKeys("200");
       driver.findElement(cardNum).sendKeys("4552331448138217");
       driver.findElement(cardExpDat).sendKeys("1022");
       driver.findElement(cardCvvCode).sendKeys("159");
       driver.findElement(firstName).sendKeys("IVAN");
       driver.findElement(lastName).sendKeys("SUSANIN");
       driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
       driver.findElement(submit).click();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

       //Check
       String expectPhoneNum = "+380934861370";
       String expectCardNum = "4552 **** **** 8217";
       String expectTransferSum = "200 UAH";
       String expectCommissionSum = "4 UAH";


       By actualPhoneNumber = By.xpath("//div[@data-qa-node='details']");
       By actualCardNumber = By.xpath("//td[@data-qa-node='card']");
       By actualSum = By.xpath("//div[@data-qa-node='amount']");
       By actualCommission = By.xpath("//span    [@data-qa-node='commission']");


       Assert.assertEquals("Поповнення телефону. На номер " + expectPhoneNum, driver.findElement(actualPhoneNumber).getText());
       Assert.assertEquals(expectCardNum, driver.findElement(actualCardNumber).getText());
       Assert.assertEquals(expectTransferSum, driver.findElement(actualSum).getText());
       Assert.assertEquals(expectCommissionSum, driver.findElement(actualCommission).getText() + " UAH");

       driver.close();
   }
}
