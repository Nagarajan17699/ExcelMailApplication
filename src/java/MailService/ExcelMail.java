/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailService;

/**
 *
 * @author Nagarajan
 */
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelMail {
    
    public List<String> readFile(String str)
    {
        List<String> li = new ArrayList<>();
        try
        {
            System.out.println("The file is:"+str);
            FileInputStream file = new FileInputStream(new File(str));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int i=1;
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                //Cell cell = cellIterator.next();
                while (cellIterator.hasNext()) 
                {
                     Cell cell = cellIterator.next();
                     li.add(cell.getStringCellValue());
                }
              
            }
            file.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return li;
    }
}
