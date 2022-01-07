# Commands
    /skills - Opens the main GUI.
    /skills reset <player> - Resets a players profile completely.
    /skills setlevel <player> <type> <level> - Sets the level of a specific skill.
    /skills addexp <player> <type> <amount> - Adds a specific amount of exp to a skill.
    /skills givereward <player> <reward> - Gives a reward to a player.

# Permissions
    command.skills.use - should be added everyone
    command.skills.reset - admin
    command.skills.setlevel - admin
    command.skills.addexp - admin
    command.skills.givereward - admin

# API
    SkillsPlugin#getAPI - Returns the main class of the plugin.

# Events
    SkillLevelupEvent - Gives all relevent information when a player levels a skill.