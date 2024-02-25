
package com.linweiyun.lycoris.block.custom;


import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.blockentity.Lv1InjectingMagicCoreAltarEntity;
import com.linweiyun.lycoris.init.LycorisRegistry;
import com.linweiyun.lycoris.screen.Lv1InjectingMagicAltarMenu;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class Lv1InjectingMagicAltarCore extends BaseEntityBlock {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LycorisMod.MOD_ID);
	public static final RegistryObject<Block> LV1_INJECTING_MAGIC_ALTAR_CORE = LycorisRegistry.registerBlock("lv1_injecting_magic_altar_core", BLOCKS,
			() -> new Lv1InjectingMagicAltarCore(BlockBehaviour.Properties.copy(Blocks.STONE)
					.noOcclusion()
					.requiresCorrectToolForDrops()
					.strength(6f)
					.explosionResistance(1000.f)));
	public Lv1InjectingMagicAltarCore() {
		super(Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.GRAVEL).strength(1f, 10f));
	}
	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	public Lv1InjectingMagicAltarCore(Properties strength) {
		super(strength);
	}


	@Override
	public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(new Property[]{HorizontalDirectionalBlock.FACING});
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection().getOpposite());
	}
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(HorizontalDirectionalBlock.FACING)) {
			default -> Shapes.or(box(3, 0, 3, 13, 1, 13), box(4, 1, 4, 12, 10, 12), box(2, 10, 2, 14, 12, 14));
			case NORTH -> Shapes.or(box(3, 0, 3, 13, 1, 13), box(4, 1, 4, 12, 10, 12), box(2, 10, 2, 14, 12, 14));
			case EAST -> Shapes.or(box(3, 0, 3, 13, 1, 13), box(4, 1, 4, 12, 10, 12), box(2, 10, 2, 14, 12, 14));
			case WEST -> Shapes.or(box(3, 0, 3, 13, 1, 13), box(4, 1, 4, 12, 10, 12), box(2, 10, 2, 14, 12, 14));
		};
	}

	@Override
	public InteractionResult use(BlockState state, Level level,
								 BlockPos pos, Player entity, InteractionHand hand,
								 BlockHitResult res) {
		super.use(state, level, pos, entity, hand, res);

		if (entity instanceof ServerPlayer player) {
			System.out.println("成功了");
			NetworkHooks.openScreen(player, new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("null");
				}
				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new Lv1InjectingMagicAltarMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		return InteractionResult.SUCCESS;}
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new Lv1InjectingMagicCoreAltarEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
		// 判断新的方块是不是和旧的方块是同一个方块
		if(state.getBlock() != newState.getBlock()){
			// 获得方块的entity
			BlockEntity block = level.getBlockEntity(blockPos);
			// 如果该entity 是
			if(block instanceof Lv1InjectingMagicCoreAltarEntity){
//                entity.drops();
				// 调用实体的掉落方法。
				((Lv1InjectingMagicCoreAltarEntity) block).drops();
			}

		}
		super.onRemove(state, level, blockPos, newState, isMoving);
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		// 为指定的实体创建一个新的BlockEntityticker
		return createTickerHelper(type, Lv1InjectingMagicCoreAltarEntity.LV1_INJECTING_MAGIC_ALTAR_CORE_ENTITY.get(),
				Lv1InjectingMagicCoreAltarEntity::tick);
	}

	public static void register(IEventBus eventBus)
	{
		BLOCKS.register(eventBus);
	}


}
