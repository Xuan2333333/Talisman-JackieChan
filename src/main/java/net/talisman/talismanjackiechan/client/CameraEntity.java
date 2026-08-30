package net.talisman.talismanjackiechan.client;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CameraEntity extends Entity {

    public CameraEntity(Level level) {
        super(EntityType.ARMOR_STAND, level);
        this.noPhysics = true;
        this.setNoGravity(true);
    }

    public void copyFromPlayer() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        double x = mc.player.getX();
        double y = mc.player.getY() + mc.player.getEyeHeight();
        double z = mc.player.getZ();
        float yaw = mc.player.getYRot();
        float pitch = mc.player.getXRot();

        this.setPos(x, y, z);
        this.setYRot(yaw);
        this.setXRot(pitch);

        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.xRotO = pitch;
        this.yRotO = yaw;
        this.setDeltaMovement(Vec3.ZERO);
    }

    public void updateLastTickValues() {
        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();
        this.xRotO = this.getXRot();
        this.yRotO = this.getYRot();
    }

    public void setCameraRotations(float yaw, float pitch) {
        this.setYRot(yaw);
        this.setXRot(pitch);
    }

    public void updateCameraRotations(float yawChange, float pitchChange) {
        float yaw = this.getYRot() + yawChange * 0.15F;
        float pitch = net.minecraft.util.Mth.clamp(this.getXRot() + pitchChange * 0.15F, -90.0F, 90.0F);
        this.setCameraRotations(yaw, pitch);
    }

    @Override
    public boolean isSpectator() {
        return true;
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {}

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return null;
    }
}