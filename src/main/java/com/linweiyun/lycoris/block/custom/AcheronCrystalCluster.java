package com.linweiyun.lycoris.block.custom;

import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.init.LycorisRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AcheronCrystalCluster extends Block {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LycorisMod.MOD_ID);
    public static final RegistryObject<Block> ACHERON_CRYSTAL_CLUSTER = LycorisRegistry.registerFireproofBlock("acheron_crystal_cluster",
        BLOCKS, () -> new AcheronCrystalCluster(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
                .noOcclusion()
                .strength(6f, 3000f),
                UniformInt.of(4, 12)));




    public AcheronCrystalCluster(Properties pProperties, UniformInt uniformInt) {
        super(pProperties);
    }


    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20);//线程数量。如果只有一个，同时有两个方块被破坏
                                                                                                             //只能一个个排队执行，不能同时执行
                                                                                                             //也就是能同时处理的爆炸的数量
    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (!pLevel.isClientSide){
            scheduler.schedule(() -> {
                pLevel.explode(null, pPos.getX(), pPos.getY(), pPos.getZ(), 4, Level.ExplosionInteraction.MOB);
            }, 1200L, TimeUnit.MILLISECONDS);

        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{HorizontalDirectionalBlock.FACING});
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection().getOpposite());
    }


    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
