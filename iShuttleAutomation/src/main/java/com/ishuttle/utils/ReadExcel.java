package com.ishuttle.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



public class ReadExcel {
	
	
	 	static Splitter splitter = Splitter.on(',').omitEmptyStrings().trimResults();
	 	static Joiner joiner = Joiner.on(',').skipNulls();
	
	public static String[][] getExcelData(String fileName) {
		String[][] arrayExcelData = null;
		String[][] arrayExcel = null;
		
		HashMap<Object,Object> hm=new HashMap<Object,Object>(); 
		String text="";
		String text1="";
		String xlsFile =System.getProperty("user.dir")+"/Data/"+fileName+".xls";
		
		
		try {
			FileInputStream fs = new FileInputStream(xlsFile);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(0);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();
		
		
			arrayExcelData = new String[totalNoOfRows][totalNoOfCols]; 
		
			for (int i=0; i < totalNoOfRows; i++) 
			{

				for (int j=0; j < totalNoOfCols; j++) 
				{
					arrayExcelData[i][j] = sh.getCell(j,i).getContents();
					
					
					//System.out.print(arrayExcelData[i][j]);
                  
				}
				for(int b=1; b<=arrayExcelData.length+1;b++)
				{
				  text=text+arrayExcelData[i][b]+",";
				 
				 //System.out.print(text);
				 
				}
				
			}
			
			text1=text;
			
            int n=0;
            int t=0;
            for(int m=1; m<=arrayExcelData.length;m++)
            {
            hm.put(arrayExcelData[n][t],cleanUpCommas(text1));
            }
            System.out.println(hm);
			
			
			arrayExcel=new String[hm.size()][2];
            int i=0,k=0;
            for (Object key : hm.keySet()) {
            	k=0;
            	Object value = hm.get(key);
            	arrayExcel[i][k]=(String) key;
            	arrayExcel[i][k+1]=(String) value;
                i=i+1;
           
            }
			
           
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		
		
		return arrayExcel;
	}
	
	

	public static String cleanUpCommas(String string) {
	        return joiner.join(splitter.split(string));
	    }


	
	
}