package games.moegirl.sinocraft.sinocore.api.crafting;

import net.minecraft.world.Container;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

/**
 * Crafting API
 */
public interface CraftingApi {

    RecipeContainer createContainer(Container items, IFluidHandler fluids);

    RecipeContainer createTankContainer(Container items, IFluidTank fluid);
}
