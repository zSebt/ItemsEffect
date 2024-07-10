package cc.dreamcode.itemseffect.commands.resolver;

import cc.dreamcode.command.resolver.transformer.ObjectTransformer;
import lombok.NonNull;
import org.bukkit.potion.PotionEffectType;

import java.util.Optional;

public class EffectResolver implements ObjectTransformer<PotionEffectType> {
    @Override
    public Class<?> getGeneric() {
        return PotionEffectType.class;
    }

    @Override
    public Optional<PotionEffectType> transform(@NonNull Class<?> type, @NonNull String input) {
        return Optional.ofNullable(PotionEffectType.getByName(input));
    }
}
