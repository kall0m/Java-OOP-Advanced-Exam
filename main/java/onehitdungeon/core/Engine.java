package onehitdungeon.core;

import onehitdungeon.interfaces.DungeonManager;
import onehitdungeon.interfaces.InputReader;
import onehitdungeon.interfaces.OutputWriter;
import onehitdungeon.io.ConsoleReader;
import onehitdungeon.io.ConsoleWriter;

import java.util.Arrays;

import static onehitdungeon.constants.Commands.*;

public class Engine {
    private InputReader reader;
    private OutputWriter writer;
    private DungeonManager dungeonManager;

    public Engine() {
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
        this.dungeonManager = new DungeonManagerImpl();
    }

    public void run() {
        String line = reader.readLine();

        String result = "";

        while (!OVER_COMMAND.equals(line)) {
            String[] tokens = line.split(" ");

            switch (tokens[0]) {
                case HERO_COMMAND:
                    result = this.dungeonManager.hero(Arrays.asList(tokens));
                    break;
                case SELECT_COMMAND:
                    result = this.dungeonManager.select(Arrays.asList(tokens));
                    break;
                case STATUS_COMMAND:
                    result = this.dungeonManager.status(Arrays.asList(tokens));
                    break;
                case FIGHT_COMMAND:
                    result = this.dungeonManager.fight(Arrays.asList(tokens));
                    break;
                case ADVANCE_COMMAND:
                    result = this.dungeonManager.advance(Arrays.asList(tokens));
                    break;
                case TRAIN_COMMAND:
                    result = this.dungeonManager.train(Arrays.asList(tokens));
                    break;
            }

            this.writer.println(result);
            line = reader.readLine();
        }

        result = this.dungeonManager.quit(Arrays.asList(line.split(" ")));
        this.writer.println(result);
    }
}
