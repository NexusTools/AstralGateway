package nexustools.astralgateway.items;

import net.minecraft.item.Item;

/**
 *
 * @author luke
 */
public class ItemLapisDiamond extends Item{
    public ItemLapisDiamond(int id){
        super(id);
        this.setTextureFile("/nexustools/astralgateway/sheet.png");
        this.setIconIndex(0);
        this.setItemName("Lapis Diamond");
    }
}
