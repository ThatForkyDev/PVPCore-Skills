package games.trident.skills.utilities;

import org.bukkit.ChatColor;

public class TextUtil {
    public static String getBar(String done, String todo, double start, double finish, String symbol, int size) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < size; i++) {
            if(((float)i / (float)size) < (start / finish)) {
                builder.append(done + symbol + ChatColor.RESET);
                continue;
            }
            builder.append(todo + symbol + ChatColor.RESET);
        }
        builder.append(ChatColor.RESET);
        return builder.toString();
    }
}
