/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nexustools.astralgateway;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldServerMulti;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

/**
 *
 * @author luke
 */
public class DimensionManagerExtensions {
    
    static ArrayList<Integer> loaded = new ArrayList<Integer>();
    
    public static void customInitDimension(int dim){ // small amount of code from forge here, but they didnt implement a way to make a custom teleporter so...
        
        if(loaded.contains(dim)) return;
        
        WorldServer overworld = DimensionManager.getWorld(0);
        if (overworld == null) throw new RuntimeException("Cannot Hotload Dim: Overworld is not Loaded!");
        
        try{
            DimensionManager.getProviderType(dim);
        }catch(Exception e){
            System.err.println("Cannot Hotload Dim: " + e.getMessage());
            return; //If a provider hasn't been registered then we can't hotload the dim
        }
        MinecraftServer mcServer = overworld.getMinecraftServer();
        ISaveHandler savehandler = overworld.getSaveHandler();
        WorldSettings worldSettings = new WorldSettings(overworld.getWorldInfo());

        WorldServer world = (dim == 0 ? overworld : new WorldServerMulti(mcServer, savehandler, overworld.getWorldInfo().getWorldName(), dim, worldSettings, overworld, mcServer.theProfiler){

            @Override
            public Teleporter func_85176_s/*  getTeleporter?  */() {
                final WorldServer pass = this;
                return new Teleporter(this){
                    
                    

                    @Override
                    public boolean func_85188_a/*  generatePortal?  */(Entity par1Entity) {
//                        par1Entity.setLocationAndAngles(0, 250, 0, 0, 0);
                        int xr = 0;
                        int yr = 40;
                        int zr = 0;
                        for(int x = xr-10; x < xr+10; x++){
                            for(int y = yr-10; y < yr+10; y++){
                                for(int z = zr-10; z < zr+10; z++){
                                    if(x == xr && y == yr-8 && z == zr){
                                        pass.setBlock(x,y,z, 4002);
                                    }else{
                                        pass.setBlock(x,y,z,0);
                                    }
                                }
                            }   
                        }
                        for(int y = yr; y < 255; y++){
                            pass.setBlock(xr, y, zr, 0);
                        }
                        
                        par1Entity.setLocationAndAngles(xr,yr,zr,0,0);
                        
                        return true; // finally.... no more automatic nethergate spawning
                    }
                };
            }
            
        });
        world.addWorldAccess(new WorldManager(mcServer, world));
        MinecraftForge.EVENT_BUS.post(new WorldEvent.Load(world));
        if (!mcServer.isSinglePlayer()) world.getWorldInfo().setGameType(mcServer.getGameType());
        
        mcServer.setDifficultyForAllWorlds(mcServer.getDifficulty());
        
        loaded.add(dim);
        
    }
}
