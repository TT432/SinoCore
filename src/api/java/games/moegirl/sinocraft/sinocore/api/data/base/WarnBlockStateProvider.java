package games.moegirl.sinocraft.sinocore.api.data.base;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.io.IOException;

public abstract class WarnBlockStateProvider extends BlockStateProvider {
    protected final String modId;
    protected final String childModId;
    private final WarnBlockModelProvider blockModels;
    private final WarnItemModelProvider itemModels;

    public WarnBlockStateProvider(DataGenerator gen, String modid, String childModid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
        modId = modid;
        childModId = childModid;

        blockModels = new WarnBlockModelProvider(gen, modid, exFileHelper) {
            @Override
            protected void registerModels() {
            }
        };
        itemModels = new WarnItemModelProvider(gen, modid, childModId, this.blockModels.existingFileHelper) {
            @Override
            protected void registerModels() {
            }
        };
    }

    @Override
    public void run(HashCache cache) throws IOException {
        super.run(cache);
        blockModels.printAllExceptions();
        itemModels.printAllExceptions();
    }

    protected abstract void registerStatesAndModels();

    public BlockModelProvider models() {
        return blockModels;
    }

    public ItemModelProvider itemModels() {
        return itemModels;
    }

    @Nonnull
    @Override
    public String getName() {
        return "BlockStates: " + childModId;
    }

    @Override
    public ResourceLocation modLoc(String name) {
        return new ResourceLocation(childModId, name);
    }
}
