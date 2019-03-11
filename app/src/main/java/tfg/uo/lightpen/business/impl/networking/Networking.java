package tfg.uo.lightpen.business.impl.networking;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tfg.uo.lightpen.business.impl.networking.impl.networkOperations.NetworkOperation;

/**
 * Created by Iñigo on 23/06/2017.
 * Modified by Jose Manuel Redondo:
 * - Added direct HTML from URL method to obtain HTML code regardless non 200 HTTP Response
 * - Added plain URL crawler functionality (no NetOp)
 */

public interface Networking {

    void crawl(URL url, int deep, NetworkOperation netOp, boolean... socket);

    ArrayList<URL> crawl(URL url, int deep);

    NetworkOperation get(URL url, NetworkOperation netOp);

    List<URL> getUrlTree();

    String getHTMLFrom(URL url);
}
