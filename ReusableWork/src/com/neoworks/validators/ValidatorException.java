/**
 * 
 */
package com.neoworks.validators;

// TODO: Auto-generated Javadoc
/**
 * The Class ValidatorException.
 *
 * @author Abhinav
 */

public class ValidatorException
{
    
    /** The invalid file details. */
    public static String INVALID_FILE_DETAILS = "Invalid file details.";

    /**
     * Throw error.
     *
     * @param s the s
     * @return the exception
     */
    public static Exception throwError( String s )
    {
        return new Exception( s );
    }

}
