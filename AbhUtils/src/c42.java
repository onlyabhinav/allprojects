import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 */

/**
 * @author Abhinav
 */
public class c42
{
    public void doArrayListExample()
    {
        List<String> listA = new ArrayList<String>();
        listA.add( "Alex" );
        listA.add( "Melody" );
        listA.add( "Jeff" );
        ListIterator<String> li = listA.listIterator();
        System.out.println( "Element = " + li.next() );
        System.out.println( " - hasPrevious = " + li.hasPrevious() );
        System.out.println( " - hasNext = " + li.hasNext() );
        System.out.println( " - previouslndex = " + li.previousIndex() );
        System.out.println( " - nextlndex = " + li.nextIndex() );
    }

    public static void main( String args[] )
    {
        c42 listExample = new c42();
        listExample.doArrayListExample();
    }
}
