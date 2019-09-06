import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestJava
{

    public static void main( String args[] )
    {
        List lst = new ArrayList();

        lst.add( "Ruby" );
        lst.add( "lain" );
        lst.add( "John" );
        lst.add( "Ruby" );
        lst.add( "Pete" );

        System.out.println( lst );
        System.out.println( "2:" + lst.get( 2 ) );
        System.out.println( "0:" + lst.get( 0 ) );
        LinkedList queue = new LinkedList();
        queue.addFirst( "Ruby" );
        queue.addFirst( "lain" );
        queue.addFirst( "John" );
        queue.addFirst( "Ruby" );
        queue.addFirst( "Pete" );
        System.out.println( queue );
        queue.removeLast();
        queue.removeLast();
        System.out.println( queue );
    }
}