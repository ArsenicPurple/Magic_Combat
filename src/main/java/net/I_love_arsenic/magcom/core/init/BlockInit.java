package net.I_love_arsenic.magcom.core.init;

import net.I_love_arsenic.magcom.MagCom;

import net.I_love_arsenic.magcom.common.blocks.CrucibleBlock;
import net.I_love_arsenic.magcom.common.blocks.CrystalBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MagCom.MOD_ID);

    public static final RegistryObject<Block> ETCHED_OBSIDIAN = BLOCKS.register("etched_obsidian",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.OBSIDIAN)
                    .hardnessAndResistance(15f, 30f)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(2)
                    .sound(SoundType.METAL)
                    .setRequiresTool()
            ));

    public static final RegistryObject<CrucibleBlock> MAGE_CRUCIBLE = BLOCKS.register("mage_crucible",
            () -> new CrucibleBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.STONE)
                    .setRequiresTool()
                    .hardnessAndResistance(2.0F)
                    .notSolid()
            ));

    public static final RegistryObject<CrystalBlock> CRYSTAL_BLOCK = BLOCKS.register("crystal_block",
            () -> new CrystalBlock(AbstractBlock.Properties.create(Material.ICE, MaterialColor.ICE)
                    .hardnessAndResistance(-1, 10)
                    .setOpaque(BlockInit::isntSolid)
                    .setSuffocates(BlockInit::isntSolid)
                    .setBlocksVision(BlockInit::isntSolid)
            ));

    private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

}
