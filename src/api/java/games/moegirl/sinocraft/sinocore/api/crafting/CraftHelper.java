package games.moegirl.sinocraft.sinocore.api.crafting;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.ints.*;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CraftHelper {

    @Nullable
    public static Int2ObjectMap<Ingredient> matchShapeless(Container inputs, Ingredient[] ingredients) {
        return matchShapeless(inputs, Arrays.asList(ingredients));
    }

    @Nullable
    public static Int2ObjectMap<Ingredient> matchShapeless(Container inputs, Iterable<Ingredient> ingredients) {
        List<Ingredient> notFound = new LinkedList<>();
        for (Ingredient ingredient : ingredients) {
            if (!ingredient.isEmpty()) {
                notFound.add(ingredient);
            }
        }
        if (notFound.size() > inputs.getContainerSize()) {
            return null;
        }
        Int2ObjectMap<Ingredient> result = new Int2ObjectArrayMap<>(notFound.size());
        // loop0
        boolean[] take = new boolean[inputs.getContainerSize()];
        Arrays.fill(take, false);
        Iterator<Ingredient> iterator = notFound.iterator();
        while (iterator.hasNext()) {
            Ingredient ingredient = iterator.next();
            for (int i = 0; i < take.length; i++) {
                if (take[i]) {
                    continue;
                }
                if (ingredient.test(inputs.getItem(i))) {
                    take[i] = true;
                    result.put(i, ingredient);
                    iterator.remove();
                }
            }
        }
        if (notFound.isEmpty()) {
            return result;
        }

        // loop1: build table
        List<Pair<Ingredient, IntList>> list = new LinkedList<>();
        for (Ingredient ingredient : notFound) {
            IntList l = new IntArrayList();
            for (int i = 0; i < take.length; i++) {
                if (!take[i]) {
                    continue;
                }
                if (ingredient.test(inputs.getItem(i))) {
                    l.add(i);
                }
            }
            if (l.isEmpty()) {
                return null;
            }
            list.add(Pair.of(ingredient, l));
        }

        // loop2: adjust
        for (Pair<Ingredient, IntList> pair : list) {
            IntList second = pair.second();
            IntListIterator itr = second.iterator();
            while (itr.hasNext()) {
                int i = itr.nextInt();
                Ingredient exist = result.get(i);
                boolean failed = true;
                for (int t = 0; t < take.length; t++) {
                    if (take[t]) {
                        continue;
                    }
                    if (exist.test(inputs.getItem(t))) {
                        take[t] = true;
                        result.put(t, exist);
                        result.put(i, pair.first());
                        failed = false;
                    }
                }
                if (failed) {
                    return null;
                }
            }
        }

        return result;
    }
}
