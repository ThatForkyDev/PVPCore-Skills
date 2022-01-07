package games.trident.skills.type.mining;

import games.trident.skills.utilities.Level;
import lombok.NoArgsConstructor;

public class MiningLevel extends Level {
    public MiningLevel(int level, double experience) {
        super(level, experience, 5, 1500);
    }
}
