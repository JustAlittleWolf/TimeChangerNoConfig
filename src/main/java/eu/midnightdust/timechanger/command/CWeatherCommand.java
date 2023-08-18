package eu.midnightdust.timechanger.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import eu.midnightdust.timechanger.TimeChangerClient;
import eu.midnightdust.timechanger.Weather;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class CWeatherCommand {

    public static LiteralArgumentBuilder<FabricClientCommandSource> command() {
        return ClientCommandManager.literal("cweather")
                .then(literal("unset").executes(ctx -> setWeather(ctx.getSource(), Weather.UNSET)))
                .then(literal("clear").executes(ctx -> setWeather(ctx.getSource(), Weather.CLEAR)))
                .then(literal("rain").executes(ctx -> setWeather(ctx.getSource(), Weather.RAIN)))
                .then(literal("thunder").executes(ctx -> setWeather(ctx.getSource(), Weather.THUNDER)));
    }

    private static int setWeather(FabricClientCommandSource source, Weather weather) {
        TimeChangerClient.customWeather = weather;
        source.sendFeedback(Text.translatable("command.timechanger.cweather.success").append(String.valueOf(weather)));
        return 1;
    }
}