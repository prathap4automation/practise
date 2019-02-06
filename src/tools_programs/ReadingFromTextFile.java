package tools_programs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadingFromTextFile {

	public static void main(String[] args) throws Exception {
		//open a file for reading purpose
		File f1=new File("test_data.txt");
		FileReader fr=new FileReader(f1);
		BufferedReader br=new BufferedReader(fr);
		//open file for writing
		File f2=new File("test_result.txt");
		FileWriter fw=new FileWriter(f2);
		BufferedWriter bw=new BufferedWriter(fw);
		/*>>Data driven code start*/
		ArrayList<String> stringValues=new ArrayList<String>();
		ArrayList<String> numberValues=new ArrayList<String>();
		ArrayList<String> alphaValues=new ArrayList<String>();
		String stringRegex="[a-zA-Z]";
		String numberRegex="^[0-9]+$";
		String alphaNumericRegex="[A-Za-z0-9&&[0-9]]+";
		String l=null;
		int no_of_lines=0,no_of_words=0,no_of_strings=0,no_of_numbers=0,no_of_alpha_numeric=0;
		while((l=br.readLine())!=null)
		{
			//finding no of lines
			no_of_lines++;
			//findinf no of words
			String[] words=l.split(" ");
			no_of_words=no_of_words+words.length;
			//find no of string values
			Pattern p_string=Pattern.compile(stringRegex);
			Matcher m=p_string.matcher(l);
			while(m.find())
			{
				no_of_strings++;
				stringValues.add((String) m.group());				
			}
			//finding no of numeric values
			Pattern p_numbers=Pattern.compile(numberRegex);
			Matcher m_numbers=p_numbers.matcher(l);
			while(m_numbers.find())
			{
				no_of_numbers++;
				numberValues.add(m_numbers.group());
			}
			//finding no of alpha numeric
			Pattern p_alpha=Pattern.compile(alphaNumericRegex);
			Matcher m_alpha=p_alpha.matcher(l);
			while(m_alpha.find())
			{
				no_of_alpha_numeric++;
				alphaValues.add(m_alpha.group());
			}
		}
		/*<<Data driven ode end*/
		/*>>write result to test result file code start*/
		bw.write("No of Lines are : "+no_of_lines);
		bw.newLine();
		bw.write("No of Words in files is :"+no_of_words);
		bw.newLine();
		bw.write("No of Strings in file is :"+no_of_strings);
		bw.newLine();
		for(int i=0;i<stringValues.size();i++)
		{
			bw.write(stringValues.get(i));
				bw.write(",");
		}
		bw.newLine();
		bw.write("No of Numeric values are :"+no_of_numbers);
		bw.newLine();
		for(int i=0;i<numberValues.size();i++)
		{
			bw.write(numberValues.get(i));
				bw.write(",");
		}
		bw.write("No of Alpha numeric vales are :"+no_of_alpha_numeric);
		bw.newLine();
		for(int i=0;i<alphaValues.size();i++)
		{
			bw.write(alphaValues.get(i));
				bw.write(",");
		}
		/*<<write result to test result file code end*/
		/*>>close files code start*/
		br.close();
		fr.close();
		bw.close();
		fw.close();
		/*<<close files code end*/
	}

}
