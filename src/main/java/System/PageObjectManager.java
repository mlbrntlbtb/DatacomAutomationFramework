package System;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Utilities.ConfigHandler;
import Utilities.ExceptionHandler;
import Utilities.LogHandler;

public class PageObjectManager 
{
	public static HashMap<String, String> GetElementRecord(String pageName, String elementName) throws Exception 
	{
		HashMap<String, String> ElementRecord = new HashMap<String, String>();
		try 
		{
			//Get target file from object repository
			String pagePath = new File(ConfigHandler.pageObjectsPath, pageName + ".xml").getPath();
			File file = new File(pagePath);
			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			
			document.getDocumentElement().normalize();
			
			//Get element record from page xml file
			
			boolean elementFound = false;
			NodeList nodeList = document.getElementsByTagName("element");
			
			for(int n=0; n<nodeList.getLength(); n++) 
			{
				Node node = nodeList.item(n);
				String nodeName = node.getAttributes().getNamedItem("name").getNodeValue();
				
				if(nodeName.toLowerCase().equals(elementName.toLowerCase())) 
				{
					//Get values from target element
					Element nodeElement = (Element)node;
					ElementRecord.put("Type", nodeElement.getElementsByTagName("type").item(0).getTextContent());
					ElementRecord.put("SearchBy", nodeElement.getElementsByTagName("searchby").item(0).getTextContent());
					ElementRecord.put("SearchValue", nodeElement.getElementsByTagName("searchvalue").item(0).getTextContent());
					
					LogHandler.info("Retrieved locators of: [" + nodeName + "] from [" + pageName + "] at object repository.");
					elementFound = true;
					return ElementRecord;
				}
			}
			
			if(!elementFound)
				throw new Exception("Element [" + elementName + "] not found in repository page.");
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
		return null;
	}
}
