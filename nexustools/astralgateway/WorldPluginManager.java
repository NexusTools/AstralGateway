/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nexustools.astralgateway;

import java.util.ArrayList;
import java.util.Hashtable;
import nexustools.plugin.NXPlugin;

/**
 *
 * @author luke
 */
public class WorldPluginManager {
    
    static Hashtable<Integer, NXPlugin> plugins = new Hashtable<Integer, NXPlugin>();
    
    public static void addPluglin(NXPlugin plug, int at){
        plugins.put(at, plug);
        plug.runMethod("init");
    }
    
    static {
        
    }
    
    public static NXPlugin getPluginFor(int dimension){
        return plugins.get(dimension);
    }
    
}
