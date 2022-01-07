package games.trident.skills.utilities;

public interface Level {
    int getLevel();

    int getPreviousLevel();

    int getNextLevel();

    double getExp();

    boolean addExp(double exp);

    void addLevels(int levels);

    void setLevel(int level);

    double getRequiredExp();

    double getPreviousRequiredExp();
}