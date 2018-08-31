package onehitdungeon.domain;

public class DungeonLevel {
    private final static Integer DUNGEON_LEVEL_UP_MULTIPLIER = 2;

    private final static Integer DUNGEON_INITIAL_LEVELS_BATTLE_POWER = 20;

    private final static Double DUNGEON_INITIAL_LEVELS_GOLD_EARNED = 15D;

    private Integer currentDungeonBattlePower;

    private Double currentDungeonGold;

    private Integer previousDungeonBattlePower;

    private Double previousDungeonGold;

    private Integer currentLevel;

    public DungeonLevel() {
        this.currentDungeonBattlePower = DUNGEON_INITIAL_LEVELS_BATTLE_POWER;
        this.currentDungeonGold = DUNGEON_INITIAL_LEVELS_GOLD_EARNED;

        this.previousDungeonBattlePower = this.currentDungeonBattlePower / DUNGEON_LEVEL_UP_MULTIPLIER;
        this.previousDungeonGold = this.currentDungeonGold / DUNGEON_LEVEL_UP_MULTIPLIER;

        this.currentLevel = 1;
    }

    public Integer getCurrentDungeonBattlePower() {
        return currentDungeonBattlePower;
    }

    public void setCurrentDungeonBattlePower(Integer currentDungeonBattlePower) {
        this.currentDungeonBattlePower = currentDungeonBattlePower;
    }

    public Double getCurrentDungeonGold() {
        return currentDungeonGold;
    }

    public void setCurrentDungeonGold(Double currentDungeonGold) {
        this.currentDungeonGold = currentDungeonGold;
    }

    public Integer getPreviousDungeonBattlePower() {
        return previousDungeonBattlePower;
    }

    public void setPreviousDungeonBattlePower(Integer previousDungeonBattlePower) {
        this.previousDungeonBattlePower = previousDungeonBattlePower;
    }

    public Double getPreviousDungeonGold() {
        return previousDungeonGold;
    }

    public void setPreviousDungeonGold(Double previousDungeonGold) {
        this.previousDungeonGold = previousDungeonGold;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void incrementLevel() {
        this.currentLevel++;
    }

    public void decrementLevel() {
        this.currentLevel--;
    }
}
