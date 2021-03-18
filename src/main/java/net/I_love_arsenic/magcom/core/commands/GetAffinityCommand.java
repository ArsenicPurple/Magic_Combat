package net.I_love_arsenic.magcom.core.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.I_love_arsenic.magcom.core.capabilities.affinity.Affinities;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public final class GetAffinityCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("getaffinity")
                .requires(source -> source.hasPermissionLevel(4))
                .executes(context -> getAffinity(context.getSource())
                )
        );
    }

    private static int getAffinity(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        player.getCapability(Affinities.ENTITY_AFFINITY_TYPE).ifPresent(a -> {
            int type = a.getAffinity();
            player.sendStatusMessage(new StringTextComponent(Integer.toString(type)), true);
        });
        return 0;
    }
}
