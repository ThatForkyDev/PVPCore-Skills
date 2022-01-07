package games.trident.skills.utilities;

import java.util.UUID;

public class UUIDUtil {

    public static String strip(UUID uuid) {
        return uuid.toString().replaceAll("-", "");
    }

    public static UUID build(String uuid) {
        StringBuilder builder = new StringBuilder();
        char[] array = uuid.toLowerCase().toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (i == 8 | i == 12 | i == 16 | i == 20) {
                builder.append("-");
            }
            builder.append(array[i]);
        }

        return UUID.fromString(builder.toString());
    }
}