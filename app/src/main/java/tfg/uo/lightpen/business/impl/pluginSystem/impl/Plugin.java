package tfg.uo.lightpen.business.impl.pluginSystem.impl;

import java.net.URL;
import java.util.ArrayList;

import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.Error;
import tfg.uo.lightpen.model.ContextData;

/**
 * Created by Iñigo Llaneza Aller on 31/1/17.
 * Interfaz que deben implementar todos los Plugins que deseen incluirse en la aplicacion
 */
public interface Plugin {

    /**
     * Metodo que comienza la ejecucion del plugin
     * @param dir direccion objetivo
     * @return elemento JSON con los resultados de las pruebas
     */
    ArrayList<Error> run(URL dir);

    /**
     * Metodo que devuelve una breve descripcion del plugin
     * @return descripcion del plugin
     */
    String showDescription();

    /**
     * Metodo que devuelve el nombre del Plugin
     * @return nombre del plugin
     */
    String showName();

    /**
     * Metodo que devuelve la version del plugin
     * @return version del plugin
     */
    Double showVersion();


    /**
     * Metodo que permite al plugin decidir como sera su salida
     * @param errores Errores detectados por este plugin
     * @return codigo html de su propio analisis
     */
    String printErrors(ArrayList<Error> errores);

    /**
     * Metodo que permitira al plugin acceder al contexto para las operaciones que lo necesiten
     */
    void setContextData(ContextData ctxD);

    /**
     * Sets the package when the plugin was loaded
     * @param path Path where the plugin got loaded
     */
    void setPluginPath(String path);
}
