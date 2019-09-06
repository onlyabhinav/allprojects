/**
 * 
 */
package com.neoworks.values;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class AppSettings.
 */
public class AppSettings
{

    /** The Constant MAX_FILE_SIZE. */
    public static final long MAX_FILE_SIZE = 50 * 1024 * 1024;

    /** The Constant DEBUG_MODE. */
    public static final Boolean DEBUG_MODE = true;

    /** The Constant DEFAULT_DIMENSION. */
    public static final Dimension DEFAULT_DIMENSION = new Dimension( 600, 300 );

    /** The light red. */
    public static Color LIGHT_RED = new Color( 0xFFCCFF );

    /** The dark red. */
    public static Color DARK_RED = new Color( 0xFF4B3A );

    /** The light green. */
    public static Color LIGHT_GREEN = new Color( 0x11FF60 );

    /** The cool green. */
    public static Color COOL_GREEN = new Color( 0xCCFFFF );

    /** The light yellow. */
    public static Color LIGHT_YELLOW = new Color( 0xFFCB3D );

    /** The light blue. */
    public static Color LIGHT_BLUE = new Color( 0x66A8FF );

    /**
     * Gets the log area.
     * 
     * @param ta the ta
     * @return the log area
     */
    public static JTextArea getLogArea( JTextArea ta )
    {
        ta = new JTextArea( 10, 50 );
        ta.setMargin( new Insets( 5, 5, 5, 5 ) );
        ta.setEditable( false );
        ta.setFont( new Font( "Monospaced", Font.TRUETYPE_FONT, 12 ) );
        ta.setBackground( Color.black );
        ta.setForeground( Color.LIGHT_GRAY );
        ta.setLineWrap( true );

        return ta;
    }

    /**
     * Gets the password field.
     * 
     * @param passwordField the password field
     * @return the password field
     */
    public static JPasswordField getPasswordField( JPasswordField passwordField )
    {
        passwordField.setPreferredSize( new Dimension( 80, 25 ) );
        passwordField.setMargin( new Insets( 2, 5, 2, 5 ) );
        passwordField.setBackground( Color.YELLOW );

        if ( DEBUG_MODE )
            passwordField.setText( "1234" );

        return passwordField;
    }

    /**
     * Gets the button.
     * 
     * @param newButton the new button
     * @return the button
     */
    public static JButton getButton( JButton newButton )
    {
        newButton.setPreferredSize( new Dimension( 90, 25 ) );
        newButton.setMargin( new Insets( 2, 5, 2, 5 ) );
        newButton.setBackground( COOL_GREEN );

        return newButton;
    }

    /**
     * Gets the window.
     * 
     * @param window the window
     * @return the window
     */
    public static JFrame getWindow( JFrame window )
    {
        window.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        window.setSize( DEFAULT_DIMENSION );
        window.setResizable( false );

        window.setAlwaysOnTop( true );

        window.setLocationRelativeTo( null );

        return window;
    }

    /**
     * Gets the combo.
     * 
     * @param combo the combo
     * @return the combo
     */
    public static JComboBox getCombo( JComboBox combo )
    {

        for ( int i = 1; i < 75; i = i + 4 )
        {
            combo.addItem( i );
        }

        combo.setEditable( false );

        return combo;
    }

    /**
     * Checks for valid license.
     * 
     * @return the boolean
     */
    public static Boolean hasValidLicense()
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" );
            Date lastDate = sdf.parse( "15-02-2013" );

            return new Date().before( lastDate );
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
        }

        return false;
    }

}
