/**
 * 
 */

/**
 * @author Abhinav
 */
public class CreateExcel
{

    public static void main( String a[] )
    {
        Write w = new Write( "test.xls" );
        try
        {
            w.write();
        }
        catch ( Exception e )
        {
            System.err.println( e.getMessage() );
        }

        System.err.println( "Done" );

    }

}
