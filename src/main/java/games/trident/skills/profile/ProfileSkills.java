package games.trident.skills.profile;

import com.google.common.collect.Maps;
import games.trident.skills.profile.entry.LevelableEntry;
import games.trident.skills.utilities.Level;

import java.util.HashMap;
import java.util.UUID;

public class ProfileSkills {
    private UUID uuid;
    private HashMap<String, LevelableEntry> levelableEntryHashMap = Maps.newHashMap();

    public LevelableEntry getLevableEntry(String key, Class<? extends Level> levelType) {
        if (!levelableEntryHashMap.containsKey(key))
            levelableEntryHashMap.put(key, new LevelableEntry(1, 0, levelType));

        return levelableEntryHashMap.get(key);
    }
}
