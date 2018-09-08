//
package tools_programs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexProgram {

	public static void main(String[] args) {
		String x="India won 2011 World cup";
		//preapare regex
		String regex="[A-Z][a-z]+";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(x);
		while(m.find())
		{
			System.out.println(m.group());
		}
	}

}
