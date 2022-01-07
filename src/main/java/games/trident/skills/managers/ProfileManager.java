package games.trident.skills.managers;

import com.google.common.collect.Maps;
import games.trident.skills.profile.ProfileSkills;

import java.util.HashMap;
import java.util.UUID;

public class ProfileManager {
    private HashMap<UUID, ProfileSkills> profiles = Maps.newHashMap();

    public ProfileSkills getProfile(UUID uuid) {
        if (!profiles.containsKey(uuid)) {
            profiles.put(uuid, new ProfileSkills());
        }

        return profiles.get(uuid);
    }
}
