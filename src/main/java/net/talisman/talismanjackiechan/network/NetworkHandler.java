package net.talisman.talismanjackiechan.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.network.FriendlyByteBuf;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("talisman_jackiechan", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int messageID = 0;
    private static boolean initialized = false;

    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static void init() {
        if (initialized) return;
        initialized = true;

        INSTANCE.registerMessage(messageID++, PigLaserPacket.class,
                PigLaserPacket::encode,
                PigLaserPacket::decode,
                PigLaserPacket::handle);
        INSTANCE.registerMessage(messageID++, RoosterControlPacket.class,
                RoosterControlPacket::encode,
                RoosterControlPacket::decode,
                RoosterControlPacket::handle
        );
        MinecraftForge.EVENT_BUS.register(ServerTickHandler.class);
    }

    public static <T> void addNetworkMessage(Class<T> messageType,
                                             BiConsumer<T, FriendlyByteBuf> encoder,
                                             Function<FriendlyByteBuf, T> decoder,
                                             BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        INSTANCE.registerMessage(messageID++, messageType, encoder, decoder, messageConsumer);
    }

    public static void queueServerWork(int tick, Runnable action) {
        if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
            workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
        }
    }

    public static class ServerTickHandler {
        @SubscribeEvent
        public static void onServerTick(TickEvent.ServerTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
                workQueue.forEach(work -> {
                    work.setValue(work.getValue() - 1);
                    if (work.getValue() == 0) {
                        actions.add(work);
                    }
                });
                actions.forEach(e -> e.getKey().run());
                workQueue.removeAll(actions);
            }
        }
    }
}