package org.kots.kotsmixins.mixins.mod.tweakerge;

import fi.dy.masa.tweakeroo.config.FeatureToggle;
import fi.dy.masa.tweakeroo.event.WorldLoadListener;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import org.kots.kotsmixins.config.ConfigManager;
import org.kots.kotsmixins.reference.ModReference;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Restriction(require = @Condition(ModReference.TWEAKERGE))
@Mixin(WorldLoadListener.class)
public class WorldLoadListenerMixin {
    @Inject(method = "onWorldLoadPost", at = @At(value = "INVOKE", target = "Lfi/dy/masa/tweakeroo/util/MiscUtils;toggleGammaOverrideWithMessage()V"))
    private void reimplement(ClientLevel worldBefore, ClientLevel worldAfter, Minecraft mc, CallbackInfo ci) {
        if (ConfigManager.getClient().tweakerooGammaOverrideFix) {
            FeatureToggle.TWEAK_GAMMA_OVERRIDE.setBooleanValue(true);
        }
    }
}
