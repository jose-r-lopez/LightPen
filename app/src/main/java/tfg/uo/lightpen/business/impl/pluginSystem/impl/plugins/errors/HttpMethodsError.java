package tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors;


import java.net.URL;

/**
 * Created by Iñigo Llaneza Aller on 2/5/17.
 */

public class HttpMethodsError extends Error{

    private final static Double version = 0.001;
    private URL url;
    public HttpMethodsError(URL url, String description, Double risk){
        super( description , version, risk);
        this.url = url;
    }

    @Override
    public String printError() {

        String err =  "<td><em>"+ url + "</em>: "+getDescription()+ " </td>\n";
        err+= "<td class='risk-level'> "+getRisk()+ " </td>\n";

        return err;
    }
}
