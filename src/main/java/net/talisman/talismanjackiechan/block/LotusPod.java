package net.talisman.talismanjackiechan.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class LotusPod extends Block {

	private static VoxelShape SHAPE;

	private static synchronized VoxelShape getOrCreateShape() {
		if (SHAPE == null) {
			SHAPE = makeShape();
		}
		return SHAPE;
	}

	private static VoxelShape makeShape() {
		VoxelShape shape = Shapes.empty();
		shape = Shapes.or(shape, Shapes.box(0.4375, 0, 0.5625, 0.5625, 0.0625, 0.59375));
		shape = Shapes.or(shape, Shapes.box(0.4375, 0.0625, 0.5625, 0.5625, 0.171875, 0.59375));
		shape = Shapes.or(shape, Shapes.box(0.40625, 0.0625, 0.4375, 0.4375, 0.171875, 0.5625));
		shape = Shapes.or(shape, Shapes.box(0.40625, 0, 0.4375, 0.4375, 0.0625, 0.5625));
		shape = Shapes.or(shape, Shapes.box(0.5625, 0.0625, 0.4375, 0.59375, 0.171875, 0.5625));
		shape = Shapes.or(shape, Shapes.box(0.5625, 0, 0.4375, 0.59375, 0.0625, 0.5625));
		shape = Shapes.or(shape, Shapes.box(0.4375, 0, 0.4375, 0.5625, 0.03125, 0.5625));
		shape = Shapes.or(shape, Shapes.box(0.4375, 0, 0.40625, 0.5625, 0.0625, 0.4375));
		shape = Shapes.or(shape, Shapes.box(0.4375, 0.0625, 0.40625, 0.5625, 0.171875, 0.4375));
		return shape;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return getOrCreateShape();
	}

	public LotusPod() {
		super(BlockBehaviour.Properties.of().sound(SoundType.GRAVEL).strength(1f, 10f).noCollission().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("block.talisman_jackiechan.lotus_pod.description_0"));
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