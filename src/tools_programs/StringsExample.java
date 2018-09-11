
package tools_programs;

import java.util.Scanner;

public class StringsExample {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a String");
		String value=sc.nextLine();
		System.out.println("Enter a price ex: Rs:7000/- for substring example");
		String price=sc.nextLine();
		/*get count of lowercase alphabet,uppercase alphabet,
		 digits and special characters in given string*/
		int nol=0,nou=0,nod=0,nosc=0;
		for(int i=0;i<value.length();i++)
		{
			char c=value.charAt(i);
			if(c>=97 && c<=122)
				nol++;
			else if(c>=65 && c<=90)
				nou++;
			else if(c>=48 &&c<=57)
				nod++;
			else
				nosc++;
		}
		System.out.println("No of Lower case letters are : "+nol);
		System.out.println("No of Upper case letters are : "+nou);
		System.out.println("No of Digits are : "+nod);
		System.out.println("No of special characters are : "+nosc);
		//splitting a string and display it in console
		System.out.println("Splitting a string");
		String s[]=value.split(" ");
		for(String s1:s)
		{
			System.out.print(s1);
		}
		System.out.println("");
		//Join a string
		System.out.println("Joining a string");
		String j=String.join(",", s);
		System.out.print(j);
		//substring
		System.out.println("Getting sub string from string");
		System.out.println(price.substring(3));
		System.out.println(price.substring(3, price.length()-2));
	}

}
