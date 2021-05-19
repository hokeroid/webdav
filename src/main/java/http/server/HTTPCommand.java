package http.server;

import java.util.Set;

import webdav.server.commands.*;

public abstract class HTTPCommand
{
    public HTTPCommand(String command)
    {
        this.name = command.trim().toUpperCase();
    }
    
    private final String name;
    
    /**
     * Get the name of the command.
     * 
     * @return String
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Execute the command with the HTTPMessage 'input' and in the 'environment'.
     * 
     * @param input Received HTTP message
     * @param environment Server environment
     * @return HTTPMessage
     */
    public abstract HTTPMessage Compute(HTTPMessage input, HTTPEnvironment environment);
    
    /**
     * Find a HTTPCommand in 'commands' with the command name 'command'.
     * If no HTTPCommand found, returns null.
     * 
     * @param commands Set of HTTPCommand to search in.
     * @param command Command name to find.
     * @return HTTPCommand
     */
    public static HTTPCommand getFrom(Set<HTTPCommand> commands, String command)
    {
        return commands.stream()
                .filter(c -> c.name.equals(command.trim().toUpperCase()))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Generate the command list with : OPTIONS, PROPFIND, PROPPATCH, MKCOL,
     * HEAD, GET, PUT, DELETE, LOCK, UNLOCK and MOVE.
     * 
     * @return HTTPCommand[]
     */
    public static HTTPCommand[] getStandardCommands()
    {
        return new HTTPCommand[]
        {
            new WD_Propfind(),
            new WD_Proppatch(),
            new WD_Mkcol(),
            new WD_Get(),
            new WD_Lock(),
            new WD_Unlock(),
            new WD_Move(),
            new WD_Copy()
        };
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof HTTPCommand && this.getName().equals(((HTTPCommand)obj).getName());
    }

    @Override
    public String toString()
    {
        return name;
    }
}
