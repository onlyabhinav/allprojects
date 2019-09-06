/**
 * 
 */
package neoworks.illusions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.DatatypeConverter;

/**
 * @author Abhinav Sharma
 */
public class BlueCode
{

    public static String encodeData( Object o )
        throws Exception
    {
        try
        {
            return getEncodedData( o );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            throw new Exception( e.getMessage().toString() );
        }
        // return null;
    }

    public static byte[] decodeData( Object o )
    {
        try
        {
            return getDecodedData( o );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return null;
    }

    private static String getEncodedData( Object o )
    {
        return DatatypeConverter.printBase64Binary( (byte[]) o );
    }

    private static byte[] getDecodedData( Object o )
    {
        return DatatypeConverter.parseBase64Binary( (String) o );
    }

    public static class Data
    {
        public final static byte[] load( String fileName )
        {
            try
            {
                FileInputStream fin = new FileInputStream( fileName );
                return load( fin );
            }
            catch ( Exception e )
            {
                e.printStackTrace();
                return new byte[0];
            }
        }

        public final static byte[] load( File file )
        {
            try
            {
                FileInputStream fin = new FileInputStream( file );
                return load( fin );
            }
            catch ( Exception e )
            {
                e.printStackTrace();
                return new byte[0];
            }
        }

        public final static byte[] load( FileInputStream fin )
        {
            byte readBuf[] = new byte[512 * 1024];

            try
            {
                ByteArrayOutputStream bout = new ByteArrayOutputStream();

                int readCnt = fin.read( readBuf );
                while ( 0 < readCnt )
                {
                    bout.write( readBuf, 0, readCnt );
                    readCnt = fin.read( readBuf );
                }

                fin.close();

                return bout.toByteArray();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
                return new byte[0];
            }
        }

        public static String bytesToKb( long n )
        {
            return n / ( 1024 ) + " KB";
        }

    }

}
