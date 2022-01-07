package games.trident.skills.profile;

import com.google.common.collect.Maps;
import games.trident.skills.profile.entry.LevelableEntry;

import java.util.HashMap;
import java.util.UUID;

public class ProfileSkills {
    private UUID uuid;
    private HashMap<String, LevelableEntry> levelableEntryHashMap = Maps.newHashMap();
}
