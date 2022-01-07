package games.trident.skills.utilities;

import games.trident.skills.type.mining.MiningLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Level {
    @Getter public int level;
    @Getter public double experience;
    @Getter public transient float abnormality = 5;
    @Getter public transient float decider = 1500;

    public int getPreviousLevel() {
        return level - 1 <= 0 ? 1 : level - 1;
    }

    public int getNextLevel() {
        return level + 1;
    }

    public boolean addExp(double exp) {
        this.experience += exp;

        if (this.experience >= getRequiredExp()) {
            this.level += 1;
            return true;
        }

        return false;
    }

    public void addLevels(int levels) {
        this.level += levels;
    }

    public void setLevel(int level) {
        this.level = level;
        this.experience = getPreviousRequiredExp();
    }

    public double getRequiredExp() {
        return Math.round(decider * ((getLevel() * getPreviousLevel()) + abnormality / decider));
    }

    public double getPreviousRequiredExp() {
        return new MiningLevel(getPreviousLevel(), 0).getRequiredExp();
    }
}