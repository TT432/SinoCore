package games.moegirl.sinocraft.sinocore.crafting;

import games.moegirl.sinocraft.sinocore.api.crafting.ICrafting;
import games.moegirl.sinocraft.sinocore.api.crafting.ingredient.CountPredicateSerializer;
import games.moegirl.sinocraft.sinocore.api.crafting.ingredient.PredicateIngredientSerializer;
import games.moegirl.sinocraft.sinocore.api.impl.Crafting;
import net.minecraftforge.common.crafting.CraftingHelper;

public class IngredientRegister {

    public static void register() {
        CraftingHelper.register(ICrafting.PREDICATE_INGREDIENT, PredicateIngredientSerializer.INSTANCE);
        Crafting.INSTANCE.registerPredicate(CountPredicateSerializer.INSTANCE);
    }
}
