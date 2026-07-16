package eu.midnightdust.timechanger.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import eu.midnightdust.timechanger.TimeChangerClient;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.network.chat.Component;

public class CTimeCommand {
    public static LiteralArgumentBuilder<FabricClientCommandSource> command() {
        return ClientCommands.literal("ctime").then(
            ClientCommands.argument("time", IntegerArgumentType.integer(-1))
                .executes(ctx -> setTime(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "time")))
        );
    }

    private static int setTime(FabricClientCommandSource source, int time) {
        TimeChangerClient.customTime = time;
        source.sendFeedback(Component.translatable("command.timechanger.ctime.success").append(time >= 0 ? String.valueOf(time) : "disabled"));
        return 1;
    }
}