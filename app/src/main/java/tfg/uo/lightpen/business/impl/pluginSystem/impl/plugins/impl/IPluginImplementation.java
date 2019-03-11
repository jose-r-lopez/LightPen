package tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.impl;

import java.net.URL;
import java.util.ArrayList;

import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.Error;
import tfg.uo.lightpen.model.ContextData;

/**
 * This interface is implemented by all plugin implementations so the implementations itself
 * can be loaded dynamically from the "front-end" plugin classes
 * @author Jose Manuel Redondo
 */
public interface IPluginImplementation {
    /**
     * Sets necessary context data to put the implementation to work
     * @param url URL to examine
     * @param ctxD Context information necessary to the implementation
     */
    void initialize(URL url, ContextData ctxD);

    /**
     * Run the plugin pentesting work
     * @return Security errors detected
     */
    ArrayList<Error> getPentest();
}
