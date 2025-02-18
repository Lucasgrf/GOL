package config;

public class GameOfLifeRandomizer {
    int width;
    int height;
    int generations;
    int speed;
    int layout;
    StringBuilder population;

    public GameOfLifeRandomizer(int width,int height,int generations,int speed,int layout,StringBuilder population) {
        this.width = width;
        this.height = height;
        this.generations = generations;
        this.speed = speed;
        this.layout = layout;
        this.population = population;
    }

    //metodos
    public void randomize() {

    }
}
