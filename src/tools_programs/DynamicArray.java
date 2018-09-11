package tools_programs;

import java.util.ArrayList;
import java.util.Scanner;

public class DynamicArray {

	public static void main(String[] args) {
		ArrayList<String> x=new ArrayList<String>();
		/*
		 * For adding add()
		 * to get particular value get()
		 * to modify particular value set()
		 */
		//take input from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter array size");
		int len=Integer.parseInt(sc.nextLine());
		for(int i=0;i<len;i++)
		{
			switch(i)
			{
			case 1:
				System.out.println("Enter "+(i+1)+"st value");break;
			case 2:
				System.out.println("Enter "+(i+1)+"nd value");break;
			case 3:
				System.out.println("Enter "+(i+1)+"rd value");break;
			default:
				System.out.println("Enter "+(i+1)+"th value");
			}
			x.add(sc.nextLine());
		}
		//print array values using for each
		for(String x1 : x)//here "x" is array list name
		{
			System.out.println(x1);
		}
		//change second value
		x.set(1, "changed value");
		//print modified array values using for each
		System.out.println("Values after chainging");
		for(String x1 : x)//here "x" is array list name
		{
			System.out.println(x1);
		}
	}

}
