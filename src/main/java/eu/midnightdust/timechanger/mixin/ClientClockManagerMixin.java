package eu.midnightdust.timechanger.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import eu.midnightdust.timechanger.TimeChangerClient;
import net.minecraft.client.ClientClockManager;
import net.minecraft.core.Holder;
import net.minecraft.world.clock.WorldClock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientClockManager.class)
public class ClientClockManagerMixin {
    @WrapMethod(method = "getTotalTicks")
    private long getTotalTicks(Holder<WorldClock> definition, Operation<Long> original) {
        if (TimeChangerClient.isEnabledOnWorld() && TimeChangerClient.customTime >= 0) {
            return TimeChangerClient.customTime;
        }
        return original.call(definition);
    }
}
