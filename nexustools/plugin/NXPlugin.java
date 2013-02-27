package nexustools.plugin;

/**
 *
 * @author luke
 */
public abstract class NXPlugin {
    
    public static final int TYPE_ECMASCRIPT = 1;
    public static final int TYPE_JAVA = 2;
    public static final int TYPE_NATIVE = 3; // nyi, possibly forever
    
    public String source = "";
    
    public int type = -1;
    
    public abstract Object runMethod(String methodname, NXRunnable ifnull, Object ... args);
    public abstract Object runMethod(String methodname, Object ... args);
    public abstract Object getField(String fieldname, Object ifnull);
    public abstract Object getField(String fieldname);
    
}
