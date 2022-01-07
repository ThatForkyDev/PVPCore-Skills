package games.trident.skills.type.farming;

import games.trident.skills.utilities.Level;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class FarmingLevel extends Level {
    public FarmingLevel(int level, double experience) {
        super(level, experience, 0, 7000);
    }
}
