package games.trident.skills.profile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import games.trident.skills.type.mining.MiningLevel;
import games.trident.skills.utilities.Level;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ProfileSkills {
    private UUID uuid;
    private HashMap<String, Level> levelableEntryHashMap = Maps.newHashMap();
    private HashMap<String, List<Integer>> claimedLevels = Maps.newHashMap();

    public ProfileSkills(UUID uuid) {
        this.uuid = uuid;
    }

    public Level getLevableEntry(String key, Level level) {
        if (!levelableEntryHashMap.containsKey(key))
            levelableEntryHashMap.put(key, level);

        return levelableEntryHashMap.get(key);
    }

    public boolean isClaimed(String key, int level) {
        if (!claimedLevels.containsKey(key))
            claimedLevels.put(key, Lists.newArrayList());

        return claimedLevels.get(key).contains(level);
    }

    public void setClaimed(String key, int level) {
        if (!claimedLevels.containsKey(key))
            claimedLevels.put(key, Lists.newArrayList());

        claimedLevels.get(key).add(level);
    }

    public void reset() {
        levelableEntryHashMap.clear();
        claimedLevels.clear();
    }
}
