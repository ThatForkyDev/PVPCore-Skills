package games.trident.skills.profile;

import com.google.common.collect.Maps;
import games.trident.skills.utilities.Level;

import java.util.HashMap;
import java.util.UUID;

public class ProfileSkills {
    private UUID uuid;
    private HashMap<String, Level> levelableEntryHashMap = Maps.newHashMap();

    public Level getLevableEntry(String key, Level level) {
        if (!levelableEntryHashMap.containsKey(key))
            levelableEntryHashMap.put(key, level);

        return levelableEntryHashMap.get(key);
    }
}
