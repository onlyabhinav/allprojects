/**
 * 
 */
package com.neoworks.validators;

// TODO: Auto-generated Javadoc
/**
 * The Class DataItem.
 * 
 * @author Abhinav
 */
public class DataItem
{

    /** The def length number. */
    public static Number DEF_LENGTH_NUMBER = 100;

    /** The def length string. */
    public static Number DEF_LENGTH_STRING = 256;

    /** The is null. */
    public static Boolean IS_NULL = false;

    /** The type number. */
    public static Number TYPE_NUMBER = 2;

    /** The type string. */
    public static Number TYPE_STRING = 1;

    /** The index. */
    private Number index = -1;

    /** The is key. */
    private Boolean isKey = false;

    /** The length. */
    private Number length;

    /** The name. */
    private String name;

    /** The type. */
    private Number type;

    /** The value. */
    private String value = null;

    /**
     * Gets the index.
     * 
     * @return the index
     */
    public Number getIndex()
    {
        return index;
    }

    /**
     * Gets the checks if is key.
     * 
     * @return the isKey
     */
    public Boolean getIsKey()
    {
        return isKey;
    }

    /**
     * Gets the length.
     * 
     * @return the length
     */
    public Number getLength()
    {
        return length;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the type.
     * 
     * @return the type
     */
    public Number getType()
    {
        return type;
    }

    /**
     * Gets the value.
     * 
     * @return the value
     */
    public String getValue()
    {
        return value;
    }

    /**
     * Sets the index.
     * 
     * @param index the index to set
     */
    public void setIndex( Number index )
    {
        this.index = index;
    }

    /**
     * Sets the checks if is key.
     * 
     * @param isKey the isKey to set
     */
    public void setIsKey( Boolean isKey )
    {
        this.isKey = isKey;
    }

    /**
     * Sets the length.
     * 
     * @param length the length to set
     */
    public void setLength( Number length )
    {
        this.length = length;
    }

    /**
     * Sets the name.
     * 
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Sets the type.
     * 
     * @param type the type to set
     */
    public void setType( Number type )
    {
        this.type = type;
    }

    /**
     * Sets the value.
     * 
     * @param value the value to set
     */
    public void setValue( String value )
    {
        this.value = value;
    }

}
