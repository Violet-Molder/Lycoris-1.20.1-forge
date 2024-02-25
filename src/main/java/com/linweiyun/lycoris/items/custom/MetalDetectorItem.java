package com.linweiyun.lycoris.items.custom;

import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.util.LycorisTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    public static final DeferredRegister<Item> METAL_DETECTOR_ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, LycorisMod.MOD_ID);

    public static final RegistryObject<Item> METAL_DETECTOR = METAL_DETECTOR_ITEM.register("metal_detector",
            ()-> new MetalDetectorItem(new Item.Properties().durability(500)));

    int count = 0;
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide){
            BlockPos positionClicked = pContext.getClickedPos();//获取拿该物品右键的方块的位置

            switch (pContext.getClickedFace()){
                case UP:{
                    BlockPos face = new BlockPos(positionClicked.getX()-1, positionClicked.getY(), positionClicked.getZ()-1);
                    Player player = pContext.getPlayer();
                    boolean foundOre = false;

                    for (int i = 0; i <= positionClicked.getY() + 64; i++){
                        for (int j = 0; j <3 ; j++){
                            for (int k = 0; k < 3; k++){
                                BlockPos pos = new BlockPos(face.getX() + j, face.getY() - i, face.getZ() + k);
                                BlockState state = pContext.getLevel().getBlockState(pos);

                                if (isOre(state)){
                                    outputOreCoordinates(pos, player, state.getBlock());
                                    foundOre = true;
                                    count++;
                                }
                            }
                        }
                    }
                    if (!foundOre){
                        player.sendSystemMessage(Component.literal("这里没有你需要的东西！"));
                    }else {
                        player.sendSystemMessage(Component.literal("本次探索共找到"+ count + "个矿物" ));
                        count = 0;
                    }
                    break;}
                case DOWN:{
                    BlockPos face = new BlockPos(positionClicked.getX()-1, positionClicked.getY(), positionClicked.getZ()-1);
                    Player player = pContext.getPlayer();
                    boolean foundOre = false;

                    for (int i = 0; i < 64; i++){
                        for (int j = 0; j <3 ; j++){
                            for (int k = 0; k < 3; k++){
                                BlockPos pos = new BlockPos(face.getX() + j, face.getY() + i, face.getZ() + k);
                                BlockState state = pContext.getLevel().getBlockState(pos);

                                if (isOre(state)){
                                    outputOreCoordinates(pos, player, state.getBlock());
                                    foundOre = true;
                                    count++;
                                }
                            }
                        }
                    }
                    if (!foundOre){
                        player.sendSystemMessage(Component.literal("这里没有你需要的东西！"));
                    }else {
                        player.sendSystemMessage(Component.literal("本次探索共找到"+ count + "个矿物" ));
                        count = 0;
                    }
                    break;}
                case SOUTH:{
                    BlockPos face = new BlockPos(positionClicked.getX()+1, positionClicked.getY()+1, positionClicked.getZ());
                    Player player = pContext.getPlayer();
                    boolean foundOre = false;

                    for (int i = 0; i < 64; i++){
                        for (int j = 0; j <3 ; j++){
                            for (int k = 0; k < 3; k++){
                                BlockPos pos = new BlockPos(face.getX() - j, face.getY() - k, face.getZ() - i);
                                BlockState state = pContext.getLevel().getBlockState(pos);

                                if (isOre(state)){
                                    outputOreCoordinates(pos, player, state.getBlock());
                                    foundOre = true;
                                    count++;
                                }
                            }
                        }
                    }

                    if (!foundOre){
                        player.sendSystemMessage(Component.literal("这里没有你需要的东西！"));
                    }else {
                        player.sendSystemMessage(Component.literal("本次探索共找到"+ count + "个矿物" ));
                        count = 0;
                    }
                    break;
                }
                case NORTH:{
                    BlockPos face = new BlockPos(positionClicked.getX()+1, positionClicked.getY()+1, positionClicked.getZ());
                    Player player = pContext.getPlayer();
                    boolean foundOre = false;

                    for (int i = 0; i < 64; i++){
                        for (int j = 0; j <3 ; j++){
                            for (int k = 0; k < 3; k++){
                                BlockPos pos = new BlockPos(face.getX() - j, face.getY() - k, face.getZ() + i);
                                BlockState state = pContext.getLevel().getBlockState(pos);

                                if (isOre(state)){
                                    outputOreCoordinates(pos, player, state.getBlock());
                                    foundOre = true;
                                    count++;
                                }
                            }
                        }
                    }

                    if (!foundOre){
                        player.sendSystemMessage(Component.literal("这里没有你需要的东西！"));
                    }else {
                        player.sendSystemMessage(Component.literal("本次探索共找到"+ count + "个矿物" ));
                        count = 0;
                    }
                    break;
            }
                case EAST:{
                    BlockPos face = new BlockPos(positionClicked.getX(), positionClicked.getY()+1, positionClicked.getZ()-1);
                    Player player = pContext.getPlayer();
                    boolean foundOre = false;

                    for (int i = 0; i < 64; i++){
                        for (int j = 0; j <3 ; j++){
                            for (int k = 0; k < 3; k++){
                                BlockPos pos = new BlockPos(face.getX() - i, face.getY() - k, face.getZ() + j);
                                BlockState state = pContext.getLevel().getBlockState(pos);

                                if (isOre(state)){
                                    outputOreCoordinates(pos, player, state.getBlock());
                                    foundOre = true;
                                    count++;
                                }
                            }
                        }
                    }

                    if (!foundOre){
                        player.sendSystemMessage(Component.literal("这里没有你需要的东西！"));
                    }else {
                        player.sendSystemMessage(Component.literal("本次探索共找到"+ count + "个矿物" ));
                        count = 0;
                    }
                    break;
                }
                case WEST:{
                    BlockPos face = new BlockPos(positionClicked.getX(), positionClicked.getY()+1, positionClicked.getZ()-1);
                    Player player = pContext.getPlayer();
                    boolean foundOre = false;

                    for (int i = 0; i < 64; i++){
                        for (int j = 0; j <3 ; j++){
                            for (int k = 0; k < 3; k++){
                                BlockPos pos = new BlockPos(face.getX() + i, face.getY() - k, face.getZ() + j);
                                BlockState state = pContext.getLevel().getBlockState(pos);

                                if (isOre(state)){
                                    outputOreCoordinates(pos, player, state.getBlock());
                                    foundOre = true;
                                    count++;
                                }
                            }
                        }
                    }

                    if (!foundOre){
                        player.sendSystemMessage(Component.literal("这里没有你需要的东西！"));
                    }else {
                        player.sendSystemMessage(Component.literal("本次探索共找到"+ count + "个矿物" ));
                        count = 0;
                    }
                    break;
                }
            }


        }
        //使用后-耐久度
//        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
//                player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    private void outputOreCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("找到了" + I18n.get(block.getDescriptionId()) + "。位置是" +
                "{" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + "}"));
    }



    public enum Mode {
        NORMAL,
        RARE,
        PRECIOUS,
        LYCORIS;
    }
    public static Mode metalMode = Mode.NORMAL;
    public static void setMode(Mode mode){
        metalMode = mode;
    }
    private boolean isOre(BlockState state) {
        boolean isVaule = false;
        if (metalMode == Mode.NORMAL){
            if (state.is(LycorisTags.Blocks.METAL_DETECTOR_NORMAL)
            ) {
                isVaule = true;
            }
        } else if (metalMode == Mode.RARE){
            if (state.is(LycorisTags.Blocks.METAL_DETECTOR_RARE)
            ) {
                isVaule = true;
            }
        } else if (metalMode == Mode.PRECIOUS){
            if (state.is(LycorisTags.Blocks.METAL_DETECTOR_PRECIOUS)
            ) {
                isVaule = true;
            }
        } else if (metalMode == Mode.LYCORIS){
            if (state.is(LycorisTags.Blocks.METAL_DETECTOR_LYCORIS)
            ) {
                isVaule = true;
            }
        }
        return isVaule;
    }

    public static void register(IEventBus eventBus){
        METAL_DETECTOR_ITEM.register(eventBus);
    }
}
