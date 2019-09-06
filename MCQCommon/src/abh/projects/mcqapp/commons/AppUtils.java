/*
 * Abhinav Sharma
 */
package abh.projects.mcqapp.commons;

/**
 * The Class AppUtils.
 */
public class AppUtils
{

    /**
     * Instantiates a new app utils.
     */
    private AppUtils()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Show all.
     */
    public static void showAll()
    {
        printProperty( "java.home" );
        printProperty( "java.vendor" );
        printProperty( "java.vendor.url" );
        printProperty( "java.version" );
        printProperty( "line.separator" );
        printProperty( "os.arch" );
        printProperty( "os.name" );
        printProperty( "os.version" );
        printProperty( "path.separator" );
        printProperty( "user.dir" );
        printProperty( "user.home" );
        printProperty( "user.name" );
    }

    /**
     * User home.
     * 
     * @return the string
     */
    public static String userHome()
    {
        return System.getProperty( "user.home" );
    }

    /**
     * File separator.
     * 
     * @return the string
     */
    public static String fileSeparator()
    {
        return System.getProperty( "file.separator" );
    }

    /**
     * Gets the property.
     * 
     * @param prop the prop
     * @return the property
     */
    public static String getProperty( String prop )
    {
        return System.getProperty( prop );
    }

    /**
     * Prints the property.
     * 
     * @param prop the prop
     */
    private static void printProperty( String prop )
    {
        System.out.println( prop + ":: '" + System.getProperty( prop ) + "'" );

    }

}
