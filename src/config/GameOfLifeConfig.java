package config;

import util.Check;
import java.util.ArrayList;
import java.util.List;

public class GameOfLifeConfig {
    private int width = 0;
    private int height = 0;
    private int generations = 0;
    private int speed = 0;
    private int layout = 3;
    private StringBuilder population = new StringBuilder();

    public GameOfLifeConfig(String[] args) {
        Check check = new Check();
        List<String> missingParams = new ArrayList<>();
        missingParams.add("width");
        missingParams.add("height");
        missingParams.add("generations");
        missingParams.add("speed");
        missingParams.add("population");

        for (String arg : args) {
            String[] split = arg.split("=", 2);
            if (split.length == 2) {
                String key = split[0];
                String value = split[1];

                // Processar parâmetros via linha de comando
                switch (key) {
                    case "w":
                        width = check.checkLimit(value, new int[]{10, 20, 30, 40, 80});
                        System.out.println(width > 0 ? "width = " + width : "width = invalido");
                        missingParams.remove("width");
                        break;
                    case "h":
                        height = check.checkLimit(value, new int[]{10, 20, 40});
                        System.out.println(height > 0 ? "height = " + height : "height = invalido");
                        missingParams.remove("height");
                        break;
                    case "g":
                        generations = check.checkGenerations(value);
                        System.out.println(generations > 0 ? "generations = " + generations : "generations = invalido");
                        missingParams.remove("generations");
                        break;
                    case "s":
                        speed = check.checkSpeed(value);
                        System.out.println(speed > 0 ? "speed = " + speed : "speed = invalido");
                        missingParams.remove("speed");
                        break;
                    case "n":
                        int lay = check.checkLimit(value, new int[]{1, 2, 3, 4, 5});
                        if (check.isPresentValue(lay)) {
                            layout = lay;
                        }

                        System.out.println(("vizinhaça = " + layout + " [Layout " + layout + "]"));
                        break;
                    case "p":
                        if (check.isValidPattern(value, width)) {
                            population.append(value.replace("\"", ""));
                            System.out.println("population = " + population);
                            missingParams.remove("population");
                        }else{
                            System.out.println("population = invalido");
                        }
                        break;
                    default:
                        System.out.println("Argumento desconhecido: " + key);
                        break;
                }
            }
        }

        // Verificar se há parâmetros faltando
        if (!missingParams.isEmpty()) {
            System.out.println("Parâmetros não informados:");
            for (String param : missingParams) {
                System.out.println(" - " + param);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getGenerations() {
        return generations;
    }

    public int getSpeed() {
        return speed;
    }

    public int getLayout() {
        return layout;
    }

    public String getPopulation() {
        return population.toString();
    }
}
