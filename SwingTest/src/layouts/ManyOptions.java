package layouts;

// ManyOptions.java
// An example of using the JOptionPane with a custom list of options in an
// input dialog.
//

import javax.swing.JOptionPane;

public class ManyOptions
{
    public static void main( String[] args )
    {
        JOptionPane.showInputDialog( null, "Please choose a name", "Example 1", JOptionPane.DEFAULT_OPTION, null,
                                     new Object[] { "Amanda", "Colin", "Don", "Fred", "Gordon", "Janet", "Jay", "Joe",
                                         "Judie", "Kerstin", "Lotus", "Maciek", "Mark", "Mike", "Mulhern", "Oliver",
                                         "Peter", "Quaxo", "Rita", "Sandro", "Tim", "Will" }, "Joe" );
    }
}