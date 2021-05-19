package webdav.server.commands;

import http.server.HTTPCommand;
import http.server.HTTPEnvironment;
import http.server.HTTPMessage;
import webdav.server.IResource;
import webdav.server.Lock;

import java.util.*;
import java.util.stream.Stream;

public class WD_Proppatch extends HTTPCommand
{
    public WD_Proppatch()
    {
        super("proppatch");
    }

    private static Map<String/*input*/, Map<String/*param*/, String/*value*/>> WD_ProppatchProps = new HashMap<>();

    public static String getProps(IResource resource)
    {
        String out="";
        String name = resource.getFile().getPath();
        Map<String/*param*/, String/*value*/> props = WD_ProppatchProps.getOrDefault(name, null);
        if (props == null) {
            return "";
        }
        Iterator<Map.Entry<String, String>> it = props.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String, String> e = it.next();
            out+= "<D:"+e.getKey()+">" + e.getValue()+"<D:"+e.getKey()+">\r\n";
        }
        return out;
    }


    @Override
    public HTTPMessage Compute(HTTPMessage input, HTTPEnvironment environment)
    {
        IResource f = environment.createFromPath(environment.getRoot() + input.getPath().trim());
        String resource = f.getFile().getPath();

        String content = new String(input.getContent());

        String prop = content.substring(content.indexOf("<set><prop><u:") + "<set><prop><u:".length());
        prop = prop.substring(0, prop.indexOf(">"));

        String value = content.substring(content.indexOf("<set><prop><u:"+prop+">") + ("<set><prop><u:"+prop+">").length());
        value = value.substring(0, value.indexOf("</u:")).toLowerCase();




        Map<String/*param*/, String/*value*/> props = WD_ProppatchProps.getOrDefault(resource, null);
        if (props == null)
        {
            props = new HashMap<>();
            WD_ProppatchProps.put(resource, props);
        }
        props.put(prop, value);

        HTTPMessage msg = new HTTPMessage(200, "OK");

        msg.setHeader("Content-Type", "text/xml; charset=\"utf-8\"");
        return msg;
    }

}
