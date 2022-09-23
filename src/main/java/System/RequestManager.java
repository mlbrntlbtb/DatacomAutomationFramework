package System;

import java.lang.reflect.Method;
import BaseHTTP.*;
import Utilities.ExceptionHandler;
import Utilities.LogHandler;
import Utilities.VariableHandler;

public class RequestManager 
{
	public static void Execute(int stepIndex, String description, String type, String keyword, Object[] parameters) throws Exception 
	{
		try 
		{
			LogHandler.executeStep(stepIndex, description, keyword);
			BaseRequest request;
			BaseResponse response;
			
			if(type.toLowerCase().contains("response")) 
			{
				response = new BaseResponse();
				ExecuteKeyword(response, keyword, parameters);
			}
			else if(type.toLowerCase().contains("request")) 
			{
				String[] splitType = type.split("-");
				String requestType = splitType[splitType.length - 1];
				request = new BaseRequest(RequestSpecificationManager.GetRequest(), requestType);
				ExecuteKeyword(request, keyword, parameters);
			}
			else
				throw new Exception("Type: [" + type + "] not supported.");
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public static void ExecuteKeyword(Object object, String keyword, Object[] parameters) throws Exception 
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
					Object [] params = SubstituteVariables(parameters);
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
	
	private static Object[] SubstituteVariables(Object[] params) throws Exception 
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
