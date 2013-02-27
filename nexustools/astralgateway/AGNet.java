package nexustools.astralgateway;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 *
 * @author luke
 */
public class AGNet implements IPacketHandler{

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
