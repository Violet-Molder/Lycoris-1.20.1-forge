package com.linweiyun.lycoris.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class TestKeyBinding {
    public static final String CATEGORY_SHIFT_RIGHT = "key.category.lycorismod.lycoris";
    public static final String SHIFT_RIGHT = "key.lycorismod.metal_detector_mode_switch";

    public static final KeyMapping SHIFT_RIGHT_KEY = new KeyMapping(SHIFT_RIGHT, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_0, CATEGORY_SHIFT_RIGHT);
}
