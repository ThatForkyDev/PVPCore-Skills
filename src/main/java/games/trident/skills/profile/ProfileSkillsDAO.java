package games.trident.skills.profile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import games.trident.skills.database.Query;
import games.trident.skills.utilities.UUIDUtil;

import javax.annotation.Nullable;
import java.lang.reflect.Modifier;
import java.sql.*;
import java.util.UUID;

public class ProfileSkillsDAO {
    private static Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .create();

    @Nullable
    @Query(select = "data", from = "account_skills", where = "uuid", type = Query.Type.SELECT)
    public static ProfileSkills getAccount(Connection connection, UUID uuid) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT `data` FROM `account_skills` WHERE `uuid`=UNHEX(?);")) {
            statement.setString(1, UUIDUtil.strip(uuid));
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return gson.fromJson(result.getString("data"), ProfileSkills.class);
                }

                return addAccount(connection, uuid, new ProfileSkills(uuid));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    @Query(insert = "uuid, data", into = "account_skills", type = Query.Type.INSERT)
    public static ProfileSkills addAccount(Connection connection, UUID uuid, ProfileSkills profile) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `account_skills` (`uuid`, `data`) VALUES (UNHEX(?), ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, UUIDUtil.strip(uuid));
            statement.setString(2, gson.toJson(profile));
            statement.executeUpdate();
            try (ResultSet result = statement.getGeneratedKeys()) {
                if (result.next()) {
                    return gson.fromJson(result.getString("data"), ProfileSkills.class);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Query(set = "data", from = "account_skills", where = "uuid", type = Query.Type.UPDATE)
    public static boolean setData(Connection connection, UUID uuid, ProfileSkills profile) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE `account_skills` SET `data`=? WHERE `uuid`=UNHEX(?);")) {
            statement.setString(1, gson.toJson(profile));
            statement.setString(2, UUIDUtil.strip(uuid));
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}