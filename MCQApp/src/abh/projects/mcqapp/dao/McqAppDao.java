/**
 * 
 */
package abh.projects.mcqapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hsqldb.Server;

import abh.projects.mcqapp.commons.AppUtils;

/**
 * @author Abhinav
 */
public class McqAppDao
{

    public static void main( String[] args )
        throws ClassNotFoundException, SQLException
    {

        // 'Server' is a class of HSQLDB representing
        // the database server
        Server hsqlServer = null;

        // System.out.println( "OS current temporary directory is " + System.getProperty( "user.name" ) );

        AppUtils.showAll();
        if ( true )
            return;

        try
        {
            hsqlServer = new Server();

            // System.get

            // HSQLDB prints out a lot of informations when
            // starting and closing, which we don't need now.
            // Normally you should point the setLogWriter
            // to some Writer object that could store the logs.
            hsqlServer.setLogWriter( null );
            hsqlServer.setSilent( true );

            // The actual database will be named 'xdb' and its
            // settings and data will be stored in files
            // testdb.properties and testdb.script
            hsqlServer.setDatabaseName( 0, "xdb" );
            hsqlServer.setDatabasePath( 0, "file:G:\\hdb\\mcqdb" );
            // hsqlServer.setDatabasePath( 0, "mem:mymemdb" );

            // Start the database!
            hsqlServer.start();

            Connection connection = null;
            // We have here two 'try' blocks and two 'finally'
            // blocks because we have two things to close
            // after all - HSQLDB server and connection
            try
            {
                // Getting a connection to the newly started database
                Class.forName( "org.hsqldb.jdbcDriver" );
                // Default user of the HSQLDB is 'sa'
                // with an empty password
                connection = DriverManager.getConnection( "jdbc:hsqldb:hsql://localhost/xdb", "sa", "" );

                // Here we run a few SQL statements to see if
                // everything is working.
                // We first drop an existing 'testtable' (supposing
                // it was there from the previous run), create it
                // once again, insert some data and then read it
                // with SELECT query.
                // connection.prepareStatement( "drop table testtable;" ).execute();
                // connection.prepareStatement( "create table testtable ( id INTEGER, name VARCHAR(200));" ).execute();

                connection.prepareStatement( "insert into testtable(id, name) " + "values (2, 'testvalue');" ).execute();
                ResultSet rs = connection.prepareStatement( "select * from testtable;" ).executeQuery();

                // Checking if the data is correct
                while ( rs.next() )
                {
                    System.out.println( "Id: " + rs.getInt( 1 ) + " Name: " + rs.getString( 2 ) );
                }
            }
            finally
            {
                // Closing the connection
                if ( connection != null )
                {
                    System.err.println( "Closing connection" );
                    connection.close();
                }

            }
        }
        finally
        {
            // Closing the server
            if ( hsqlServer != null )
            {
                System.err.println( "Closing server" );
                hsqlServer.stop();
            }

        }
    }
}
