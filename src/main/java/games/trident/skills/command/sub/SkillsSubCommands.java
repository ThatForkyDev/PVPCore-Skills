package games.trident.skills.command.sub;

import games.trident.skills.SkillsPlugin;
import games.trident.skills.type.farming.FarmingLevel;
import games.trident.skills.type.mining.MiningLevel;
import games.trident.skills.utilities.Level;
import games.trident.skills.utilities.command.CommandArgument;
import games.trident.skills.utilities.command.annotation.ForeignCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;

public class SkillsSubCommands {
    private SkillsPlugin plugin;
    private HashMap<String, Level> types = new HashMap<String, Level>() {{
        put("farming", new FarmingLevel(1, 0));
        put("mining", new MiningLevel(1, 0));
    }};

    public SkillsSubCommands(SkillsPlugin plugin) {
        this.plugin = plugin;
    }

    @ForeignCommand(value = "reset", usage = "reset <player>", description = "Reset a player.", permission = "command.skills.reset")
    public void reset(CommandArgument argument) {
        CommandSender sender = argument.getSender();
        String[] args = argument.getArgs();

        if (args.length != 2) {
            Player target = Bukkit.getPlayerExact(args[1]);

            if (Objects.isNull(target) || !target.isOnline()) {
                sender.sendMessage(ChatColor.RED + "That player cannot be found.");
                return;
            }

            plugin.getProfileManager().getProfile(target.getUniqueId()).reset();
            sender.sendMessage(ChatColor.GREEN + "You have reset " + target.getName());
        } else {
            sender.sendMessage(ChatColor.RED + "Invalid Usage: /skills reset <player>");
        }
    }

    @ForeignCommand(value = "setlevel", usage = "setlevel <player> <mining|farming|fishing|slaying> <level>", description = "Set a players level.", permission = "command.skills.setlevel")
    public void setlevel(CommandArgument argument) {
        CommandSender sender = argument.getSender();
        String[] args = argument.getArgs();

        if (args.length != 3) {
            Player target = Bukkit.getPlayerExact(args[1]);

            if (Objects.isNull(target) || !target.isOnline()) {
                sender.sendMessage(ChatColor.RED + "That player cannot be found.");
                return;
            }

            String skill = args[2].toLowerCase();
            if (!types.containsKey(skill)) {
                sender.sendMessage(ChatColor.RED + "That skill cannot be found.");
                return;
            }

            int level = Integer.valueOf(args[3]);

            plugin.getProfileManager().getProfile(target.getUniqueId())
                    .getLevableEntry(skill, types.get(skill))
                    .setLevel(level);

            sender.sendMessage(ChatColor.GREEN + "You have set " + target.getName() + " to level " + level);
        } else {
            sender.sendMessage(ChatColor.RED + "Invalid Usage: /skills reset <player>");
        }
    }

    //skills reset <player>
    //skills setlevel <player> <type> <level>
    //skills addexp <player> <type> <amount>
    //skills givereward <player> <reward>
}
