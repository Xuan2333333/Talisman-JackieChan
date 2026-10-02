package net.talisman.talismanjackiechan.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.talisman.talismanjackiechan.data.TalismanObtainedData;

@Mod.EventBusSubscriber(modid = "talisman_jackiechan", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TalismanCommandHandler {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("talisman")
                        .then(Commands.literal("list")
                                .executes(ctx -> {
                                    ServerLevel overworld = ctx.getSource().getServer().overworld();
                                    TalismanObtainedData data = TalismanObtainedData.get(overworld);
                                    var set = data.snapshot();
                                    if (set.isEmpty()) {
                                        ctx.getSource().sendSuccess(
                                                () -> Component.literal("No talismans obtained yet."), false);
                                    } else {
                                        StringBuilder sb = new StringBuilder("Obtained: ");
                                        for (ResourceLocation rl : set) {
                                            sb.append(rl).append("  ");
                                        }
                                        ctx.getSource().sendSuccess(
                                                () -> Component.literal(sb.toString()), false);
                                    }
                                    return 1;
                                }))
                        .then(Commands.literal("reset")
                                .requires(src -> src.hasPermission(2))
                                .then(Commands.argument("id", StringArgumentType.string())
                                        .executes(ctx -> {
                                            String raw = StringArgumentType.getString(ctx, "id");
                                            ResourceLocation rl = raw.contains(":")
                                                    ? ResourceLocation.tryParse(raw)
                                                    : ResourceLocation.tryParse("talisman_jackiechan:" + raw);
                                            if (rl == null) {
                                                ctx.getSource().sendFailure(
                                                        Component.literal("Invalid id: " + raw));
                                                return 0;
                                            }
                                            ServerLevel overworld = ctx.getSource().getServer().overworld();
                                            TalismanObtainedData data = TalismanObtainedData.get(overworld);
                                            boolean removed = data.remove(rl);
                                            if (removed) {
                                                ctx.getSource().sendSuccess(
                                                        () -> Component.literal("Reset: " + rl), true);
                                                return 1;
                                            } else {
                                                ctx.getSource().sendFailure(
                                                        Component.literal("Not obtained: " + rl));
                                                return 0;
                                            }
                                        })))
                        .then(Commands.literal("resetall")
                                .requires(src -> src.hasPermission(2))
                                .executes(ctx -> {
                                    ServerLevel overworld = ctx.getSource().getServer().overworld();
                                    TalismanObtainedData data = TalismanObtainedData.get(overworld);
                                    int n = data.clearAll();
                                    ctx.getSource().sendSuccess(
                                            () -> Component.literal("Reset all " + n + " entries."), true);
                                    return 1;
                                }))
        );
    }
}