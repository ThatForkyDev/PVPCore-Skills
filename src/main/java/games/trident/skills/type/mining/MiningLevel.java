package games.trident.skills.type.mining;

import games.trident.skills.utilities.Level;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MiningLevel implements Level {
    private int level;
    private double experience;

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getPreviousLevel() {
        return level - 1 <= 0 ? 1 : level - 1;
    }

    @Override
    public int getNextLevel() {
        return level + 1;
    }

    @Override
    public double getExp() {
        if (this.experience < getPreviousRequiredExp())
            this.experience = getPreviousRequiredExp();

        return this.experience;
    }

    @Override
    public boolean addExp(double exp) {
        this.experience += exp;

        if (this.experience >= getRequiredExp()) {
            this.level += 1;
            return true;
        }

        return false;
    }

    @Override
    public double getRequiredExp() {
        return Math.round(7000 * ((getLevel() * getPreviousLevel()) + 0f / 7000));
    }

    @Override
    public double getPreviousRequiredExp() {
        return new MiningLevel(getPreviousLevel(), 0).getRequiredExp();
    }
}
