package TestingAuto.WebPosTest;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.bouncycastle.jcajce.provider.asymmetric.DH;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class AddValue {
	String userName = "000519"; //用户名
	String passWord = "123456"; //密码
	String terminalName = "test311999999"; //终端名字
	private WebDriver iewb = null;
	private DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");
	//private Do du;
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
  public void testAddValue(){
	  
	  this.iewb.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	  this.iewb.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	  this.login(userName, passWord);
	  this.iewb.findElement(By.id("btn_prepaid")).click();
	  this.iewb.findElement(By.linkText("Type card number")).click();
	//  this.iewb.findElement(By.name("cardno")).clear();
	  this.iewb.findElement(By.xpath("//input[@name='cardno']")).sendKeys("8888880050000822");
	  this.iewb.findElement(By.id("submit_checkcardno")).click();
	  WebElement e = ( new WebDriverWait( iewb, 10 )) .until(    
		 	     new ExpectedCondition< WebElement>(){                
		 	         public WebElement apply( WebDriver d) {          
		 	             return d.findElement( By.id( "addvalue" ));
		 	         }                                                
		 	     }                                                    
		 	);        
	//  Actions action = new Actions(iewb);
	//  WebElement onElement = this.iewb.findElement(By.id("addvalue")); 
	//  action.moveToElement(onElement).clickAndHold();
	 this.iewb.findElement(By.id("addvalue")).click();
	  
	  
	  this.iewb.findElement(By.id("input_amount")).click();
	  this.iewb.findElement(By.id("input_amount")).clear();
	  this.iewb.findElement(By.id("input_amount")).sendKeys("50");
	  this.iewb.findElement(By.id("submit_addvalue")).click();
	  String Value = this.iewb.findElement(By.className("totalRow")).getText();//获取字符串
	  String expected = "RMB 50.00";//预期结果
	  System.out.println("------------------" + Value.substring(7));
//	  String Vcode = .getText().substring(7, 14).trim();
	  assertEquals(expected, Value.substring(7));//截取字符串，断言
	  this.iewb.findElement(By.linkText("Done")).click();
	  this.iewb.findElement(By.id("logout")).click();
	  
	  
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
	this.iewb.quit();
  }

}
