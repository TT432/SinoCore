package games.moegirl.sinocraft.sinocore.crafting.recipe.test;

import com.google.gson.annotations.Expose;
import games.moegirl.sinocraft.sinocore.SinoCore;
import games.moegirl.sinocraft.sinocore.crafting.recipe.base.BaseRecipe;
import games.moegirl.sinocraft.sinocore.crafting.recipe.base.Recipe;
import games.moegirl.sinocraft.sinocore.utility.json.serializer.IngredientSerializer;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

/**
 * @author DustW
 **/
@Recipe(SinoCore.MODID + ":test")
@Mod.EventBusSubscriber
public class TestRecipe extends BaseRecipe<CraftingContainer> implements CraftingRecipe {

    @Expose NonNullList<Ingredient> inputs;
    @Expose ItemStack result;

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return RecipeMatcher.findMatches(inputs, this.inputs) != null;
    }

    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    @SubscribeEvent
    public static void onEvent(PlayerEvent.PlayerLoggedInEvent event) {
        var recipes = event.getPlayer().getLevel().getRecipeManager()
                .getAllRecipesFor(RecipeType.CRAFTING).stream().filter(r -> r instanceof TestRecipe).toArray();

        if (recipes.length > 0) {
            var b = recipes;
        }
    }
}
