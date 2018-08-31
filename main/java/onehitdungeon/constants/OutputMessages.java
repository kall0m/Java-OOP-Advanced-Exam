package onehitdungeon.constants;

public final class OutputMessages {
    private OutputMessages() {}

    public final static String HERO_COMMAND_OUTPUT = "Successfully added %s - %s.";
    public final static String SELECT_COMMAND_OUTPUT = "Successfully selected %s - %s.";
    public final static String FIGHT_COMMAND_OUTPUT_WON = "Fight won. You've gained %.2f gold.";
    public final static String FIGHT_COMMAND_OUTPUT_LOST = "Fight lost. You've returned to the previous level.";
    public final static String ADVANCE_COMMAND_OUTPUT = "Successfully advanced to dungeon level %d.";
    public final static String TRAIN_COMMAND_OUTPUT_HERO_HAS_ENOUGH_GOLD = "Successfully trained hero. Current total battle power: %d.";
    public final static String TRAIN_COMMAND_OUTPUT_HERO_DOES_NOT_HAVE_ENOUGH_GOLD = "Insufficient gold for training. Needed %.2f. Got %.2f.";
    public final static String OVER_COMMAND_HERO_REPRESENTATION = "%s %s - %d (BP)";
    public final static String OVER_COMMAND_DUNGEON_LEVEL_REACHED_INFORMATION = "####################\n" +
            "Dungeon level reached: %d\n";
}
