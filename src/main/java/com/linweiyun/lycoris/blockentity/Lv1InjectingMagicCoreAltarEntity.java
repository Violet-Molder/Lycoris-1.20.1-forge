package com.linweiyun.lycoris.blockentity;


import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.block.custom.Lv1InjectingMagicAltarCore;
import com.linweiyun.lycoris.recipetype.Lv1InjectingMagicAltarRecipe;
import com.linweiyun.lycoris.screen.Lv1InjectingMagicAltarMenu;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;

public class Lv1InjectingMagicCoreAltarEntity extends BlockEntity implements MenuProvider, BlockEntityRenderer<Lv1InjectingMagicCoreAltarEntity> {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, LycorisMod.MOD_ID);
	public static final RegistryObject<BlockEntityType<Lv1InjectingMagicCoreAltarEntity>> LV1_INJECTING_MAGIC_ALTAR_CORE_ENTITY =
			REGISTRY.register("lv1_injecting_magic_altar_core_entity",
					()->BlockEntityType.Builder.of(Lv1InjectingMagicCoreAltarEntity::new,
							Lv1InjectingMagicAltarCore.LV1_INJECTING_MAGIC_ALTAR_CORE.get()).build(null));
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
	private final ItemStackHandler itemStackHandler = new ItemStackHandler(8){
		// 当槽位的内容改变时候，设置改变
		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
		}
	};
	// 合成进度追踪
	private int progress = 0;
	private int maxProgress = 78;
	public final ContainerData data;
	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

	public Lv1InjectingMagicCoreAltarEntity(BlockPos blockPos, BlockState blockState) {
		super(Lv1InjectingMagicCoreAltarEntity.LV1_INJECTING_MAGIC_ALTAR_CORE_ENTITY.get(), blockPos, blockState);
		this.data = new ContainerData() {
			// 返回和设置合成进度的数据
			@Override
			public int get(int index) {
				return switch (index){
					case 0-> Lv1InjectingMagicCoreAltarEntity.this.progress;
					case 1-> Lv1InjectingMagicCoreAltarEntity.this.maxProgress;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value) {
				switch (index){
					case 0-> Lv1InjectingMagicCoreAltarEntity.this.progress = value;
					case 1-> Lv1InjectingMagicCoreAltarEntity.this.maxProgress = value;
				}
			}

			@Override
			public int getCount() {
				return 2;
			}
		};
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
		return new Lv1InjectingMagicAltarMenu(id, inventory, this, this.data);
	}

	// 读取实体的数据
	// 重写了BlockEntity中的getCapability的方法，
	// 提供对lazyItemHandler的访问
	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if(cap == ForgeCapabilities.ITEM_HANDLER){
			return lazyItemHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	// 在onload阶段，加载lazyItemHandler
	@Override
	public void onLoad() {
		super.onLoad();
		lazyItemHandler = LazyOptional.of(()->itemStackHandler);
	}

	// 重写了BlockEntity中的invalidateCaps方法。
	// 在卸载实体方块时候将layItemHandler变量无效
	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		lazyItemHandler.invalidate();
	}
	// 存储实体的数据
	@Override
	protected void saveAdditional(CompoundTag nbt) {
		nbt.put("inventory",itemStackHandler.serializeNBT());
		nbt.putInt("gem_infusing_station.progress",this.progress);
		super.saveAdditional(nbt);
	}

	// 读取实体的数据
	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
		this.progress = nbt.getInt("gem_infusing_station.progress");
	}

	// 当方块被破坏时候掉落方块中的内容。
	public void drops(){
		SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
		for(int i = 0;i<itemStackHandler.getSlots();i++){
			inventory.setItem(i,itemStackHandler.getStackInSlot(i));
		}
		Containers.dropContents(this.level,this.worldPosition,inventory);
	}

	//静态方法，每次回调都会更新状态
	public static void tick(Level level, BlockPos blockPos, BlockState state, Lv1InjectingMagicCoreAltarEntity entity) {
		System.out.println(entity.progress);
		if (level.isClientSide){
			return;
		}
		//  仅仅在服务器端运行
		// 判断是否能够合成
		if(hasRecipe(entity)){
			// 进度增加
			entity.progress +=10 ;
			setChanged(level,blockPos,state);
			// 如果进度条满了
			if(entity.progress >= entity.maxProgress){
				// 合成一个物品
				craftItem(entity);
			}
		}else{
			// 没有合成表就重置
			entity.resetProgress();
			setChanged(level,blockPos,state);
		}


	}
	// 重置进度
	private void resetProgress() {
		this.progress = 0;
	}
	// 合成物品
	private static void craftItem(Lv1InjectingMagicCoreAltarEntity entity) {
		Level level = entity.level;    //获取实体所在的世界
		//新建一个仓库，槽位数量为该方块实体拥有的槽位数量。和方块本身的槽位不一样，这是创建一个新槽位用来与合成表比对
		SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());
		for(int i=0;i < entity.itemStackHandler.getSlots(); i++) {
			inventory.setItem(i,entity.itemStackHandler.getStackInSlot(i));//将上面新建的仓库各槽位的物品设置为放置在该方块内各槽位的物品
		}
		// 获得当前的recipe
		Optional<Lv1InjectingMagicAltarRecipe> recipe = level.getRecipeManager().getRecipeFor(
				Lv1InjectingMagicAltarRecipe.Type.INSTANCE,inventory,level);
		boolean canCraft = false;
		if(hasRecipe(entity)){//检测有没有这个配方
			System.out.println("有配方");
			if (!recipe.isPresent()) return;//如果这个配方是空的则直接返回

			System.out.println("开始检测原料成分");

			//获取配方并检测是否满足合成条件
			Lv1InjectingMagicAltarRecipe Trepice = recipe.get();
			System.out.println("结果" + 	Trepice.matches(inventory, level));//检测是否满足合成条件并输出结果
			//如果满足合成条件则开始合成
			if (Trepice.matches(inventory, level)) {
				//从槽位中逐个取出合成原料，从1开始。0是催化剂。
				for (int i = 1; i < 7; i ++){
					entity.itemStackHandler.extractItem(i, 1, false);
				}
				//将合成结果放入槽位7
				entity.itemStackHandler.setStackInSlot(7, new ItemStack(recipe.get().getResultItem(level.registryAccess()).getItem(),
						entity.itemStackHandler.getStackInSlot(7).getCount() + 1));
				//重置合成进度
				entity.resetProgress();
			}
			// 合成的结果是recipe的result

		}
	}
	// 是否具有合成表
	private static boolean hasRecipe(Lv1InjectingMagicCoreAltarEntity entity) {
		Level level = entity.level;
		SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());
		for(int i=0;i<entity.itemStackHandler.getSlots();i++){
			inventory.setItem(i,entity.itemStackHandler.getStackInSlot(i));
		}
		Optional<Lv1InjectingMagicAltarRecipe> recipe = level.getRecipeManager().getRecipeFor(Lv1InjectingMagicAltarRecipe.Type
				.INSTANCE,inventory,level);

		return recipe.isPresent() && canInsertAmountInToOutputSlot(inventory)&&
				canInsertItemToOutputSlot(inventory,recipe.get().getResultItem(level.registryAccess()));
	}
	// 判断插入slot是是否是相同的item，以及是否为空
	private static boolean canInsertItemToOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
		return inventory.getItem(7).getItem() == itemStack.getItem() || inventory.getItem(7).isEmpty();
	}
	// 判断堆叠是否已满，还能否放入item
	private static boolean canInsertAmountInToOutputSlot(SimpleContainer inventory) {
		return inventory.getItem(7).getMaxStackSize() > inventory.getItem(7).getCount();
	}


	@Override
	public Component getDisplayName() {
		return null;
	}

	@Override
	public void render(Lv1InjectingMagicCoreAltarEntity entity, float v, PoseStack poseStack,
					   MultiBufferSource multiBufferSource, int i, int i1) {
		SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());
		i = 12;
		i1 = 10;

		ItemStack stack = entity.itemStackHandler.getStackInSlot(2);
		// 如果锅中有物品，就渲染这个物品。
		if (!stack.isEmpty()) {
			poseStack.pushPose();
			poseStack.translate(2D, 2D, 2D);
			Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.GROUND,
					0,0, poseStack, multiBufferSource, entity.getLevel(), 0);
			poseStack.popPose();
		}
	}
}
