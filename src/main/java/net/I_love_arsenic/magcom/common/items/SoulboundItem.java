package net.I_love_arsenic.magcom.common.items;

import net.I_love_arsenic.magcom.common.helpers.ItemNBTHelper;
import net.I_love_arsenic.magcom.core.capabilities.affinity.Affinities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/*
Code taken from the Botania Mod
Link Here: https://github.com/Vazkii/Botania/blob/8f0eb1340e9684e9143879958bb7b35ce39b095e/src/main/java/vazkii/botania/common/core/helper/ItemNBTHelper.java#L25
 */

public class SoulboundItem extends Item {
    public SoulboundItem(Properties properties) {
        super(properties);
    }

    private static final String TAG_PLAYER_ID = "playerUUID";
    private static final String TAG_CUSTOM_MODEL_DATA = "CustomModelData";

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        if (!hasUUID(stack)) {
            tooltip.add(new TranslationTextComponent("tooltip.magic_combat.unbound"));
        } else {
            if (getSoulboundUUID(stack).equals(Minecraft.getInstance().player.getUniqueID())) {
                tooltip.add(new TranslationTextComponent("tooltip.magic_combat.bound_to", Minecraft.getInstance().player.getName()));
            }
        }
    }

    @Override
    public boolean updateItemStackNBT(CompoundNBT nbt) {
        return super.updateItemStackNBT(nbt);
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote()) {
            if (!getSoulboundUUID(playerIn.getHeldItem(handIn).getStack()).equals(playerIn.getUniqueID())) {
                EffectInstance effect = new EffectInstance(Effects.WEAKNESS, 2000,20);

                playerIn.addPotionEffect(effect);

                ITextComponent textComponent = new TranslationTextComponent("notif.magic_combat.foreign_soulbound");
                playerIn.sendMessage(textComponent, playerIn.getUniqueID());

                return ActionResult.resultFail(playerIn.getHeldItem(handIn));
            }
        }

        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isRemote() && entityIn instanceof PlayerEntity) {
            if (stack.isEmpty()) {
                return;
            }

            if (!hasUUID(stack)) {
                bindToUUID(entityIn.getUniqueID(), stack);
                if (stack.getItem() instanceof WandItem) {
                    entityIn.getCapability(Affinities.ENTITY_AFFINITY_TYPE).ifPresent(a -> {
                        setCustomModelData(stack, a.getAffinity());
                    });
                }
            }
        }

        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    public void bindToUUID(UUID uuid, ItemStack stack) {
        ItemNBTHelper.setString(stack, TAG_PLAYER_ID, uuid.toString());
    }

    public UUID getSoulboundUUID(ItemStack stack) {
        if (ItemNBTHelper.verifyExistance(stack, TAG_PLAYER_ID)) {
            try {
                return UUID.fromString(ItemNBTHelper.getString(stack, TAG_PLAYER_ID, ""));
            } catch (IllegalArgumentException ex) { // Bad UUID in tag
                ItemNBTHelper.removeEntry(stack, TAG_PLAYER_ID);
            }
        }
        return null;
    }

    public boolean hasUUID(ItemStack stack) {
        return getSoulboundUUID(stack) != null;
    }

    public void setCustomModelData(ItemStack stack, int data) {
        ItemNBTHelper.setInt(stack, TAG_CUSTOM_MODEL_DATA, data);
    }
}
