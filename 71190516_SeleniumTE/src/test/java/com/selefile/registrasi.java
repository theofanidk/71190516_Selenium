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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class registrasi {
    ChromeDriver chromeDriver;
    @Given("browser dibuka")
    public void browser_dibuka() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Step Browser Dibuka");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chromedriver.exe");
        ///Objects.requireNonNull(getClass().getClassLoader().getResource("webdriver/chromedriver.exe")).getFile());
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

    }
    @Given("user berada di halaman registrasi")
    public void user_berada_di_halaman_registrasi() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("User berada di di halaman registrasi");
        chromeDriver.navigate().to("https://demo.guru99.com/insurance/v1/register.php");

        // Cek apakah ada tombol create dan reset
        List<WebElement> listButtonCreate = chromeDriver.findElements(By.name("submit"));
        assertTrue(listButtonCreate.size()>0);
        System.out.println("Tombol Create Ditemukan");
        List<WebElement> listButtonReset = chromeDriver.findElements(By.xpath("/html/body/div[3]/form/div[5]/input[1]"));
        assertTrue(listButtonReset.size()>0);
        System.out.println("Tombol Reset Ditemukan");
    }
    ///@When("^User wajib mengisi nama \"([^\"]*)\" dan password \"([^\"]*)\"$")
    @When("User wajib mengisi nama as {string} email as {string} dan password as {string}")
    public void user_wajib_mengisi_nama_as_email_as_dan_password_as(String string, String string2, String string3) {
        ///String password = "123456789";
        // Write code here that turns the phrase above into concrete actions
        System.out.println("memasukkan username dan password");
        chromeDriver.findElement(By.name("firstname")).sendKeys(string);
        chromeDriver.findElement(By.name("email")).sendKeys(string2);
        chromeDriver.findElement(By.name("password")).sendKeys(string3);
        chromeDriver.findElement(By.name("c_password")).sendKeys(string3);
        assertTrue(string3.length()>=8 && string3.length()<=13);

    }
    @When("tombol create ditekan")
    public void tombol_create_ditekan() {
        System.out.println("Tombol create di tekan");
        chromeDriver.findElement(By.name("submit")).click();

    }
    @Then("user redirect ke halaman login")
    public void user_redirect_ke_halaman_login() {
        List<WebElement> list = chromeDriver.findElements(By.xpath("/html/body/div[3]/form/div[3]/input"));
        assertTrue(list.size()>0,"Tombol Login Tidak Ditemukan");
    }
}
