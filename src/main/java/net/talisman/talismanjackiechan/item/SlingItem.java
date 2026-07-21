package net.talisman.talismanjackiechan.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerPlayer;

import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.CuriosApi;

public abstract class SlingItem extends ArmorItem implements ICurioItem {

	private static final int TALISMAN_GOAL = 12;

	public SlingItem(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override public int getDurabilityForType(ArmorItem.Type type) { return 0; }
			@Override public int getDefenseForType(ArmorItem.Type type) { return new int[]{2,5,6,2}[type.getSlot().getIndex()]; }
			@Override public int getEnchantmentValue() { return 20; }
			@Override public SoundEvent getEquipSound() { return SoundEvents.EMPTY; }
			@Override public Ingredient getRepairIngredient() { return Ingredient.of(); }
			@Override public String getName() { return "sling"; }
			@Override public float getToughness() { return 0f; }
			@Override public float getKnockbackResistance() { return 0f; }
		}, type, properties);
	}

	@Override
	public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
		if (slotContext.entity() instanceof ServerPlayer player) {
			ensureTalismanActivated(player);
		}
	}

	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		if (slotContext.entity() instanceof ServerPlayer player) {
			ensureTalismanDeactivated(player);
		}
	}

	@Override
	public boolean canEquip(SlotContext slotContext, ItemStack stack) {
		return true;
	}

	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
		super.inventoryTick(stack, level, entity, slotId, isSelected);
	}

	// ---------- 公开的槽位控制方法（供主类调用） ----------

	public static void ensureTalismanActivated(ServerPlayer player) {
		CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
			handler.getStacksHandler("talisman").ifPresent(stacks -> {
				int current = stacks.getSlots();
				if (current < TALISMAN_GOAL) {
					handler.growSlotType("talisman", TALISMAN_GOAL - current);
				}
			});
		});
	}

	public static void ensureTalismanDeactivated(ServerPlayer player) {
		CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
			handler.getStacksHandler("talisman").ifPresent(stacks -> {
				int current = stacks.getSlots();
				if (current > 0) {
					handler.shrinkSlotType("talisman", current);
				}
			});
		});
	}

	// ---------- 内部类 ----------
	public static class Chestplate extends SlingItem {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties());
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "talisman_jackiechan:textures/models/armor/sling1_layer_1.png";
		}
	}
}