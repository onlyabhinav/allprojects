/**
 * 
 */
package com.neoworks.transform.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import neoworks.illusions.BlueCode;
import neoworks.illusions.YellowCode;

import com.neoworks.transform.DTO;
import com.neoworks.utils.Token;
import com.neoworks.values.AppSettings;

// TODO: Auto-generated Javadoc
/**
 * The Class ReceiverWindow.
 *
 * @author Abhinav
 */
@SuppressWarnings( "serial" )
public class ReceiverWindow
    extends JPanel
    implements ActionListener, KeyListener
{
    
    /** The Constant newline. */
    static private final String newline = "\n";

    /** The save to file. */
    JButton downloadData, saveToFile;

    /** The log. */
    JTextArea log;

    /** The fc. */
    JFileChooser fc;

    /** The file. */
    File file;

    /** The gatepass. */
    JPasswordField gatepass;

    /** The token. */
    String token;

    /** The dto. */
    DTO dto;

    /** The key. */
    String key;

    /** The encoded string. */
    StringBuilder encodedString;

    /** The clipboard string. */
    StringBuilder clipboardString;

    /**
     * Instantiates a new receiver window.
     */
    public ReceiverWindow()
    {
        super( new BorderLayout() );

        if ( !AppSettings.hasValidLicense() )
            return;

        log = AppSettings.getLogArea( log );

        JScrollPane logScrollPane = new JScrollPane( log );

        fc = new JFileChooser();

        gatepass = AppSettings.getPasswordField( new JPasswordField() );
        gatepass.addActionListener( this );

        // gatepass.addKeyListener( this );

        JLabel label = new JLabel( "1. Enter key: " );
        label.setLabelFor( gatepass );

        downloadData = AppSettings.getButton( new JButton( "2. Receive" ) );
        if ( !AppSettings.DEBUG_MODE )
            downloadData.setEnabled( false );
        downloadData.addActionListener( this );

        saveToFile = AppSettings.getButton( new JButton( "3. Save" ) );
        saveToFile.setEnabled( false );
        saveToFile.addActionListener( this );

        JPanel buttonPanel = new JPanel( new FlowLayout( FlowLayout.CENTER ) ); // use FlowLayout

        buttonPanel.add( label );
        buttonPanel.add( gatepass );
        buttonPanel.add( downloadData );
        buttonPanel.add( saveToFile );

        add( buttonPanel, BorderLayout.NORTH );
        add( logScrollPane, BorderLayout.CENTER );

        this.addKeyListener( this );
        this.setFocusable( true );
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed( ActionEvent e )
    {

        if ( e.getSource() == downloadData )
        {
            Runnable doCopyClipboard = new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        token = ( "".equals( gatepass.getText() ) ? Token.MAGIC_STRING : gatepass.getText() );
                        log.append( "Downloading encoded data..." + newline );
                        log.append( "Using passcode: " + token + newline );

                        clipboardString =
                            new StringBuilder(
                                               (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData( DataFlavor.stringFlavor ) );

                        log.append( "Data downloaded..." + newline );
                        log.append( "Analysing encoded data..." + newline );

                        dto = (DTO) YellowCode.getJObject( clipboardString.toString(), new DTO() );

                        if ( token.equals( dto.getKey() ) && ( dto.getLength() == dto.getRaw().length() ) )
                        {
                            log.append( "Bingo!! You are ready to save file..." + newline );
                            encodedString = new StringBuilder( dto.getRaw() );
                        }
                        else
                        {
                            throw new Exception( "Invalid Data or Key..." );
                        }

                        log.append( "File received: " + dto.getFilename() + newline );
                        log.append( "File size: "
                            + BlueCode.Data.bytesToKb( encodedString.toString().getBytes( "UTF-8" ).length ) + newline );

                        saveToFile.setEnabled( true );
                    }
                    catch ( Exception e )
                    {
                        log.append( "Invalid content to download: " + e.getMessage() );
                    }
                }
            };

            SwingUtilities.invokeLater( doCopyClipboard );

        }
        if ( e.getSource() == saveToFile )
        {
            fc.setName( dto.getFilename() );
            fc.setSelectedFile( new File( dto.getFilename() ) );

            int returnVal = fc.showSaveDialog( ReceiverWindow.this );

            if ( returnVal == JFileChooser.APPROVE_OPTION )
            {
                file = fc.getSelectedFile();
                log.append( "Saving " + file.getName() + "." + newline );

                byte[] b = BlueCode.decodeData( encodedString.toString() );
                try
                {
                    FileOutputStream fos = new FileOutputStream( file );

                    fos.write( b );

                    fos.close();
                    log.append( "File saved " + file.getName() + "." + newline );
                    // saveToFile.setEnabled( false );
                }
                catch ( Exception ex )
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                log.append( "Open command cancelled by user." + newline );
            }
        }
    }

    /**
     * Creates the and show gui.
     */
    public static void createAndShowGUI()
    {
        // Create and set up the window.
        JFrame frame = AppSettings.getWindow( new JFrame( "File Decoder" ) );
        frame.add( new ReceiverWindow() );
        frame.setVisible( true );
    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped( KeyEvent e )
    {
        // log.append( "Typed: " + e.getKeyChar() );
    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed( KeyEvent e )
    {
        log.append( "Code: " + e.getKeyCode() );
        if ( e.getKeyCode() >= 0x41 && e.getKeyCode() <= 0x5A )
        {
            log.append( " Pressed: " + e.getKeyChar() );
            key += e.getKeyChar();
            if ( key.contains( "abhinav" ) )
            {
                log.append( newline + "Bingo!! Unlocked..." );
                downloadData.setEnabled( true );
            }
        }
        log.append( newline );
    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased( KeyEvent e )
    {
        // log.append( "Released: " + e.getKeyChar() );
    }

    /**
     * Start.
     */
    public static void start()
    {
        SwingUtilities.invokeLater( new Runnable()
        {
            public void run()
            {
                // Turn off metal's use of bold fonts
                // UIManager.put( "swing.boldMetal", Boolean.FALSE );
                createAndShowGUI();
            }
        } );
    }

}
