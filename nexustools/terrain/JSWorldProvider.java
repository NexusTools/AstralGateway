/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nexustools.terrain;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import nexustools.astralgateway.WorldPluginManager;
import nexustools.plugin.NXPlugin;

/**
 *
 * @author luke
 */
public class JSWorldProvider extends WorldProvider {
    
    ScriptEngine e = null;
    Invocable i = null;
    
    public JSWorldProvider(){
        String script = "";
        ScriptEngine cr = null;
        for(ScriptEngineFactory factory : new ScriptEngineManager().getEngineFactories()){ // this doesnt run new ScriptEngineManager() every time.... right?
            if(factory.getEngineName().toLowerCase().contains("js") || factory.getEngineName().toLowerCase().contains("avasc") || factory.getEngineName().toLowerCase().contains("ecma") || factory.getEngineName().toLowerCase().contains("rhino") || factory.getEngineName().toLowerCase().contains("v8")){ // uggo
                cr = factory.getScriptEngine();
                break;
            }
        }
        
        e = cr;
        
        try {
            i = (Invocable) e.eval(script);
        } catch (ScriptException ex) {
            Logger.getLogger(JSWorldProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            i.invokeFunction("init");
        } catch (ScriptException ex) {
            Logger.getLogger(JSWorldProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JSWorldProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        Block.stone.getBlockTexture(worldObj, dimensionId, dimensionId, dimensionId, dimensionId);
    }

    NXPlugin plug = null;
    
    @Override
    public void setDimension(int dim) {
        
        if(WorldPluginManager.getPluginFor(dim) != null){ //yes this runs twice, weee
            plug = WorldPluginManager.getPluginFor(dim);
            plug.runMethod("init");
        }
        
        super.setDimension(dim);
    }
    
//    
//    this.
//
//    @Override
//    public Vec3 getFogColor(float par1, float par2) {
//        return worldObj.getWorldVec3Pool().getVecFromPool(0.9f, 0.9f, 0.1f);
////        return super.getFogColor(par1, par2);
//    }
//    @Override
//    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
//        return worldObj.getWorldVec3Pool().getVecFromPool(0.8f, 0.8f,0f);
//    }
////    
//    this.

//    @Override
//    public BiomeGenBase getBiomeGenForCoords(int x, int z) {
////        System.out.println("getfor " + x +", " + z);
////        return new BiomeGenDesert(-1);
////        return super.getBiomeGenForCoords(x, z);
//    }
    
//    this.
    
//    @Override
//    public float getCloudHeight() {
//        return 220f;
//    }
    
//    this.

    @Override
    public ChunkCoordinates getSpawnPoint() {
        return new ChunkCoordinates(0, 250, 0);
    }
    
//    this.
    
//    this.

    @Override
    public String getDimensionName() {
        try {
            return (String) i.invokeFunction("getName");
    //        throw new UnsupportedOperationException("Not supported yet.");
        } catch (ScriptException ex) {
            Logger.getLogger(JSWorldProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JSWorldProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "unknown";
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        SimplexProvider p = new SimplexProvider(worldObj, 0, false);
//        this.dimensionId
        if(plug != null){
            Object res = plug.runMethod("isBiome");
            if(res != null){
                System.out.println(res.toString());
            }
        }
        CustomBiome cb = new CustomBiome();
//        cb.topBlock = (short) Block.waterStill.blockID;
        cb.topBlock=(short)Block.blockDiamond.blockID;
        cb.fillerBlock=(short)Block.blockGold.blockID;
//        cb.fillerBlock = (short) Block.lavaStill.blockID;
//        p.setSingleBiome(cb);
//        p.setSingle(true);
        return p;
    }
    
}
