package net.talisman.talismanjackiechan.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModMenus;

public class StoneSteleMenu extends AbstractContainerMenu {

    private final BlockPos pos;
    private final boolean isFront;
    private final String frontText;
    private final String backText;

    public StoneSteleMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, buf.readBlockPos(), buf.readBoolean(),
                buf.readUtf(4096), buf.readUtf(4096));
    }

    public StoneSteleMenu(int id, Inventory inv, BlockPos pos, boolean isFront) {
        this(id, inv, pos, isFront, "", "");
    }

    public StoneSteleMenu(int id, Inventory inv, BlockPos pos, boolean isFront,
                          String front, String back) {
        super(TalismanJackiechanModMenus.STONE_STELE.get(), id);
        this.pos = pos;
        this.isFront = isFront;
        this.frontText = front;
        this.backText = back;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) { return ItemStack.EMPTY; }

    @Override
    public boolean stillValid(Player player) {
        return player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) < 64.0;
    }

    public BlockPos getPos() { return pos; }
    public boolean isFront() { return isFront; }
    public String getFrontText() { return frontText; }
    public String getBackText() { return backText; }
    public String getCurrentText() { return isFront ? frontText : backText; }
}