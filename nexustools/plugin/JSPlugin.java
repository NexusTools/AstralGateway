/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nexustools.plugin;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author luke
 */
public class JSPlugin extends NXPlugin {

    Invocable inv;
    
    public JSPlugin(String script){
        type = NXPlugin.TYPE_ECMASCRIPT;
        System.out.println("New JSPlugin with source: " + script);
        ScriptEngine cr = null;
        for(ScriptEngineFactory factory : new ScriptEngineManager().getEngineFactories()){ // this doesnt run new ScriptEngineManager() every time.... right?
            if(factory.getEngineName().toLowerCase().contains("v8") || factory.getEngineName().toLowerCase().contains("js") || factory.getEngineName().toLowerCase().contains("avasc") || factory.getEngineName().toLowerCase().contains("ecma") || factory.getEngineName().toLowerCase().contains("rhino")){ // uggo
                cr = factory.getScriptEngine();
                System.out.println("Using engine: " + factory.getEngineName());
                break;
            }
        }
        try {
            cr.eval(script);
            inv = (Invocable)cr;
        } catch (ScriptException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public Object runMethod(String methodname, NXRunnable ifnull, Object ... args) {
        try {
            return inv.invokeFunction(methodname, args);
        } catch (ScriptException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        return ifnull.run(args);
    }

    @Override
    public Object runMethod(String methodname, Object ... args) {
        try {
            return inv.invokeFunction(methodname, args);
        } catch (ScriptException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        return null;
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
