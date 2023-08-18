package eu.midnightdust.timechanger.mixin;

import eu.midnightdust.timechanger.TimeChangerClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.Properties.class)

public abstract class MixinClientWorldProperties {

    @Inject(at = @At("RETURN"), method = "getTimeOfDay", cancellable = true)
    @Environment(EnvType.CLIENT)
    public void getTimeOfDay(CallbackInfoReturnable<Long> cir) {
        if (TimeChangerClient.isEnabledOnWorld() && TimeChangerClient.customTime >= 0) {
            cir.setReturnValue((long) TimeChangerClient.customTime);
        }
        else cir.cancel();
    }
}
