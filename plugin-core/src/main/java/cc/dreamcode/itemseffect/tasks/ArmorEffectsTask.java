package cc.dreamcode.itemseffect.tasks;

import cc.dreamcode.itemseffect.configuration.PluginConfig;
import cc.dreamcode.itemseffect.serializer.Item;
import cc.dreamcode.platform.bukkit.component.scheduler.Scheduler;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;

@Scheduler(async = false, delay = 20L, interval = 20L)
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ArmorEffectsTask extends BukkitRunnable {
    private final PluginConfig pluginConfig;

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            for (ItemStack armor : player.getInventory().getArmorContents()) {
                if (armor == null) continue;

                Optional<Item> item = this.pluginConfig.armorEffects.stream().filter(it -> it.getItemStack().equals(armor)).findAny();

                if (!item.isPresent()) continue;

                item.get().getPotionEffects().forEach(effect -> player.addPotionEffect(effect));
            }
        });
    }
}
