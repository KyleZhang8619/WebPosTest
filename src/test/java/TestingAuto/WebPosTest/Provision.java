package TestingAuto.WebPosTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class Provision {
	private WebDriver iewb = null;
	private DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");
	String userName = "000521"; //用户名
	String passWord = "123456"; //密码
	String terminalName = "test" + System.currentTimeMillis();  //终端名字

  @Test
  public void testProvision() {
		this.iewb.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.iewb.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	    this.provision(userName, passWord, terminalName);
		assertTrue(this.iewb.getCurrentUrl().contains("control/main"));
	    System.out.println(this.iewb.getCurrentUrl());
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

  
  public void provision(String userName,String passWord,String terminalName){
		this.iewb.get("https://qa1-eng.kargotest.com/webpos/control/provision?merchant=00062000000");
		
		Select selectEg = new Select(iewb.findElement(By.id("localeSelect")));
		selectEg.selectByVisibleText("English");
		this.iewb.findElement(By.xpath("//a[text()='重置浏览器']")).click();
		Alert alert = iewb.switchTo().alert(); // 把焦点切换到警告对话框
	    String actualText = alert.getText(); // 获取警告对话框的实际内容
	    System.out.print(actualText);
	    String expectedText = "重置您的浏览器？";
	    assertEquals(expectedText, actualText); // 和期望的内容进行对比
	    alert.accept(); // 点击“OK”（或者“确定”）
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Select selectStore = new Select(iewb.findElement(By.id("storeid")));
		selectStore.selectByValue("00062006000");
		this.iewb.findElement(By.id("USERNAME")).sendKeys(userName);
		this.iewb.findElement(By.id("PASSWORD")).sendKeys(passWord);
		this.iewb.findElement(By.id("buttonP")).click();
	    this.iewb.findElement(By.id("tName")).sendKeys(terminalName);
		this.iewb.findElement(By.id("buttonP")).click();
		this.iewb.findElement(By.cssSelector("input.submit.button")).click();
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
  
  
  
  @AfterClass
  public void afterClass() {
  }

}
