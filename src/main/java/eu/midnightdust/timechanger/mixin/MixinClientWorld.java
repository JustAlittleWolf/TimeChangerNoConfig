package eu.midnightdust.timechanger.mixin;

import eu.midnightdust.timechanger.TimeChangerClient;
import eu.midnightdust.timechanger.Weather;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.WritableLevelData;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientLevel.class)

public abstract class MixinClientWorld extends Level {

    protected MixinClientWorld(WritableLevelData levelData, ResourceKey<Level> dimension, RegistryAccess registryAccess, Holder<net.minecraft.world.level.dimension.DimensionType> dimensionTypeRegistration, boolean isClientSide, boolean isDebug, long biomeZoomSeed, int maxChainedNeighborUpdates) {
        super(levelData, dimension, registryAccess, dimensionTypeRegistration, isClientSide, isDebug, biomeZoomSeed, maxChainedNeighborUpdates);
    }

    @Override
    public float getRainLevel(float delta) {
        if (TimeChangerClient.isEnabledOnWorld() && !TimeChangerClient.customWeather.equals(Weather.UNSET)) {
            if (TimeChangerClient.customWeather.equals(Weather.CLEAR)) {
                return 0f;
            } else return 1f;
        }
        return super.getRainLevel(delta);
    }

    @Override
    public float getThunderLevel(float delta) {
        if (TimeChangerClient.isEnabledOnWorld() && !TimeChangerClient.customWeather.equals(Weather.UNSET)) {
            if (TimeChangerClient.customWeather.equals(Weather.THUNDER)) {
                return 1f;
            } else return 0f;
        }
        return super.getThunderLevel(delta);
    }
}
