package tools_pragrams;

public class StringDifference {
	public static void main(String[] args) {
		String str1 = "prathap_mallipeddi";
		String str2 = "prathapm";
		String diff="";
		for(int i=0;i<str1.length();i++)
		{
			String sub_str1=str1.substring(i, i+1);
			for(int j=0;j<str2.length();j++)
			{
				String sub_str2=str2.substring(j, j+1);
//				System.out.println("comparing : "+sub_str1+" "+str2.substring(j, j+1));
				if(sub_str1.equals(str2.substring(j, j+1)))
					break;
				else {
					if(j==str2.length()-1)
						diff=diff+sub_str1;
				}
			}
		}
		System.out.println(diff);
	}
}
