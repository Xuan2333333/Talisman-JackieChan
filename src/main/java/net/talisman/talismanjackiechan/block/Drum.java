package net.talisman.talismanjackiechan.block;

import net.talisman.talismanjackiechan.procedures.Drum1Procedure;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class Drum extends Block {

	private static VoxelShape SHAPE;

	private static synchronized VoxelShape getOrCreateShape() {
		if (SHAPE == null) {
			SHAPE = makeShape();
		}
		return SHAPE;
	}

	private static VoxelShape makeShape() {
		VoxelShape shape = Shapes.empty();
		shape = Shapes.or(shape, Shapes.box(0.406648125, 0, 0.29512875, 0.594148125, 0.01875, 0.70450375));
		shape = Shapes.or(shape, Shapes.box(0.295710625, 0, 0.40606625, 0.705085625, 0.01875, 0.59356625));
		shape = Shapes.or(shape, Shapes.box(0.406648125, 0.38125, 0.29512875, 0.594148125, 0.4, 0.70450375));
		shape = Shapes.or(shape, Shapes.box(0.295710625, 0.38125, 0.40606625, 0.705085625, 0.4, 0.59356625));
		shape = Shapes.or(shape, Shapes.box(0.406878125, 0.0000625, 0.27348375, 0.594378125, 0.4000625, 0.29848375));
		shape = Shapes.or(shape, Shapes.box(0.701960625, 0.0000625, 0.40606625, 0.726960625, 0.4000625, 0.59356625));
		shape = Shapes.or(shape, Shapes.box(0.274295625, 0.0000625, 0.40606625, 0.299295625, 0.4000625, 0.59356625));
		shape = Shapes.or(shape, Shapes.box(0.406878125, 0.0000625, 0.70114875, 0.594378125, 0.4000625, 0.72614875));
		return shape;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return getOrCreateShape();
	}

	public Drum() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("block.talisman_jackiechan.drum.description_0"));
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

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		Drum1Procedure.execute(world, x, y, z);
		return InteractionResult.SUCCESS;
	}
}