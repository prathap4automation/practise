package TestNgFlowBySir;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Class1 {
	@BeforeSuite
	public void beforeSuite()
	{
		Assert.assertTrue("In before suite", true);
	}
	
	@BeforeTest
	public void beforeTest()
	{
		Assert.assertTrue("in before test", true);
	}
	
	@BeforeClass
	public void beforeClass()
	{
		Assert.assertTrue("in before class", true);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		Assert.assertTrue("in before method", true);
	}
	
	@Test
	public void testInClassOne()
	{
		Assert.assertTrue("in test method of class one", true);
	}
	@AfterClass
	public void afterClass()
	{
		Assert.assertTrue("in after class", true);
	}
}
