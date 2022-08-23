package clases;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainScript {
/*
 * Configurar java 1.8, no corre con Java 15
 * Configurar JAR de Selenium en Build Path
 * Verificar la ruta del driver. En mac se puede jalar el archivo al terminal para obtener la ruta
 * en Windows no olvidar variables de entorno
*/
	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "/Users/mfhv/Documents/Drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/automation-practice-form");
		driver.manage().window().maximize();
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("document.body.style.zoom = '0.6'");
		
		//WebDriverWait wait = new WebDriverWait(driver,30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		//Ingresar Nombre
		driver.findElement(By.xpath("//input[contains(@id, 'firstName')]")).sendKeys("juan");
		//Ingresar Apellido
		driver.findElement(By.xpath("//input[contains(@id, 'lastName')]")).sendKeys("perez");
		//Seleccionar genero
		WebElement genero = driver.findElement(By.xpath("//input[contains(@name, 'gender')]"));
		JavascriptExecutor executorGenero = (JavascriptExecutor)driver;
		executorGenero.executeScript("arguments[0].click();", genero);
	
		//Ingresar NumeroTLF
		driver.findElement(By.xpath("//input[contains(@id, 'userNumber')]")).sendKeys("1234567890");
		
		//Submit Form
		WebElement Submit = driver.findElement(By.xpath("//button[contains(@type, 'submit')]"));
		JavascriptExecutor executorSubmit = (JavascriptExecutor)driver;
		executorSubmit.executeScript("arguments[0].click();", Submit);
		
		//Validacion:Verificar que se muestre el elemento. 
		WebElement exists = driver.findElement(By.xpath("//div[text()='Thanks for submitting the form']"));
		exists.isDisplayed();
		if(exists.isDisplayed()==true) {
			System.out.println("Script termino satisfactoriamente");
		}else {
			System.out.println("Script fallo");
		}
		takeSnapShot(driver, "/Users/mfhv/Documents/COPIAAAAAA/Capturas/BCP.png");
	    driver.close();
	}
	
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

                File DestFile=new File(fileWithPath);

                FileUtils.copyFile(SrcFile, DestFile);

    }

}
