package TestingAuto.WebPosTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class RessiueCard {
	String userName = "000719"; //ressiue的交易，假如新卡要可以手动输入的话，必须是HQ门店和CS用户，仅在QA环境
	String passWord = "123456"; //密码
	String terminalName = "test311999999"; //终端名字
	String OldCard = "8888880050000780"; 
	String NewCard = "1232323232323233";
	private WebDriver iewb = null;
	private DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");
  @Test
  public void testRessiueCard() {
	  this.iewb.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	  this.iewb.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
	  this.login(userName, passWord);
	  this.iewb.findElement(By.id("btn_prepaid")).click();
	  this.iewb.findElement(By.xpath("//a[text()='Reissue Card']")).click();
	  this.iewb.findElement(By.xpath("//input[@id='input_oldcardno']")).sendKeys(OldCard);
	  this.iewb.findElement(By.xpath("//a[text()='Type card number']")).click(); //新卡
	  this.iewb.findElement(By.xpath("//input[@id='input_typecardno_cardto']")).sendKeys(NewCard);
	  this.iewb.findElement(By.xpath("//a[text()='Submit']")).click();
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
  }

}
