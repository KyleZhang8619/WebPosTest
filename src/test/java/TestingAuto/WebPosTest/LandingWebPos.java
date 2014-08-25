package TestingAuto.WebPosTest;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class LandingWebPos {
	WebDriver dr = new ChromeDriver();
	String userName = "000521"; //用户名
	String passWord = "123456"; //密码
	String terminalName = "test" + System.currentTimeMillis();  //终端名字
	@Before
	public void setUp() throws Exception {
	//	 this.dr = new ChromeDriver();	
	}
	@After
	public void tearDown() throws Exception {
		 this.dr.quit();
	}
	//@Test
	public void testProvision(){
		this.dr.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.dr.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	    this.provision(userName, passWord, terminalName);
		assertTrue(this.dr.getCurrentUrl().contains("control/main"));
	    System.out.println(this.dr.getCurrentUrl());
	/*	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		}
	//@Test
	public void testLogin(){
	    this.provision(userName, passWord, terminalName);
		this.login(userName, passWord);
		assertTitle(this.dr.getTitle().contains("KargoCard WebPos"));
		System.out.print(this.dr.getTitle());
		}
	private void assertTitle(boolean contains) {
		// TODO Auto-generated method stub
		
	}
    //@Test
	public void testLogout() {
	    this.provision(userName, passWord, terminalName);
		this.login(userName, passWord);
		this.dr.findElement(By.id("logout")).click();
		Alert alert = dr.switchTo().alert(); // 把焦点切换到警告对话框
        String actualText = alert.getText(); // 获取警告对话框的实际内容
        System.out.print(actualText);
        String expectedText = "Confirm the exit!";
        assertEquals(expectedText, actualText); // 和期望的内容进行对比
        alert.accept(); // 点击“OK”（或者“确定”）
    //  } catch (NoAlertPresentException e) { // 警告对话框没有出现
    //   throw new IllegalStateException(e);	
	    }
    public void login(String userName,String passWord){
	    this.dr.findElement(By.id("username")).sendKeys(userName);
		this.dr.findElement(By.id("password")).sendKeys(passWord);
		this.dr.findElement(By.cssSelector("body")).click();
	    this.dr.findElement(By.linkText("登录")).click();
		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Select selectEnglish = new Select(dr.findElement(By.id("localeSelect")));
		selectEnglish.selectByValue("en");
		}
	public void provision(String userName,String passWord,String terminalName){
		this.dr.get("https://qa1-eng.kargotest.com/webpos/control/provision?merchant=00062000000");
		Select selectStore = new Select(dr.findElement(By.id("storeid")));
		selectStore.selectByValue("00062006000");
		this.dr.findElement(By.id("USERNAME")).sendKeys(userName);
		this.dr.findElement(By.id("PASSWORD")).sendKeys(passWord);
		this.dr.findElement(By.id("buttonP")).click();
	    this.dr.findElement(By.id("tName")).sendKeys(terminalName);
		this.dr.findElement(By.id("buttonP")).click();
		this.dr.findElement(By.cssSelector("input.submit.button")).click();
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

    
    
		
	
	
	
	
	
	
	

