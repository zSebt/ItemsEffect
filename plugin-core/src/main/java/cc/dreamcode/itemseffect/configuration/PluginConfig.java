package cc.dreamcode.itemseffect.configuration;

import cc.dreamcode.itemseffect.serializer.Item;
import cc.dreamcode.menu.adventure.BukkitMenuBuilder;
import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.Header;

import java.util.Collections;
import java.util.List;

@Configuration(child = "config.yml")
@Header("## Dream-ItemsEffect (Main-Config) ##")
public class PluginConfig extends OkaeriConfig {
    @Comment("Debug pokazuje dodatkowe informacje w konsoli. Lepiej wylaczyc. :P")
    @CustomKey("debug")
    public boolean debug = true;

    @Comment("Lista przedmiotow, ktore posiadaja efekt")
    @CustomKey("effect-items")
    public List<Item> effects = Collections.emptyList();

    @Comment("Lista armoru, ktory posiada efekt")
    @CustomKey("armor-items")
    public List<Item> armorEffects = Collections.emptyList();

    @Comment("Wyglad gui z przedmiotami, ktore posiadaja efekt")
    @CustomKey("effect-menu")
    public BukkitMenuBuilder effectsMenu = new BukkitMenuBuilder("&7Przedmioty z efektem", 3, Collections.emptyMap());
}
