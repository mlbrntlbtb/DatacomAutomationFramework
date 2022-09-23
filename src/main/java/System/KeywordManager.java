package System;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import BaseElements.*;
import Utilities.ExceptionHandler;
import Utilities.LogHandler;
import Utilities.VariableHandler;

public class KeywordManager 
{
	public static void Execute(WebDriver driver, int stepIndex, String description, String pageName, String elementName, String keyword, String[] parameters) throws Exception 
	{
		try 
		{
			LogHandler.executeStep(stepIndex, description, keyword);
			HashMap<String,String> ElementRecord = PageObjectManager.GetElementRecord(pageName, elementName);
			BaseButton button;
			BaseTextBox textbox;
			BaseLabel label;
			BaseList list;
			BaseComboBox combobox;
			BaseAlert alert;
			BaseToolTip tooltip;
			BaseSort sort;
			BaseTab tab;
			BaseThumbnail thumbnail;
			// -- Add more element types here --
			
			switch(ElementRecord.get("Type").toLowerCase()) 
			{
			case "button":
				button = new BaseButton(driver, elementName, ElementRecord.get("SearchBy"), ElementRecord.get("SearchValue"));
				ExecuteKeyword(button, keyword, parameters);
				break;
			
			case "textbox":
				textbox = new BaseTextBox(driver, elementName, ElementRecord.get("SearchBy"), ElementRecord.get("SearchValue"));
				ExecuteKeyword(textbox, keyword, parameters);
				break;
				
			case "label":
				label = new BaseLabel(driver, elementName, ElementRecord.get("SearchBy"), ElementRecord.get("SearchValue"));
				ExecuteKeyword(label, keyword, parameters);
				break;
				
			case "list":
				list = new BaseList(driver, elementName, ElementRecord.get("SearchBy"), ElementRecord.get("SearchValue"));
				ExecuteKeyword(list, keyword, parameters);
				break;
				
			case "combobox":
				combobox = new BaseComboBox(driver, elementName, ElementRecord.get("SearchBy"), ElementRecord.get("SearchValue"));
				ExecuteKeyword(combobox, keyword, parameters);
				break;
				
			case "alert":
				alert = new BaseAlert(driver, elementName, ElementRecord.get("SearchBy"), ElementRecord.get("SearchValue"));
				ExecuteKeyword(alert, keyword, parameters);
				break;
				
			case "tooltip":
				tooltip = new BaseToolTip(driver, elementName, ElementRecord.get("SearchBy"), ElementRecord.get("SearchValue"));
				ExecuteKeyword(tooltip, keyword, parameters);
				break;
				
			case "sort":
				sort = new BaseSort(driver, elementName, ElementRecord.get("SearchBy"), ElementRecord.get("SearchValue"));
				ExecuteKeyword(sort, keyword, parameters);
				break;

			case "tab":
				tab = new BaseTab(driver, elementName, ElementRecord.get("SearchBy"), ElementRecord.get("SearchValue"));
				ExecuteKeyword(tab, keyword, parameters);
				break;
				
			case "thumbnail":
				thumbnail = new BaseThumbnail(driver, elementName, ElementRecord.get("SearchBy"), ElementRecord.get("SearchValue"));
				ExecuteKeyword(thumbnail, keyword, parameters);
				break;
			
			default:
				throw new Exception("Unsupported element type: [" + ElementRecord.get("Type") + "]");
			}
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e, driver);
		}
	}
	
	public static void ExecuteKeyword(Object object, String keyword, String[] parameters) throws Exception 
	{
		Method[] methods = object.getClass().getDeclaredMethods();
		
		boolean keywordFound = false;
		for(Method method : methods) 
		{
			if(method.getName().equals(keyword)) 
			{
				if(parameters == null) //Invoking keyword without parameters
				{
					method.invoke(object);
				}
				else //Invoking keywords with parameters
				{
					String [] params = SubstituteVariables(parameters);
					switch(parameters.length) 
					{
						case 1:
							method.invoke(object, params[0]);
							break;
						case 2:
							method.invoke(object, params[0], params[1]);
							break;
						case 3:
							method.invoke(object, params[0], params[1], params[2]);
							break;
						case 4:
							method.invoke(object, params[0], params[1], params[2], params[3]);
							break;
						default:
							throw new Exception("Unsupported Keyword: [" + keyword + "] with number of parameters: [" + parameters.length + 1 +"]");
					}
				}
				keywordFound = true;
			}
		}
		
		if(!keywordFound)
			throw new Exception("Unsupported Keyword: [" + keyword + "] not found.");
	}
	
	private static String[] SubstituteVariables(String[] params) throws Exception 
	{
		LogHandler.info("Starting substitution of variables... ");
		
		if(params != null) 
		{
			for(int p=0; p<params.length; p++) 
			{
				if(VariableHandler.AllowSubstitute(params[p])) 
				{
					String varValue = VariableHandler.GetVariable(VariableHandler.SubstituteWith(params[p]));
					if(varValue != null)
					{
						params[p] = varValue;
					}
				}
			}
		}
		return params;
	}
}
