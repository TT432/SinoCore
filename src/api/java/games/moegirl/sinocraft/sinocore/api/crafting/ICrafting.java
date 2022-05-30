package games.moegirl.sinocraft.sinocore.api.crafting;

import games.moegirl.sinocraft.sinocore.api.SinoCoreAPI;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

/**
 * Crafting API
 */
public interface ICrafting {

    ResourceLocation PREDICATE_INGREDIENT = new ResourceLocation(SinoCoreAPI.getId(), "with_predicates");

    /**
     * A predicate to filter item stack's count
     * @param count minimum count
     * @return predicate
     */
    ICraftPredicateSerializer.Predicate<?> count(int count);

    /**
     * Add a new predicate
     * @param serializer predicate serializer
     */
    void registerPredicate(ICraftPredicateSerializer<?> serializer);

    /**
     * Get a predicate serializer
     * @param id predicate id
     * @return serializer
     */
    Optional<ICraftPredicateSerializer<?>> getPredicateSerializer(ResourceLocation id);
}
