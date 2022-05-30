package games.moegirl.sinocraft.sinocore.api.impl;

import games.moegirl.sinocraft.sinocore.api.crafting.ICraftPredicateSerializer;
import games.moegirl.sinocraft.sinocore.api.crafting.ICrafting;
import games.moegirl.sinocraft.sinocore.api.crafting.ingredient.CountPredicateSerializer;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Crafting implements ICrafting {

    INSTANCE;

    private static final Map<ResourceLocation, ICraftPredicateSerializer<?>> SERIALIZER_MAP = new HashMap<>();

    @Override
    public ICraftPredicateSerializer.Predicate<?> count(int count) {
        return new CountPredicateSerializer.Predicate(count);
    }

    @Override
    public void registerPredicate(ICraftPredicateSerializer<?> serializer) {
        SERIALIZER_MAP.put(serializer.id(), serializer);
    }

    @Override
    public Optional<ICraftPredicateSerializer<?>> getPredicateSerializer(ResourceLocation id) {
        return Optional.ofNullable(SERIALIZER_MAP.get(id));
    }
}
