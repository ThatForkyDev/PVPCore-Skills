package games.trident.skills.command;

import games.trident.skills.SkillsPlugin;
import games.trident.skills.command.sub.SkillsSubCommands;
import games.trident.skills.ui.SkillsMenu;
import games.trident.skills.utilities.command.BaseCommand;
import games.trident.skills.utilities.command.annotation.CommandDescription;
import games.trident.skills.utilities.command.annotation.CommandName;
import games.trident.skills.utilities.command.annotation.CommandPermission;
import games.trident.skills.utilities.command.annotation.ForeignCommand;
import games.trident.skills.utilities.menu.MenuAPI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandName("skills")
@CommandPermission("command.skills.use")
@CommandDescription("Skills command")
public class SkillsCommand extends BaseCommand<CommandSender> {
    private SkillsPlugin plugin;

    public SkillsCommand(SkillsPlugin plugin) {
        super(SkillsCommand.class);

        this.plugin = plugin;

        registerForeign(new SkillsSubCommands(plugin));
    }

    @Override
    protected void onExecute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            MenuAPI.openMenu(player, new SkillsMenu());
        }
    }
}
