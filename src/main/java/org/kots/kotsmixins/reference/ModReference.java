package org.kots.kotsmixins.reference;

import org.kots.kotsmixins.util.Platform;

public class ModReference {

    public static final String TWEAKERGE = "tweakerge";
    public static final String VW50 = "vw50";

    public static boolean has(String modid) {
        return Platform.hasMod(modid);
    }
}
