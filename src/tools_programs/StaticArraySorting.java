package tools_programs;

import java.util.Scanner;

public class StaticArraySorting {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		//give array length
		System.out.println("Enter array length");
		int arraySize=sc.nextInt();
		//declare 5 integer static array
		int x[]=new int[arraySize];
		//Taking input
		for(int i=0;i<arraySize;i++)
		{
			System.out.println("Enter value for : "+(i+1));
			x[i]=sc.nextInt();
		}
		//printing default array
		for(int i=0;i<x.length;i++)
		{
			System.out.print(x[i]+" ");
		}
		//sorting array
		for(int i=0;i<x.length;i++)
		{
			for(int j=0;j<x.length-1;j++)
			{
				if(x[j]>x[j+1])
				{
					int temp=x[j];
					x[j]=x[j+1];
					x[j+1]=temp;
				}
			}
		}
		System.out.println();
		//printing sorted array
		for(int i=0;i<x.length;i++)
		{
			System.out.print(x[i]+" ");
		}
	}

}
