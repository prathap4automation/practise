package gmail_login_keywordDriven;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class RunnerUsingPOI {

	public static void main(String[] args) throws Exception {
		//get the excel file
		FileInputStream inputStream=new FileInputStream("test_2.xls");
		HSSFWorkbook rwb=new HSSFWorkbook(inputStream);
		HSSFSheet rsh1=rwb.getSheetAt(0);
		try {
			int nour = rsh1.getLastRowNum()+1;
			HSSFRow row = rsh1.getRow(0);
			int nouc = row.getLastCellNum();
			System.out.println("No of used rows in sheet 1 : "+nour);
			System.out.println("No of used columns in sheet 1 : "+nouc);
			//reading cell data
			for(int j=0;j<nouc;j++)
			{
				for(int i=1;i<nour;i++)
				{
					Double res=rsh1.getRow(i).getCell(j).getNumericCellValue()+rsh1.getRow(i).getCell(j+1).getNumericCellValue();
					System.out.println(res);
					row=rsh1.createRow(i);
					Cell cell=row.createCell(2);
					cell.setCellValue(res);
				}
				break;
			}
			inputStream.close();//close file which opened for reading purpose
			FileOutputStream outputStream = new FileOutputStream("test_2.xls");
			rwb.write(outputStream);
			outputStream.close();
		}
		catch(Exception ex) {System.out.println(ex.getMessage());}
	}

}
