package eu.midnightdust.timechanger;

import eu.midnightdust.timechanger.command.CTimeCommand;
import eu.midnightdust.timechanger.command.CWeatherCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;

public class TimeChangerClient implements ClientModInitializer {
    private static final MinecraftClient client = MinecraftClient.getInstance();
    public static int customTime = -1;
    public static Weather customWeather = Weather.UNSET;

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(CTimeCommand.command()));
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(CWeatherCommand.command()));
    }
    public static boolean isEnabledOnWorld() {
        return client.getCurrentServerEntry() != null;
    }
}
