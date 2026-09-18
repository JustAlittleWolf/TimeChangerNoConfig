package me.wolfii.timechangerupdated.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.wolfii.timechangerupdated.TimeChangerClient;
import me.wolfii.timechangerupdated.Weather;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.network.chat.Component;

public class CWeatherCommand {
    public static LiteralArgumentBuilder<FabricClientCommandSource> command() {
        return ClientCommands.literal("cweather")
            .then(ClientCommands.literal("unset").executes(ctx -> setWeather(ctx.getSource(), Weather.UNSET)))
            .then(ClientCommands.literal("clear").executes(ctx -> setWeather(ctx.getSource(), Weather.CLEAR)))
            .then(ClientCommands.literal("rain").executes(ctx -> setWeather(ctx.getSource(), Weather.RAIN)))
            .then(ClientCommands.literal("thunder").executes(ctx -> setWeather(ctx.getSource(), Weather.THUNDER)));
    }

    private static int setWeather(FabricClientCommandSource source, Weather weather) {
        TimeChangerClient.customWeather = weather;
        source.sendFeedback(Component.translatable("command.timechanger.cweather.success").append(String.valueOf(weather)));
        return 1;
    }
}