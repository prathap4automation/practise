package testngflow;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClassOne {
	@BeforeSuite
	public void beforeSuiteInClassOne()
	{
		System.out.println("Before suite");
	}
	@BeforeTest
	public void beforeTestInClassOne()
	{
		System.out.println("In before Test");
	}
	@BeforeClass
	public void beforeClassInClassOne()
	{
		System.out.println("Before class");
	}
	@BeforeMethod
	public void beforeMethodInClassOne()
	{
		System.out.println("In before Method");
	}
	@Test
	public void testOneInClassOne()
	{
		System.out.println("In test one method");
	}
	@Test
	public void testTwoInClassOne()
	{
		System.out.println("In test two method");
	}
	@AfterSuite
	public void afterSuiteInClassOne()
	{
		System.out.println("after suite");
	}
}
