/**
 * 
 */
package neoworks.illusions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Abhinav
 */
public class YellowCode
{

    public static final String getJString( Object o )
    {

        Gson g = new Gson();
        try
        {
            return g.toJson( o );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }

    public static final Object getJObject( String raw, Object obj )
    {

        // System.err.println( "Class type: " + obj.getClass() );

        Gson g = new GsonBuilder().create();
        try
        {
            obj = g.fromJson( raw, obj.getClass() );
            return obj;
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }
}
