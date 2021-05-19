import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import http.server.HTTPCommand;
import http.server.HTTPServer;
import http.server.HTTPServerSettings;
import webdav.server.standard.StandardResourceManager;

public class Main {

    public static void main(String[] args) {
        HTTPServerSettings settings = new HTTPServerSettings(
                "WebDAV Server",
                HTTPCommand.getStandardCommands(),
                StandardResourceManager.class,
                "/test"
        );

        HTTPServer s = new HTTPServer(1700, settings);
        s.run(); // Run the server

    }
}
