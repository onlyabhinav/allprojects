/**
 * 
 */
package gui.networks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.neoworks.network.Server;
import com.neoworks.utils.Commons;
import com.neoworks.utils.GeneralProps;
import com.neoworks.values.AppSettings;
import com.neoworks.values.Strings;

// TODO: Auto-generated Javadoc
/**
 * The Class PortMonitor.
 * 
 * @author Abhinav
 */
@SuppressWarnings( "serial" )
public class PortMonitor
    extends JPanel
    implements ActionListener, PropertyChangeListener
{

    /** The newline. */
    static String newline = "\n";

    /** The default file name. */
    static String DEFAULT_FILE_NAME = "connection_report.csv";

    /** The Constant COL_INDEX_STATUS. */
    static final int COL_INDEX_STATUS = 3;

    /** The Constant COL_INDEX_MESSGAE. */
    static final int COL_INDEX_MESSGAE = 4;

    /** The Constant STATUS_FAILED. */
    static final String STATUS_FAILED = "FAILED";

    /** The Constant STATUS_PASS. */
    static final String STATUS_PASS = "PASS";

    /** The Constant STATUS_READY. */
    static final String STATUS_READY = "READY";

    /** The Constant STATUS_WAITING. */
    static final String STATUS_WAITING = "WAITING";

    /** The Constant STATUS_ABORTED. */
    static final String STATUS_ABORTED = "ABORTED";

    /** The Constant STATUS_RUNNING. */
    static final String STATUS_RUNNING = "IN PROGRESS...";

    /** The btn abort. */
    JButton btnLoadFile, btnStart, btnExport, btnAbort;

    /** The icon. */
    JLabel icon;

    /** The log. */
    JTextArea error, log;

    /** The table servers. */
    JTable tableServers;

    /** The model. */
    DefaultTableModel model;

    /** The fc. */
    JFileChooser fc;

    /** The is aborted. */
    Boolean isOK = false, isAborted = false;

    /** The executor. */
    ThreadPoolExecutor executor;

    /** The progress. */
    int progress = 0;

    /** The max threads. */
    int maxThreads = 4;

    /** The num cores. */
    int numCores = -1;

    // String machineName = "No name found";

    /** The timer. */
    Timer timer;

    /** The pc_id. */
    String pc_id = "";

    /** The timer val. */
    int timerVal = -1;

    /** The drp thread count. */
    JComboBox drpThreadCount;

    /** The frame. */
    static JFrame frame;

    /** The progress bar. */
    private JProgressBar progressBar;

    /** The task. */
    Task task;

    /** The servers. */
    public static List<Server> servers;

    /** The results. */
    public String[] results;

    /**
     * Instantiates a new port monitor.
     */
    public PortMonitor()
    {
        super( new BorderLayout() );

        if ( !AppSettings.hasValidLicense() )
        {
            // removeAll();

            error = AppSettings.getLogArea( error );
            error.setBackground( Color.BLACK );
            error.setForeground( Color.RED );
            error.setText( Strings.LICENSE_EXPIRED );
            error.setFont( new Font( "Monospace", Font.TRUETYPE_FONT, 24 ) );

            add( error );

            System.err.println( Strings.LICENSE_EXPIRED );
            return;
        }

        numCores = Runtime.getRuntime().availableProcessors();
        pc_id = getMachineName();

        fc = new JFileChooser();

        btnLoadFile = AppSettings.getButton( new JButton( "1. Load" ) );
        btnStart = AppSettings.getButton( new JButton( "2. Test" ) );
        btnExport = AppSettings.getButton( new JButton( "3. Export" ) );
        btnAbort = AppSettings.getButton( new JButton( "ABORT" ) );

        btnStart.setBackground( AppSettings.LIGHT_RED );
        btnLoadFile.setBackground( AppSettings.COOL_GREEN );
        btnAbort.setBackground( AppSettings.LIGHT_RED );

        btnStart.addActionListener( this );
        btnLoadFile.addActionListener( this );
        btnAbort.addActionListener( this );
        btnExport.addActionListener( this );

        JLabel label = new JLabel( "  Progress: " );
        progressBar = new JProgressBar( 0, 100 );
        progressBar.setValue( 0 );
        progressBar.setPreferredSize( new Dimension( 200, 20 ) );
        label.setLabelFor( progressBar );

        tableServers = new JTable();
        model = new DefaultTableModel();
        tableServers.setModel( model );
        model.setColumnIdentifiers( new String[] { "S No", "Host Name", "Port", "Status", "Message" } );

        tableServers.setAutoResizeMode( JTable.AUTO_RESIZE_LAST_COLUMN );

        tableServers.getColumnModel().getColumn( 0 ).setPreferredWidth( 10 );
        tableServers.getColumnModel().getColumn( 1 ).setPreferredWidth( 200 );
        tableServers.getColumnModel().getColumn( 2 ).setPreferredWidth( 50 );
        tableServers.getColumnModel().getColumn( 3 ).setPreferredWidth( 60 );
        tableServers.getColumnModel().getColumn( 4 ).setMinWidth( 300 );
        // tableServers.setBackground( Color.CYAN );

        tableServers.setPreferredScrollableViewportSize( new Dimension( 250, 70 ) );
        tableServers.setDefaultRenderer( Object.class, new NewCellRenderer() );
        JScrollPane scrollPane = new JScrollPane( tableServers );

        drpThreadCount = AppSettings.getCombo( new JComboBox() );
        drpThreadCount.setSelectedIndex( 2 );
        JLabel threadLabel = new JLabel( "  Threads: " );
        threadLabel.setLabelFor( drpThreadCount );

        JPanel buttonPanel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
        buttonPanel.add( threadLabel );
        buttonPanel.add( drpThreadCount );
        buttonPanel.add( btnLoadFile );
        buttonPanel.add( btnStart );
        buttonPanel.add( progressBar );
        buttonPanel.add( btnExport );
        buttonPanel.add( btnAbort );

        log = AppSettings.getLogArea( new JTextArea() );
        log.setForeground( Color.GREEN );
        JScrollPane logScroller = new JScrollPane( log );

        log.append( "Machine Name: " + getMachineName() + newline );
        log.append( "Available cores: " + numCores + newline );
        log.append( "Default Threads: " + drpThreadCount.getSelectedItem() + newline );

        btnStart.setEnabled( false );
        btnExport.setEnabled( false );
        btnAbort.setEnabled( false );

        add( buttonPanel, BorderLayout.NORTH );
        add( scrollPane, BorderLayout.CENTER );
        add( logScroller, BorderLayout.SOUTH );

        setBackground( Color.LIGHT_GRAY );

        setFocusable( true );
    }

    /**
     * Start.
     */
    public static void start()
    {
        Runnable showUI = new Runnable()
        {
            // @Override
            public void run()
            {
                // frame = new JFrame( Strings.TITLE_LOCKED );
                frame = AppSettings.getWindow( new JFrame( Strings.TITLE_LOCKED ) );
                frame.add( new PortMonitor() );
                frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

                frame.setAlwaysOnTop( false );

                Toolkit tk = Toolkit.getDefaultToolkit();
                int xSize = ( (int) tk.getScreenSize().getWidth() );
                int ySize = ( (int) tk.getScreenSize().getHeight() );
                frame.setSize( xSize - 50, ySize - 50 );

                frame.setLocation( 25, 10 );
                frame.setVisible( true );
            }
        };

        SwingUtilities.invokeLater( showUI );

    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed( ActionEvent e )
    {
        if ( e.getSource() == btnLoadFile )
        {

            int returnVal = fc.showOpenDialog( this );

            if ( returnVal == JFileChooser.APPROVE_OPTION )
            {
                ArrayList<String[]> serversList = (ArrayList<String[]>) Commons.readFromCSVFile( fc.getSelectedFile() );

                System.out.println( "File has " + serversList.size() + " records" );

                loadData( serversList );

                log.append( "Total test count: " + servers.size() + newline );

                updateTableData();

                setAllStatus( STATUS_READY );

                btnStart.setEnabled( true );

            }

        }
        else if ( e.getSource() == btnStart )
        {
            maxThreads = Integer.parseInt( drpThreadCount.getSelectedItem().toString() );

            log.append( "______________" + newline );
            log.append( "Num of threads: " + maxThreads + newline );
            startTime();

            setAllStatus( STATUS_WAITING );

            // model.removeRow( 0 );
            btnStart.setEnabled( false );
            btnLoadFile.setEnabled( false );
            btnAbort.setEnabled( true );
            progressBar.setIndeterminate( true );
            progressBar.setStringPainted( true );
            progressBar.setValue( 0 );
            progress = 0;
            // Instances of javax.swing.SwingWorker are not reusuable, so
            // we create new instances as needed.
            task = new Task();
            task.addPropertyChangeListener( this );
            task.execute();

        }
        else if ( e.getSource() == btnAbort )
        {

            // model.removeRow( 0 );
            btnStart.setEnabled( true );
            btnLoadFile.setEnabled( true );
            btnAbort.setEnabled( false );

            if ( executor != null )
                executor.shutdownNow();

            log.append( "Aborting..." + newline );
            isAborted = true;
            timer.stop();

        }
        else if ( e.getSource() == btnExport )
        {
            // model.removeRow( 0 );
            fc.setName( pc_id + "_" + DEFAULT_FILE_NAME );
            fc.setSelectedFile( new File( pc_id + "_" + DEFAULT_FILE_NAME ) );
            int returnVal = fc.showSaveDialog( this );

            if ( returnVal == JFileChooser.APPROVE_OPTION )
            {
                Commons.saveToCSVFile( getTableData(), fc.getSelectedFile() );
            }

        }
    }

    /**
     * Start time.
     */
    public void startTime()
    {
        int delay = 1000; // milliseconds
        ActionListener taskPerformer = new ActionListener()
        {
            public void actionPerformed( ActionEvent evt )
            {
                timerVal++;
                log.append( "-" );
            }
        };
        timer = new Timer( delay, taskPerformer );// .start();
        log.append( "Timeline: " );
        timer.start();
    }

    /**
     * Gets the machine name.
     * 
     * @return the machine name
     */
    public String getMachineName()
    // throws UnknownHostException
    {
        try
        {
            return InetAddress.getLocalHost().getHostName();
        }
        catch ( UnknownHostException e )
        {
            // TODO Auto-generated catch block
            log.append( "No machine name" + newline );
        }
        return "No name";
    }

    /**
     * Sets the state.
     * 
     * @param isOK the new state
     */
    public void setState( Boolean isOK )
    {
        btnLoadFile.setEnabled( isOK );
        btnStart.setEnabled( isOK );
        // icon.setEnabled( isOK );
        if ( isOK )
        {
            setBackground( Color.WHITE );
        }
    }

    /**
     * The Class Task.
     */
    class Task
        extends SwingWorker<Void, Void>
    {

        /*
         * (non-Javadoc)
         * @see javax.swing.SwingWorker#doInBackground()
         */
        @Override
        public Void doInBackground()
        {
            setProgress( 0 );

            executor =
                new ThreadPoolExecutor( maxThreads, maxThreads, 1, TimeUnit.MINUTES,
                                        new ArrayBlockingQueue<Runnable>( maxThreads, true ),
                                        new ThreadPoolExecutor.CallerRunsPolicy() );

            for ( final Server s : servers )
            {
                System.err.println( s.getHost() );

                executor.submit( new Runnable()
                {

                    public void run()
                    {
                        // tableServers.setValueAt( "connecting...", s.getServerIndex(), 2 );
                        int prog = Math.round( ( (float) progress++ / (float) servers.size() ) * 100 );
                        setProgress( Math.min( prog, 100 ) );

                        ping( s );
                    }

                } );

                System.out.println( "Completed tasks: " + executor.getActiveCount() );
            }

            executor.shutdown();

            if ( isAborted )
            {
                log.append( "Aborted..." + newline );
                return null;
            }

            try
            {
                if ( !executor.awaitTermination( 60, TimeUnit.SECONDS ) )
                {
                    System.err.println( "1st Try..." );
                    executor.shutdownNow();
                }
            }
            catch ( InterruptedException ex )
            {
                System.err.println( "Final Try..." );
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }

            return null;
        }

        /*
         * (non-Javadoc)
         * @see javax.swing.SwingWorker#done()
         */
        @Override
        public void done()
        {
            System.out.println( "Done..." );
            setProgress( 100 );

            if ( isAborted )
            {
                setAllStatus( STATUS_ABORTED );
            }

            Toolkit.getDefaultToolkit().beep();
            btnStart.setEnabled( true );
            btnLoadFile.setEnabled( true );
            btnExport.setEnabled( true );
            btnAbort.setEnabled( false );
            isAborted = false;

            timer.stop();
            log.append( timerVal + " secs. Done!!" + newline );
            timerVal = -1;
        }
    }

    /**
     * Load data.
     * 
     * @param serversList the servers list
     */
    public void loadData( ArrayList<String[]> serversList )
    {
        servers = new ArrayList<Server>();

        int serverIndex = -1;

        Server s;// = new Server();
        // s.setHost( "www.google.com" );
        // s.setPorts( new ArrayList<Number>( Arrays.asList( 23, 24 ) ) );

        for ( String[] serverDetail : serversList )
        {

            String[] host = {}, port = {};

            int commaIndex = serverDetail[0].indexOf( GeneralProps.FWD_SLASH );

            if ( serverDetail[0].indexOf( GeneralProps.FWD_SLASH ) >= 0 )
            {
                host = serverDetail[0].split( GeneralProps.FWD_SLASH );
            }
            else
            {
                host = new String[1];
                host[0] = serverDetail[0];
            }

            if ( serverDetail[1].indexOf( GeneralProps.COMMA ) >= 0 )
            {
                port = serverDetail[1].split( GeneralProps.COMMA );
            }
            else
            {
                port = new String[1];
                port[0] = serverDetail[1];
            }

            for ( String h : host )
            {
                for ( String p : port )
                {
                    try
                    {
                        System.err.format( "HOST: %10s, PORT: %10s\n", h, p );
                        s = new Server();
                        s.setServerIndex( serverIndex++ );
                        s.setHost( h.trim() );
                        s.setPort( Integer.parseInt( p.trim() ) );
                        servers.add( s );
                    }
                    catch ( Exception e )
                    {
                        log.append( "WARNING: Index " + serverIndex + " Skipping " + h + ":" + p + ", Reason: "
                            + e.getLocalizedMessage() + newline );
                        e.printStackTrace();
                    }

                }
            }

        }

        System.err.println( "Ready to start tests on " + servers.size() + " connections" );

        for ( Server si : servers )
        {
            System.err.format( "Host is:\t%s\tPort is:\t%d\n", si.getHost(), si.getPort() );

        }

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

        executor.shutdown();

        try
        {
            if ( !executor.awaitTermination( 60, TimeUnit.SECONDS ) )
            {
                System.err.println( "1st Try..." );
                executor.shutdownNow();
            }

        }
        catch ( InterruptedException ex )
        {
            System.err.println( "Final Try..." );
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

    }

    /**
     * Update table data.
     */
    public void updateTableData()
    {
        if ( tableServers.getModel().getRowCount() > 1 )
        {
            DefaultTableModel tempModel = (DefaultTableModel) tableServers.getModel();
            log.append( "Cleaning current list: " + tempModel.getRowCount() + newline );
            for ( int i = tempModel.getRowCount() - 1; i >= 0; i-- )
            {
                tempModel.removeRow( i );
            }
        }

        for ( Server s : servers )
        {
            model.addRow( new String[] { Integer.toString( s.getServerIndex() + 1 ), s.getHost(),
                Integer.toString( s.getPort() ), s.getStatusBoolean() ? "idle..." : "waiting...", s.getErrorString() } );

        }
        // model.addRow( new )

    }

    /**
     * Sets the all status.
     * 
     * @param status the new all status
     */
    public void setAllStatus( String status )
    {
        if ( tableServers.getModel().getRowCount() > 1 )
        {
            DefaultTableModel tempModel = (DefaultTableModel) tableServers.getModel();
            for ( int i = tempModel.getRowCount() - 1; i >= 0; i-- )
            {
                tempModel.setValueAt( status, i, COL_INDEX_STATUS );
            }
        }
    }

    /**
     * Gets the table data.
     * 
     * @return the table data
     */
    public String[] getTableData()
    {
        DefaultTableModel tempModel = (DefaultTableModel) tableServers.getModel();

        if ( tempModel.getRowCount() > 1 )
        {
            log.append( "Storing results." + tempModel.getRowCount() + newline );

            int colCount = tempModel.getColumnCount();
            int rowCount = tempModel.getRowCount();
            String[] res = new String[rowCount + 1];

            String tmpData = "";

            for ( int i = 0; i < colCount; i++ )
            {
                tmpData +=
                    "".equals( tmpData ) ? tempModel.getColumnName( i ) : GeneralProps.COMMA
                        + tempModel.getColumnName( i );
            }

            res[0] = tmpData;

            for ( int i = 1; i <= rowCount; i++ )
            {
                tmpData = "";
                for ( int j = 0; j < colCount; j++ )
                {
                    tmpData +=
                        "".equals( tmpData ) ? tempModel.getValueAt( i - 1, j ) : GeneralProps.COMMA
                            + tempModel.getValueAt( i - 1, j );
                    // tempModel.getValueAt( i - 1, j ) + GeneralProps.COMMA;
                }
                res[i] = tmpData;
            }

            log.append( "Total rows: " + res.length );

            return res;
        }

        return null;

    }

    /**
     * Ping.
     * 
     * @param s the s
     */
    public void ping( Server s )
    {
        InetAddress ip;
        Socket Skt;

        String host = s.getHost();
        int port = s.getPort();

        tableServers.setValueAt( STATUS_RUNNING, s.getServerIndex(), COL_INDEX_STATUS );

        try
        {
            ip = InetAddress.getByName( host );

            s.setHost( host );
            s.setIpString( ip.getHostAddress() );
            s.setPort( port );

            // System.out.println( "IP address is " + ip.getHostAddress() );
            System.out.format( "Connecting %10s %10d\n", host, port );
            Skt = new Socket( host, port );
            // ( host, port );

            s.setStatusBoolean( true );
            s.setErrorString( "PASS" );

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

        tableServers.setValueAt( s.getStatusBoolean() ? STATUS_PASS : STATUS_FAILED, s.getServerIndex(), 3 );
        tableServers.setValueAt( s.getErrorString(), s.getServerIndex(), COL_INDEX_MESSGAE );

    }

    /*
     * (non-Javadoc)
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */

    public void propertyChange( PropertyChangeEvent evt )
    {
        if ( "progress" == evt.getPropertyName() )
        {
            int progress = (Integer) evt.getNewValue();
            progressBar.setIndeterminate( false );
            progressBar.setValue( progress );
        }
    }

}
