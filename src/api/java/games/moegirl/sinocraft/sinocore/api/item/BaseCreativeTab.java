package games.moegirl.sinocraft.sinocore.api.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * A create tab impl
 */
public abstract class BaseCreativeTab extends CreativeModeTab {

    private ItemStack icon = null;

    protected BaseCreativeTab(String label) {
        super(label);
    }

    /**
     * Set the icon of this tab
     * @param label The label of the tab.
     * @param iconItem The icon of the tab.
     */
    protected BaseCreativeTab(String label, ItemLike iconItem) {
        super(label);

        icon = iconItem.asItem().getDefaultInstance();
    }

    @Override
    public ItemStack makeIcon() {
        // qyl27: Potato is a meme.
        return Objects.requireNonNullElseGet(icon, () -> new ItemStack(Items.POTATO));
    }
}
