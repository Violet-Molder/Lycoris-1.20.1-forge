package com.linweiyun.lycoris.screen;



import com.linweiyun.lycoris.block.custom.Lv1InjectingMagicAltarCore;
import com.linweiyun.lycoris.blockentity.Lv1InjectingMagicCoreAltarEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class Lv1InjectingMagicAltarMenu extends AbstractContainerMenu {

    public final Lv1InjectingMagicCoreAltarEntity entity;
    private final Level level;
    private final ContainerData data;

    public Lv1InjectingMagicAltarMenu(int id, Inventory inventory, FriendlyByteBuf extraData){
        this(id,inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()),new SimpleContainerData(2));
    }

    public Lv1InjectingMagicAltarMenu(int id, Inventory inv, BlockEntity entity, ContainerData data){
        super(LycorisMenuType.LV1_INJECTING_MAGIC_ALTAR_MENU_TYPE.get(),id);
        // 检查slots是否为3
        checkContainerSize(inv,8);
        this.entity = (Lv1InjectingMagicCoreAltarEntity) entity;
        this.level = inv.player.level();
        this.data =data;

        // 处理玩家的背包和物品栏
        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        // 为当的menu增加3个slot，分别对应itemhandler的0,1,2,
        this.entity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler,0,59,59));
            this.addSlot(new SlotItemHandler(handler,1,59,18));
            this.addSlot(new SlotItemHandler(handler,2,95,43));
            this.addSlot(new SlotItemHandler(handler,3,95,76));
            this.addSlot(new SlotItemHandler(handler,4,59,100));
            this.addSlot(new SlotItemHandler(handler,5,23,76));
            this.addSlot(new SlotItemHandler(handler,6,23,43));
            this.addSlot(new SlotItemHandler(handler,7,167,59){
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }
            });
        });
        // 将进度数据同步到Client
        addDataSlots(data);
    }
    private void addPlayerInventory(Inventory playerInventory){
        for (int i=0;i<3;i++){
            for (int l =0;l<9;l++){
                this.addSlot(new Slot(playerInventory,l+i*9+9,34+l*18,138+i*18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory){
        for(int i=0;i<9;i++){
            this.addSlot(new Slot(playerInventory,i,34+i*18,196));
        }
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }



    // 快速移动物品用，直接复制
    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 8;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
    // 每个menu需要实现的方法
    // 用于检查玩家是否在blockentity 8个方块内。
    @Override
    public boolean stillValid(Player player) {
        // containerLevelAccess 在服务器创建一个 containerlevelaccesss对象，提供了当前的世界和方块位置是否在一个封闭的范围
        return stillValid(ContainerLevelAccess.create(level,entity.getBlockPos()),
                player, Lv1InjectingMagicAltarCore.LV1_INJECTING_MAGIC_ALTAR_CORE.get());
    }


}
