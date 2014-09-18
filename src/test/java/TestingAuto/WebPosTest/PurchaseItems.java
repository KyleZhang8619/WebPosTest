package TestingAuto.WebPosTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

public class PurchaseItems {
	String userName = "000519"; //用户名
	String passWord = "123456"; //密码
	String terminalName = "test311999999"; //终端名字
	String number = "RMB -100.00";
	String CardNO = "8888880050000814";
	private WebDriver iewb = null;
	private DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");
  @BeforeClass
  public void launchIE(){
		System.setProperty("webdriver.ie.driver", projectpath+"/tool/IEDriverServer.exe");
	    caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, false);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);   
		caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");		
		caps.setCapability("ignoreZoomSetting", true);
		iewb = new InternetExplorerDriver(caps);
	} 	
 
  @Test
  public void testPurchaseItems(){
	    this.iewb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    this.iewb.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	    this.login(userName, passWord);
	    this.iewb.findElement(By.id("btn_prepaid")).click();
	    this.iewb.findElement(By.linkText("Type card number")).click();
	 //   this.iewb.findElement(By.name("cardno")).clear();
	    this.iewb.findElement(By.xpath("//input[@name='cardno']")).sendKeys("8888880050000814");
	    this.iewb.findElement(By.id("submit_checkcardno")).click();
	   
	    WebElement e = ( new WebDriverWait( iewb, 10 )) .until(    
		 	     new ExpectedCondition< WebElement>(){                
		 	         public WebElement apply( WebDriver d) {          
		 	             return d.findElement( By.id( "purchaseitems" ));
		 	         }                                                
		 	     }                                                    
		 	);  
	    this.iewb.findElement(By.id("purchaseitems")).click();
	    this.iewb.findElement(By.id("input_amount")).clear();
	    this.iewb.findElement(By.id("input_amount")).sendKeys("100");
	    this.iewb.findElement(By.id("submit_purchaseitems")).click();
	    String Value = this.iewb.findElement(By.className("totalRow")).getText();// 
		String expected = "RMB -100.00";
		System.out.println("------------------" + Value.substring(7));
		assertEquals(expected, Value.substring(7));
		
		this.logout();
}
//@Test
  public void testPurchaseItemsNotActived(){
	    this.iewb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    this.iewb.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	    this.login(userName, passWord);
	    this.iewb.findElement(By.id("btn_prepaid")).click();
	    this.iewb.findElement(By.linkText("Type card number")).click();
	    this.iewb.findElement(By.name("cardno")).clear();
	    this.iewb.findElement(By.xpath("//input[@name='cardno']")).sendKeys("8888880050000814");
	    this.iewb.findElement(By.id("submit_checkcardno")).click();
	    WebElement e = ( new WebDriverWait( iewb, 10 )) .until(    
		 	     new ExpectedCondition< WebElement>(){                
		 	         public WebElement apply( WebDriver d) {          
		 	             return d.findElement( By.id( "purchaseitems" ));
		 	         }                                                
		 	     }                                                    
		 	);  
	    this.iewb.findElement(By.id("purchaseitems")).click();
	    this.iewb.findElement(By.id("input_amount")).clear();
	    this.iewb.findElement(By.id("input_amount")).sendKeys("100");
	    this.iewb.findElement(By.id("submit_purchaseitems")).click();
	    String get = this.iewb.findElement(By.className("infoTable")).getText();
	    System.out.println("------------------" + get);
	    String mesg = "Card Not Active";
	    assertEquals(mesg, get);

		this.logout();
	  
  }
@Test //错误的金额
public void testPurchaseItemsNotAmount(){
	this.iewb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    this.iewb.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    this.login(userName, passWord);
    this.iewb.findElement(By.id("btn_prepaid")).click();
    this.iewb.findElement(By.linkText("Type card number")).click();
    this.iewb.findElement(By.name("cardno")).clear();
    this.iewb.findElement(By.xpath("//input[@name='cardno']")).sendKeys(CardNO);
    this.iewb.findElement(By.id("submit_checkcardno")).click();
    this.iewb.findElement(By.id("purchaseitems")).click();
    this.iewb.findElement(By.id("input_amount")).clear();
    this.iewb.findElement(By.id("input_amount")).sendKeys("-3fr00");
    this.iewb.findElement(By.id("submit_purchaseitems")).click();
    Alert alert = iewb.switchTo().alert(); // 把焦点切换到警告对话框
    String actualText = alert.getText(); // 获取警告对话框的实际内容
    System.out.print(actualText);
    String expectedText = "Amount format error";
    assertEquals(expectedText, actualText); // 和期望的内容进行对比
    alert.accept(); // 点击“OK”（或者“确定”）*/  
    assertTrue(this.iewb.getCurrentUrl().contains("webpos/control/purchaseitems#"));
	System.out.println(this.iewb.getCurrentUrl());

	this.logout();
	
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

public void logout(){
	this.iewb.findElement(By.id("logout")).click();
	Alert alert = iewb.switchTo().alert(); // 把焦点切换到警告对话框
    String actualText = alert.getText(); // 获取警告对话框的实际内容
    System.out.print(actualText);
    String expectedText = "Confirm the exit!";
    assertEquals(expectedText, actualText); // 和期望的内容进行对比
    alert.accept(); // 点击“OK”（或者“确定”）
	
}



  @AfterClass
  public void afterClass() {
	// this.iewb.quit();
  }

}
