package cc.dreamcode.itemseffect.menu;

import cc.dreamcode.itemseffect.configuration.PluginConfig;
import cc.dreamcode.menu.adventure.BukkitMenuBuilder;
import cc.dreamcode.menu.adventure.base.BukkitMenu;
import cc.dreamcode.menu.adventure.setup.BukkitMenuPlayerSetup;
import eu.okaeri.injector.annotation.Inject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.HumanEntity;

import java.util.Collections;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class EffectItemsMenu implements BukkitMenuPlayerSetup {
    private final PluginConfig pluginConfig;

    @Override
    public BukkitMenu build(@NonNull HumanEntity humanEntity) {
        BukkitMenuBuilder builder = this.pluginConfig.effectsMenu;
        BukkitMenu bukkitMenu = builder.buildEmpty();

        bukkitMenu.setCancelInventoryClick(false);

        this.pluginConfig.effects.forEach(effect -> bukkitMenu.addItem(effect.getItemStack()));
        this.pluginConfig.armorEffects.forEach(effect -> bukkitMenu.addItem(effect.getItemStack()));
        return bukkitMenu;
    }
}
