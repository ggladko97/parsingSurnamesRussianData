import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;

public class ExcelParser {
    private File openFile;
    private String path;
    private List<String>listOfSurnameXLS;
    FileInputStream fis;
    HSSFWorkbook workbook;
    HSSFSheet spreadsheet;
    static HSSFRow row;


    public ExcelParser(String path) throws IOException {
        this.path = path;
        openFile = new File(path);
        fis = new FileInputStream(openFile);
        workbook = new HSSFWorkbook(fis);
    }

    public List<String> extractRow(){
        listOfSurnameXLS = new ArrayList<>();
        spreadsheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = spreadsheet.iterator();
        while (rowIterator.hasNext())
        {
            row = (HSSFRow) rowIterator.next();
            Iterator <Cell> cellIterator = row.cellIterator();
            while ( cellIterator.hasNext())
            {
                HSSFCell cell = (HSSFCell) cellIterator.next();
                try{
                    if(cell.getCellType()== HSSFCell.CELL_TYPE_STRING){
                        listOfSurnameXLS.add(cell.getStringCellValue());
                    }
                    else
                    {
                        break;
                    }
                }
                catch(IllegalFormatException e){
                    e.printStackTrace();
                }
            }

        }
        return listOfSurnameXLS;

    }
}
