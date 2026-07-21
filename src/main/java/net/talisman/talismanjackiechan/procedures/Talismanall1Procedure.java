package net.talisman.talismanjackiechan.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.AdvancementEvent;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class Talismanall1Procedure {
	@SubscribeEvent
	public static void onAdvancement(AdvancementEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof ServerPlayer _plr0 && _plr0.level() instanceof ServerLevel
				&& _plr0.getAdvancements().getOrStartProgress(_plr0.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:a_fly_is_flying"))).isDone() && entity instanceof ServerPlayer _plr1
				&& _plr1.level() instanceof ServerLevel && _plr1.getAdvancements().getOrStartProgress(_plr1.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:astral_projection"))).isDone()
				&& entity instanceof ServerPlayer _plr2 && _plr2.level() instanceof ServerLevel
				&& _plr2.getAdvancements().getOrStartProgress(_plr2.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:confringo"))).isDone() && entity instanceof ServerPlayer _plr3
				&& _plr3.level() instanceof ServerLevel && _plr3.getAdvancements().getOrStartProgress(_plr3.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:immortality"))).isDone()
				&& entity instanceof ServerPlayer _plr4 && _plr4.level() instanceof ServerLevel
				&& _plr4.getAdvancements().getOrStartProgress(_plr4.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:kaleidoscopic"))).isDone() && entity instanceof ServerPlayer _plr5
				&& _plr5.level() instanceof ServerLevel && _plr5.getAdvancements().getOrStartProgress(_plr5.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:sunshine_rainbow_and_little_white_horse"))).isDone()
				&& entity instanceof ServerPlayer _plr6 && _plr6.level() instanceof ServerLevel
				&& _plr6.getAdvancements().getOrStartProgress(_plr6.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:superman"))).isDone() && entity instanceof ServerPlayer _plr7
				&& _plr7.level() instanceof ServerLevel && _plr7.getAdvancements().getOrStartProgress(_plr7.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:the_flash"))).isDone()
				&& entity instanceof ServerPlayer _plr8 && _plr8.level() instanceof ServerLevel
				&& _plr8.getAdvancements().getOrStartProgress(_plr8.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:titen_like"))).isDone() && entity instanceof ServerPlayer _plr9
				&& _plr9.level() instanceof ServerLevel && _plr9.getAdvancements().getOrStartProgress(_plr9.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:turn_static_into_dynamic"))).isDone()
				&& entity instanceof ServerPlayer _plr10 && _plr10.level() instanceof ServerLevel
				&& _plr10.getAdvancements().getOrStartProgress(_plr10.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:white_is_black"))).isDone() && entity instanceof ServerPlayer _plr11
				&& _plr11.level() instanceof ServerLevel && _plr11.getAdvancements().getOrStartProgress(_plr11.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:without_shadow"))).isDone()) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:talismanall"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
		}
	}
}