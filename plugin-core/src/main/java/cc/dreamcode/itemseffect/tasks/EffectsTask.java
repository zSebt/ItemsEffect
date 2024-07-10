package cc.dreamcode.itemseffect.tasks;

import cc.dreamcode.itemseffect.configuration.PluginConfig;
import cc.dreamcode.itemseffect.serializer.Item;
import cc.dreamcode.platform.bukkit.component.scheduler.Scheduler;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;

@Scheduler(async = false, delay = 20L, interval = 20L)
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class EffectsTask extends BukkitRunnable {
    private final PluginConfig pluginConfig;

    @Override
    public void run() {
        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            Optional<Item> item = this.pluginConfig.effects.stream().filter(it -> it.getItemStack().equals(player.getItemInHand())).findAny();

            if (!item.isPresent()) return;

            item.get().getPotionEffects().forEach(effect -> player.addPotionEffect(effect));
        });
    }
}
