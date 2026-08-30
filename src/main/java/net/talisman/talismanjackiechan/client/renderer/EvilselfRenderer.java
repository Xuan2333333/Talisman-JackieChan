package net.talisman.talismanjackiechan.client.renderer;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.talisman.talismanjackiechan.entity.EvilselfEntity;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EvilselfRenderer extends HumanoidMobRenderer<EvilselfEntity, PlayerModel<EvilselfEntity>> {

	private static final ResourceLocation DEFAULT_TEXTURE =
			new ResourceLocation("talisman_jackiechan", "textures/entities/evilself.png");

	private static final Map<UUID, ResourceLocation> SKIN_CACHE = new ConcurrentHashMap<>();
	private static final Map<UUID, Boolean> REQUESTED = new ConcurrentHashMap<>();
	private static final Map<UUID, Boolean> SLIM_CACHE = new ConcurrentHashMap<>();
	private final PlayerModel<EvilselfEntity> wideModel;
	private final PlayerModel<EvilselfEntity> slimModel;

	public EvilselfRenderer(EntityRendererProvider.Context context) {
		super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5f);
		this.wideModel = this.model;
		this.slimModel = new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER_SLIM), true);

		this.addLayer(new HumanoidArmorLayer<>(this,
				new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
				new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)),
				context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(EvilselfEntity entity) {
		boolean slim = entity.getOwnerUUID()
				.map(uuid -> SLIM_CACHE.getOrDefault(uuid, DefaultPlayerSkin.getSkinModelName(uuid).equals("slim")))
				.orElse(false);
		this.model = slim ? slimModel : wideModel;

		if (entity.getOwnerUUID().isEmpty() || entity.getOwnerName().isBlank()) {
			return DEFAULT_TEXTURE;
		}

		UUID uuid = entity.getOwnerUUID().get();
		String name = entity.getOwnerName();

		ResourceLocation cached = SKIN_CACHE.get(uuid);
		if (cached != null) {
			return cached;
		}

		if (REQUESTED.putIfAbsent(uuid, true) == null) {
			requestSkin(uuid, name);
		}

		return DefaultPlayerSkin.getDefaultSkin(uuid);
	}

	private void requestSkin(UUID uuid, String name) {
		Minecraft mc = Minecraft.getInstance();
		GameProfile profile = new GameProfile(uuid, name);

		mc.getSkinManager().registerSkins(profile, (type, location, texture) -> {
			if (type == MinecraftProfileTexture.Type.SKIN) {
				SKIN_CACHE.put(uuid, location);

				String model = texture.getMetadata("model");
				boolean slim = "slim".equals(model);
				SLIM_CACHE.put(uuid, slim);
			}
		}, true);
	}
}