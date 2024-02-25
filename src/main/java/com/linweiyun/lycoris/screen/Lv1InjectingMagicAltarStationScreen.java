package com.linweiyun.lycoris.screen;


import com.linweiyun.lycoris.LycorisMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
// screen和直接的menu对应，可以使用 AbstractContainerScreen类
// 处理渲染和menu的对应处理，处理slot相关内容
// AbstractContainerScreen需要三个参数
// 正在处理的menu，玩家的intentory 以及screen titile

public class Lv1InjectingMagicAltarStationScreen extends AbstractContainerScreen<Lv1InjectingMagicAltarMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(LycorisMod.MOD_ID,"textures/gui/lvj_injecting_magic_altar_screen.png");

    public Lv1InjectingMagicAltarStationScreen(Lv1InjectingMagicAltarMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.imageWidth = 228;
        this.imageHeight = 220;
    }

    @Override
    protected void init() {
        super.init();
    }
    // 渲染背景
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        RenderSystem.disableBlend();
    }



    // 先添加背景，在添加super渲染容器，在添加提示信息。
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }
    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }
}
