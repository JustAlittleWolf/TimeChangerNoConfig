package me.wolfii.timechangerupdated.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.wolfii.timechangerupdated.TimeChangerClient;
import me.wolfii.timechangerupdated.Weather;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Level.class)
public abstract class LevelMixin {
    @WrapMethod(method = "getRainLevel")
    public float getRainLevel(float a, Operation<Float> original) {
        if (TimeChangerClient.isEnabledOnWorld() && !TimeChangerClient.customWeather.equals(Weather.UNSET)) {
            if (TimeChangerClient.customWeather.equals(Weather.CLEAR)) {
                return 0f;
            } else return 1f;
        }
        return original.call(a);
    }

    @WrapMethod(method = "getThunderLevel")
    public float getThunderLevel(float a, Operation<Float> original) {
        if (TimeChangerClient.isEnabledOnWorld() && !TimeChangerClient.customWeather.equals(Weather.UNSET)) {
            if (TimeChangerClient.customWeather.equals(Weather.THUNDER)) {
                return 1f;
            } else return 0f;
        }
        return original.call(a);
    }
}
