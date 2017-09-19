package com.ishuttle.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadCSVData {
	
	public static Object[][] readData(String fileName) {
		
		Object[][] datatoBeReturned = null;

		HashMap<Object,Object> hm=new HashMap<Object,Object>();  

        String csvFile = System.getProperty("user.dir")+"/Data/"+fileName+".csv";
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        int j=0;
        try {

	          br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] testData = line.split(csvSplitBy);
                String text="";
                          for(int i=1;i<testData.length;i++) 
                          {
                        	  text=text+testData[i]+",";
                          }
                          text = text.substring(0, text.length()-1);

                          hm.put(testData[j], text);


            }
            datatoBeReturned=new String[hm.size()][2];
            int i=0,k=0;
            for (Object key : hm.keySet()) {
            	k=0;
            	Object value = hm.get(key);
            	datatoBeReturned[i][k]=key;
            	datatoBeReturned[i][k+1]=value;
                i=i+1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
  return datatoBeReturned;
    }

}
