package cc.dreamcode.itemseffect.configuration;

import cc.dreamcode.notice.adventure.BukkitNotice;
import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.Headers;

@Configuration(child = "message.yml")
@Headers({
        @Header("## Dream-ItemsEffect (Message-Config) ##"),
        @Header("Dostepne type: (DO_NOT_SEND, CHAT, ACTION_BAR, SUBTITLE, TITLE, TITLE_SUBTITLE)")
})
public class MessageConfig extends OkaeriConfig {
    @CustomKey("command-usage")
    public BukkitNotice usage = BukkitNotice.chat("&7Przyklady uzycia komendy: &c{label}");
    @CustomKey("command-usage-help")
    public BukkitNotice usagePath = BukkitNotice.chat("&f{usage} &8- &7{description}");

    @CustomKey("command-usage-not-found")
    public BukkitNotice usageNotFound = BukkitNotice.chat("&cNie znaleziono pasujacych do kryteriow komendy.");
    @CustomKey("command-path-not-found")
    public BukkitNotice pathNotFound = BukkitNotice.chat("&cTa komenda jest pusta lub nie posiadasz dostepu do niej.");
    @CustomKey("command-no-permission")
    public BukkitNotice noPermission = BukkitNotice.chat("&cNie posiadasz uprawnien.");
    @CustomKey("command-not-player")
    public BukkitNotice notPlayer = BukkitNotice.chat("&cTa komende mozna tylko wykonac z poziomu gracza.");
    @CustomKey("command-not-console")
    public BukkitNotice notConsole = BukkitNotice.chat("&cTa komende mozna tylko wykonac z poziomu konsoli.");
    @CustomKey("command-invalid-format")
    public BukkitNotice invalidFormat = BukkitNotice.chat("&cPodano nieprawidlowy format argumentu komendy. ({input})");
    @CustomKey("command-not-holding-item")
    public BukkitNotice notHoldingItem = BukkitNotice.chat("&cMusisz trzymac przedmiot w rece.");
    @CustomKey("command-not-holding-armor")
    public BukkitNotice notHoldingArmor = BukkitNotice.chat("&cMusisz trzymac zbroje w rece.");

    @CustomKey("command-effect-add")
    public BukkitNotice effectAdd = BukkitNotice.chat("&aEfekt &2{type} &azostal dodany.");
    @CustomKey("command-effect-not-exists")
    public BukkitNotice effectRemoveNotExists = BukkitNotice.chat("&cTen przedmiot nie posiada zadnych efektow.");
    @CustomKey("command-effect-clear")
    public BukkitNotice effectClear = BukkitNotice.chat("&aEfekty tego przedmiotu zostaly wyczyszczone.");
    @CustomKey("command-effect-remove")
    public BukkitNotice effectRemove = BukkitNotice.chat("&aEfekt &2{type} &azostal usuniety!");
    @CustomKey("command-effect-not-present")
    public BukkitNotice effectNotPresent = BukkitNotice.chat("&cTen przedmiot nie posiada tego efektu!");

    @CustomKey("effect-not-found")
    public BukkitNotice effectNotFound = BukkitNotice.chat("&cPodanego efektu nie znaleziono.");
}
