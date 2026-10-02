package net.talisman.talismanjackiechan.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SmallCenser extends Block {

    public static final BooleanProperty ON_SLAB = BooleanProperty.create("on_slab");

    public static final double SCALE = 2.0D;

    public static final double CENTER_X = 0.5D;
    public static final double CENTER_Y = 0.0D;
    public static final double CENTER_Z = 0.5D;

    private static VoxelShape SHAPE;

    public SmallCenser() {
        super(BlockBehaviour.Properties.of()
                .sound(SoundType.STONE)
                .strength(1f, 10f)
                .noOcclusion()
                .isRedstoneConductor((bs, br, bp) -> false));
        registerDefaultState(stateDefinition.any().setValue(ON_SLAB, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ON_SLAB);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState below = ctx.getLevel().getBlockState(ctx.getClickedPos().below());
        return defaultBlockState().setValue(ON_SLAB, isBottomSlab(below));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                  LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (direction == Direction.DOWN) {
            return state.setValue(ON_SLAB, isBottomSlab(neighborState));
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    private static boolean isBottomSlab(BlockState state) {
        return state.getBlock() instanceof SlabBlock
                && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM;
    }

    private static synchronized VoxelShape getOrCreateShape() {
        if (SHAPE == null) {
            SHAPE = scaleShape(makeShape(), SCALE, CENTER_X, CENTER_Y, CENTER_Z);
        }
        return SHAPE;
    }

    private static VoxelShape scaleShape(VoxelShape original, double scale, double cx, double cy, double cz) {
        List<AABB> boxes = new ArrayList<>();
        original.forAllBoxes((x1, y1, z1, x2, y2, z2) -> boxes.add(new AABB(
                cx + (x1 - cx) * scale,
                cy + (y1 - cy) * scale,
                cz + (z1 - cz) * scale,
                cx + (x2 - cx) * scale,
                cy + (y2 - cy) * scale,
                cz + (z2 - cz) * scale
        )));

        VoxelShape result = Shapes.empty();
        for (AABB box : boxes) {
            result = Shapes.or(result, Shapes.create(box));
        }
        return result;
    }

    private static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0.4375, 0, 0.390625, 0.5625, 0.015625, 0.40625));
        shape = Shapes.or(shape, Shapes.box(0.59375, 0, 0.4375, 0.609375, 0.015625, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0, 0.59375, 0.5625, 0.015625, 0.609375));
        shape = Shapes.or(shape, Shapes.box(0.390625, 0, 0.4375, 0.40625, 0.015625, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.421875, 0.015625, 0.421875, 0.578125, 0.040625, 0.578125));
        shape = Shapes.or(shape, Shapes.box(0.403125, 0.015625, 0.4375, 0.421875, 0.040625, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.578125, 0.015625, 0.4375, 0.596875, 0.040625, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0.015625, 0.578125, 0.5625, 0.040625, 0.596875));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0.015625, 0.403125, 0.5625, 0.040625, 0.421875));
        shape = Shapes.or(shape, Shapes.box(0.4125, 0.040625, 0.4125, 0.5875, 0.065625, 0.5875));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0.040625, 0.3875, 0.5625, 0.065625, 0.4125));
        shape = Shapes.or(shape, Shapes.box(0.5875, 0.040625, 0.4375, 0.6125, 0.065625, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0.040625, 0.5875, 0.5625, 0.065625, 0.6125));
        shape = Shapes.or(shape, Shapes.box(0.3875, 0.040625, 0.4375, 0.4125, 0.065625, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.421875, 0.065625, 0.371875, 0.578125, 0.090625, 0.396875));
        shape = Shapes.or(shape, Shapes.box(0.603125, 0.065625, 0.421875, 0.628125, 0.090625, 0.578125));
        shape = Shapes.or(shape, Shapes.box(0.421875, 0.065625, 0.603125, 0.578125, 0.090625, 0.628125));
        shape = Shapes.or(shape, Shapes.box(0.371875, 0.065625, 0.421875, 0.396875, 0.090625, 0.578125));
        shape = Shapes.or(shape, Shapes.box(0.4125, 0.0875, 0.4125, 0.5875, 0.1125, 0.5875));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0.0875, 0.3875, 0.5625, 0.1125, 0.4125));
        shape = Shapes.or(shape, Shapes.box(0.5875, 0.0875, 0.4375, 0.6125, 0.1125, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0.0875, 0.5875, 0.5625, 0.1125, 0.6125));
        shape = Shapes.or(shape, Shapes.box(0.3875, 0.0875, 0.4375, 0.4125, 0.1125, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.421875, 0.1125, 0.421875, 0.578125, 0.1375, 0.578125));
        shape = Shapes.or(shape, Shapes.box(0.403125, 0.1125, 0.4375, 0.421875, 0.1375, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.578125, 0.1125, 0.4375, 0.596875, 0.1375, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0.1125, 0.578125, 0.5625, 0.1375, 0.596875));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0.1125, 0.403125, 0.5625, 0.1375, 0.421875));
        shape = Shapes.or(shape, Shapes.box(0.428125, 0.1375, 0.4125, 0.44375, 0.146875, 0.428125));
        shape = Shapes.or(shape, Shapes.box(0.4125, 0.1375, 0.4125, 0.428125, 0.146875, 0.428125));
        shape = Shapes.or(shape, Shapes.box(0.4125, 0.1375, 0.428125, 0.428125, 0.146875, 0.44375));
        shape = Shapes.or(shape, Shapes.box(0.571875, 0.1375, 0.428125, 0.5875, 0.146875, 0.44375));
        shape = Shapes.or(shape, Shapes.box(0.571875, 0.1375, 0.4125, 0.5875, 0.146875, 0.428125));
        shape = Shapes.or(shape, Shapes.box(0.55625, 0.1375, 0.4125, 0.571875, 0.146875, 0.428125));
        shape = Shapes.or(shape, Shapes.box(0.55625, 0.1375, 0.571875, 0.571875, 0.146875, 0.5875));
        shape = Shapes.or(shape, Shapes.box(0.571875, 0.1375, 0.571875, 0.5875, 0.146875, 0.5875));
        shape = Shapes.or(shape, Shapes.box(0.571875, 0.1375, 0.55625, 0.5875, 0.146875, 0.571875));
        shape = Shapes.or(shape, Shapes.box(0.4125, 0.1375, 0.55625, 0.428125, 0.146875, 0.571875));
        shape = Shapes.or(shape, Shapes.box(0.4125, 0.1375, 0.571875, 0.428125, 0.146875, 0.5875));
        shape = Shapes.or(shape, Shapes.box(0.428125, 0.1375, 0.571875, 0.44375, 0.146875, 0.5875));
        shape = Shapes.or(shape, Shapes.box(0.40625, 0, 0.40625, 0.59375, 0.015625, 0.59375));
        shape = Shapes.or(shape, Shapes.box(0.396875, 0.065625, 0.396875, 0.603125, 0.090625, 0.603125));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0.1375, 0.39375, 0.5625, 0.146875, 0.4125));
        shape = Shapes.or(shape, Shapes.box(0.5875, 0.1375, 0.4375, 0.60625, 0.146875, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0.1375, 0.5875, 0.5625, 0.146875, 0.60625));
        shape = Shapes.or(shape, Shapes.box(0.39375, 0.1375, 0.4375, 0.4125, 0.146875, 0.5625));
        return shape;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape base = getOrCreateShape();
        if (state.getValue(ON_SLAB)) {
            return base.move(0, -0.5, 0);
        }
        return base;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }
}