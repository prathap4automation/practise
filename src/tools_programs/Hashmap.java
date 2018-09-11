package tools_programs;

import java.util.HashMap;
import java.util.Map;

public class Hashmap {

	public static void main(String[] args) {
		Hashmap h=new Hashmap();
		HashMap<Integer,String> x=new HashMap<Integer,String>();
		/*
		 * for adding x.put(key,value)
		 * for changing key x.put(NewUniqueKeyValue,remove(ExistingKeyvalue)) 
		 * For changing value by using key x.put(ExistingKeyValue,modifiedValue)
		 */
		//Insert data as pairs
		x.put(101, "Prathap");
		x.put(102, "jalaj");
		x.put(103, "krisha");
		x.put(104, "yeswanth");
		//Display
		System.out.println("Displaying hashmap data pair");
		for(Map.Entry<Integer,String> e:x.entrySet())
		{
			System.out.println(e.getKey()+" "+e.getValue());
		}
		System.out.println("Changed key");
		//change key
		h.nextline(3);
		x.put(105, x.remove(102));
		System.out.println("Displaying hashmap after changing key of jalaj");
		for(Map.Entry<Integer,String> e:x.entrySet())
		{
			System.out.println(e.getKey()+" "+e.getValue());
		}
		//Change value
		h.nextline(3);
		x.put(101, "Prathap Mallipeddi");
		System.out.println("Displaying hashmap after changing value of first pair");
		for(Map.Entry<Integer,String> e:x.entrySet())
		{
			System.out.println(e.getKey()+" "+e.getValue());
		}
	}
	void nextline(int num)
	{
		for(int i=0;i<num;i++)
		{
			System.out.println("");
		}
	}

}
