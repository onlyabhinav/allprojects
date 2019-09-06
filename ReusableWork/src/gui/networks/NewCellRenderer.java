/**
 * 
 */
package gui.networks;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.neoworks.values.AppSettings;

// TODO: Auto-generated Javadoc
/**
 * The Class NewCellRenderer.
 * 
 * @author Abhinav
 */
public class NewCellRenderer
    extends DefaultTableCellRenderer
{

    /*
     * (non-Javadoc)
     * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable,
     * java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                    int row, int column )
    {
        Component c = super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );

        if ( !table.isRowSelected( row ) )
        {
            // System.err.println( "COL:: " + column + ",Value:: " + value.toString() );

            if ( column == PortMonitor.COL_INDEX_STATUS && value.toString().equals( PortMonitor.STATUS_PASS ) )
                c.setBackground( AppSettings.LIGHT_GREEN );
            else if ( column == PortMonitor.COL_INDEX_STATUS && value.toString().equals( PortMonitor.STATUS_FAILED ) )
                c.setBackground( AppSettings.LIGHT_RED );
            else if ( column == PortMonitor.COL_INDEX_STATUS && value.toString().equals( PortMonitor.STATUS_RUNNING ) )
                c.setBackground( AppSettings.LIGHT_BLUE );
            else if ( column == PortMonitor.COL_INDEX_STATUS && value.toString().equals( PortMonitor.STATUS_WAITING ) )
                c.setBackground( AppSettings.LIGHT_YELLOW );
            else if ( column == PortMonitor.COL_INDEX_STATUS && value.toString().equals( PortMonitor.STATUS_ABORTED ) )
                c.setBackground( AppSettings.DARK_RED );
            else
                c.setBackground( table.getBackground() );
        }
        return c;
    }

}
