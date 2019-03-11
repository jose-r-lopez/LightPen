package tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.impl;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import tfg.uo.lightpen.R;
import tfg.uo.lightpen.business.impl.networking.impl.networkOperations.HttpOperations.Resources;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.DirListingError;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.Error;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.riskRating.OwaspRiskRating;
import tfg.uo.lightpen.infrastructure.factories.Factories;
import tfg.uo.lightpen.model.ContextData;

/**
 * Created by José Manuel Redondo on 08/3/19.
 * Plugin realizado para el analisis de páginas de error devueltas por el sistema remoto
 */
public class DirListingImpl implements IPluginImplementation {

    private static final String TAG = "DIRLISTING_IMPL";
    private ArrayList<Error> errors = new ArrayList<>();
    private URL url;
    private ContextData ctxD;

    public void initialize(URL url, ContextData ctxD) {
        setUrl(url);
        setContextData(ctxD);
    }

    public ArrayList<Error> getPentest() {
        HashMap<URL, String> html = getListingPages();
        if (html != null)
            analize(html);
        return getErrors();
    }

    private HashMap<URL, String> getListingPages() {
        String parsedUrl = getUrl().getHost();

        if (getUrl().toString().contains("https"))
            parsedUrl = "https://" + parsedUrl;
        else
            parsedUrl = "http://" + parsedUrl;

        try {
            URL baseDir = new URL(parsedUrl + "/");
            Resources rs = new Resources();
            Factories.business
                    .createNetworkingFactory()
                    .createNetworking(ctxD)
                    .crawl(baseDir, 1, rs);
            return rs.getResponse();
        } catch (MalformedURLException e) {
            Log.e(TAG, "getDirlisting:: ", e);
        }

        return null;
    }

    private void analize(HashMap<URL, String> hash) {
        for (URL url : hash.keySet()) {
            String html = hash.get(url);
            if (isDirListingPage(html)) {
                getErrors().add(new DirListingError(getContextData().getContext().getString(R.string.dl_vulnerability) + ".\n" +
                        getContextData().getContext().getString(R.string.dl_problematic) + ": " + url,
                        new OwaspRiskRating(9, 9, 6, 9).getRiskLevel()));
            }
        }
    }

    private boolean isDirListingPage(String html) {
        html = html.toLowerCase();
        if (html.contains("directory listing") ||
                html.contains("index of /") ||
                html.contains("parent directory"))
            return true;
        return false;
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
