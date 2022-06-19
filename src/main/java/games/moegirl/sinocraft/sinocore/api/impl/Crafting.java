package games.moegirl.sinocraft.sinocore.api.impl;

import games.moegirl.sinocraft.sinocore.api.crafting.CraftPredicateSerializer;
import games.moegirl.sinocraft.sinocore.api.crafting.CraftingApi;
import games.moegirl.sinocraft.sinocore.api.crafting.RecipeContainer;
import games.moegirl.sinocraft.sinocore.api.crafting.ingredient.CountPredicateSerializer;
import games.moegirl.sinocraft.sinocore.crafting.FluidRecipeContainer;
import games.moegirl.sinocraft.sinocore.crafting.FluidsRecipeContainer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Crafting implements CraftingApi {

    INSTANCE;

    private static final Map<ResourceLocation, CraftPredicateSerializer<?>> SERIALIZER_MAP = new HashMap<>();

    @Override
    public CraftPredicateSerializer.Predicate<?> count(int count) {
        return new CountPredicateSerializer.Predicate(count);
    }

    @Override
    public void registerPredicate(CraftPredicateSerializer<?> serializer) {
        SERIALIZER_MAP.put(serializer.id(), serializer);
    }

    @Override
    public Optional<CraftPredicateSerializer<?>> getPredicateSerializer(ResourceLocation id) {
        return Optional.ofNullable(SERIALIZER_MAP.get(id));
    }

    @Override
    public RecipeContainer createContainer(Container items, IFluidHandler fluids) {
        return new FluidsRecipeContainer(items, fluids);
    }

    @Override
    public RecipeContainer createTankContainer(Container items, IFluidTank fluid) {
        return new FluidRecipeContainer(items, fluid);
    }
}
