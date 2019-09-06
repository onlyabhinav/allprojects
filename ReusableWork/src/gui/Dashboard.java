/**
 * 
 */
package gui;

import gui.listeners.AllListeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.neoworks.transform.ui.PublishWindow;
import com.neoworks.transform.ui.ReceiverWindow;
import com.neoworks.utils.Commons;
import com.neoworks.utils.Token;
import com.neoworks.values.AppSettings;
import com.neoworks.values.Strings;

// TODO: Auto-generated Javadoc
/**
 * The Class Dashboard.
 * 
 * @author Abhinav
 */
@SuppressWarnings( "serial" )
public class Dashboard
    extends JPanel
    implements ActionListener, KeyListener
{

    /** The decoder window. */
    JButton encoderWindow, decoderWindow;

    /** The icon. */
    JLabel icon;

    /** The error. */
    JTextArea error;

    /** The listener. */
    AllListeners listener;

    /** The is ok. */
    Boolean isOK = false;

    /** The frame. */
    static JFrame frame;

    /** The key. */
    private String key = "";

    /**
     * Instantiates a new dashboard.
     */
    public Dashboard()
    {
        super( new BorderLayout() );

        if ( !AppSettings.hasValidLicense() )
        {
            // removeAll();

            error = AppSettings.getLogArea( error );
            error.setBackground( Color.BLACK );
            error.setForeground( Color.RED );
            error.setText( Strings.LICENSE_EXPIRED );
            error.setFont( new Font( "Monospace", Font.TRUETYPE_FONT, 24 ) );

            add( error );

            System.err.println( Strings.LICENSE_EXPIRED );
            return;
        }

        encoderWindow = new JButton( "Publish" );
        decoderWindow = new JButton( "Receive" );

        decoderWindow.setBackground( AppSettings.LIGHT_RED );
        encoderWindow.setBackground( AppSettings.COOL_GREEN );

        decoderWindow.addActionListener( this );
        encoderWindow.addActionListener( this );

        icon = new JLabel( Commons.createImageIcon( Strings.ICON_LOCKED ) );

        setState( isOK );

        JPanel buttonPanel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
        buttonPanel.add( encoderWindow );
        buttonPanel.add( decoderWindow );

        add( buttonPanel, BorderLayout.PAGE_START );
        add( icon, BorderLayout.CENTER );

        setBackground( Color.LIGHT_GRAY );

        addKeyListener( this );

        setFocusable( true );
    }

    /**
     * Start.
     */
    public static void start()
    {
        Runnable showUI = new Runnable()
        {

            @Override
            public void run()
            {
                // frame = new JFrame( Strings.TITLE_LOCKED );
                frame = AppSettings.getWindow( new JFrame( Strings.TITLE_LOCKED ) );
                frame.add( new Dashboard() );
                frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                frame.setVisible( true );
            }
        };

        SwingUtilities.invokeLater( showUI );

    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed( ActionEvent e )
    {
        if ( isOK )
        {
            if ( e.getSource() == encoderWindow )
                PublishWindow.start();
            else if ( e.getSource() == decoderWindow )
                ReceiverWindow.start();
        }
    }

    /**
     * Sets the state.
     * 
     * @param isOK the new state
     */
    public void setState( Boolean isOK )
    {
        encoderWindow.setEnabled( isOK );
        decoderWindow.setEnabled( isOK );
        // icon.setEnabled( isOK );
        if ( isOK )
        {
            icon.setIcon( Commons.createImageIcon( Strings.ICON_UNLOCKED ) );
            setBackground( Color.WHITE );
        }
    }

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
        if ( e.getKeyCode() >= 0x41 && e.getKeyCode() <= 0x5A )
        {
            key += e.getKeyChar();
            if ( key.contains( Token.UNLOCK_CODE ) )
            {
                isOK = true;
                setState( isOK );
                frame.setTitle( Strings.TITLE_UNLOCKED );
            }
        }
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
