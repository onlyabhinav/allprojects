import java.util.ArrayList;
import java.util.List;

// Fig. G.1: SplashDemo.java
// Splash screen demonstration.
public class SplashDemo
{
    public static void main( String[] args )
    {
        List<Boolean> iList = new ArrayList<Boolean>();
        try
        {
            Thread.sleep( 5000 );
        } // end try
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        } // end catch

        System.out.println( "This was the splash screen demo." );
    } // end method main
} // end class SplashDemo