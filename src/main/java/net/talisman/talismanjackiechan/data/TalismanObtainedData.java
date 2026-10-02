package net.talisman.talismanjackiechan.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashSet;
import java.util.Set;

public class TalismanObtainedData extends SavedData {

    public static final String DATA_NAME = "talisman_jackiechan_obtained";

    private final Set<ResourceLocation> obtained = new HashSet<>();

    public TalismanObtainedData() {}

    public static TalismanObtainedData get(ServerLevel level) {
        return level.getServer().overworld().getDataStorage()
                .computeIfAbsent(TalismanObtainedData::load, TalismanObtainedData::new, DATA_NAME);
    }

    public boolean isObtained(ResourceLocation id) {
        return id != null && obtained.contains(id);
    }

    public void markObtained(ResourceLocation id) {
        if (id != null && obtained.add(id)) {
            setDirty();
        }
    }

    public Set<ResourceLocation> snapshot() {
        return new HashSet<>(obtained);
    }

    public static TalismanObtainedData load(CompoundTag tag) {
        TalismanObtainedData data = new TalismanObtainedData();
        ListTag list = tag.getList("Obtained", Tag.TAG_STRING);
        for (int i = 0; i < list.size(); i++) {
            ResourceLocation rl = ResourceLocation.tryParse(list.getString(i));
            if (rl != null) data.obtained.add(rl);
        }
        return data;
    }
    public boolean remove(ResourceLocation id) {
        if (id != null && obtained.remove(id)) {
            setDirty();
            return true;
        }
        return false;
    }

    public int clearAll() {
        int n = obtained.size();
        if (n > 0) {
            obtained.clear();
            setDirty();
        }
        return n;
    }
    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for (ResourceLocation rl : obtained) {
            list.add(StringTag.valueOf(rl.toString()));
        }
        tag.put("Obtained", list);
        return tag;
    }
}