package games.moegirl.sinocraft.sinocore.api.crafting;

import com.google.common.base.Suppliers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;
import java.util.function.Supplier;

public record RecipeHolder<C extends Container, T extends Recipe<C>, S extends AbstractRecipeSerializer<T>>
        (ResourceLocation name, Supplier<? extends Item> sign, RecipeType<T> recipeType, S serializer, Class<T> type) {

    public static <C extends Container, T extends Recipe<C>, S extends AbstractRecipeSerializer<T>> RecipeHolder<C, T, S> register(
            DeferredRegister<RecipeSerializer<?>> registry, RegistryObject<? extends ItemLike> sign, S serializer) {
        ResourceLocation id = sign.getId();
        RecipeType<T> recipeType = RecipeType.register(id.toString());
        registry.register(id.getPath(), () -> serializer);
        return new RecipeHolder<>(id, Suppliers.memoize(() -> sign.get().asItem()), recipeType, serializer, serializer.recipeClass());
    }

    public Optional<T> match(Level level, C container) {
        return level.getRecipeManager().getRecipeFor(recipeType, container, level);
    }

    public Optional<ItemStack> matchResult(Level level, C container) {
        return match(level, container).map(recipe -> recipe.assemble(container));
    }
}
