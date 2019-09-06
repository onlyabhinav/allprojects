import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import statements
public class ReadExcelDemo
{
    public static void main( String[] args )
    {
        System.out.println( java.lang.Runtime.getRuntime().totalMemory() );
        try
        {
            FileInputStream file = new FileInputStream( new File( "new_example2.xlsx" ) );

            // Create Workbook instance holding reference to .xlsx file

            XSSFWorkbook workbook = new XSSFWorkbook( file );

            SXSSFWorkbook wb = new SXSSFWorkbook( workbook );

            // Get first/desired sheet from the workbook
            SXSSFSheet sheet = (SXSSFSheet) wb.cloneSheet( 0 );
            // SXSSFSheet sheet = (SXSSFSheet) wb.getSheetAt( 0 );

            ArrayList<PersonDO> persons = PersonDO.getPersons( 1000 );

            int startIndex = 1;

            System.out.println( "Populating data..." );
            for ( PersonDO p : persons )
            {
                // Row row = sheet.getRow( startIndex );
                Row row = sheet.createRow( startIndex ); // getRow( startIndex );

                row.createCell( 0 ).setCellValue( p.getName() );
                row.createCell( 1 ).setCellValue( p.getPhone() );
                row.createCell( 2 ).setCellValue( p.getCity() );

                if ( startIndex % 500 == 0 )
                {

                    System.out.println( "At " + startIndex );
                    System.out.println( java.lang.Runtime.getRuntime().freeMemory() + " bytes" );
                }

                startIndex++;
            }

            System.out.println( "Writing to excel..." );
            FileOutputStream fileOut = new FileOutputStream( "workbook.xlsx" );

            wb.write( fileOut );

            System.out.println( "Completed..." );

            fileOut.close();
            file.close();

            wb.dispose();

        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}