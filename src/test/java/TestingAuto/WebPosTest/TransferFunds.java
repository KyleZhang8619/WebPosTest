package TestingAuto.WebPosTest;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TransferFunds {
	String userName = "000519"; //用户名
	String passWord = "123456"; //密码
	String terminalName = "test311999999"; //终端名字
	String FromCardNO = "8888880050000780"; 
	String ToCardNO = "8888880050000814"; 
	private WebDriver iewb = null;
	private DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");
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
	  
  @Test
  public void testTransferFunds() {
	  this.iewb.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	  this.iewb.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
	  this.login(userName, passWord);
	  this.iewb.findElement(By.id("btn_prepaid")).click();
	  this.iewb.findElement(By.linkText("Type card number")).click();
	  this.iewb.findElement(By.xpath("//input[@name='cardno']")).sendKeys(FromCardNO);
	  this.iewb.findElement(By.id("submit_checkcardno")).click();
	  this.iewb.findElement(By.id("transferfunds")).click();
	  this.iewb.findElement(By.id("a_changeinput")).click();
	  this.iewb.findElement(By.id("input_typecardno_cardto")).sendKeys(ToCardNO);
	  this.iewb.findElement(By.id("input_amount")).sendKeys("1");
	  this.iewb.findElement(By.id("submit_transferfunds")).click();
	  String Value = this.iewb.findElement(By.className("alt")).getText();//获取字符串
      String expected = "Card From Balance:";//预期结果
      System.out.println("------------------" + Value.substring(1,7));
      assertEquals(expected, Value.substring(1,7));//截取字符串，断言
      this.iewb.findElement(By.linkText("Done")).click();
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
