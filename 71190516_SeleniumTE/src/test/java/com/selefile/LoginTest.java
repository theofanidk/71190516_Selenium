package com.selefile;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class LoginTest {
    String email;
    String password;
    ChromeDriver chromeDriver;
    @Given("browser terbuka")
    public void browser_terbuka() {
        System.out.println("Browser dibuka");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chromedriver.exe");

        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }
    @Given("user berada di halaman login")
    public void user_berada_di_halaman_login() {
        System.out.println("user berada di halaman login");

        chromeDriver.navigate().to("https://demo.guru99.com/insurance/v1/index.php");
    }
    @When("^user memasukkan ([^\"]*) and ([^\"]*)$")
    public void user_memasukkan_username_and_password(String Email, String psswd) {
        System.out.println("User input email dan password untuk login");
        email = Email;
        password = psswd;
        chromeDriver.findElement(By.name("email")).sendKeys(Email);
        chromeDriver.findElement(By.name("password")).sendKeys(psswd);
    }
    @When("tombol login diklik")
    public void tombol_login_diklik() {
        System.out.println("tombol login di klik");
        chromeDriver.findElement(By.name("submit")).click();
    }
    @Then("user ke direct ke halaman utama")
    public void user_ke_direct_ke_halaman_utama() {
        System.out.println("user ke direct ke halaman utama");
        Assertions.assertTrue(password.matches("[A-Za-z0-9]+") && password.length()!=0 && password.length() >= 8 && password.length() <= 13 && !email.equalsIgnoreCase(""),"Login gagal");
        List<WebElement> list = chromeDriver.findElements(By.xpath("/html/body/div[3]/form/input"));
        Assertions.assertTrue(list.size()>0,"Tombol logout tidak ditemukan");
        chromeDriver.close();
        chromeDriver.quit();
    }
}
