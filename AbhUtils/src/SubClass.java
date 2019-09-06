/**
 * 
 */

/**
 * @author Abhinav
 */
public class SubClass
    extends BaseClass
{
    public Manager baseMethod()
    {
        return new Manager();
    }

    public String getName( String s )
    {
        return s;
    }

    public int getName( String s, String s1 )
    {
        return 1;
    }

}
