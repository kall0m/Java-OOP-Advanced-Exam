package onehitdungeon.core;

import onehitdungeon.domain.DungeonLevel;
import onehitdungeon.factories.HeroFactory;
import onehitdungeon.interfaces.DungeonManager;
import onehitdungeon.interfaces.Hero;
import onehitdungeon.interfaces.HeroTrainer;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static onehitdungeon.constants.OutputMessages.*;

public class DungeonManagerImpl implements DungeonManager {
    private final static Integer DUNGEON_LEVEL_UP_MULTIPLIER = 2;

    private Map<String, Hero> heroes;

    private Hero selectedHero;

    private DungeonLevel currentDungeonLevel;

    private HeroTrainer heroTrainer;

    public DungeonManagerImpl() {
        this.heroes = new LinkedHashMap<>();
        this.selectedHero = null;
        this.currentDungeonLevel = new DungeonLevel();
        this.heroTrainer = new HeroTrainerImpl();
    }

    @Override
    public String hero(List<String> arguments) {
        String type = arguments.get(1);
        String name = arguments.get(2);

        try {
            Hero hero = HeroFactory.create(name, type);

            this.heroes.put(name, hero);

            if(this.heroes.size() == 1) {
                this.selectedHero = hero;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format(HERO_COMMAND_OUTPUT, type, name);
    }

    @Override
    public String select(List<String> arguments) {
        String name = arguments.get(1);

        Hero hero = this.heroes.get(name);

        this.selectedHero = hero;

        return String.format(SELECT_COMMAND_OUTPUT, this.selectedHero.getHeroClass(), name);
    }

    @Override
    public String status(List<String> arguments) {
        return this.selectedHero.toString();
    }

    @Override
    public String fight(List<String> arguments) {
        String result = "";

        if(this.selectedHero.getTotalBattlePower() >= this.currentDungeonLevel.getCurrentDungeonBattlePower()) {
            this.selectedHero.earnGold(this.currentDungeonLevel.getCurrentDungeonGold());
            result = String.format(FIGHT_COMMAND_OUTPUT_WON, this.currentDungeonLevel.getCurrentDungeonGold());
        } else {
            returnToPreviousLevel(this.currentDungeonLevel);
            result = FIGHT_COMMAND_OUTPUT_LOST;
        }

        return result;
    }

    private void returnToPreviousLevel(DungeonLevel currentDungeonLevel) { //current - 80, 60
        currentDungeonLevel.decrementLevel();

        Integer previousBattlePower = currentDungeonLevel.getPreviousDungeonBattlePower(); //40
        Double previousGold = currentDungeonLevel.getPreviousDungeonGold(); //30

        currentDungeonLevel.setCurrentDungeonBattlePower(currentDungeonLevel.getPreviousDungeonBattlePower() / DUNGEON_LEVEL_UP_MULTIPLIER); //40
        currentDungeonLevel.setCurrentDungeonGold(currentDungeonLevel.getPreviousDungeonGold() / DUNGEON_LEVEL_UP_MULTIPLIER); //30

        currentDungeonLevel.setPreviousDungeonBattlePower(previousBattlePower / DUNGEON_LEVEL_UP_MULTIPLIER); //20
        currentDungeonLevel.setPreviousDungeonGold(previousGold / DUNGEON_LEVEL_UP_MULTIPLIER); //15
    }

    @Override
    public String advance(List<String> arguments) {
        this.currentDungeonLevel.incrementLevel();

        Integer currentBattlePower = this.currentDungeonLevel.getCurrentDungeonBattlePower(); //40
        Double currentGold = this.currentDungeonLevel.getCurrentDungeonGold(); //30

        this.currentDungeonLevel.setPreviousDungeonBattlePower(currentBattlePower); //40
        this.currentDungeonLevel.setPreviousDungeonGold(currentGold); //30

        this.currentDungeonLevel.setCurrentDungeonBattlePower(currentBattlePower * DUNGEON_LEVEL_UP_MULTIPLIER); //80
        this.currentDungeonLevel.setCurrentDungeonGold(currentGold * DUNGEON_LEVEL_UP_MULTIPLIER); //60

        return String.format(ADVANCE_COMMAND_OUTPUT, this.currentDungeonLevel.getCurrentLevel());
    }

    @Override
    public String train(List<String> arguments) {
        String result = "";

        if(this.selectedHero.getGold() >= this.selectedHero.getTotalPriceForUpgrade()) {
            this.selectedHero.payGold(this.selectedHero.getTotalPriceForUpgrade());
            //45 15 25 20 10 25 22 60
            this.heroTrainer.trainHero(this.selectedHero);

            incrementHeroTimesTrained(this.selectedHero);

            result = String.format(TRAIN_COMMAND_OUTPUT_HERO_HAS_ENOUGH_GOLD,
                    this.selectedHero.getTotalBattlePower());
        } else {
            result = String.format(TRAIN_COMMAND_OUTPUT_HERO_DOES_NOT_HAVE_ENOUGH_GOLD,
                    this.selectedHero.getTotalPriceForUpgrade(),
                    this.selectedHero.getGold());
        }

        return result;
    }

    private void incrementHeroTimesTrained(Hero hero) {
        try {
            Field heroTimesTrainedField = hero.getClass().getSuperclass().
                    getDeclaredField("timesTrained");

            heroTimesTrainedField.setAccessible(true);

            Integer heroTimesTrained = (Integer) heroTimesTrainedField.get(hero);

            heroTimesTrainedField.set(hero, heroTimesTrained + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String quit(List<String> arguments) {
        StringBuilder sb = new StringBuilder();

        for(Hero hero : heroes.values()) {
            sb.append(String.format(OVER_COMMAND_HERO_REPRESENTATION, hero.getHeroClass(), hero.getName(), hero.getTotalBattlePower()))
                    .append(System.lineSeparator());
        }

        sb.append(String.format(OVER_COMMAND_DUNGEON_LEVEL_REACHED_INFORMATION, this.currentDungeonLevel.getCurrentLevel()));

        return sb.toString();
    }
}
