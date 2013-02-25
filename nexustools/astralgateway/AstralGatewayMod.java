/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nexustools.astralgateway;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import nexustools.plugin.JSPlugin;
import nexustools.plugin.NXPlugin;
import nexustools.plugin.NXRunnable;
import nexustools.terrain.JSWorldProvider;

/**
 *
 * @author luke
 */
@Mod(modid = "Astral Gateway", version = "0.1")
@NetworkMod(channels = { "Astral Gateway" }, packetHandler = AGNet.class, clientSideRequired = true)
public class AstralGatewayMod {
    
    Configuration conf = null;
    Configuration worlds = null;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        String scr = "";
        int r = 0;
        byte[] b = new byte[1024];
        InputStream in = AstralGatewayMod.class.getResourceAsStream("/nexustools/astralgateway/internalplugins/TestPlugin.js");
        try {
            while((r = in.read(b))>-1){
                scr += new String(b,0,r);
            }
        } catch (IOException ex) {
            Logger.getLogger(AstralGatewayMod.class.getName()).log(Level.SEVERE, null, ex);
        }
        WorldPluginManager.addPluglin(new JSPlugin(scr), 420);
        String fil = event.getSuggestedConfigurationFile().toString();
        conf = new Configuration(new File(fil));
        worlds = new Configuration(new File(fil.substring(0,fil.lastIndexOf(".")) + "_worlds.cfg"));
        
        for(int i = 0; i < worlds.get("meta", "count", "0").getInt(); i++){
            DimensionManager.registerProviderType(worlds.get("world_" + i, "realid", "0").getInt(), JSWorldProvider.class, true);
        }
        
//        DimensionManager.
        
        DimensionManager.registerProviderType(420, JSWorldProvider.class, true);
        DimensionManager.registerDimension(420, 420);
        
        test = new TestTransporter(4002, 423, Material.rock);
        
//        DimensionManager.unregisterDimension(1);
//        DimensionManager.registerDimension(1, 420);
//        DimensionManager.
        
//        DimensionManager.
//        worlds = new Configuration(new File(fil.toString()))
    }
    
    Sifter sifter = null;
    
    LapisDiamond lapisdiamond = null;
    
    DialerCore dialercore = null;
    DataCore datacore = null;
    
    BlockIronScreen isc = null;
    
    BlockDialer dialer = null;
    
    TestTransporter test = null;
    
    @Init
    public void init(FMLInitializationEvent event) {
        
        GameRegistry.registerBlock(test, "Test Transporter");
        LanguageRegistry.addName(test, "Test Transporter");
        
        GameRegistry.addRecipe(new ItemStack(test),
                "ooo",
                "oeo",
                "ooo",
                'o', new ItemStack(Block.obsidian),
                'e', new ItemStack(Item.enderPearl));
        
//       
//        GameRegistry.addRecipe(new ItemStack(isc),
//                "i i",
//                " i ",
//                "i i",
//                'i', new ItemStack(Item.ingotIron));
//        
//        GameRegistry.addRecipe(new ItemStack(sifter),
//                "sss",
//                "sis",
//                "sss",
//                'i', new ItemStack(isc),
//                's', new ItemStack(Item.stick));
//        
//        GameRegistry.addRecipe(new ItemStack(lapisdiamond),
//                "lll",
//                "ldl",
//                "lrl",
//                'l', new ItemStack(Item.dyePowder, 1, 4),
//                'd', new ItemStack(Item.diamond),
//                'r', new ItemStack(Item.redstone));
//        
//        GameRegistry.addRecipe(new ItemStack(dialercore),
//                "lll",
//                "lel",
//                "lll",
//                'l', new ItemStack(lapisdiamond),
//                'e', new ItemStack(Item.enderPearl));
//        
//        GameRegistry.addRecipe(new ItemStack(datacore),
//                "qqq",
//                "qeq",
//                "qrq",
//                'q', new ItemStack(lapisdiamond),
//                'e', new ItemStack(Item.enderPearl),
//                'r', new ItemStack(Item.redstone));
                
//        DimensionManager.registerProviderType(id, event, true);
    
    }
    
}
