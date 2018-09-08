package gmail_login_keywordDriven;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import gmail_login_keywordDriven.TestBase;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TestRunner {
	static Workbook rwb=null;
	static WritableWorkbook wwb=null;
	public static void main(String[] args) throws Exception {
		//get excel file
		File f=new File("test_gmail_xl.xls");
		try {
			//get workbook from file
			rwb=Workbook.getWorkbook(f);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}
		if(rwb==null)
		{
			System.out.println("Workbook not loaded");
			System.exit(0);
		}
		else {
			try {
				//get first sheet which cains tests, also we can get by name
				Sheet rsh1=rwb.getSheet(0);
				//get rows and columns count of first sheet
				int nor1=rsh1.getRows();//rows count of sheet 1
				int noc1=rsh1.getColumns();//coulms count of sheet 1
				//get second sheet which contais steps for specific test
				Sheet rsh2=rwb.getSheet(1);
				int nor2=rsh2.getRows();//rows count of sheet2
				int noc2=rsh2.getColumns();//columns count of sheet2
				//create writable workbook from above workbook
				wwb=Workbook.createWorkbook(f,rwb);
				//create writable sheets
				WritableSheet wsh1=wwb.getSheet(0);//Tests sheet
				WritableSheet wsh2=wwb.getSheet(1);//Steps sheet
				//prepare a name for column which stores results in sheets
				SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yy-H-mm-ss");
				Date d=new Date();
				String col_name=sf.format(d);
				//prepare formating options for cell
				WritableCellFormat cf=new WritableCellFormat();
				cf.setAlignment(Alignment.JUSTIFY);
				cf.setWrap(true);
				//using Label class we prepare and add columns to sheets at the end ot columns
				Label l1=new Label(noc1,0,col_name,cf);
				wsh1.addCell(l1);//add column in tests sheet of writable sheet
				Label l2=new Label(noc2,0,col_name,cf);
				wsh2.addCell(l2);//add column in steps sheet of writable sheet
				//Now get methods from our methods written class
				TestBase base=new TestBase();
				Method m[]=base.getClass().getMethods();
				//take a variable flag, if anything goes wrong we assign as 1 else it will be zero
				int flag=0;
				//looping in test sheet
				for(int i=1;i<nor1;i++)//i=1 for reading from 2nd row
				{
					//get testid and mode
					String test_id=rsh1.getCell(0, i).getContents();//zero for first column
					String mode=rsh1.getCell(2, i).getContents();//2 for third column which contains mode.
					System.out.println(test_id+" "+mode);
					//if mode value is yes then that steps are ready to test
					if(mode.equalsIgnoreCase("yes"))
					{
						//goto steps sheet
						for(int j=1;j<nor2;j++)
						{
							//get test id
							String step_id=rsh2.getCell(0, j).getContents();
							if(test_id.equalsIgnoreCase(step_id))//if testid matched in both the sheets
							{
								System.out.println(step_id+" test can be performed now");
								//get method name,element,data,criteria from steps sheet
								String method_name=rsh2.getCell(2, j).getContents();
								String element=rsh2.getCell(3, j).getContents();
								String data=rsh2.getCell(4, j).getContents();
								String criteria=rsh2.getCell(5, j).getContents();
								System.out.println("Method Name :"+method_name+" Element :"+element+" Data :"+data+" criteria :"+criteria);
								for(int k=0;k<m.length;k++)
								{
									//if method name mathed from steps sheet and in methods array
									if(m[k].getName().equals(method_name))
									{
										System.out.println(method_name+" method name matched");
										//execute the method and get result
										String result=(String)m[k].invoke(base, element, data, criteria);
										Label lb=new Label(noc2,j,result,cf);//j for second sheet result column
										wsh2.addCell(lb);//add result to result column of steps sheet
										if(result.equals("unknown browser"))
										{
											wwb.write();
											wwb.close();
											rwb.close();
											System.exit(0);
										}
										if(result.contains("failed") || result.contains("inturrupted"))
											flag=1;
									}
								}//k closing
							}
						}//j closing
						if(flag==0)
						{
							Label l=new Label(noc1,i,"Passed",cf);
							wsh1.addCell(l);
						}
						else {
							Label l=new Label(noc1,i,"Failed",cf);
							wsh1.addCell(l);
						}
					}
				}
				
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			//save and close workbook
			wwb.write();
			wwb.close();
			rwb.close();
		}
	}
}
