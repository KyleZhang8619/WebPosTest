package TestingAuto.WebPosTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.mysql.jdbc.Connection;

public class Refund {
	String userName = "000520"; //用户名
	String passWord = "123456"; //密码
	String terminalName = "test311999999"; //终端名字
	String CardNO = "8888880050000780"; 
	private WebDriver iewb = null;
	private DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");
  @Test
  public void testRefund() {
	  String ss = JDBC_Test.query();
	  System.out.print(ss);
	  this.iewb.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
	  this.iewb.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
	  this.login(userName, passWord);
	  this.iewb.findElement(By.id("btn_prepaid")).click();
	  this.iewb.findElement(By.linkText("Type card number")).click();
	  this.iewb.findElement(By.xpath("//input[@name='cardno']")).sendKeys("8888880050000780");
	  this.iewb.findElement(By.id("submit_checkcardno")).click();
	  WebElement e = ( new WebDriverWait( iewb, 10 )) .until(    
		 	     new ExpectedCondition< WebElement>(){                
		 	         public WebElement apply( WebDriver d) {          
		 	             return d.findElement( By.id( "returnitems" ));
		 	         }                                                
		 	     }                                                    
		 	);        
	  this.iewb.findElement(By.id("returnitems")).click(); 
	//  this.iewb.findElement(By.xpath("//a[text()='Refund']")).click();
	  this.iewb.findElement(By.xpath("//input[@id = 'input_txid']")).sendKeys(ss);
	  this.iewb.findElement(By.xpath("//input[@id = 'input_amount']")).sendKeys("1");
	  this.iewb.findElement(By.xpath("//a[text() = 'Submit']")).click();
	  
  }
  @BeforeClass
  public void launchIE() {
		System.setProperty("webdriver.ie.driver", projectpath+"/tool/IEDriverServer.exe");
	    caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, false);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);   
		caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");		
		caps.setCapability("ignoreZoomSetting", true);
		iewb = new InternetExplorerDriver(caps);
		
	  }
  public void login(String userName,String passWord){
	    this.iewb.get("https://qa1-eng.kargotest.com/webpos/control/checkLogin?merchant=00062000000");
	    this.iewb.findElement(By.id("username")).sendKeys(userName);
		this.iewb.findElement(By.id("password")).sendKeys(passWord);
		this.iewb.findElement(By.cssSelector("body")).click();
	    this.iewb.findElement(By.linkText("登录")).click();
		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}

  @AfterClass
  public void afterClass() {
	//  this.iewb.quit();
  }

}
