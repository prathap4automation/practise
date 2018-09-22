package testngflow;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TestClassTwo {
	@Test
	public void testMethodOneInClassTwo()
	{
		System.out.println("test method one in class 2");
	}
	@AfterMethod
	public void afterMethodInClassTwo()
	{
		System.out.println("In after method");
	}
	@AfterClass
	public void afterClassInClassTwo()
	{
		System.out.println("after class in class 2");
	}
	@AfterTest
	public void afterTestInClassTwo()
	{
		System.out.println("In after test method");
	}
}
