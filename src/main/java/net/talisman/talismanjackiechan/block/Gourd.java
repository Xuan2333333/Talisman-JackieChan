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

public class Gourd extends Block {

	private static VoxelShape SHAPE;

	private static synchronized VoxelShape getOrCreateShape() {
		if (SHAPE == null) {
			SHAPE = makeShape();
		}
		return SHAPE;
	}

	private static VoxelShape makeShape() {
		VoxelShape shape = Shapes.empty();
		shape = Shapes.or(shape, Shapes.box(0.5625, 0.1875, 0.4375, 0.625, 0.25, 0.5625));
		shape = Shapes.or(shape, Shapes.box(0.4375, 0.1875, 0.375, 0.5625, 0.25, 0.4375));
		shape = Shapes.or(shape, Shapes.box(0.4375, 0.1875, 0.5625, 0.5625, 0.25, 0.625));
		shape = Shapes.or(shape, Shapes.box(0.375, 0.1875, 0.4375, 0.4375, 0.25, 0.5625));
		shape = Shapes.or(shape, Shapes.box(0.4375, 0, 0.4375, 0.5625, 0.3125, 0.5625));
		shape = Shapes.or(shape, Shapes.box(0.4375, 0.0625, 0.3375, 0.5625, 0.1875, 0.6625));
		shape = Shapes.or(shape, Shapes.box(0.34375, 0.0625, 0.4375, 0.6625, 0.1875, 0.5625));
		shape = Shapes.or(shape, Shapes.box(0.5625, 0.0625, 0.390625, 0.609375, 0.1875, 0.4375));
		shape = Shapes.or(shape, Shapes.box(0.5625, 0.0625, 0.5625, 0.609375, 0.1875, 0.609375));
		shape = Shapes.or(shape, Shapes.box(0.390625, 0.0625, 0.5625, 0.4375, 0.1875, 0.609375));
		shape = Shapes.or(shape, Shapes.box(0.390625, 0.0625, 0.390625, 0.4375, 0.1875, 0.4375));
		shape = Shapes.or(shape, Shapes.box(0.5625, 0, 0.4375, 0.625, 0.0625, 0.5625));
		shape = Shapes.or(shape, Shapes.box(0.4375, 0, 0.375, 0.5625, 0.0625, 0.4375));
		shape = Shapes.or(shape, Shapes.box(0.4375, 0, 0.5625, 0.5625, 0.0625, 0.625));
		shape = Shapes.or(shape, Shapes.box(0.375, 0, 0.4375, 0.4375, 0.0625, 0.5625));
		shape = Shapes.or(shape, Shapes.box(0.41875, 0.271875, 0.421875, 0.578125, 0.41875, 0.58125));
		shape = Shapes.or(shape, Shapes.box(0.49375, 0.45, 0.496875, 0.509375, 0.540625, 0.5125));
		shape = Shapes.or(shape, Shapes.box(0.45, 0.421875, 0.453125, 0.553125, 0.484375, 0.55625));
		return shape;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return getOrCreateShape();
	}

	public Gourd() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SNOW).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("block.talisman_jackiechan.gourd.description_0"));
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