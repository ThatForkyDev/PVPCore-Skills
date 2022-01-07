package games.trident.skills.profile.entry;

import games.trident.skills.type.mining.MiningLevel;
import games.trident.skills.utilities.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;

@Getter
public class LevelableEntry {
    private int level;
    private float experience;
    private Level cachedLevel;

    public LevelableEntry(int level, float experience, Class<? extends Level> levelType) {
        this.level = level;
        this.experience = experience;

        cache(levelType);
    }

    public <T extends Level> void cache(Class<T> levelType) {
        try {
            cachedLevel = levelType.getDeclaredConstructor(Integer.class, Float.class).newInstance(level, experience);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
