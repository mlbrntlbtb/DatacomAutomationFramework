package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataHandler 
{
	private static XSSFWorkbook workbook;
	private static XSSFSheet worksheet;

	//Public methods
	public static void SetFile(String fileName, String sheetName) throws Exception
	{
		try 
		{
			if(FileNameUtils.getExtension(fileName).equals("xlsx")) 
			{
				File file = new File(new File(ConfigHandler.userTestDataPath, fileName).getPath());
				FileInputStream inputStream = new FileInputStream(file);
				
				workbook = new XSSFWorkbook(inputStream);
				worksheet = workbook.getSheet(sheetName);
			}
			else //Supports Excel files only for now. Add more file types here --
			{
				throw new Exception("File type: [" + FileNameUtils.getExtension(fileName) + "] not supported.");
			}
			
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public static Object[] GetData() throws Exception 
	{
		try 
		{
			if(worksheet != null) 
			{
				Object[] list = new Object[getRowSize()-1];
				List<String> columnHeaders = getColumnHeaders();
				
				for(int r=1; r < getRowSize(); r++) 
				{
					LinkedHashMap<String,String> rowRecord = new LinkedHashMap<String,String>();
					
					for(int c=0; c < getColumnSize(); c++) 
					{
						String value = worksheet.getRow(r).getCell(c) == null ? null : //Check if cell is null
								new DataFormatter().formatCellValue(worksheet.getRow(r).getCell(c)); //Format cell value to string
						
						rowRecord.put(columnHeaders.get(c), value);
						list[r-1] = rowRecord;
					}
				}
				return list;
			}
			return null;
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
			return null;
		}
	}
	
	public static Object[][] GetDataProvider()
	{
		//Subtract '1' to row size to exclude count for column header
		//Start with '1' in for loop to start with first row and not column header row
		//Subtract '1' in object list row to avoid null rows
		if(worksheet != null) 
		{
			Object[][] list = new Object[getRowSize()-1][getColumnSize()];
			for(int r=1; r < getRowSize(); r++) 
			{
				for(int c=0; c < getColumnSize(); c++) 
				{
					String value = worksheet.getRow(r).getCell(c) == null ? null : //Check if cell is null
						new DataFormatter().formatCellValue(worksheet.getRow(r).getCell(c)); //Format cell value to string
				
					list[r-1][c] = value;
				}
			}
			return list;
		}
		return null;
	}
	
	//Private methods
	private static int getRowSize() 
	{
		return worksheet.getPhysicalNumberOfRows(); //Actual number of rows
	}
	
	private static int getColumnSize() 
	{
		return worksheet.getRow(0).getPhysicalNumberOfCells(); //Getting number of cells from first row since this is declared with column headers
	}
	
	private static List<String> getColumnHeaders() throws Exception
	{
		List<String> columnHeaders = new ArrayList<String>();
		try 
		{
			Iterator<Cell> cellIterator = worksheet.getRow(0).iterator(); //Get first row since this is considered as column headers
			while(cellIterator.hasNext()) 
			{
				Cell cell = cellIterator.next();
				columnHeaders.add(cell.toString());
			}
			return columnHeaders;
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
			return null;
		}
	}
}
