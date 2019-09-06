/**
 * 
 */
package gui.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// TODO: Auto-generated Javadoc
/**
 * The Class AllListeners.
 *
 * @author Abhinav
 */
public class AllListeners
    implements KeyListener
{

    /** The is ok. */
    public Boolean isOk = false;

    /** The key. */
    public String key = "";

    /*
     * (non-Javadoc)
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped( KeyEvent e )
    {
    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed( KeyEvent e )
    {
        System.out.println( e.getKeyChar() );
    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased( KeyEvent e )
    {
    }

}
