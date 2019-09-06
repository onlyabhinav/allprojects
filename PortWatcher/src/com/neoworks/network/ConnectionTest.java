/**
 * 
 */
package com.neoworks.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionTest.
 * 
 * @author Abhinav
 */
public class ConnectionTest
{

    /** The servers. */
    public static List<Server> servers;

    /** The results. */
    public static List<ConTest> results;

    /**
     * Load data.
     * 
     * @param filename the filename
     */
    public static void loadData( String filename )
    {
        servers = new ArrayList<Server>();
        results = new ArrayList<ConTest>();

        // Server s = new Server();
        // s.setHost( "www.google.com" );
        // s.setPorts( new ArrayList<Number>( Arrays.asList( 23, 24, 54, 65, 47 ) ) );
        //
        // servers.add( s );
        //
        // s = new Server();
        // s.setHost( "www.yahoo.com" );
        // s.setPorts( new ArrayList<Number>( Arrays.asList( 23, 24, 54, 765, 43, 76, 34, 99, 223 ) ) );
        //
        // servers.add( s );
        //
        // s = new Server();
        // s.setHost( "www.tcs.com" );
        // s.setPorts( new ArrayList<Number>( Arrays.asList( 23, 45, 78, 11, 89, 24, 54 ) ) );
        //
        // servers.add( s );

    }

    /**
     * Start test.
     */
    public static void startTest()
    {
        if ( servers == null )
        {
            System.err.println( "Servers not loaded yet" );
            return;
        }

        int maxThreads = 4;

        ExecutorService executor =
            new ThreadPoolExecutor( maxThreads, maxThreads, 1, TimeUnit.MINUTES,
                                    new ArrayBlockingQueue<Runnable>( maxThreads, true ),
                                    new ThreadPoolExecutor.CallerRunsPolicy() );

        for ( Server s : servers )
        {
            System.out.println( s.getHost() );

            // for ( Number p : s.getPorts() )
            // {
            // final String h = s.getHost();
            // final int prt = p.intValue();
            // executor.submit( new Runnable()
            // {
            // @Override
            // public void run()
            // {
            // ping( h, prt );
            // }
            // } );
            //
            // }
        }

        executor.shutdown();

        try
        {
            if ( !executor.awaitTermination( 60, TimeUnit.SECONDS ) )
            {
                // pool didn't terminate after the first try
                System.err.println( "1st Try..." );
                executor.shutdownNow();
            }

            if ( !executor.awaitTermination( 60, TimeUnit.SECONDS ) )
            {
                // pool didn't terminate after the second try
                System.err.println( "2nd Try..." );
            }
        }
        catch ( InterruptedException ex )
        {
            System.err.println( "Final Try..." );
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.err.println( "Process completed... Results below. Total tests: " + results.size() + "\n" );

        for ( ConTest s : results )
        {

            System.out.format( "\n%10s%32d%32s%16s", s.getHost(), s.getPort(), s.getStatusBoolean(), s.getErrorString() );
        }

    }

    /**
     * Ping.
     * 
     * @param host the host
     * @param port the port
     */
    public static void ping( String host, int port )
    {
        InetAddress ip;
        Socket Skt;

        ConTest s = new ConTest();

        try
        {
            ip = InetAddress.getByName( host );

            s.setHost( host );
            s.setIpString( ip.getHostAddress() );
            s.setPort( port );

            // System.out.println( "IP address is " + ip.getHostAddress() );
            System.out.format( "Connecting %10s %10d\n", host, port );
            Skt = new Socket( host, port );

            s.setStatusBoolean( true );

            // System.out.println( "There is a server on port " + port + " of " + host );
        }
        catch ( UnknownHostException e )
        {
            // System.out.println( "Exception occured" + e );
            s.setStatusBoolean( false );
            s.setErrorString( e.getMessage() );
            // break;
        }
        catch ( IOException e )
        {
            // System.out.println( "Exception occured" + e );
            s.setStatusBoolean( false );
            s.setErrorString( e.getMessage() );
        }
        catch ( Exception e )
        {
            // System.out.println( "Other Exception" + e );
            s.setStatusBoolean( false );
            s.setErrorString( e.getMessage() );
        }

        results.add( s );

    }
}
