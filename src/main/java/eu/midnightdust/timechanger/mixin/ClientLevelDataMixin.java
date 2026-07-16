package eu.midnightdust.timechanger.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import eu.midnightdust.timechanger.TimeChangerClient;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientLevel.ClientLevelData.class)
public abstract class ClientLevelDataMixin {
    @WrapMethod(method = "getGameTime")
    public long getTimeOfDay(Operation<Long> original) {
        if (TimeChangerClient.isEnabledOnWorld() && TimeChangerClient.customTime >= 0) {
            return TimeChangerClient.customTime;
        }
        return original.call();
    }
}
