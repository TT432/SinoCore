package games.moegirl.sinocraft.sinocore.api.crafting;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonObject;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class AbstractRecipeSerializer<T extends Recipe<?>> extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T> {

    private final Class<T> type;

    public AbstractRecipeSerializer() {
        type = (Class<T>) TypeToken.of(getClass()).getRawType();
    }

    public abstract void toJson(JsonObject pJson, T pRecipe);

    public Class<T> recipeClass() {
        return type;
    }
}
