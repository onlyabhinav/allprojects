/**
 * 
 */
package abh.projects.mcqapp.commons;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author Abhinav
 */
public class CommonGUITasks
{

    public static JFrame getWindow( String title, Dimension dim )
    {
        JFrame window = new JFrame( title );

        window.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        window.setSize( dim );

        window.setResizable( false );

        // window.setAlwaysOnTop( true );

        // window.setLocationRelativeTo( null );

        return window;
    }

}
