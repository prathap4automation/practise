package TestNgFlowBySir;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Class2 {
	@Test
	public void test()
	{
		Assert.assertTrue("in test method of class 2", true);
	}
	@AfterTest
	public void afterTest()
	{
		Assert.assertTrue("In after test", true);
	}
	@AfterMethod
	public void afterMethod()
	{
		Assert.assertTrue("in after method", true);
	}
	
}
