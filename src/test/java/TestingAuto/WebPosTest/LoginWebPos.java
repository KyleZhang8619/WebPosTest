package TestingAuto.WebPosTest;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class LoginWebPos {
	
	private WebDriver iewb = null;
	private DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");

	String Data [][] = {
			{"000111","123457","用户名或密码错误!"},
			{"000519","84845","登录时发生下列错误：密码不正确。"},
			{"4545","123456","用户名或密码错误! "}
	};
				
 @Test
  public void testLoginWebPos1() {
	  String userName = "000519"; //用户名
      String passWord = "123456"; //密码
	 // String terminalName = "test311999999"; //终端名字
	  this.iewb.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  this.iewb.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	  this.login(userName, passWord);
	  assertTitle(this.iewb.getTitle().contains("KargoCard WebPos"));
	  this.iewb.findElement(By.id("logout")).click();
	  Alert alert = iewb.switchTo().alert(); // 把焦点切换到警告对话框
      String actualText = alert.getText(); // 获取警告对话框的实际内容
      System.out.print(actualText);
      String expectedText = "Confirm the exit!";
      assertEquals(expectedText, actualText); // 和期望的内容进行对比
      alert.accept(); // 点击“OK”（或者“确定”）
 }
 
  
 private void assertTitle(boolean contains) {
	// TODO Auto-generated method stub
	
}


 @Test
  public void testLoginWebPosFailed2(){
for(String[] item : this.Data){
	String userName = item[0];
	String passWord = item[1];
	String erro = item[2];
	this.login(userName, passWord);
	assertTrue(this.iewb.getCurrentUrl().contains("webpos/control/login"));
}
}
	  	 
  private void assertTrue(boolean contains) {
	// TODO Auto-generated method stub
	
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

  @AfterClass
  public void afterClass() {
	  this.iewb.quit();
  }
	
}
