package nexustools.terrain;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import nexustools.astralgateway.WorldPluginManager;
import nexustools.plugin.NXPlugin;

/**
 *
 * @author luke
 */
public class PluginWorldProvider extends WorldProvider {
    
    NXPlugin plug = null;
    boolean single = false;
    float temp = 0.5f;

    @Override
    public void setDimension(int dim) {
        plug = WorldPluginManager.getPluginFor(dim);
        Object res = plug.runMethod("isBiome");
        if(res != null){
            single = res.toString().toLowerCase().equals("true");
            if(single){
                res = plug.runMethod("getTemperature");
                if(res != null) temp = Float.parseFloat(res.toString());
            }
        }
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

    WorldChunkManager chnk = null;
    
    public WorldChunkManager getWorldChunkMgr() {
        return (chnk==null)?(chnk=new WorldChunkManager(this.worldObj){

            @Override
            public float[] getTemperatures(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
                if(single){
                    float[] ret = super.getTemperatures(par1ArrayOfFloat, par2, par3, par4, par5);
                    for(int i = 0; i < ret.length; i++){
                        ret[i] = temp;
                    }
                    return ret;
                } else {
                    return super.getTemperatures(par1ArrayOfFloat, par2, par3, par4, par5);
                }
            }
            
        }):chnk;
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        SimplexProvider p = new SimplexProvider(worldObj, 0, false);
        if(plug != null){
            if(single){
                CustomBiome cb = new CustomBiome();
                cb.topBlock=(short) Double.parseDouble(plug.runMethod("getTopBlock").toString());
                cb.fillerBlock=(short) Double.parseDouble(plug.runMethod("getFillerBlock").toString());
                p.setSingleBiome(cb);
                p.setSingle(true);
            }
            Object res = plug.runMethod("getSimplexResolution");
            if(res != null){
                p.res = Float.parseFloat(res.toString());
            }
        }
        return p;
    }
    
}
