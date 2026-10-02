package net.talisman.talismanjackiechan.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.talisman.talismanjackiechan.menu.StoneSteleMenu;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class StoneStele extends HorizontalDirectionalBlock implements EntityBlock {

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
        shape = Shapes.or(shape, Shapes.box(0.165625, 0, 0.21875, 0.18125, 0.03125, 0.234375));
        shape = Shapes.or(shape, Shapes.box(0.1, 0, 0.21875, 0.115625, 0.03125, 0.234375));
        shape = Shapes.or(shape, Shapes.box(0.09375, 0, 0.15625, 0.1875, 0.0625, 0.21875));
        shape = Shapes.or(shape, Shapes.box(0.09375, 0, 0.03125, 0.1875, 0.1875, 0.15625));
        shape = Shapes.or(shape, Shapes.box(0.121875, 0, 0.21875, 0.1375, 0.03125, 0.234375));
        shape = Shapes.or(shape, Shapes.box(0.14375, 0, 0.21875, 0.159375, 0.03125, 0.234375));
        shape = Shapes.or(shape, Shapes.box(0.165625, 0, 1.03125, 0.18125, 0.03125, 1.046875));
        shape = Shapes.or(shape, Shapes.box(0.1, 0, 1.03125, 0.115625, 0.03125, 1.046875));
        shape = Shapes.or(shape, Shapes.box(0.09375, 0, 0.96875, 0.1875, 0.0625, 1.03125));
        shape = Shapes.or(shape, Shapes.box(0.09375, 0, 0.84375, 0.1875, 0.1875, 0.96875));
        shape = Shapes.or(shape, Shapes.box(0.121875, 0, 1.03125, 0.1375, 0.03125, 1.046875));
        shape = Shapes.or(shape, Shapes.box(0.14375, 0, 1.03125, 0.159375, 0.03125, 1.046875));
        shape = Shapes.or(shape, Shapes.box(0.81875, 0, 0.21875, 0.834375, 0.03125, 0.234375));
        shape = Shapes.or(shape, Shapes.box(0.884375, 0, 0.21875, 0.9, 0.03125, 0.234375));
        shape = Shapes.or(shape, Shapes.box(0.8125, 0, 0.15625, 0.90625, 0.0625, 0.21875));
        shape = Shapes.or(shape, Shapes.box(0.8125, 0, 0.03125, 0.90625, 0.1875, 0.15625));
        shape = Shapes.or(shape, Shapes.box(0.8625, 0, 0.21875, 0.878125, 0.03125, 0.234375));
        shape = Shapes.or(shape, Shapes.box(0.840625, 0, 0.21875, 0.85625, 0.03125, 0.234375));
        shape = Shapes.or(shape, Shapes.box(0.81875, 0, 1.03125, 0.834375, 0.03125, 1.046875));
        shape = Shapes.or(shape, Shapes.box(0.884375, 0, 1.03125, 0.9, 0.03125, 1.046875));
        shape = Shapes.or(shape, Shapes.box(0.8125, 0, 0.96875, 0.90625, 0.0625, 1.03125));
        shape = Shapes.or(shape, Shapes.box(0.8125, 0, 0.84375, 0.90625, 0.1875, 0.96875));
        shape = Shapes.or(shape, Shapes.box(0.8625, 0, 1.03125, 0.878125, 0.03125, 1.046875));
        shape = Shapes.or(shape, Shapes.box(0.840625, 0, 1.03125, 0.85625, 0.03125, 1.046875));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.078125, -0.0625, 0.875, 0.28125, 1.078125));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0.4375, 0.09375, 0.8125, 0.5625, 0.9375));
        shape = Shapes.or(shape, Shapes.box(0.234375, 1.828125, 0.40625, 0.765625, 2, 0.625));
        shape = Shapes.or(shape, Shapes.box(0.25, 0.5625, 0.421875, 0.75, 1.828125, 0.609375));
        shape = Shapes.or(shape, Shapes.box(0.15625, 0.28125, 0, 0.84375, 0.4375, 1.03125));
        return shape;
    }

    private static VoxelShape rotateY(VoxelShape shape, int times) {
        VoxelShape result = shape;
        int n = ((times % 4) + 4) % 4;
        for (int i = 0; i < n; i++) result = rotateYOnce(result);
        return result;
    }

    private static VoxelShape rotateYOnce(VoxelShape shape) {
        List<AABB> boxes = new ArrayList<>();
        shape.forAllBoxes((x1, y1, z1, x2, y2, z2) -> boxes.add(new AABB(1 - z2, y1, x1, 1 - z1, y2, x2)));
        VoxelShape result = Shapes.empty();
        for (AABB box : boxes) result = Shapes.or(result, Shapes.create(box));
        return result;
    }

    public StoneStele() {
        super(BlockBehaviour.Properties.of()
                .sound(SoundType.STONE)
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
        list.add(Component.translatable("block.talisman_jackiechan.stone_stele.description_0"));
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StoneSteleBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof StoneSteleBlockEntity stele)) return InteractionResult.PASS;

        Direction facing = state.getValue(FACING);
        Direction hitDir = hit.getDirection();

        boolean isFront = hitDir == facing.getOpposite();
        boolean isBack  = hitDir == facing;
        if (!isFront && !isBack) return InteractionResult.PASS;

        final boolean front = isFront;
        NetworkHooks.openScreen((ServerPlayer) player, new SimpleMenuProvider(
                (id, inv, p) -> new StoneSteleMenu(id, inv, pos, front),
                Component.translatable("block.talisman_jackiechan.stone_stele")
        ), buf -> {
            buf.writeBlockPos(pos);
            buf.writeBoolean(front);
            buf.writeUtf(stele.getFrontText(), 4096);
            buf.writeUtf(stele.getBackText(), 4096);
        });

        return InteractionResult.SUCCESS;
    }
}