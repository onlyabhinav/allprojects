/**
 * 
 */
package com.neoworks.transform;

/**
 * @author Abhinav
 */
public class DTO
{
    private String raw;

    private String key;

    private String filename;

    private String extension;

    /**
     * @return the filename
     */
    public String getFilename()
    {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename( String filename )
    {
        this.filename = filename;
    }

    /**
     * @return the extension
     */
    public String getExtension()
    {
        return extension;
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension( String extension )
    {
        this.extension = extension;
    }

    private int length;

    /**
     * @return the raw
     */
    public String getRaw()
    {
        return raw;
    }

    /**
     * @param raw the raw to set
     */
    public void setRaw( String raw )
    {
        this.raw = raw;
    }

    /**
     * @return the key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey( String key )
    {
        this.key = key;
    }

    /**
     * @return the length
     */
    public int getLength()
    {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength( int length )
    {
        this.length = length;
    }

}
