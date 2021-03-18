package net.I_love_arsenic.magcom.common.items.melees;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.I_love_arsenic.magcom.client.util.ClientUtils;
import net.I_love_arsenic.magcom.common.items.SoulboundItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class RainbowSwordItem extends SoulboundItem {

    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public RainbowSwordItem(Properties properties, float attackSpeedIn) {
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)attackSpeedIn, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public int hits = 0;
    private String target = "";

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        tooltip.add(new TranslationTextComponent("tooltip.magic_combat.rainbow_sword"));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (player.getCooledAttackStrength(0) != 1.0f) {
            return false;
        }

        if (entity instanceof LivingEntity) {

            if (target.equals(entity.getUniqueID().toString()) || target.isEmpty()) {
                target = entity.getUniqueID().toString();

                if (player.world.isRemote) {
                    assert Minecraft.getInstance().world != null;
                    Random rand = Minecraft.getInstance().world.getRandom();

                    ClientUtils.spawnParticles(
                            player.world,
                            ParticleTypes.SOUL,
                            entity.getPosX(),
                            entity.getPosY() + entity.getHeight() / 2,
                            entity.getPosZ(),
                            rand.nextDouble() * 0.6 - 0.3,
                            rand.nextDouble() * 0.3,
                            rand.nextDouble() * 0.6 - 0.3,
                            6);
                }

                hits++;
                setCustomModelData(stack, hits);

                if (hits >= 7) {
                    DamageSource rainbow = new DamageSource("dead_end_rainbow");
                    entity.attackEntityFrom(rainbow, ((LivingEntity) entity).getMaxHealth() * 10);

                    hits = 0;
                    setCustomModelData(stack, hits);

                    target = "";
                }

            } else {
                hits = 1;
                setCustomModelData(stack, hits);
                target = entity.getUniqueID().toString();
            }
        }
        return true;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(equipmentSlot);
    }
}
