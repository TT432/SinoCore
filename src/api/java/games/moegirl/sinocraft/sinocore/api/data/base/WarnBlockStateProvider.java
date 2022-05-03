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
    protected final String mainModId;
    private final WarnBlockModelProvider blockModels;
    private final WarnItemModelProvider itemModels;

    @Deprecated(forRemoval = true, since = "1.1.2")
    public WarnBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
        modId = modid;
        mainModId = modid;

        blockModels = new WarnBlockModelProvider(gen, modid, exFileHelper) {
            @Override
            protected void registerModels() {
            }
        };
        itemModels = new WarnItemModelProvider(gen, modid, this.blockModels.existingFileHelper) {
            @Override
            protected void registerModels() {
            }
        };
    }

    public WarnBlockStateProvider(DataGenerator gen, String modid, String mainModid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
        modId = modid;
        mainModId = mainModid;

        blockModels = new WarnBlockModelProvider(gen, modid, exFileHelper) {
            @Override
            protected void registerModels() {
            }
        };
        itemModels = new WarnItemModelProvider(gen, modid, this.blockModels.existingFileHelper) {
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
        return "BlockStates: " + modId;
    }

    @Override
    public ResourceLocation modLoc(String name) {
        return new ResourceLocation(mainModId, name);
    }
}
