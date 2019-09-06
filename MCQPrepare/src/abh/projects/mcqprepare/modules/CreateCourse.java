/**
 * 
 */
package abh.projects.mcqprepare.modules;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

/**
 * @author Abhinav
 */
public class CreateCourse
    extends JPanel
{

    protected JButton btnCreate;

    protected JLabel lblCourseName, lblClass, lblSubject, lblChapters, lblReferences;

    protected JTextField txtCourseName, txtClass, txtSubject, txtChapters, txtReferences;;

    public CreateCourse()
    {
        setLayout( new MigLayout() );

        TitledBorder titledBorder = BorderFactory.createTitledBorder( "New Course" );
        titledBorder.setTitleJustification( TitledBorder.LEFT );
        setBorder( titledBorder );

    }

    protected void initComponents()
    {
        lblCourseName = new JLabel( "" );
        lblClass = new JLabel();
        lblSubject = new JLabel();
        lblChapters = new JLabel();
        lblReferences = new JLabel();
    }

}
