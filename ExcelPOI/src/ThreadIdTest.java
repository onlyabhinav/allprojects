import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadIdTest
{

    public static void main( String[] args )
    {

        final int numThreads = 2;
        ExecutorService exec = Executors.newFixedThreadPool( numThreads );

        for ( int i = 0; i < 10; i++ )
        {
            exec.execute( new Runnable()
            {
                public void run()
                {
                    long threadId = Thread.currentThread().getId();
                    System.out.println( "I am thread " + threadId + " of " + numThreads );
                    try
                    {
                        Thread.sleep( 2000 );
                    }
                    catch ( InterruptedException e )
                    {
                        e.printStackTrace();
                    }
                }
            } );
        }

        exec.shutdown();
    }
}