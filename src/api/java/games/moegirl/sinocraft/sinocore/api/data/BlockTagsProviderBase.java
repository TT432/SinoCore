package games.moegirl.sinocraft.sinocore.api.data;

import games.moegirl.sinocraft.sinocore.api.data.base.ExtendedBlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagManager;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

/**
 * Register block tags
 *
 * @author skyinr
 */
public abstract class BlockTagsProviderBase extends ExtendedBlockTagsProvider {
    protected String childModId;

    public BlockTagsProviderBase(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, modId, existingFileHelper);
        childModId = modId;
    }

    public BlockTagsProviderBase(DataGenerator pGenerator, String modId, String childModIdIn, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, modId, existingFileHelper);
        childModId = childModIdIn;
    }

    @Override
    public String getName() {
        return "Mod " + childModId + " Block Tags";
    }

    @Override
    protected Path getPath(ResourceLocation loc) {
        var key = this.registry.key();
        return this.generator.getOutputFolder().resolve("data/" + modId + "/" + TagManager.getTagDir(key) + "/" + loc.getPath() + ".json");
    }

    public String getModId() {
        return modId;
    }
}
