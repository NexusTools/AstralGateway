package nexustools.astralgateway.te;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 *
 * @author luke
 */
public class TileEntityTransporter extends TileEntity {

    public static final int STATE_STATIC_LINK = 0;
    public short state = -1;
    public int linkid = -1;
    
    public TileEntityTransporter(World w) {
        System.out.println("new TileEntityTransporter");
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        state = compound.getShort("state");
        linkid = compound.getInteger("activelink");
        switch(state){
            
            case STATE_STATIC_LINK:{  break;}
            
            default: break;
        }
        super.readFromNBT(compound); //To change body of generated methods, choose Tools | Templates.
    }
    
}
