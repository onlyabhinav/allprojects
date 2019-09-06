/**
 * 
 */
package com.neoworks.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.neoworks.transform.ui.PublishWindow;

// TODO: Auto-generated Javadoc
/**
 * The Class Commons.
 * 
 * @author Abhinav
 */
public class Commons
{

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     * 
     * @param path the path
     * @return the image icon
     */
    public static ImageIcon createImageIcon( String path )
    {
        java.net.URL imgURL = PublishWindow.class.getResource( path );
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

    /**
     * Save to csv file.
     * 
     * @param results the results
     * @param file the file
     * @return the boolean
     */
    public static Boolean saveToCSVFile( String[] results, File file )
    {
        try
        {
            CSVWriter writer = new CSVWriter( new FileWriter( file ), GeneralProps.DELIMITER_COMMA );

            for ( String row : results )
            {
                String[] items = row.split( GeneralProps.COMMA );
                writer.writeNext( items );
            }
            writer.close();

            return true;

        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Read from csv file.
     * 
     * @param csvFile the csv file
     * @return the list
     */
    public static List<String[]> readFromCSVFile( File csvFile )
    {
        List<String[]> csvData = new ArrayList<String[]>();
        try
        {
            CSVReader reader =
                new CSVReader( new FileReader( csvFile ), GeneralProps.DELIMITER_COMMA, GeneralProps.DOUBLE_QUOTES );

            String[] nextLine;
            while ( ( nextLine = reader.readNext() ) != null )
            {
                if ( nextLine != null )
                {
                    csvData.add( nextLine );
                }
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();

            return null;
        }
        return csvData;
    }
}
