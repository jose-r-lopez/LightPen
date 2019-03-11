package tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.impl;

import java.net.URL;
import java.util.ArrayList;

import tfg.uo.lightpen.business.impl.networking.impl.networkOperations.SocketOperations.Ports;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.Error;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.PortScanError;
import tfg.uo.lightpen.infrastructure.factories.Factories;
import tfg.uo.lightpen.model.ContextData;

/**
 * This plugin scan remote system ports using the Android network tools library functionality.
 * Is scans only the top most scanned ports worldwide.
 *
 * Created by Iñigo Llaneza Aller on 22/4/17.
 * Modified by Jose Manuel Redondo:
 * - AndroidNetworkTools integration
 * - Worldwide most scanned ports scanned (threshold 0,005%)
 * - Port most common services found added to the information returned by the plugin
 * - TCP and UDP services are scanned
 */
public class PortScanImpl implements IPluginImplementation {

    //private static final String TAG = "HTTPPRODUCTSCAN_IMPL";
    private ArrayList<Error> errors = new ArrayList<>();
    private URL url;
    private ContextData ctxD;

    public void initialize(URL url, ContextData ctxD) {
        setUrl(url);
        setContextData(ctxD);
    }

    public ArrayList<Error> getPentest() {
        Ports port = new Ports();

        Factories
                .business
                .createNetworkingFactory()
                .createNetworking(ctxD)
                .crawl(getUrl(), port.maxThreads(ctxD), port, true);
        for (String s : port.getResponse()) {
            getErrors().add(new PortScanError(s));
        }
        return getErrors();
    }


    //region Getters & Setters
    private void setUrl(URL url) {
        this.url = url;
    }

    private URL getUrl() {
        return url;
    }

    private ArrayList<Error> getErrors() {
        return errors;
    }

    public ContextData getContextData() {
        return ctxD;
    }

    public void setContextData(ContextData ctxD) {
        this.ctxD = ctxD;
    }
    //endregion
}
