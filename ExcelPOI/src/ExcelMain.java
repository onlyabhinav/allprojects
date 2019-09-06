import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 
 */

/**
 * @author abhinav
 */
public class ExcelMain
{

    public static void main( String[] a )
    {
        try
        {
            doSome();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    static void doSome()
        throws Exception
    {
        Workbook wb = new HSSFWorkbook();
        // Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet( "new sheet" );

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow( (short) 0 );
        // Create a cell and put a value in it.
        Cell cell = row.createCell( 0 );
        cell.setCellValue( 1 );

        // Or do it on one line.
        row.createCell( 1 ).setCellValue( 1.2 );
        row.createCell( 2 ).setCellValue( createHelper.createRichTextString( "This is a string" ) );
        row.createCell( 3 ).setCellValue( true );

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream( "workbook.xls" );
        wb.write( fileOut );
        fileOut.close();
    }
}
