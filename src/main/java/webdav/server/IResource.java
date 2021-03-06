package webdav.server;

import java.io.File;
import java.nio.file.attribute.FileTime;
import java.util.Date;

public interface IResource
{
    /**
     * Get if the resource is a file.
     * 
     * @return boolean
     */
    public boolean isFile();
    
    /**
     * Get if the resource is a directory.
     * 
     * @return boolean
     */
    public boolean isDirectory();
    
    /**
     * Get the web name of the resource.
     * 
     * @return String
     */
    public String getWebName();
    /**
     * Get the resource name.
     * 
     * @return String
     */
    public String getName();
    /**
     * Get the mime type of the resource.
     * 
     * @return String
     */
    public String getMimeType();
    /**
     * Get the size of the resource (byte).
     * 
     * @return long
     */
    public long getSize();
    /**
     * Get the creation time.
     * 
     * @return FileTime
     */
    public FileTime getCreationTime();
    /**
     * Get the last modified date.
     * 
     * @return Date
     */
    public Date getLastModified();
    /**
     * Get the list of the resources contained in this resource.
     * 
     * @return IResource[]
     */
    public IResource[] listResources();
    /**
     * Get if the resource exists.
     * 
     * @return boolean
     */
    public boolean exists();
    
    /**
     * Get the content of the resource.
     * 
     * @return byte[]
     */
    public byte[] getContent();
    /**
     * Set the content of the resource.
     * 
     * @param content Content to put in the resource
     */
    public void setContent(byte[] content);
    
    /**
     * Delete the resource.
     * 
     * @return boolean
     */
    public boolean delete();
    /**
     * Copy a resource from the current path to 'resource' path.
     *
     * @param resource Path to copy to.
     * @return boolean
     */
    public boolean copyTo(IResource resource);

    /**
     * Rename or move a resource from the current path to 'resource' path.
     * 
     * @param resource Path to move/rename to.
     * @return boolean
     */
    public boolean renameTo(IResource resource);
    /**
     * Create the resource as a directory.
     * 
     * @return boolean
     */
    public boolean createDirectory();
    /**
     * Create the resource as a file.
     * 
     * @return boolean
     */
    public boolean createFile();

    public File getFile();
}
