package nexustools.astralgateway.api;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

/**
 *
 * @author luke
 */
public class DimensionManagerExtensions {

    public static void transferEntityToDimension(EntityPlayerMP ent, int dim) {
        final WorldServer pass = DimensionManager.getWorld(dim);
        if(dim == 0)
            ent.mcServer.getConfigurationManager().transferPlayerToDimension(ent, dim, new Teleporter(pass){
                
                @Override
                public boolean placeInExistingPortal(Entity ent, double par2, double par4, double par6, float par8) {
                    ChunkCoordinates c = pass.getSpawnPoint();
                    if(c == null){ // sadfaic
                        ent.setVelocity(0,0,0);
                        ent.setLocationAndAngles(0, pass.getHeightValue(0,0)+4, 0, 0, 0);
                        System.out.println("Moved to 0,0 (spawnpoint is null!)");
                    }else{
                        ent.setVelocity(0,0,0);
                        ent.setLocationAndAngles(c.posX, pass.getHeightValue(c.posX,c.posZ)+4, c.posZ, 0, 0);
                        System.out.println("Moved to " + c.posX + ", " + c.posY + ", " + c.posZ);
                    }
                    return true;
                }

                @Override
                public void placeInPortal(Entity ent, double par2, double par4, double par6, float par8) {
                    ChunkCoordinates c = pass.getSpawnPoint();
                    if(c == null){ // sadfaic
                        ent.setVelocity(0,0,0);
                        ent.setLocationAndAngles(0, pass.getHeightValue(0,0)+4, 0, 0, 0);
                        System.out.println("Moved to 0,0 (spawnpoint is null!)");
                    }else{
                        ent.setVelocity(0,0,0);
                        ent.setLocationAndAngles(c.posX, pass.getHeightValue(c.posX,c.posZ)+4, c.posZ, 0, 0);
                        System.out.println("Moved to " + c.posX + ", " + c.posY + ", " + c.posZ);
                    }
                }
                
            }); // no idea if this will cause issues with the nether gate (creating an invalid return point)
        else
            ent.mcServer.getConfigurationManager().transferPlayerToDimension(ent, dim, new Teleporter(pass){

                @Override
                public boolean func_85188_a/*  generatePortal?  */(Entity ent) {
                    int xr = 0;
                    int yr = 40;
                    int zr = 0;
                    for (int x = xr - 10; x < xr + 10; x++) {
                        for (int y = yr - 10; y < yr + 10; y++) {
                            for (int z = zr - 10; z < zr + 10; z++) {
                                if (x == xr && y == yr - 8 && z == zr) {
                                    pass.setBlock(x, y, z, 4002);
                                } else {
                                    pass.setBlock(x, y, z, 0);
                                }
                            }
                        }
                    }
                    for (int y = yr; y < 255; y++) {
                        pass.setBlock(xr, y, zr, 0);
                    }

                    ent.setVelocity(0,0,0);
                    ent.setLocationAndAngles(xr, yr+1, zr, 0, 0);

                    return true; // finally.... no more automatic nethergate spawning
                }

            });
    }
 
}
