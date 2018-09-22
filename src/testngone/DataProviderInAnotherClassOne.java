package testngone;

import org.testng.annotations.DataProvider;

public class DataProviderInAnotherClassOne {
	@DataProvider(name="searchword")
	public static Object[][] testData()
	{
		return new Object[][]
		{
			{"kalam"},{"selenium"},{"appium"}
		};
	}
}
