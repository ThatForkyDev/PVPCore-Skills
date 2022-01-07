package games.trident.skills.utilities;

public interface Level {
    int getLevel();

    int getPreviousLevel();

    int getNextLevel();

    double getExp();

    boolean addExp(double exp);

    double getRequiredExp();

    double getPreviousRequiredExp();
}