package net.talisman.talismanjackiechan.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.talisman.talismanjackiechan.config.TalismanJackiechanConfig;
import net.talisman.talismanjackiechan.data.TalismanObtainedData;
import net.talisman.talismanjackiechan.events.TalismanItemProtectionHandler;
import org.jetbrains.annotations.NotNull;

public class AddItemLootModifier extends LootModifier {

    public static final Codec<AddItemLootModifier> CODEC = RecordCodecBuilder.create(inst ->
            codecStart(inst)
                    .and(ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(m -> m.item))
                    .and(Codec.FLOAT.fieldOf("chance").forGetter(m -> m.chance))
                    .apply(inst, AddItemLootModifier::new)
    );

    private final Item item;
    private final float chance;

    public AddItemLootModifier(LootItemCondition[] conditions, Item item, float chance) {
        super(conditions);
        this.item = item;
        this.chance = chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        final boolean uniqueEnabled = TalismanJackiechanConfig.UNIQUE_TALISMAN_PER_SAVE.get();

        TalismanObtainedData resolvedData = null;
        if (uniqueEnabled) {
            try {
                ServerLevel level = context.getLevel();
                resolvedData = TalismanObtainedData.get(level);
            } catch (Throwable ignored) {
                resolvedData = null;
            }
        }
        final TalismanObtainedData data = resolvedData;
        final boolean uniqueMode = uniqueEnabled && data != null;

        if (uniqueMode) {
            generatedLoot.removeIf(stack -> {
                if (stack == null || stack.isEmpty()) return false;
                if (!TalismanItemProtectionHandler.isProtectedItem(stack)) return false;
                ResourceLocation id = ForgeRegistries.ITEMS.getKey(stack.getItem());
                if (id == null) return false;

                if (data.isObtained(id)) {
                    return true;
                } else {
                    data.markObtained(id);
                    return false;
                }
            });
        }

        ResourceLocation selfId = ForgeRegistries.ITEMS.getKey(item);
        boolean selfAlreadyObtained = uniqueMode
                && selfId != null
                && TalismanItemProtectionHandler.isProtectedItem(new ItemStack(item))
                && data.isObtained(selfId);

        if (!selfAlreadyObtained && context.getRandom().nextFloat() < chance) {
            generatedLoot.add(new ItemStack(item));
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}