package com.linweiyun.lycoris.key;

import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.items.custom.MetalDetectorItem;
import com.linweiyun.lycoris.util.TestKeyBinding;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class ShiftRightKey {
    private static int mode = 0;
    @Mod.EventBusSubscriber(modid = LycorisMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(TickEvent.PlayerTickEvent event) {
            if (event.phase == TickEvent.Phase.END && !event.player.level().isClientSide) {
                if (TestKeyBinding.SHIFT_RIGHT_KEY.consumeClick()) {
                    ItemStack mainHandStack = event.player.getItemInHand(InteractionHand.MAIN_HAND);
                    boolean isShiftRight = mainHandStack.getItem() instanceof MetalDetectorItem;
                    if (isShiftRight) {// 检查主手是否有物品
                        mode++;
                        if (mode == 1){
                            MetalDetectorItem.setMode(MetalDetectorItem.Mode.RARE);
                            event.player.sendSystemMessage(Component.literal("切换为仅寻找稀有矿物")); // 发送消息给玩家
                            // 在这里处理主手物品的相关逻辑
                        } else if (mode == 2) {
                            MetalDetectorItem.setMode(MetalDetectorItem.Mode.PRECIOUS);
                            event.player.sendSystemMessage(Component.literal("切换为仅寻找珍贵矿物"));
                        } else if (mode == 3) {
                            MetalDetectorItem.setMode(MetalDetectorItem.Mode.LYCORIS);
                            event.player.sendSystemMessage(Component.literal("切换为仅寻找本MOD添加的矿物"));
                        } else if (mode == 4) {
                            MetalDetectorItem.setMode(MetalDetectorItem.Mode.NORMAL);
                            event.player.sendSystemMessage(Component.literal("切换为寻找所有矿物"));
                            mode = 0;
                        }
                    }
                }
            }
        }
    }
    @Mod.EventBusSubscriber(modid = LycorisMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    private static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegistry(RegisterKeyMappingsEvent event) {
            event.register(TestKeyBinding.SHIFT_RIGHT_KEY);
        }
    }
}
