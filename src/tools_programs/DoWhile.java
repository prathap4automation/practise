package tools_programs;

import java.util.ArrayList;
import java.util.Scanner;

public class DoWhile {

	public static void main(String[] args) {
		ArrayList<Integer> list=new ArrayList<Integer>();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter array size");
		int len=Integer.parseInt(sc.nextLine());
		for(int i=0;i<len;i++)
		{
			System.out.println("Enter "+i+" value");
			list.add(sc.nextInt());
		}
		//display
		int i=0;
		do
		{
			System.out.println(list.get(i));
			i++;
		}while(i<len);
	}

}
