package org.kots.kotsmixins.mixins.mod.tweakerge;

import fi.dy.masa.tweakeroo.util.MiscUtils;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import org.kots.kotsmixins.config.ConfigManager;
import org.kots.kotsmixins.reference.ModReference;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Restriction(require = @Condition(value = ModReference.TWEAKERGE))
@Mixin(value = MiscUtils.class, remap = false)
public class MiscUtilsMixin {
    @Inject(method = "toggleGammaOverrideWithMessage", at = @At("HEAD"), remap = false, cancellable = true)
    private static void fixNightVision(CallbackInfo ci) {
        if (ConfigManager.getClient().tweakerooGammaOverrideFix) {
            ci.cancel();// the other usages are in mixins and I won't use mixin squared
        }
    }
}
