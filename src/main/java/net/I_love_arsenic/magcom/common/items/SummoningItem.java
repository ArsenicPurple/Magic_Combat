package net.I_love_arsenic.magcom.common.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.storage.IWorldInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class SummoningItem extends Item {
    public SummoningItem(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        tooltip.add(new TranslationTextComponent("tooltip.magic_combat.red_crystal"));
    }

    //Checks is Dropped Entity is in the open then Spawn a Boorit Mob Entity
    @Override
    public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
        World world = player.getEntityWorld();

        BlockPos itemPos = item.getAttachedEntity().getPosition();

        if (!world.isRemote()) {
            if (world.canSeeSky(itemPos)) {
                ITextComponent textComponent = new TranslationTextComponent("notif.magic_combat.boorit_spawn");
                player.sendMessage(textComponent, player.getUniqueID());

                if (!world.isRemote()) {
                    IWorldInfo worldInfo = world.getWorldInfo();
                    worldInfo.setRaining(true);
                    world.setThunderStrength(100);

                    //Spawn Boorit Here
                    Random rand = random;
                    Vector3i spawnPos = new Vector3i(rand.nextInt(60) - 30,0,rand.nextInt(60) - 30);
                    itemPos.add(spawnPos);
                }
            }
        }
        return true;
    }
}
