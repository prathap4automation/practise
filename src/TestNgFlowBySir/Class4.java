package TestNgFlowBySir;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Class4 {
	@AfterSuite
	public void afterSuite()
	{
		Assert.assertTrue("in after suite", true);
	}
	
	
	
	@Test
	public void testMethodOne()
	{
		Assert.assertTrue("in test method one", true);
	}
	
}
