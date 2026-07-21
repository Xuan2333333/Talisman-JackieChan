package net.talisman.talismanjackiechan.network;

import net.talisman.talismanjackiechan.TalismanJackiechanMod;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TalismanJackiechanModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		TalismanJackiechanMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new, SavedDataSyncMessage::handler);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData mapdata = MapVariables.get(event.getEntity().level());
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (mapdata != null)
					TalismanJackiechanMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					TalismanJackiechanMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (worlddata != null)
					TalismanJackiechanMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "talisman_jackiechan_worldvars";
		public double tu = 0.0;
		public double hou = 1.0;
		public double x = 0;
		public double y = 0;
		public double z = 0;
		public double moshi = 0;
		public double yinx = 0;
		public double yiny = 0;
		public double yinz = 0;
		public double yangx = 0;
		public double yangy = 0;
		public double yangz = 0;
		public double goulp = 0;
		public double shulp = 0;
		public double niulp = 0;
		public double hulp = 0;
		public double tulp = 0;
		public double longlp = 0;
		public double shelp = 0;
		public double malp = 0;
		public double yanglp = 0;
		public double houlp = 0;
		public double jilp = 0;
		public double zhulp = 0;
		public double lpxf = 0;
		public double dizi = 0;

		public static WorldVariables load(CompoundTag tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			tu = nbt.getDouble("tu");
			hou = nbt.getDouble("hou");
			x = nbt.getDouble("x");
			y = nbt.getDouble("y");
			z = nbt.getDouble("z");
			moshi = nbt.getDouble("moshi");
			yinx = nbt.getDouble("yinx");
			yiny = nbt.getDouble("yiny");
			yinz = nbt.getDouble("yinz");
			yangx = nbt.getDouble("yangx");
			yangy = nbt.getDouble("yangy");
			yangz = nbt.getDouble("yangz");
			goulp = nbt.getDouble("goulp");
			shulp = nbt.getDouble("shulp");
			niulp = nbt.getDouble("niulp");
			hulp = nbt.getDouble("hulp");
			tulp = nbt.getDouble("tulp");
			longlp = nbt.getDouble("longlp");
			shelp = nbt.getDouble("shelp");
			malp = nbt.getDouble("malp");
			yanglp = nbt.getDouble("yanglp");
			houlp = nbt.getDouble("houlp");
			jilp = nbt.getDouble("jilp");
			zhulp = nbt.getDouble("zhulp");
			lpxf = nbt.getDouble("lpxf");
			dizi = nbt.getDouble("dizi");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putDouble("tu", tu);
			nbt.putDouble("hou", hou);
			nbt.putDouble("x", x);
			nbt.putDouble("y", y);
			nbt.putDouble("z", z);
			nbt.putDouble("moshi", moshi);
			nbt.putDouble("yinx", yinx);
			nbt.putDouble("yiny", yiny);
			nbt.putDouble("yinz", yinz);
			nbt.putDouble("yangx", yangx);
			nbt.putDouble("yangy", yangy);
			nbt.putDouble("yangz", yangz);
			nbt.putDouble("goulp", goulp);
			nbt.putDouble("shulp", shulp);
			nbt.putDouble("niulp", niulp);
			nbt.putDouble("hulp", hulp);
			nbt.putDouble("tulp", tulp);
			nbt.putDouble("longlp", longlp);
			nbt.putDouble("shelp", shelp);
			nbt.putDouble("malp", malp);
			nbt.putDouble("yanglp", yanglp);
			nbt.putDouble("houlp", houlp);
			nbt.putDouble("jilp", jilp);
			nbt.putDouble("zhulp", zhulp);
			nbt.putDouble("lpxf", lpxf);
			nbt.putDouble("dizi", dizi);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level level && !level.isClientSide())
				TalismanJackiechanMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "talisman_jackiechan_mapvars";

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level && !world.isClientSide())
				TalismanJackiechanMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e), MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
		private final int type;
		private SavedData data;

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			this.type = buffer.readInt();
			CompoundTag nbt = buffer.readNbt();
			if (nbt != null) {
				this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
				if (this.data instanceof MapVariables mapVariables)
					mapVariables.read(nbt);
				else if (this.data instanceof WorldVariables worldVariables)
					worldVariables.read(nbt);
			}
		}

		public SavedDataSyncMessage(int type, SavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.type);
			if (message.data != null)
				buffer.writeNbt(message.data.save(new CompoundTag()));
		}

		public static void handler(SavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}
}