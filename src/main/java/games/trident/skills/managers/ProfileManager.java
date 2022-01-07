package games.trident.skills.managers;

import com.google.common.collect.Maps;
import games.trident.skills.database.BaseDatabase;
import games.trident.skills.profile.ProfileSkills;
import games.trident.skills.profile.ProfileSkillsDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class ProfileManager {
    private HashMap<UUID, ProfileSkills> profiles = Maps.newHashMap();

    public ProfileSkills getProfile(UUID uuid) {
        if (!profiles.containsKey(uuid)) {
            profiles.put(uuid, load(uuid));
        }

        return profiles.get(uuid);
    }

    public ProfileSkills load(UUID uuid) {
        try (Connection connection = BaseDatabase.getInstance().getConnection()) {
            return ProfileSkillsDAO.getAccount(connection, uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(UUID uuid, boolean delete) {
        if (profiles.containsKey(uuid)) {
            try (Connection connection = BaseDatabase.getInstance().getConnection()) {
                ProfileSkillsDAO.setBalance(connection, uuid, profiles.get(uuid));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (delete)
                profiles.remove(uuid);
        }
    }
}
