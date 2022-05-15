package games.moegirl.sinocraft.sinocore.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

/**
 * A block with {@link  BlockEntity}.
 * <p>Base on {@link  BaseEntityBlock}, use model render and impl getTicker method</p>
 * <p>If entity need update, impl {@link  BlockEntityTicker} on BlockEntity.</p>
 */
public abstract class AbstractEntityBlock<T extends BlockEntity> extends BaseEntityBlock {

    protected final BlockEntityType<T> entityType;

    public AbstractEntityBlock(Properties properties, BlockEntityType<T> entityType) {
        super(properties);
        this.entityType = entityType;
    }

    public AbstractEntityBlock(BlockEntityType<T> entityType) {
        this(BlockBehaviour.Properties.of(Material.METAL), entityType);
    }

    public AbstractEntityBlock(Material material, float strength, BlockEntityType<T> entityType) {
        this(BlockBehaviour.Properties.of(material).strength(strength), entityType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return entityType.create(pPos, pState);
    }

    @Override
    @SuppressWarnings("deprecation")
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pBlockEntityType instanceof BlockEntityTicker t ? t : null;
    }
}
