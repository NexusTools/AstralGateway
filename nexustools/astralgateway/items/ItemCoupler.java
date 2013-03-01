package nexustools.astralgateway.items;

import net.minecraft.item.Item;

/**
 *
 * @author luke
 */
public class ItemCoupler extends Item{
    public ItemCoupler(int id){
        super(id);
        this.setTextureFile("/nexustools/astralgateway/sheet.png");
        this.setIconIndex(1);
        this.setItemName("Coupler");
    }
}
