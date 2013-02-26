/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nexustools.plugin;

/**
 *
 * @author luke
 */
public class JSPlugin extends NXPlugin {

    public JSPlugin(String script){
        type = NXPlugin.TYPE_ECMASCRIPT;
        System.out.println("New JSPlugin with source: " + script);
    }
    
    @Override
    public Object runMethod(String methodname, NXRunnable ifnull, Object ... args) {
        return null; // haax
    }

    @Override
    public Object runMethod(String methodname, Object ... args) {
        return null; // haax
    }

    @Override
    public Object getField(String fieldname, Object ifnull) {
        return null; // haax
    }

    @Override
    public Object getField(String fieldname) {
        return null; // haax
    }
    
}
