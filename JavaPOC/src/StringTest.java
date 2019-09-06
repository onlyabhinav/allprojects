/**
 * 
 */

/**
 * @author Abhinav
 */
public class StringTest
{

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        String s1 = "abhinav";
        String s2 = new String( "abhinav" );
        // String s2 = new String( "abhinav" );

        System.out.printf( "s1 is %s\n", s1 );
        System.out.printf( "s2 is %s\n", s2 );

        print( s1 );
        print( s2 );

        if ( s1 == s2 )
            System.err.println( "Ok" );
        else
            System.err.println( "Not Ok" );

        s2 = "sharma";
        System.out.printf( "s1 is %s\n", s1 );
        System.out.printf( "s2 is %s\n", s2 );

    }

    public static void print( Object o )
    {
        System.out.println( "Object Class: " + o.getClass() );
        System.out.println( "Object Hash Code: " + o.hashCode() );
        System.out.println( "Object to String: " + o.toString() );

    }

}
