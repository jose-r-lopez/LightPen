package tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.impl;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import tfg.uo.lightpen.R;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.Error;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.ErrorPagesError;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.riskRating.OwaspRiskRating;
import tfg.uo.lightpen.infrastructure.factories.Factories;
import tfg.uo.lightpen.model.ContextData;

/**
 * Created by José Manuel Redondo on 08/3/19.
 * Plugin realizado para el analisis de páginas de error devueltas por el sistema remoto
 */
public class ErrorPagesImpl implements IPluginImplementation {

    private static final String TAG = "ERRORPAGES_IMPL";
    private String DummyPage = "LightPenRandom";
    private ArrayList<Error> errors = new ArrayList<>();
    private URL url;
    private ContextData ctxD;

    public void initialize(URL url, ContextData ctxD) {
        setUrl(url);
        setContextData(ctxD);
        double num = Math.random();
        String randomSequence = "" + num;
        randomSequence = randomSequence.substring(2);
        DummyPage += randomSequence + ".html";
    }

    public ArrayList<Error> getPentest() {
        String html = getErrorPage();
        if (html != null)
            analize(html);
        return getErrors();
    }

    private String getErrorPage() {
        String parsedUrl = getUrl().getHost();
        String errors = "";

        if (getUrl().toString().contains("https"))
            parsedUrl = "https://" + parsedUrl;
        else
            parsedUrl = "http://" + parsedUrl;

        try {
            URL errorPageDir = new URL(parsedUrl + "/" + DummyPage);
            errors = Factories.business
                    .createNetworkingFactory()
                    .createNetworking(ctxD).getHTMLFrom(errorPageDir);
        } catch (MalformedURLException e) {
            Log.e(TAG, "getErrorPages:: ", e);
        }
        return errors;
    }

    private void analize(String html) {
        //Analyze default 404
        if (isDefaultErrorPage(html)) {
            String serverGuess = guessWebServer(html);
            getErrors().add(new ErrorPagesError(getContextData().getContext().getString(R.string.ep_404_default_vulnerability) + ".\n" +
                    (serverGuess == null ? "" : getContextData().getContext().getString(R.string.ep_problematic) + ": " + serverGuess),
                    new OwaspRiskRating(9, 9, 6, 9).getRiskLevel()));
        }
        //Future: Analyze other default error pages
    }

    private String guessWebServer(String html) {
        String[] lines = html.split("\\n");
        for (String line : lines) {
            if (webServerLine(line))
                return removeTags(line);
        }
        return null;
    }

    private String removeTags(String line) {
        try {
            if (line.contains(">")) {
                String[] parts1 = line.split(">");
                String txt = parts1[parts1.length - 1];
                if (txt.contains("<")) {
                    String[] parts2 = txt.split("<");
                    return parts2[0];
                }
                return txt;
            }
        } catch (Exception ex) {
        }
        return line;
    }

    private boolean webServerLine(String line) {
        line = line.toLowerCase();
        if ((line.contains("apache")) ||
                (line.contains("iis")) ||
                (line.contains("internet information server"))
                || (line.contains("internet information services"))
                || (line.contains("nginx")))
            return true;
        return false;
    }

    private boolean isDefaultErrorPage(String html) {
        //Apache
        if (html.contains("<h1>Not Found</h1>"))
            return true;
        //Nginx
        if (html.contains("404 Not Found") || html.contains("404 Page Not Found"))
            return true;
        //IIS
        if (html.contains("HTTP Error 404 - File or directory not found"))
            return true;
        //IIS + ASP .NET
        return html.contains("HTTP Error 404.0 - Not Found");
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
