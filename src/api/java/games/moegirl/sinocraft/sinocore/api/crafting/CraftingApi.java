package games.moegirl.sinocraft.sinocore.api.crafting;

import games.moegirl.sinocraft.sinocore.api.SinoCoreAPI;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Optional;

/**
 * Crafting API
 */
public interface CraftingApi {

    @Deprecated(forRemoval = true)
    ResourceLocation PREDICATE_INGREDIENT = new ResourceLocation(SinoCoreAPI.getId(), "with_predicates");

    /**
     * A predicate to filter item stack's count
     * @param count minimum count
     * @return predicate
     * @deprecated remove because it is difficult to use to remove item from container after crafting
     */
    @Deprecated(forRemoval = true)
    CraftPredicateSerializer.Predicate<?> count(int count);

    /**
     * Add a new predicate
     * @param serializer predicate serializer
     * @deprecated remove because it is difficult to use to remove item from container after crafting
     */
    @Deprecated(forRemoval = true)
    void registerPredicate(CraftPredicateSerializer<?> serializer);

    /**
     * Get a predicate serializer
     * @param id predicate id
     * @return serializer
     * @deprecated remove because it is difficult to use to remove item from container after crafting
     */
    @Deprecated(forRemoval = true)
    Optional<CraftPredicateSerializer<?>> getPredicateSerializer(ResourceLocation id);

    RecipeContainer createContainer(Container items, IFluidHandler fluids);

    RecipeContainer createTankContainer(Container items, IFluidTank fluid);
}
