package me.wolfii.timechangerupdated.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.wolfii.timechangerupdated.TimeChangerClient;
import net.minecraft.client.ClientClockManager;
import net.minecraft.core.Holder;
import net.minecraft.world.clock.WorldClock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientClockManager.ClientClockInstance.class)
public class ClientClockInstanceMixin {
    @WrapMethod(method = "totalTicks")
    private long getTotalTicks(Operation<Long> original) {
        if (TimeChangerClient.isEnabledOnWorld() && TimeChangerClient.customTime >= 0) {
            return TimeChangerClient.customTime;
        }
        return original.call();
    }
}
