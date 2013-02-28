package nexustools.astralgateway.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nexustools.astralgateway.te.TileEntityTransporter;

/**
 *
 * @author luke
 */
public class BlockTransporter extends BlockContainer {

    public BlockTransporter(int id, int tx, Material mat){
        super(id, tx, mat);
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TileEntityTransporter(var1);
    }
    
}
