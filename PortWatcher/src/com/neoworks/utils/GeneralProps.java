/**
 * 
 */
package com.neoworks.utils;

import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneralProps.
 * 
 * @author Abhinav
 */
public class GeneralProps
{

    /**
     * The Class ExternalFileDetails.
     */
    public class ExternalFileDetails
    {

        /** The data file. */
        public String dataFile;

        /** The definition file. */
        public String definitionFile;

        /** The definition type. */
        public String definitionType = GeneralProps.DEFINITION_TYPE_XML;

        /** The delimitier. */
        public Character delimitier = GeneralProps.DELIMITER_COMMA;

        /** The double quotes. */
        public Boolean doubleQuotes = GeneralProps.DOUBLE_QUOTES_NO;

        /** The key index. */
        public Number keyIndex = -1;

        /** The total attributes. */
        public Number totalAttributes = -1;

    }

    /** The definition type table. */
    public static String DEFINITION_TYPE_TABLE = "table";

    /** The definition type xml. */
    public static String DEFINITION_TYPE_XML = "xml";

    /** The delimiter comma. */
    public static Character DELIMITER_COMMA = ',';

    /** The delimiter pipe. */
    public static Character DELIMITER_PIPE = '|';

    /** The double quotes. */
    public static Character DOUBLE_QUOTES = '"';

    /** The fwd slash. */
    public static String FWD_SLASH = "/";

    /** The comma. */
    public static String COMMA = ",";

    /** The delimiter tab. */
    public static String DELIMITER_TAB = "tab";

    /** The double quotes no. */
    public static Boolean DOUBLE_QUOTES_NO = false;

    /** The double quotes yes. */
    public static Boolean DOUBLE_QUOTES_YES = true;

    /**
     * Gets the data definitions.
     * 
     * @param efd the efd
     * @return the data definitions
     */
    // public List<DataItem> getDataDefinitions( ExternalFileDetails efd )
    // {
    // if ( efd.dataFile == null )
    // {
    // try
    // {
    // ValidatorException.throwError( ValidatorException.INVALID_FILE_DETAILS );
    // }
    // catch ( Exception e )
    // {
    // e.printStackTrace();
    // }
    // return null;
    // }
    //
    // if ( efd.definitionType.equals( DEFINITION_TYPE_XML ) )
    // {
    // return mockDefinition();
    // }
    //
    // return null;
    // }

    /**
     * Load csv data.
     * 
     * @param efd the efd
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void loadCSVData( ExternalFileDetails efd )
        throws IOException
    {
        CSVReader reader = new CSVReader( new FileReader( efd.dataFile ), efd.delimitier );
        String[] nextLine;

        while ( ( nextLine = reader.readNext() ) != null )
        {
            System.out.println( "Name: [" + nextLine[0] + "]\nAge: [" + nextLine[1] + "]\nCity: [" + nextLine[2] + "]" );
        }
    }

    /**
     * Mock definition.
     * 
     * @return the list
     */
    // private List<DataItem> mockDefinition()
    // {
    // List<DataItem> l = new ArrayList<DataItem>();
    //
    // DataItem d = new DataItem();
    //
    // d.setType( DataItem.TYPE_STRING );
    // d.setLength( 10 );
    // d.setName( "Name" );
    // l.add( d );
    //
    // d = new DataItem();
    // d.setType( DataItem.DEF_LENGTH_NUMBER );
    // d.setLength( 2 );
    // d.setName( "Age" );
    // l.add( d );
    //
    // d = new DataItem();
    // d.setType( DataItem.DEF_LENGTH_STRING );
    // d.setLength( 20 );
    // d.setName( "City" );
    // l.add( d );
    //
    // d = new DataItem();
    // d.setType( DataItem.DEF_LENGTH_NUMBER );
    // d.setLength( 10 );
    // d.setName( "Phone" );
    // l.add( d );
    //
    // return l;
    //
    // }

}
