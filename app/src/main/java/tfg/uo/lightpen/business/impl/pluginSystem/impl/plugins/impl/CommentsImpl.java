package tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.impl;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import tfg.uo.lightpen.R;
import tfg.uo.lightpen.business.impl.networking.impl.networkOperations.HttpOperations.Resources;
import tfg.uo.lightpen.business.impl.networking.impl.networkOperations.HttpOperations.URLMap;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.DirListingError;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.Error;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.riskRating.OwaspRiskRating;
import tfg.uo.lightpen.infrastructure.factories.Factories;
import tfg.uo.lightpen.model.ContextData;

/**
 * Created by José Manuel Redondo on 08/3/19.
 * Plugin realizado para el analisis de comentarios en las paginas presentes en el sistema remoto
 */
public class CommentsImpl implements IPluginImplementation {

    private static final String TAG = "COMMENTS_IMPL";
    private ArrayList<Error> errors = new ArrayList<>();
    private URL url;
    private ContextData ctxD;

    public void initialize(URL url, ContextData ctxD) {
        setUrl(url);
        setContextData(ctxD);
    }

    public ArrayList<Error> getPentest() {
        HashMap<URL, String> html = getPagesHTML();
        if (html != null)
            analize(html);
        return getErrors();
    }

    private HashMap<URL, String> getPagesHTML() {
        String parsedUrl = getUrl().getHost();

        if (getUrl().toString().contains("https"))
            parsedUrl = "https://" + parsedUrl;
        else
            parsedUrl = "http://" + parsedUrl;

        try {
            URL baseDir = new URL(parsedUrl);
            Resources rs = new Resources();
            URLMap uM = new URLMap();
            Factories.business
                    .createNetworkingFactory()
                    .createNetworking(ctxD)
                    .crawl(baseDir, uM.maxURLDeep(ctxD), rs);
            return rs.getResponse();
        } catch (MalformedURLException e) {
            Log.e(TAG, "getDirlisting:: ", e);
        }

        return null;
    }

    private void analize(HashMap<URL, String> hash) {
        String comment;
        for (URL url : hash.keySet()) {
            String html = hash.get(url);
            comment = hasComment(html);
            if (comment != null) {
                getErrors().add(new DirListingError(getContextData().getContext().getString(R.string.cm_vulnerability) + ".\n" +
                        getContextData().getContext().getString(R.string.cm_problematic) + ": " + url, //+ cutComment(comment),
                        new OwaspRiskRating(9, 2, 8, 9).getRiskLevel()));
            }
        }
    }

    /**
     * Comment tokens we locate
     */
    private static String[] commentTokens = new String[]{"<!--", "//", "/*"};

    /**
     * Returns the first occurrence of the comment token
     *
     * @param html Web page HTML
     * @return First line with comments
     */
    private String hasComment(String html) {
        String[] lines = html.split("\n");
        for (String line : lines) {
            for (String comment : commentTokens)
                if (line.contains(comment)) {
                    if (line.toUpperCase().contains("DOCTYPE"))
                        break;
                    if (comment.equals("//"))
                        if (line.toUpperCase().contains("HTTP://") || line.toUpperCase().contains("HTTPS://"))
                            break;
                    return line;
                }
        }
        return null;
    }

    private static int MAX_COMMENT_LENGTH = 50;

    private String cutComment(String str) {
        str = str.trim();
        if (str.length() > MAX_COMMENT_LENGTH)
            str = str.substring(0, MAX_COMMENT_LENGTH) + "...";
        return " (" + str + ")";
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
