package net.talisman.talismanjackiechan.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class TalismanJackiechanConfig {

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<String> STELE_TEXT_COLOR;
    public static final ForgeConfigSpec.BooleanValue UNIQUE_TALISMAN_PER_SAVE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("stele");
        STELE_TEXT_COLOR = builder
                .comment("Stele text color.",
                        "Supported formats:",
                        "  \"#RRGGBB\"  (e.g. \"#0A0A0A\")",
                        "  \"0xRRGGBB\" (e.g. \"0x0A0A0A\")",
                        "  \"RRGGBB\"   (e.g. \"0A0A0A\")",
                        "  \"#RGB\"     (e.g. \"#000\", shorthand)",
                        "Default \"#0A0A0A\" (near-black).",
                        "Examples: \"#000000\" pure black, \"#2A2A2A\" dark gray, \"#8B0000\" dark red.")
                .define("steleTextColor", "#0A0A0A");
        builder.pop();

        builder.push("talisman");
        UNIQUE_TALISMAN_PER_SAVE = builder
                .comment("When true, each talisman type can only be obtained once per world.",
                        "Once any player obtains a talisman, that type will no longer appear in loot.",
                        "This state is shared across all dimensions, players, and loot tables.",
                        "Default true.")
                .define("uniqueTalismanPerSave", true);
        builder.pop();

        SPEC = builder.build();
    }

    public static int parseColor(String value, int fallback) {
        if (value == null) return fallback;
        String s = value.trim();
        if (s.isEmpty()) return fallback;

        try {
            if (s.startsWith("#")) {
                s = s.substring(1);
            } else if (s.startsWith("0x") || s.startsWith("0X")) {
                s = s.substring(2);
            }

            if (s.length() == 3) {
                char r = s.charAt(0);
                char g = s.charAt(1);
                char b = s.charAt(2);
                s = "" + r + r + g + g + b + b;
            }

            if (s.length() != 6) return fallback;
            return Integer.parseInt(s, 16);
        } catch (Throwable t) {
            return fallback;
        }
    }

    private TalismanJackiechanConfig() {}
}