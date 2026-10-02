package net.talisman.talismanjackiechan.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Censer extends HorizontalDirectionalBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static Map<Direction, VoxelShape> SHAPES;

    private static synchronized void initShapes() {
        if (SHAPES != null) return;
        VoxelShape base = makeShape();
        Map<Direction, VoxelShape> map = new EnumMap<>(Direction.class);
        map.put(Direction.NORTH, base);
        map.put(Direction.EAST, rotateY(base, 1));
        map.put(Direction.SOUTH, rotateY(base, 2));
        map.put(Direction.WEST, rotateY(base, 3));
        SHAPES = map;
    }

    private static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(1.1875, 0.0625, 0, 1.28125, 0.265625, 0.09375));
        shape = Shapes.or(shape, Shapes.box(1.1875, 0, -0.01875, 1.3, 0.0625, 0.09375));
        shape = Shapes.or(shape, Shapes.box(-0.28125, 0.265625, 0, 1.28125, 0.328125, 1));
        shape = Shapes.or(shape, Shapes.box(-0.2875, 0.328125, -0.00625, 1.2875, 0.390625, 0.05625));
        shape = Shapes.or(shape, Shapes.box(-0.2875, 0.328125, 0.94375, 1.2875, 0.390625, 1.00625));
        shape = Shapes.or(shape, Shapes.box(-0.29375, 0.390625, 0.95, 1.29375, 0.453125, 1.0125));
        shape = Shapes.or(shape, Shapes.box(-0.29375, 0.390625, 0.05, -0.215625, 0.453125, 0.95));
        shape = Shapes.or(shape, Shapes.box(-0.29375, 0.390625, -0.0125, 1.29375, 0.453125, 0.05));
        shape = Shapes.or(shape, Shapes.box(-0.2875, 0.328125, 0.05625, -0.209375, 0.390625, 0.94375));
        shape = Shapes.or(shape, Shapes.box(1.209375, 0.328125, 0.05625, 1.2875, 0.390625, 0.94375));
        shape = Shapes.or(shape, Shapes.box(1.215625, 0.390625, 0.05, 1.29375, 0.453125, 0.95));
        shape = Shapes.or(shape, Shapes.box(-0.3, 0.453125, 0.95625, 1.3, 0.515625, 1.01875));
        shape = Shapes.or(shape, Shapes.box(-0.3, 0.453125, 0.04375, -0.221875, 0.515625, 0.95625));
        shape = Shapes.or(shape, Shapes.box(-0.3, 0.453125, -0.01875, 1.3, 0.515625, 0.04375));
        shape = Shapes.or(shape, Shapes.box(1.221875, 0.453125, 0.04375, 1.3, 0.515625, 0.95625));
        shape = Shapes.or(shape, Shapes.box(-0.30625, 0.515625, 0.9625, 1.30625, 0.578125, 1.025));
        shape = Shapes.or(shape, Shapes.box(-0.30625, 0.515625, 0.0375, -0.228125, 0.578125, 0.9625));
        shape = Shapes.or(shape, Shapes.box(-0.30625, 0.515625, -0.025, 1.30625, 0.578125, 0.0375));
        shape = Shapes.or(shape, Shapes.box(1.228125, 0.515625, 0.0375, 1.30625, 0.578125, 0.9625));
        shape = Shapes.or(shape, Shapes.box(-0.3125, 0.578125, 0.96875, 1.3125, 0.640625, 1.03125));
        shape = Shapes.or(shape, Shapes.box(-0.3125, 0.578125, 0.03125, -0.234375, 0.640625, 0.96875));
        shape = Shapes.or(shape, Shapes.box(-0.3125, 0.578125, -0.03125, 1.3125, 0.640625, 0.03125));
        shape = Shapes.or(shape, Shapes.box(1.234375, 0.578125, 0.03125, 1.3125, 0.640625, 0.96875));
        shape = Shapes.or(shape, Shapes.box(1.240625, 0.640625, 0.025, 1.31875, 0.703125, 0.975));
        shape = Shapes.or(shape, Shapes.box(-0.31875, 0.640625, 0.025, -0.240625, 0.703125, 0.975));
        shape = Shapes.or(shape, Shapes.box(-0.31875, 0.640625, -0.0375, 1.31875, 0.703125, 0.025));
        shape = Shapes.or(shape, Shapes.box(-0.31875, 0.640625, 0.975, 1.31875, 0.703125, 1.0375));
        shape = Shapes.or(shape, Shapes.box(1.246875, 0.703125, 0.01875, 1.325, 0.765625, 0.98125));
        shape = Shapes.or(shape, Shapes.box(-0.325, 0.703125, 0.01875, -0.246875, 0.765625, 0.98125));
        shape = Shapes.or(shape, Shapes.box(-0.325, 0.703125, -0.04375, 1.325, 0.765625, 0.01875));
        shape = Shapes.or(shape, Shapes.box(-0.325, 0.703125, 0.98125, 1.325, 0.765625, 1.04375));
        shape = Shapes.or(shape, Shapes.box(1.309375, 0.578125, 0.39375, 1.475, 0.640625, 0.60625));
        shape = Shapes.or(shape, Shapes.box(1.390625, 0.640625, 0.39375, 1.475, 0.971875, 0.60625));
        shape = Shapes.or(shape, Shapes.box(1.475, 0.878125, 0.39375, 1.63125, 0.971875, 0.60625));
        shape = Shapes.or(shape, Shapes.box(-0.63125, 0.878125, 0.39375, -0.475, 0.971875, 0.60625));
        shape = Shapes.or(shape, Shapes.box(-0.475, 0.578125, 0.39375, -0.309375, 0.640625, 0.60625));
        shape = Shapes.or(shape, Shapes.box(-0.475, 0.640625, 0.39375, -0.390625, 0.971875, 0.60625));
        shape = Shapes.or(shape, Shapes.box(1.19375, 0.765625, 0.984375, 1.25, 1.590625, 1.040625));
        shape = Shapes.or(shape, Shapes.box(-0.25, 0.765625, 0.984375, -0.19375, 1.590625, 1.040625));
        shape = Shapes.or(shape, Shapes.box(-0.25, 0.765625, -0.040625, -0.19375, 1.590625, 0.015625));
        shape = Shapes.or(shape, Shapes.box(1.19375, 0.765625, -0.040625, 1.25, 1.590625, 0.015625));
        shape = Shapes.or(shape, Shapes.box(1.078125, 0.203125, 0, 1.1875, 0.265625, 0.09375));
        shape = Shapes.or(shape, Shapes.box(1.125, 0.140625, 0, 1.1875, 0.203125, 0.09375));
        shape = Shapes.or(shape, Shapes.box(1.1875, 0.140625, 0.09375, 1.28125, 0.203125, 0.15625));
        shape = Shapes.or(shape, Shapes.box(1.1875, 0.203125, 0.09375, 1.28125, 0.265625, 0.203125));
        shape = Shapes.or(shape, Shapes.box(-0.28125, 0.140625, 0.09375, -0.1875, 0.203125, 0.15625));
        shape = Shapes.or(shape, Shapes.box(-0.28125, 0.0625, 0, -0.1875, 0.265625, 0.09375));
        shape = Shapes.or(shape, Shapes.box(-0.3, 0, -0.01875, -0.1875, 0.0625, 0.09375));
        shape = Shapes.or(shape, Shapes.box(-0.1875, 0.203125, 0, -0.078125, 0.265625, 0.09375));
        shape = Shapes.or(shape, Shapes.box(-0.1875, 0.140625, 0, -0.125, 0.203125, 0.09375));
        shape = Shapes.or(shape, Shapes.box(-0.28125, 0.203125, 0.09375, -0.1875, 0.265625, 0.203125));
        shape = Shapes.or(shape, Shapes.box(-0.28125, 0.0625, 0.90625, -0.1875, 0.265625, 1));
        shape = Shapes.or(shape, Shapes.box(-0.3, 0, 0.90625, -0.1875, 0.0625, 1.01875));
        shape = Shapes.or(shape, Shapes.box(-0.1875, 0.203125, 0.90625, -0.078125, 0.265625, 1));
        shape = Shapes.or(shape, Shapes.box(-0.1875, 0.140625, 0.90625, -0.125, 0.203125, 1));
        shape = Shapes.or(shape, Shapes.box(-0.28125, 0.140625, 0.84375, -0.1875, 0.203125, 0.90625));
        shape = Shapes.or(shape, Shapes.box(-0.28125, 0.203125, 0.796875, -0.1875, 0.265625, 0.90625));
        shape = Shapes.or(shape, Shapes.box(1.1875, 0.140625, 0.84375, 1.28125, 0.203125, 0.90625));
        shape = Shapes.or(shape, Shapes.box(1.1875, 0.0625, 0.90625, 1.28125, 0.265625, 1));
        shape = Shapes.or(shape, Shapes.box(1.1875, 0, 0.90625, 1.3, 0.0625, 1.01875));
        shape = Shapes.or(shape, Shapes.box(1.078125, 0.203125, 0.90625, 1.1875, 0.265625, 1));
        shape = Shapes.or(shape, Shapes.box(1.125, 0.140625, 0.90625, 1.1875, 0.203125, 1));
        shape = Shapes.or(shape, Shapes.box(1.1875, 0.203125, 0.796875, 1.28125, 0.265625, 0.90625));
        shape = Shapes.or(shape, Shapes.box(-0.26875, 1.590625, -0.0625, 1.26875, 1.653125, 1.0625));
        return shape;
    }

    private static VoxelShape rotateY(VoxelShape shape, int times) {
        VoxelShape result = shape;
        int n = ((times % 4) + 4) % 4;
        for (int i = 0; i < n; i++) {
            result = rotateYOnce(result);
        }
        return result;
    }

    private static VoxelShape rotateYOnce(VoxelShape shape) {
        List<AABB> boxes = new ArrayList<>();
        shape.forAllBoxes((x1, y1, z1, x2, y2, z2) ->
                boxes.add(new AABB(1 - z2, y1, x1, 1 - z1, y2, x2)));
        VoxelShape result = Shapes.empty();
        for (AABB box : boxes) {
            result = Shapes.or(result, Shapes.create(box));
        }
        return result;
    }

    public Censer() {
        super(BlockBehaviour.Properties.of()
                .sound(SoundType.METAL)
                .strength(1f, 10f)
                .noOcclusion()
                .isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        initShapes();
        return SHAPES.getOrDefault(state.getValue(FACING), SHAPES.get(Direction.NORTH));
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