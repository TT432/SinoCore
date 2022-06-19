package games.moegirl.sinocraft.sinocore.api.network;

import games.moegirl.sinocraft.sinocore.api.utility.Id;
import net.minecraftforge.network.simple.SimpleChannel;

public interface NetworkApi {

    SimpleChannel channel();

    Id id();
}
