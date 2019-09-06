import java.util.ArrayList;

/**
 * 
 */

/**
 * @author abhinav
 */
public class PersonDO
{
    private String name;

    private String city;

    private String phone;

    public static ArrayList<PersonDO> getPersons( Number n )
    {
        ArrayList<PersonDO> list = new ArrayList<PersonDO>();

        for ( int i = 0; i <= n.intValue(); i++ )
        {
            PersonDO p = new PersonDO();

            p.setName( "abhinav " + i );
            p.setCity( "pune " + i );
            p.setPhone( "phone " + i );

            list.add( p );

        }

        return list;

    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity( String city )
    {
        this.city = city;
    }

    /**
     * @return the phone
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone( String phone )
    {
        this.phone = phone;
    }

}
