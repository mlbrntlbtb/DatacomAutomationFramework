package Utilities;

import org.apache.commons.lang3.StringUtils;

public class VariableHandler 
{
	public static void SetVariable(String variableName, String variableValue) throws Exception 
	{
		ConfigHandler.SetProperty("global", variableName, variableValue);
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
	
	public static boolean AllowSubstitute(Object value) 
	{
		try 
		{
			String parseValue = String.valueOf(value);
			if(parseValue.contains("D{") & parseValue.endsWith("}"))
			{
				return true;
			}
			else if(parseValue.startsWith("O{") & parseValue.endsWith("}")) 
			{
				return true;
			}
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
		
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
	
	public static Object SubstituteWith(Object value) 
	{
		try 
		{
			if(AllowSubstitute(value)) 
			{
				String subValue = StringUtils.replace(String.valueOf(value), "D{", "");
				subValue = StringUtils.replaceChars(subValue, "O{", "");
				subValue = StringUtils.replaceChars(subValue, "}", "");
				return subValue;
			}
		}
		catch(Exception e)
		{
			//Do nothing. Send previous value.
		}
		return value;
	}
	
	public static String GetVariable(String variableName) throws Exception 
	{
		return ConfigHandler.GetProperty("global", variableName);
	}
	
	public static String GetVariable(Object variableName) throws Exception 
	{
		try 
		{
			return ConfigHandler.GetProperty("global", String.valueOf(variableName));
		}
		catch(Exception e) 
		{
			return null;
		}
	}
}
