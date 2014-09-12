package TestingAuto.WebPosTest;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class NewBatch {
	String userName = "000719"; //用户名
	String passWord = "123456"; //密码
	String terminalName = "test311999999"; //终端名字
	String Box_ID = "2014912000002000002";
	String Innerpack_ID = "20149120002000002";
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
  //@Test
  public void testNewBatch_uploadFile() {
	  this.iewb.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	  this.iewb.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	  this.login(userName, passWord);
	  WebElement batch = iewb.findElement(By.linkText("Batch Operation"));
	  batch.click();
	  WebElement newBatch = iewb.findElement(By.linkText("New Batch"));
	  newBatch.click();
	  this.iewb.findElement(By.id("submit_batchSelectType")).click();
	  Select selectTxnType = new Select(iewb.findElement(By.id("txnType")));
	  selectTxnType.selectByValue("reload");
	  this.iewb.findElement(By.name("uploadedFile")).sendKeys("D:\\批量模板\\batch_reload.xls");
	  this.iewb.findElement(By.id("uploadSubmit")).click();
	  this.iewb.findElement(By.id("doBatch")).click();
	  Alert alert = iewb.switchTo().alert(); // 把焦点切换到警告对话框
      String actualText = alert.getText(); // 获取警告对话框的实际内容
      System.out.print(actualText);
      String expectedText = "Are you sure you want to submit batch file?";
      assertEquals(expectedText, actualText); // 和期望的内容进行对比
      alert.accept(); // 点击“OK”（或者“确定”）
      this.iewb.findElement(By.id("logout")).click();
	  Alert alert1 = iewb.switchTo().alert(); // 把焦点切换到警告对话框
      String actualText1 = alert1.getText(); // 获取警告对话框的实际内容
      System.out.print(actualText);
      String expectedText1 = "Confirm the exit!";
      assertEquals(expectedText1, actualText1); // 和期望的内容进行对比
      alert.accept(); // 点击“OK”（或者“确定”）
      
      this.logout();
      this.ApprovalBatch();
  }
      
//@Test
public void testNewBatch_BoxID(){
	 this.iewb.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	 this.iewb.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	 this.login(userName, passWord);
	 WebElement batch = iewb.findElement(By.linkText("Batch Operation"));
	 batch.click();
	 WebElement newBatch = iewb.findElement(By.linkText("New Batch"));
	 newBatch.click();
	 Select selectChoosetype = new Select(iewb.findElement(By.id("select_choosetype")));
	 selectChoosetype.selectByValue("B");
	 this.iewb.findElement(By.id("submit_batchSelectType")).click();
	 this.iewb.findElement(By.id("a_changeinput")).click();
	 this.iewb.findElement(By.id("input_typeid_cardto")).sendKeys(Box_ID);
	 this.iewb.findElement(By.xpath("//a[contains(text(),'OK')]")).click(); 
	 WebElement continue1 = iewb.findElement(By.linkText("Continue"));
	 continue1.click(); 
	 this.iewb.findElement(By.id("doBatch")).click();
	 Alert alert = iewb.switchTo().alert(); // 把焦点切换到警告对话框
     String actualText = alert.getText(); // 获取警告对话框的实际内容
     System.out.print(actualText);
     String expectedText = "Are you sure you want to submit batch ?";
     assertEquals(expectedText, actualText); // 和期望的内容进行对比
     alert.accept(); // 点击“OK”（或者“确定”）	 
}
  
  
 @Test
 public void testNewBatch_InnerpackID(){
	 this.iewb.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	 this.iewb.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	 this.login(userName, passWord);
	 WebElement batch = iewb.findElement(By.linkText("Batch Operation"));
	 batch.click();
	 WebElement newBatch = iewb.findElement(By.linkText("New Batch"));
	 newBatch.click();
	 Select selectChoosetype = new Select(iewb.findElement(By.id("select_choosetype")));
	 selectChoosetype.selectByValue("I");
	 this.iewb.findElement(By.id("submit_batchSelectType")).click();
	 this.iewb.findElement(By.id("a_changeinput")).click();
	 this.iewb.findElement(By.id("input_typeid_cardto")).sendKeys(Innerpack_ID);
	 this.iewb.findElement(By.xpath("//a[contains(text(),'OK')]")).click(); 
	 WebElement continue1 = iewb.findElement(By.linkText("Continue"));
	 continue1.click(); 
	 this.iewb.findElement(By.id("doBatch")).click();
	 Alert alert = iewb.switchTo().alert(); // 把焦点切换到警告对话框
     String actualText = alert.getText(); // 获取警告对话框的实际内容
     System.out.print(actualText);
     String expectedText = "Are you sure you want to submit batch ?";
     assertEquals(expectedText, actualText); // 和期望的内容进行对比
     alert.accept(); // 点击“OK”（或者“确定”）	 
	 
 }
   //审核前必须把商户更改为HQ门店
  public void ApprovalBatch(){
	 // this.iewb.get("https://qa1-eng.kargotest.com/webpos/control/checkLogin?merchant=00062000000");
      this.iewb.findElement(By.id("username")).sendKeys("000519");
	  this.iewb.findElement(By.id("password")).sendKeys("123456");
	  this.iewb.findElement(By.cssSelector("body")).click();
	  this.iewb.findElement(By.linkText("登录")).click();
	  WebElement batch2 = iewb.findElement(By.linkText("Batch Operation "));
	  batch2.submit();
	  this.iewb.findElement(By.id("newCard active button")).click();
	  this.iewb.findElement(By.className("detailsLink1")).click();
      this.iewb.findElement(By.id("remark_1")).sendKeys("11111");
      this.iewb.findElement(By.id("submitapproval_1")).click();
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
	// this.iewb.quit();
}


}
