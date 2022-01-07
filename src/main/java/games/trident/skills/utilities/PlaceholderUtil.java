package games.trident.skills.utilities;

import com.google.common.collect.Lists;

import java.util.List;

public class PlaceholderUtil {
    public static String replacePlaceholders(String message, Placeholder... placeholders) {
        for (Placeholder placeholder : placeholders) {
            if (message.contains(placeholder.getKey())) {
                message = message.replace(placeholder.getKey(), placeholder.getValue().toString());
            }
        }

        return message;
    }

    public static List<String> replacePlaceholders(List<String> message, Placeholder... placeholders) {
        List<String> returned = Lists.newArrayList();

        for (String s : message) {
            for (Placeholder placeholder : placeholders) {
                if (s.contains(placeholder.getKey())) {
                    s = s.replace(placeholder.getKey(), placeholder.getValue());
                }
            }

            returned.add(s);
        }

        return returned;
    }
}
