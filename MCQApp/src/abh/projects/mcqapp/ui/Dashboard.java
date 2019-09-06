/**
 * 
 */
package abh.projects.mcqapp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import abh.projects.mcqapp.commons.CommonGUITasks;
import abh.projects.mcqapp.props.AppColors;
import abh.projects.mcqapp.props.AppProperties;

/**
 * @author Abhinav
 */
public class Dashboard
    extends JPanel
    implements ActionListener, KeyListener
{

    static JFrame frame;

    /** The decoder window. */
    JButton btnStartTest, btnViewCourse;

    /** The icon. */
    JLabel icon;

    /** The error. */
    JTextArea error;

    /** The is ok. */
    Boolean isOK = false;

    /** The key. */
    private String key = "";

    public static void start()
    {
        Runnable showUI = new Runnable()
        {
            public void run()
            {
                frame = CommonGUITasks.getWindow( AppProperties.APP_NAME, AppProperties.DEFAULT_DIMENSION );
                frame.add( new Dashboard() );
                frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

                // frame.setExtendedState( Frame.MAXIMIZED_BOTH );
                Toolkit tk = Toolkit.getDefaultToolkit();
                int xSize = ( (int) tk.getScreenSize().getWidth() );
                int ySize = ( (int) tk.getScreenSize().getHeight() );
                // frame.setSize( xSize - 50, ySize - 50 );
                frame.setSize( xSize, ySize );

                // frame.setLocation(25, 10 );
                frame.setLocation( 0, 0 );

                frame.setVisible( true );
            }
        };

        SwingUtilities.invokeLater( showUI );

    }

    public Dashboard()
    {
        super( new BorderLayout() );

        btnStartTest = new JButton( "Start Test" );
        btnViewCourse = new JButton( "Veiw Course" );

        btnViewCourse.setBackground( AppColors.LIGHT_RED );
        btnStartTest.setBackground( AppColors.COOL_GREEN );

        btnViewCourse.addActionListener( this );
        btnStartTest.addActionListener( this );

        icon = new JLabel( createImageIcon( AppProperties.ICON_LOCKED ) );

        JPanel buttonPanel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
        buttonPanel.add( btnStartTest );
        buttonPanel.add( btnViewCourse );

        add( buttonPanel, BorderLayout.PAGE_START );
        add( icon, BorderLayout.CENTER );

        setBackground( Color.WHITE );

        addKeyListener( this );

        setFocusable( true );
    }

    // ---------------------------------------
    // Interface methods implementations
    // ---------------------------------------

    public void keyTyped( KeyEvent e )
    {
    }

    public void keyPressed( KeyEvent e )
    {
    }

    public void keyReleased( KeyEvent e )
    {
    }

    public void actionPerformed( ActionEvent e )
    {
        if ( e.getSource() == btnStartTest )
        {
            icon.setIcon( createImageIcon( AppProperties.ICON_UNLOCKED ) );
        }
        else if ( e.getSource() == btnViewCourse )
        {
            icon.setIcon( createImageIcon( AppProperties.ICON_LOCKED ) );
        }
    }

    public ImageIcon createImageIcon( String path )
    {
        URL imgURL = Dashboard.class.getResource( path );

        if ( imgURL != null )
        {
            return new ImageIcon( imgURL );
        }
        else
        {
            System.err.println( "Couldn't find file: " + path );
            return null;
        }
    }

}
