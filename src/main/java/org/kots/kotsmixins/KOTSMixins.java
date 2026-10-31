package org.kots.kotsmixins;

import org.kots.kotsmixins.util.Platform;

public class KOTSMixins {
    public static final String MOD_ID = "kotsmixins";
    public static final String MOD_NAME = "KOTS-Mixins";
    public static final String MOD_VERSION = Platform.getModVersion(MOD_ID).orElseThrow();

}
