package cc.dreamcode.itemseffect;

import cc.dreamcode.command.bukkit.BukkitCommandProvider;
import cc.dreamcode.itemseffect.commands.ItemsEffectCommand;
import cc.dreamcode.itemseffect.commands.handler.InvalidInputHandlerImpl;
import cc.dreamcode.itemseffect.commands.handler.InvalidPermissionHandlerImpl;
import cc.dreamcode.itemseffect.commands.handler.InvalidSenderHandlerImpl;
import cc.dreamcode.itemseffect.commands.handler.InvalidUsageHandlerImpl;
import cc.dreamcode.itemseffect.commands.resolver.EffectResolver;
import cc.dreamcode.itemseffect.configuration.MessageConfig;
import cc.dreamcode.itemseffect.configuration.PluginConfig;
import cc.dreamcode.itemseffect.menu.EffectItemsMenu;
import cc.dreamcode.itemseffect.serializer.ItemSerdes;
import cc.dreamcode.itemseffect.tasks.ArmorEffectsTask;
import cc.dreamcode.itemseffect.tasks.EffectsTask;
import cc.dreamcode.menu.adventure.BukkitMenuProvider;
import cc.dreamcode.menu.adventure.serializer.MenuBuilderSerializer;
import cc.dreamcode.notice.adventure.BukkitNoticeProvider;
import cc.dreamcode.notice.adventure.serializer.BukkitNoticeSerializer;
import cc.dreamcode.platform.DreamVersion;
import cc.dreamcode.platform.bukkit.DreamBukkitConfig;
import cc.dreamcode.platform.bukkit.DreamBukkitPlatform;
import cc.dreamcode.platform.bukkit.component.ConfigurationResolver;
import cc.dreamcode.platform.component.ComponentService;
import cc.dreamcode.platform.other.component.DreamCommandExtension;
import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.tasker.bukkit.BukkitTasker;
import lombok.Getter;
import lombok.NonNull;

public final class EffectPlugin extends DreamBukkitPlatform implements DreamBukkitConfig {
    @Getter private static EffectPlugin instance;

    @Override
    public void load(@NonNull ComponentService componentService) {
        instance = this;
    }

    @Override
    public void enable(@NonNull ComponentService componentService) {
        componentService.setDebug(false);

        this.registerInjectable(BukkitTasker.newPool(this));
        this.registerInjectable(BukkitMenuProvider.create(this));
        this.registerInjectable(BukkitNoticeProvider.create(this));

        this.registerInjectable(BukkitCommandProvider.create(this));
        componentService.registerExtension(DreamCommandExtension.class);

        componentService.registerResolver(ConfigurationResolver.class);
        componentService.registerComponent(MessageConfig.class);

        componentService.registerComponent(EffectResolver.class);
        componentService.registerComponent(InvalidInputHandlerImpl.class);
        componentService.registerComponent(InvalidPermissionHandlerImpl.class);
        componentService.registerComponent(InvalidSenderHandlerImpl.class);
        componentService.registerComponent(InvalidUsageHandlerImpl.class);

        componentService.registerComponent(PluginConfig.class, pluginConfig -> componentService.setDebug(pluginConfig.debug));

        componentService.registerComponent(EffectsTask.class);
        componentService.registerComponent(ArmorEffectsTask.class);
        componentService.registerComponent(EffectItemsMenu.class);
        componentService.registerComponent(ItemsEffectCommand.class);
    }

    @Override
    public void disable() {

    }

    @Override
    public @NonNull DreamVersion getDreamVersion() {
        return DreamVersion.create("Dream-ItemsEffect", "1.0", "Sebt");
    }

    @Override
    public OkaeriSerdesPack getConfigSerdesPack() {
        return registry -> {
            registry.register(new BukkitNoticeSerializer());
            registry.register(new MenuBuilderSerializer());
            registry.register(new ItemSerdes());
        };
    }


}
