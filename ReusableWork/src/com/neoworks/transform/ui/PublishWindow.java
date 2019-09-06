/**
 * 
 */
package com.neoworks.transform.ui;

/**
 * @author Abhinav
 *
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import neoworks.illusions.BlueCode;
import neoworks.illusions.YellowCode;

import com.neoworks.transform.DTO;
import com.neoworks.utils.Token;
import com.neoworks.values.AppSettings;

// TODO: Auto-generated Javadoc
/**
 * The Class PublishWindow.
 */
@SuppressWarnings( "serial" )
public class PublishWindow
    extends JPanel
    implements ActionListener
{

    /** The Constant newline. */
    static private final String newline = "\n";

    /** The receiver. */
    JButton openButton, encodeFile, publishFile, receiver;

    /** The encoded. */
    JTextArea log, encoded;

    /** The fc. */
    JFileChooser fc;

    /** The status. */
    JLabel status;

    /** The gatepass. */
    JPasswordField gatepass;

    /** The file. */
    File file;

    /** The token. */
    String token;

    // Cipher c;

    /** The byte array. */
    byte[] byteArray;

    /** The encoded string. */
    StringBuilder encodedString;

    /** The raw. */
    StringBuilder raw;

    /** The dto. */
    DTO dto;

    /**
     * Instantiates a new publish window.
     */
    public PublishWindow()
    {
        super( new BorderLayout() );

        if ( !AppSettings.hasValidLicense() )
            return;

        log = AppSettings.getLogArea( log );

        JScrollPane logScrollPane = new JScrollPane( log );
        encoded = new JTextArea( 20, 20 );
        encoded.setEditable( false );
        encoded.setLineWrap( true );
        encoded.setWrapStyleWord( true );
        encoded.setBackground( new Color( 0xCCCCCC ) );

        // JScrollPane ecodedScrollPane = new JScrollPane( encoded );

        fc = new JFileChooser();

        openButton = AppSettings.getButton( new JButton( "1. Open" ) );
        openButton.addActionListener( this );

        encodeFile = AppSettings.getButton( new JButton( "2. Load" ) );
        encodeFile.setEnabled( false );
        encodeFile.addActionListener( this );

        gatepass = AppSettings.getPasswordField( new JPasswordField() );
        gatepass.addActionListener( this );
        JLabel label = new JLabel( "  3. Key: " );
        label.setLabelFor( gatepass );

        publishFile = AppSettings.getButton( new JButton( "4. Publish" ) );
        publishFile.setEnabled( false );
        publishFile.addActionListener( this );

        receiver = AppSettings.getButton( new JButton( "Receiver" ) );
        receiver.setBackground( AppSettings.LIGHT_RED );
        receiver.addActionListener( this );

        JPanel buttonPanel = new JPanel(); // use FlowLayout
        buttonPanel.add( openButton );
        buttonPanel.add( encodeFile );
        buttonPanel.add( label );
        buttonPanel.add( gatepass );
        buttonPanel.add( publishFile );
        buttonPanel.add( receiver );

        JPanel statusPanel = new JPanel(); // use FlowLayout
        status = new JLabel();
        status.setText( "Select File" );
        statusPanel.add( status );

        // Add the buttons and the log to this panel.
        add( buttonPanel, BorderLayout.PAGE_START );
        // add( ecodedScrollPane, BorderLayout.AFTER_LAST_LINE );
        // add(statusPanel,BorderLayout.AFTER_LAST_LINE);
        add( logScrollPane, BorderLayout.CENTER );
    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed( ActionEvent e )
    {
        if ( e.getSource() == gatepass )
        {
            // if (123gatepass.getText())

        }
        if ( e.getSource() == openButton )
        {
            int returnVal = fc.showOpenDialog( PublishWindow.this );

            if ( returnVal == JFileChooser.APPROVE_OPTION )
            {
                file = fc.getSelectedFile();
                log.append( "Opening: " + file.getName() + "." + newline );
                log.append( "Size: " + BlueCode.Data.bytesToKb( file.length() ) + newline );

                status.setText( "File selected " + file.getName() );

                if ( file.length() > AppSettings.MAX_FILE_SIZE )
                {
                    log.append( "File size should not be larger than "
                        + BlueCode.Data.bytesToKb( AppSettings.MAX_FILE_SIZE ) + newline );
                    log.append( "Try again with small size files" + newline );

                    return;
                }

                encodeFile.setEnabled( true );

            }
            else
            {
                log.append( "Open command cancelled by user." + newline );
            }
            log.setCaretPosition( log.getDocument().getLength() );

        }
        else if ( e.getSource() == encodeFile )
        {
            // int returnVal = fc.showSaveDialog(FileToB64.this);
            if ( file != null )
            {
                log.append( "Encoding " + file.getName() + "." + newline );

                Runnable doConversion = new Runnable()
                {
                    public void run()
                    {
                        try
                        {

                            log.append( "Encoding... " + newline );

                            // byteArray = LoadFile.load( file );
                            byteArray = BlueCode.Data.load( file );
                            encodedString = new StringBuilder( BlueCode.encodeData( byteArray ) );

                            log.append( "Encoding done... " + newline );
                            log.append( "File size: "
                                + BlueCode.Data.bytesToKb( encodedString.toString().getBytes( "UTF-8" ).length )
                                + newline );

                            publishFile.setEnabled( true );
                        }
                        catch ( Exception e )
                        {
                            log.append( e.getMessage() + newline );
                            e.printStackTrace();
                        }
                    }
                };

                SwingUtilities.invokeLater( doConversion );
                log.append( "Encoding in process... wait" + "." + newline );

            }

            else
            {
                log.append( "Save command cancelled by user." + newline );
            }
            log.setCaretPosition( log.getDocument().getLength() );
        }

        else if ( e.getSource() == publishFile )
        {

            Runnable doConversion = new Runnable()
            {
                public void run()
                {
                    try
                    {

                        log.append( "Uploading " + file.getName() + newline );

                        if ( !"".equals( gatepass.getText() ) )
                        {
                            token = gatepass.getText();
                        }
                        else
                        {
                            token = Token.MAGIC_STRING;
                        }

                        dto = new DTO();
                        dto.setKey( token );
                        dto.setLength( encodedString.length() );
                        dto.setRaw( encodedString.toString() );
                        dto.setFilename( file.getName() );
                        dto.setExtension( null );

                        raw = new StringBuilder( YellowCode.getJString( dto ) );

                        Toolkit toolkit = Toolkit.getDefaultToolkit();
                        Clipboard clip = toolkit.getSystemClipboard();
                        StringSelection strSel = new StringSelection( raw.toString() );
                        clip.setContents( strSel, null );

                        publishFile.setEnabled( false );

                        log.append( "Data uploaded... " + newline );
                    }
                    catch ( Exception e )
                    {
                        e.printStackTrace();
                    }
                }
            };

            SwingUtilities.invokeLater( doConversion );
        }

        else if ( e.getSource() == receiver )
        {

            Runnable openDecoderNow = new Runnable()
            {
                public void run()
                {
                    try
                    {
                        ReceiverWindow.createAndShowGUI();

                        log.append( "Showing decoder... " + newline );
                    }
                    catch ( Exception e )
                    {
                        e.printStackTrace();
                    }
                }
            };

            SwingUtilities.invokeLater( openDecoderNow );
        }

    }

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked from the event dispatch thread.
     */
    private static void createAndShowGUI()
    {
        // Create and set up the window.
        JFrame frame = AppSettings.getWindow( new JFrame( "File Encoder" ) );
        frame.add( new PublishWindow() );
        frame.setVisible( true );
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
                UIManager.put( "swing.boldMetal", Boolean.FALSE );
                createAndShowGUI();
            }
        } );
    }

}
