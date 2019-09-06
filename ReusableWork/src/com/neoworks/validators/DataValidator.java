/**
 * 
 */
package com.neoworks.validators;

import java.util.ArrayList;
import java.util.List;

import com.neoworks.utils.GeneralProps;

// TODO: Auto-generated Javadoc
/**
 * The Class DataValidator.
 * 
 * @author Abhinav
 */
public class DataValidator
{

    /**
     * The main method.
     * 
     * @param a the arguments
     */
    public static void main( String a[] )
    {
        try
        {

            List<DataItem> dataDefinitions = new ArrayList<DataItem>();

            GeneralProps ed = new GeneralProps();

            GeneralProps.ExternalFileDetails efd = ed.new ExternalFileDetails();

            efd.dataFile = "G:\\workspace\\allprojects\\JavaPOC\\src\\address.csv";
            efd.definitionFile = "address.ddef.xml";
            efd.keyIndex = 1;
            efd.totalAttributes = 4;
            efd.doubleQuotes = GeneralProps.DOUBLE_QUOTES_YES;
            efd.delimitier = GeneralProps.DELIMITER_PIPE;

            ed.getDataDefinitions( efd );

            ed.loadCSVData( efd );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

    }

    /**
     * Check data.
     * 
     * @param d the d
     * @return the boolean
     */
    protected Boolean checkData( DataItem d )
    {

        return true;
    }

}
