package games.moegirl.sinocraft.sinocore.api.crafting.ingredient;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import games.moegirl.sinocraft.sinocore.api.crafting.CraftPredicateSerializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * An ingredient use {@link CraftPredicateSerializer.Predicate}
 * to filter stacks
 *
 * @deprecated remove because it is difficult to use to remove item from container after crafting
 */
@Deprecated(forRemoval = true)
public class PredicateIngredient extends Ingredient {

    public static PredicateIngredient of(Ingredient ingredient,
                                         Collection<? extends CraftPredicateSerializer.Predicate<?>> predicates) {
        return new PredicateIngredient(new Value(ingredient, predicates));
    }

    final Value value;

    PredicateIngredient(Value value) {
        super(Stream.of(value));
        this.value = value;
    }

    @Override
    public boolean test(@Nullable ItemStack stack) {
        return value.ingredient.test(stack) && value.predicates.stream().allMatch(m -> m.test(stack));
    }

    @Override
    public JsonElement toJson() {
        return PredicateIngredientSerializer.INSTANCE.write(this);
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    record Value(Ingredient ingredient,
                 Collection<? extends CraftPredicateSerializer.Predicate<?>> predicates) implements Ingredient.Value {

        @Override
        public Collection<ItemStack> getItems() {
            List<ItemStack> input = Arrays.stream(ingredient.getItems()).toList();
            if (input.isEmpty()) {
                return Collections.emptyList();
            }
            for (CraftPredicateSerializer.Predicate<?> modifier : predicates) {
                input = modifier.getAllStack(input);
                if (input.isEmpty()) {
                    return Collections.emptyList();
                }
            }
            return input;
        }

        @Override
        public JsonObject serialize() {
            return PredicateIngredientSerializer.INSTANCE.writeValue(this);
        }
    }
}
