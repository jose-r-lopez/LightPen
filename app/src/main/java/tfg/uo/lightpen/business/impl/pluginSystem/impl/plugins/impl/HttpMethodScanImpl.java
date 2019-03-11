package tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import tfg.uo.lightpen.R;
import tfg.uo.lightpen.business.impl.networking.impl.networkOperations.HttpOperations.HttpMethod;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.Error;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.HttpMethodsError;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.riskRating.OwaspRiskRating;
import tfg.uo.lightpen.infrastructure.factories.Factories;
import tfg.uo.lightpen.model.ContextData;

/**
 * Created by Iñigo Llaneza Aller on 9/5/17.
 */

public class HttpMethodScanImpl implements IPluginImplementation {

    //private static final String TAG = "HttpMethodScan_Impl";
    private ArrayList<Error> errors = new ArrayList<>();
    private URL url;
    private ContextData ctxD;
    private ArrayList<HttpMethod> httpMethods = new ArrayList<HttpMethod>() {
        {
            add(new HttpMethod("PUT"));
            add(new HttpMethod("DELETE"));
            add(new HttpMethod("CONNECT"));
            add(new HttpMethod("TRACE"));
            //WebDAV
            add(new HttpMethod("PROPFIND"));
            add(new HttpMethod("PROPPATCH"));
            add(new HttpMethod("MKCOL"));
            add(new HttpMethod("COPY"));
            add(new HttpMethod("MOVE"));
            add(new HttpMethod("LOCK"));
            add(new HttpMethod("UNLOCK"));
        }
    };

    public void initialize(URL url, ContextData ctxD) {
        setUrl(url);
        setContextData(ctxD);
    }

    public ArrayList<Error> getPentest() {
        HttpMethod httpMth = new HttpMethod("GET");
        ArrayList<URL> urls = Factories
                .business
                .createNetworkingFactory()
                .createNetworking(ctxD)
                .crawl(url, httpMth.maxURLDeep(ctxD));

        analyzeMethods(urls);
        return getErrors();
    }

    private void analyzeMethods(ArrayList<URL> urls) {
        for (HttpMethod method : httpMethods) {
            for (URL u : urls) {
                if (method.connectionPossibleTo(u))
                    add(checkConnectionMethod(method.getMethod(), u));
            }
        }
    }

    private HttpMethodsError checkConnectionMethod(String mth, URL url) {
        HttpMethodsError err;
        switch (mth.toUpperCase()) {
            case "PUT":
                err = new HttpMethodsError(url, getContextData()
                        .getContext()
                        .getString(R.string.httphs_method_put),
                        new OwaspRiskRating(9, 8, 7, 9).getRiskLevel());
                break;
            case "DELETE":
                err = new HttpMethodsError(url, getContextData()
                        .getContext()
                        .getString(R.string.httphs_method_delete),
                        new OwaspRiskRating(9, 9, 9, 9).getRiskLevel());
                break;
            case "CONNECT":
                err = new HttpMethodsError(url, getContextData()
                        .getContext()
                        .getString(R.string.httphs_method_connect),
                        new OwaspRiskRating(9, 4, 3, 9).getRiskLevel());
                break;
            case "TRACE":
                err = new HttpMethodsError(url, getContextData()
                        .getContext()
                        .getString(R.string.httphs_method_trace),
                        new OwaspRiskRating(9, 8, 6, 9).getRiskLevel());
                break;
            default:
                err = assertWebdav(mth, url);
                break;
        }
        return err;
    }

    private Set<String> webDAVMethods = new HashSet<String>() {{
        add("PROPFIND");
        add("PROPPATCH");
        add("MKCOL");
        add("COPY");
        add("MOVE");
        add("LOCK");
        add("UNLOCK");
    }};

    private HttpMethodsError assertWebdav(String mth, URL url) {
        if (webDAVMethods.contains(mth))
            return new HttpMethodsError(url, mth + ": " + getContextData()
                    .getContext()
                    .getString(R.string.httphs_method_custom),
                    new OwaspRiskRating(9, 3, 4, 9).getRiskLevel());
        return null;
    }


    //region Getters & Setters
    public void setUrl(URL url) {
        this.url = url;
    }

    private ArrayList<Error> getErrors() {
        return errors;
    }

    private ContextData getContextData() {
        return ctxD;
    }

    private void setContextData(ContextData ctxD) {
        this.ctxD = ctxD;
    }

    private void add(HttpMethodsError httpMethodsError) {
        if (httpMethodsError != null)
            getErrors().add(httpMethodsError);
    }
    //endregion
}
