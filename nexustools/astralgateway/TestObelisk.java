package nexustools.astralgateway;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import nexustools.astralgateway.api.DimensionManagerExtensions;

/**
 *
 * @author luke
 */
public class TestObelisk extends Block {
    public TestObelisk(int id, int tx, Material mat){
        super(id, tx, mat);
        this.setBlockName("Test Return Obelisk");
        this.setLightValue(4.20f);
        this.setCreativeTab(CreativeTabs.tabTransport);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer ent, int par6, float par7, float par8, float par9) {
        if(!par1World.isRemote){
//            System.out.println("Activated");
//            if(ent.dimension==420){
                DimensionManagerExtensions.transferEntityToDimension((EntityPlayerMP)ent, 0);
////                ent.travelToDimension(0);
//            }else{
//                DimensionManager.initDimension(420);
////                ent.travelToDimension(420);
//                DimensionManagerExtensions.transferEntityToDimension((EntityPlayerMP)ent, 420);
//            }
        }
        return true;
    }
}
