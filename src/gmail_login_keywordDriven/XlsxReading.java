package gmail_login_keywordDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxReading {

	public static void main(String[] args) throws Exception {
		//get the excel file
		FileInputStream inputStream=new FileInputStream("tst_excel.xlsx");
		XSSFWorkbook rwb=new XSSFWorkbook(inputStream);
		XSSFSheet rsh1=rwb.getSheetAt(0);
		try {
			int nour = rsh1.getLastRowNum()+1;
			XSSFRow row = rsh1.getRow(0);
			int nouc = row.getLastCellNum();
			System.out.println("No of used rows in sheet 1 : "+nour);
			System.out.println("No of used columns in sheet 1 : "+nouc);
			//reading cell data
			for(int j=0;j<nouc;j++)
			{
				for(int i=1;i<nour;i++)
				{
					Double input1=rsh1.getRow(i).getCell(j).getNumericCellValue();
					Double input2 = rsh1.getRow(i).getCell(j+1).getNumericCellValue();
					Double res=input1+input2;
					System.out.println(res);
					row=rsh1.createRow(i);
					row.createCell(j).setCellValue(input1);
					row.createCell(j+1).setCellValue(input2);
					row.createCell(j+2).setCellValue(res);
				}
				break;
			}
			inputStream.close();//close file which opened for reading purpose
			FileOutputStream outputStream = new FileOutputStream(new File("tst_excel.xlsx")); 
			rwb.write(outputStream);
			outputStream.close();
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}
	}
}
