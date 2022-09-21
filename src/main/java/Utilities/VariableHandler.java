package Utilities;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

public class VariableHandler 
{
	private static HashMap<String, String> Variables = new HashMap<String,String>();
	
	public static void SetVariable(String variableName, String variableValue) 
	{
		Variables.put(variableName, variableValue);
	}
	
	public static boolean AllowSubstitute(String value) 
	{
		if(value.startsWith("D{") & value.endsWith("}"))
		{
			return true;
		}
		else if(value.startsWith("O{") & value.endsWith("}")) 
		{
			return true;
		}
		return false;
	}
	
	public static String SubstituteWith(String value) 
	{
		if(AllowSubstitute(value)) 
		{
			String subValue = StringUtils.replace(value, "D{", "");
			subValue = StringUtils.replaceChars(subValue, "O{", "");
			subValue = StringUtils.replaceChars(subValue, "}", "");
			return subValue;
		}
		return value;
	}
	
	public static String GetVariable(String variableName) 
	{
		return Variables.get(variableName);
	}
}
