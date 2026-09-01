package net.talisman.talismanjackiechan.network;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;
import net.talisman.talismanjackiechan.item.MonkeyTalismanItem;
import net.talisman.talismanjackiechan.procedures.Hou1Procedure;

import java.util.UUID;
import java.util.function.Supplier;

public class MonkeyTransformPacket {

    private final UUID targetUUID;

    public MonkeyTransformPacket(UUID targetUUID) {
        this.targetUUID = targetUUID;
    }

    public static void encode(MonkeyTransformPacket msg, FriendlyByteBuf buf) {
        buf.writeUUID(msg.targetUUID);
    }

    public static MonkeyTransformPacket decode(FriendlyByteBuf buf) {
        return new MonkeyTransformPacket(buf.readUUID());
    }

    public static void handle(MonkeyTransformPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;
            if (!(player.getMainHandItem().getItem() instanceof MonkeyTalismanItem)) return;

            Entity target = player.serverLevel().getEntity(msg.targetUUID);
            if (!(target instanceof LivingEntity living) || !living.isAlive()) return;
            if (player.distanceTo(target) > 24.0F) return;

            spawnRingParticles(player.serverLevel(), living);

            Hou1Procedure.execute(
                    living.level(),
                    living.getX(),
                    living.getY(),
                    living.getZ(),
                    living
            );
        });
        ctx.get().setPacketHandled(true);
    }

    private static void spawnRingParticles(ServerLevel level, LivingEntity target) {
        double cx = target.getX();
        double cy = target.getY() + target.getBbHeight() * 0.5;
        double cz = target.getZ();
        double radius = Math.max(0.6, target.getBbWidth() * 0.8);

        int count = 12;
        for (int i = 0; i < count; i++) {
            double angle = (Math.PI * 2.0 * i) / count;
            double px = cx + Math.cos(angle) * radius;
            double pz = cz + Math.sin(angle) * radius;
            level.sendParticles(
                    ParticleTypes.ENCHANT,
                    px, cy, pz,
                    1,
                    0.0, 0.05, 0.0,
                    0.0
            );
        }
    }
}