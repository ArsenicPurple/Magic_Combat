package net.I_love_arsenic.magcom.common.blocks;

import net.I_love_arsenic.magcom.core.util.ServerUtils;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.TimerTask;

public class CrystalBlock extends Block {

    private LivingEntity target;

    public CrystalBlock(Properties properties) {
        super(properties);
    }

    public Block setTarget(LivingEntity target) { this.target = target; return this; }

    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
        if (entityIn.isEntityEqual(target)) {
            target.attackEntityFrom(DamageSource.MAGIC, 7);
            worldIn.removeBlock(pos, false);
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);

        if (target == null) {
            worldIn.removeBlock(pos, false);
            return;
        }

        Vector3d posT = target.getPositionVec();
        BlockPos dir = BlockPos.ZERO.subtract(pos.subtract(new Vector3i(posT.x, posT.y, posT.z)));
        BlockPos newPos = pos.add(Math.min(dir.getX(), 0.5D), Math.min(dir.getY(), 0.5D), Math.min(dir.getZ(), 0.5D));
        if (!(worldIn.getBlockState(newPos.subtract(new Vector3i(0,1,0))).getBlock() instanceof AirBlock)) {
            if (worldIn.getBlockState(newPos).getBlock() instanceof AirBlock) {
                worldIn.removeBlock(pos, true);
                worldIn.setBlockState(newPos, state);
            }
        }

        worldIn.getPendingBlockTicks().scheduleTick(newPos, state.getBlock(), 1);
    }
}
