/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nexustools.astralgateway;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 *
 * @author luke
 */
public class TestTransporter extends Block{
    public TestTransporter(int id, int tx, Material mat){
        super(id, tx, mat);
        this.setBlockName("Test Transporter");
        this.setLightValue(4.20f);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
//        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
        
//        par1World
        if(!par1World.isRemote){
            System.out.println("Activated");
            if(par5EntityPlayer.dimension==420){
                par5EntityPlayer.travelToDimension(0);
            }else{
                DimensionManagerExtensions.customInitDimension(420);
                par5EntityPlayer.travelToDimension(420);
            }
        }
        return true;
    }
}
