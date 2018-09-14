package tools_programs;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Number;

public class JxlExampleOne {

	public static void main(String[] args) throws Exception {
		File f=new File("test.xls");
		Workbook rwb=Workbook.getWorkbook(f);
		Sheet rsh=rwb.getSheet(0);
		int nour=rsh.getRows();
		int nouc=rsh.getColumns();
		WritableWorkbook wwb=Workbook.createWorkbook(f,rwb);
		WritableSheet wsh=wwb.getSheet(0);
		try {
			for(int i=1;i<nour;i++)
			{
				int x=Integer.parseInt(rsh.getCell(0,i).getContents());
				int y=Integer.parseInt(rsh.getCell(1,i).getContents());
				int z=x+y;
				Number n=new Number(2,i,z);
				wsh.addCell(n);
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		wwb.write();
		wwb.close();
		rwb.close();
	}

}
