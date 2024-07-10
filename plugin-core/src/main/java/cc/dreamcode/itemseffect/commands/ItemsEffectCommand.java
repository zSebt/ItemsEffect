package cc.dreamcode.itemseffect.commands;

import cc.dreamcode.command.CommandBase;
import cc.dreamcode.command.DreamSender;
import cc.dreamcode.command.annotation.*;
import cc.dreamcode.itemseffect.configuration.MessageConfig;
import cc.dreamcode.itemseffect.configuration.PluginConfig;
import cc.dreamcode.itemseffect.menu.EffectItemsMenu;
import cc.dreamcode.itemseffect.serializer.Item;
import cc.dreamcode.utilities.builder.MapBuilder;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import java.util.Optional;

@Command(name = "itemseffect")
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ItemsEffectCommand implements CommandBase {
    private final PluginConfig pluginConfig;
    private final MessageConfig messageConfig;
    private final EffectItemsMenu effectItemsMenu;

    @Async
    @Permission("dream-itemeffects.use")
    @Sender(DreamSender.Type.CLIENT)
    @Executor(description = "Otwiera gui z przedmiotami, ktore posiadaja efekt")
    void gui(Player sender) {
        this.effectItemsMenu.build(sender).open(sender);
    }

    @Async
    @Permission("dream-itemeffects.add")
    @Sender(DreamSender.Type.CLIENT)
    @Executor(path = "add", description = "Dodaje efekt do przedmiotu")
    void add(Player sender, @Arg PotionEffectType type, @Arg Integer value, @Arg Integer time) {
        if (sender.getItemInHand() == null || sender.getItemInHand().getType().equals(Material.AIR)) {
            this.messageConfig.notHoldingItem.send(sender);
            return;
        }

        Optional<Item> item = this.pluginConfig.effects.stream().filter(it -> it.getItemStack().equals(sender.getItemInHand())).findAny();

        if (item.isPresent()) item.get().getPotionEffects().add(new PotionEffect(type, time * 20, value));
        else
            this.pluginConfig.effects.add(new Item(sender.getItemInHand(), Collections.singletonList(new PotionEffect(type, time * 20, value))));

        this.pluginConfig.save();

        this.messageConfig.effectAdd.send(sender, new MapBuilder<String, Object>()
                .put("type", type.getName())
                .build());
    }

    @Async
    @Permission("dream-itemeffects.remove")
    @Sender(DreamSender.Type.CLIENT)
    @Executor(path = "remove", description = "Usuwa efekt z przedmiotu")
    void remove(Player sender) {
        if (sender.getItemInHand() == null || sender.getItemInHand().getType().equals(Material.AIR)) {
            this.messageConfig.notHoldingItem.send(sender);
            return;
        }

        Optional<Item> item = this.pluginConfig.effects.stream().filter(it -> it.getItemStack().equals(sender.getItemInHand())).findAny();

        if (!item.isPresent()) {
            this.messageConfig.effectNotFound.send(sender);
            return;
        }

        this.pluginConfig.effects.remove(item.get());
        this.pluginConfig.save();

        this.messageConfig.effectClear.send(sender);
    }

    @Async
    @Permission("dream-itemeffects.set-add")
    @Sender(DreamSender.Type.CLIENT)
    @Executor(path = "set add", description = "Nadaje efekt na set")
    void setAdd(Player sender, @Arg PotionEffectType type, @Arg Integer value, @Arg Integer time) {
        if (sender.getItemInHand() == null || sender.getItemInHand().getType().equals(Material.AIR)) {
            this.messageConfig.notHoldingItem.send(sender);
            return;
        }

        /* if (!sender.getItemInHand().getType().name().endsWith("_HELMET") ||
                !sender.getItemInHand().getType().name().endsWith("_CHESTPLATE") ||
                !sender.getItemInHand().getType().name().endsWith("_LEGGINGS") ||
                !sender.getItemInHand().getType().name().endsWith("_BOOTS")) {
            this.messageConfig.notHoldingArmor.send(sender);
            return;
        } */

        Optional<Item> item = this.pluginConfig.armorEffects.stream().filter(it -> it.getItemStack().equals(sender.getItemInHand())).findAny();

        if (item.isPresent()) item.get().getPotionEffects().add(new PotionEffect(type, time * 20, value));
        else
            this.pluginConfig.armorEffects.add(new Item(sender.getItemInHand(), Collections.singletonList(new PotionEffect(type, time * 20, value))));

        this.pluginConfig.save();

        this.messageConfig.effectAdd.send(sender, new MapBuilder<String, Object>()
                .put("type", type.getName())
                .build());
    }

    @Async
    @Permission("dream-itemeffects.set-remove")
    @Sender(DreamSender.Type.CLIENT)
    @Executor(path = "set remove", description = "Usuwa efekt z seta")
    void setRemove(Player sender, @Arg PotionEffectType type) {
        if (sender.getItemInHand() == null || sender.getItemInHand().getType().equals(Material.AIR)) {
            this.messageConfig.notHoldingItem.send(sender);
            return;
        }

        /* if (!sender.getItemInHand().getType().name().endsWith("_HELMET") ||
                !sender.getItemInHand().getType().name().endsWith("_CHESTPLATE") ||
                !sender.getItemInHand().getType().name().endsWith("_LEGGINGS") ||
                !sender.getItemInHand().getType().name().endsWith("_BOOTS")) {
            this.messageConfig.notHoldingArmor.send(sender);
            return;
        } */

        Optional<Item> item = this.pluginConfig.armorEffects.stream().filter(it -> it.getItemStack().equals(sender.getItemInHand())).findAny();

        if (!item.isPresent()) {
            this.messageConfig.effectRemoveNotExists.send(sender);
            return;
        }

        Optional<PotionEffect> effect = item.get().getPotionEffects().stream().filter(eff -> eff.getType().equals(type)).findFirst();

        if (!effect.isPresent()) {
            this.messageConfig.effectNotPresent.send(sender);
            return;
        }

        Item i = item.get();

        this.pluginConfig.armorEffects.remove(i);
        i.getPotionEffects().remove(effect.get());

        if (i.getPotionEffects().size() > 0)
            this.pluginConfig.armorEffects.add(i);

        this.pluginConfig.save();

        this.messageConfig.effectRemove.send(sender, new MapBuilder<String, Object>()
                .put("type", type.getName())
                .build());
    }
}
