package cc.dreamcode.itemseffect.commands.handler;

import cc.dreamcode.command.DreamSender;
import cc.dreamcode.command.bukkit.BukkitSender;
import cc.dreamcode.command.handler.InvalidInputHandler;
import cc.dreamcode.itemseffect.configuration.MessageConfig;
import cc.dreamcode.utilities.builder.MapBuilder;
import eu.okaeri.injector.annotation.Inject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class InvalidInputHandlerImpl implements InvalidInputHandler {
    private final MessageConfig messageConfig;

    @Override
    public void handle(@NonNull DreamSender<?> dreamSender, @NonNull Class<?> requiringClass, @NonNull String input) {
        final BukkitSender bukkitSender = (BukkitSender) dreamSender;

        if (requiringClass.isAssignableFrom(PotionEffectType.class)) {
            this.messageConfig.effectNotFound.send(bukkitSender.getHandler());
            return;
        }

        this.messageConfig.invalidFormat.send(bukkitSender.getHandler(), new MapBuilder<String, Object>()
                .put("input", input)
                .build());
    }
}
