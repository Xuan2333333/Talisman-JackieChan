package net.talisman.talismanjackiechan.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModBlockEntities;

public class StoneSteleBlockEntity extends BlockEntity {

    private String frontText = "";
    private String backText = "";

    public StoneSteleBlockEntity(BlockPos pos, BlockState state) {
        super(TalismanJackiechanModBlockEntities.STONE_STELE.get(), pos, state);
    }

    public String getFrontText() { return frontText; }
    public void setFrontText(String t) { this.frontText = t == null ? "" : t; }
    public String getBackText() { return backText; }
    public void setBackText(String t) { this.backText = t == null ? "" : t; }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("FrontText", frontText);
        tag.putString("BackText", backText);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        frontText = tag.getString("FrontText");
        backText = tag.getString("BackText");
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        if (tag != null) load(tag);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}