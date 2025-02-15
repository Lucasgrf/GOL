package app;

import config.GameOfLifeConfig;
import util.GameOfLifeRunner;

public class GameOfLife {
    public static void main(String[] args) {
        GameOfLifeConfig config = new GameOfLifeConfig(args);
        GameOfLifeRunner runner = new GameOfLifeRunner(config);
        runner.run();
    }
}
