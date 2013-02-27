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
    
    NXPlugin plug = null;
    
    @Override
    public void setDimension(int dim) {
        plug = WorldPluginManager.getPluginFor(dim);
        super.setDimension(dim);
    }
    
//    @Override
//    public Vec3 getFogColor(float par1, float par2) {
//        return worldObj.getWorldVec3Pool().getVecFromPool(0.9f, 0.9f, 0.1f);
////        return super.getFogColor(par1, par2);
//    }
//    @Override
//    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
//        return worldObj.getWorldVec3Pool().getVecFromPool(0.8f, 0.8f,0f);
//    }

//    @Override
//    public BiomeGenBase getBiomeGenForCoords(int x, int z) {
////        System.out.println("getfor " + x +", " + z);
////        return new BiomeGenDesert(-1);
////        return super.getBiomeGenForCoords(x, z);
//    }
    
//    @Override
//    public float getCloudHeight() {
//        return 220f;
//    }

    @Override
    public ChunkCoordinates getSpawnPoint() {
        return new ChunkCoordinates(0, 250, 0);
    }

    @Override
    public String getDimensionName() {
        return (String)plug.runMethod("getName");
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        SimplexProvider p = new SimplexProvider(worldObj, 0, false);
        if(plug != null){
            Object res = plug.runMethod("isBiome");
            if(res != null){
                System.out.println("isBiome: "+res.toString());
                if(res.toString().toLowerCase().equals("true")){ //lol
                    CustomBiome cb = new CustomBiome();
                    cb.topBlock=(short) Double.parseDouble(plug.runMethod("getTopBlock").toString());
                    cb.fillerBlock=(short) Double.parseDouble(plug.runMethod("getFillerBlock").toString());
                    p.setSingleBiome(cb);
                    p.setSingle(true);
                }
            }
        }
        return p;
    }
    
}
