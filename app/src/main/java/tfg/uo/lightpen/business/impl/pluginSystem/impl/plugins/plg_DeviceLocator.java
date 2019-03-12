package tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins;

import java.net.URL;
import java.util.ArrayList;

import tfg.uo.lightpen.R;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.Plugin;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.Error;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.impl.IPluginImplementation;
import tfg.uo.lightpen.model.ContextData;

/**
 * Created by Iñigo Llaneza Aller on 22/4/17.
 * Plugin realizado para el analisis del fichero robots.txt
 */
public class plg_DeviceLocator implements Plugin {

    //private static final String TAG = "DEVICELOCATOR";
    private ContextData ctxD;
    private static IPluginImplementation implementation = null;
    private String pluginPath;
    private static URL lastUrl;

    public void setPluginPath(String path) {
        pluginPath = path;
    }

    @Override
    public ArrayList<Error> run(URL dir) {
        if ((implementation == null) || (lastUrl == null)) {
            if (dir.equals(lastUrl))
                return implementation.getPentest();
            try {
                Class<?> cl = Class.forName(pluginPath + ".impl.DeviceLocatorImpl");
                implementation = (IPluginImplementation) cl.newInstance();
                lastUrl = dir;
            } catch (Exception e) {
                throw new RuntimeException("Unexpected failure on Device locator plugin:" + e.toString());
            }
        }
        implementation.initialize(dir, ctxD);
        return implementation.getPentest();
    }

    @Override
    public String showDescription() {
        return ctxD.getContext().getString(R.string.dloc_description);
    }

    @Override
    public String showName() {
        return ctxD.getContext().getString(R.string.dloc_name);
    }

    @Override
    public Double showVersion() {
        return Double.parseDouble(ctxD.getContext().getString(R.string.dloc_version));
    }


    @Override
    public String printErrors(ArrayList<Error> errores) {
        String ans = "<div class=\"plugin-result\">";
        ans += "<h1 class=\"plugin-title\">" + showName() + "</h1>\n";
        ans += "<p class=\"plugin-description\">" + showDescription() + "</p>\n";
        if (!errores.isEmpty()) {
            ans += "<div class=\"plugin-result-table-wrapper\">\n";
            ans += "<table id='robot-scan-table' class='error-table'>\n";
            ans += "<tr>\n";
            ans += "<th>" + ctxD.getContext()
                    .getString(R.string.dloc_ip) + "</th>\n";
            ans += "<th>" + ctxD.getContext()
                    .getString(R.string.dloc_hostname) + "</th>\n";
            ans += "<th>" + ctxD.getContext()
                    .getString(R.string.dloc_mac) + "</th>\n";
            ans += "<th>" + ctxD.getContext()
                    .getString(R.string.dloc_manufacturer) + "</th>\n";
            ans += "</tr>\n";
            for (Error e : errores) {
                ans += "<tr>\n";
                ans += e.printError();
                ans += "</tr>\n";
            }
            ans += "</table>\n";
            ans += "</div>\n"; //plugin-result-table-wrapper

        } else {
            ans += "<div class=\"plugin-result-wrapper\">\n";
            ans += "<p>" + ctxD.getContext()
                    .getString(R.string.dloc_nodevices) + "</p>";
            ans += "</div>\n";
        }

        ans += "</div>\n"; // plugin-result

        return ans;
    }

    @Override
    public void setContextData(ContextData ctxD) {
        this.ctxD = ctxD;
    }
}
