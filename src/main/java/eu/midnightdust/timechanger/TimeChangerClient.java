package eu.midnightdust.timechanger;

import eu.midnightdust.timechanger.command.CTimeCommand;
import eu.midnightdust.timechanger.command.CWeatherCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.Minecraft;

public class TimeChangerClient implements ClientModInitializer {
    private static final Minecraft client = Minecraft.getInstance();
    public static int customTime = -1;
    public static Weather customWeather = Weather.UNSET;

    public static boolean isEnabledOnWorld() {
        return client.getCurrentServer() != null;
    }

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, _) -> dispatcher.register(CTimeCommand.command()));
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, _) -> dispatcher.register(CWeatherCommand.command()));
    }
}
