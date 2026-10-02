package net.talisman.talismanjackiechan.morph;

import net.minecraft.world.entity.EntityType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class MorphRegistry {

    private static final List<MorphEntry> ENTRIES = new ArrayList<>();
    private static final Map<Integer, MorphEntry> BY_HOU = new HashMap<>();

    public static final int MIN_HOU;
    public static final int MAX_HOU;

    static {
        reg(1,  EntityType.PIG);
        reg(2,  EntityType.HORSE,       none(), 0.28F, 0, 0, 0, 0);
        reg(3,  EntityType.DONKEY);
        reg(4,  EntityType.MULE);
        reg(5,  EntityType.COW);
        reg(6,  EntityType.SHEEP);
        reg(7,  EntityType.CHICKEN,     ab(MorphAbility.SLOW_FALL));
        reg(8,  EntityType.WOLF);
        reg(9,  EntityType.FOX);
        reg(10, EntityType.CAT);

        reg(11, EntityType.COD,         ab(MorphAbility.AQUATIC));
        reg(12, EntityType.DOLPHIN,     ab(MorphAbility.AQUATIC), 0, 1.2F, 0, 0, 0);
        reg(13, EntityType.TURTLE,      ab(MorphAbility.AQUATIC));
        reg(16, EntityType.FROG,        ab(MorphAbility.AQUATIC));
        reg(17, EntityType.SALMON,      ab(MorphAbility.AQUATIC));

        reg(14, EntityType.PARROT,      ab(MorphAbility.FLY));
        reg(15, EntityType.BEE,         ab(MorphAbility.FLY));
        reg(19, EntityType.BAT,         ab(MorphAbility.FLY));

        reg(18, EntityType.RABBIT,      ab(MorphAbility.HIGH_BOUNCE));
        reg(20, EntityType.GOAT,        ab(MorphAbility.RAM), 0, 0, 0, 0, 0.35F);
        reg(21, EntityType.PANDA,       none(), -0.15F, 0, 2, 0, 0);
        reg(22, EntityType.POLAR_BEAR,  none(), 0, 0, 0, 0.50F, 0);
        reg(23, EntityType.LLAMA,       none(), 0.25F, 0, 0, 0, 0);
        reg(24, EntityType.AXOLOTL,     ab(MorphAbility.AQUATIC));

        reg(25, EntityType.SPIDER,      ab(MorphAbility.CLIMB));
        reg(26, EntityType.CAVE_SPIDER, ab(MorphAbility.CLIMB));

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (MorphEntry e : ENTRIES) {
            if (e.hou() < min) min = e.hou();
            if (e.hou() > max) max = e.hou();
        }
        MIN_HOU = min;
        MAX_HOU = max;
    }

    private MorphRegistry() {}

    private static Set<MorphAbility> none() {
        return Set.of();
    }

    private static Set<MorphAbility> ab(MorphAbility... abilities) {
        if (abilities.length == 0) return Set.of();
        EnumSet<MorphAbility> s = EnumSet.noneOf(MorphAbility.class);
        Collections.addAll(s, abilities);
        return s;
    }

    private static void reg(int hou, EntityType<?> type) {
        reg(hou, type, none(), 0, 0, 0, 0, 0);
    }

    private static void reg(int hou, EntityType<?> type, Set<MorphAbility> abilities) {
        reg(hou, type, abilities, 0, 0, 0, 0, 0);
    }

    private static void reg(int hou, EntityType<?> type, Set<MorphAbility> abilities,
                            float land, float swim, float armor, float attack, float jump) {
        MorphEntry e = new MorphEntry(hou, type, abilities, land, swim, armor, attack, jump);
        ENTRIES.add(e);
        BY_HOU.put(hou, e);
    }

    public static List<MorphEntry> all() {
        return Collections.unmodifiableList(ENTRIES);
    }

    public static MorphEntry byHou(int hou) {
        return BY_HOU.get(hou);
    }

    public static EntityType<?> typeOf(int hou) {
        MorphEntry e = BY_HOU.get(hou);
        return e == null ? null : e.type();
    }

    public static boolean hasAbility(int hou, MorphAbility ability) {
        MorphEntry e = BY_HOU.get(hou);
        return e != null && e.has(ability);
    }
}