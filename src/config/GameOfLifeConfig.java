package config;

import util.Check;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A classe {@code GameOfLifeConfig} é responsável por processar e validar os parâmetros de configuração
 * fornecidos via linha de comando para o jogo Game of Life. Ela gerencia parâmetros como largura, altura,
 * número de gerações, velocidade, tipo de vizinhança e o padrão inicial de população.
 */
public class GameOfLifeConfig {

    private int width = 0;
    private int height = 0;
    private int generations = 0;
    private int speed = 0;
    private int layout = 3;
    private StringBuilder population = new StringBuilder();
    Random rand = new Random();

    /**
     * Construtor que processa os parâmetros fornecidos via linha de comando e valida suas configurações.
     *
     * @param args os parâmetros fornecidos via linha de comando no formato chave=valor, onde cada chave
     *             representa um parâmetro do jogo (ex.: "w", "h", "g", etc.).
     */
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
                        width = check.limit(value, new int[]{10, 20, 30, 40, 80});
                        System.out.println(width > 0 ? "width = " + width : "width = invalid | please type 10,20,30,40 or 80.");
                        missingParams.remove("width");
                        break;
                    case "h":
                        height = check.limit(value, new int[]{10, 20, 40});
                        System.out.println(height > 0 ? "height = " + height : "height = invalid | please type 10,20 or 40.");
                        missingParams.remove("height");
                        break;
                    case "g":
                        generations = check.generations(value);
                        System.out.println(generations >= 0 ? "generations = " + generations : "generations = invalid | please type a number positive.");
                        missingParams.remove("generations");
                        break;
                    case "s":
                        speed = check.speed(value);
                        System.out.println(speed > 0 ? "speed = " + speed : "speed = invalid  | please type a number positive between 250 and 1000.");
                        missingParams.remove("speed");
                        break;
                    case "n":
                        int lay = check.limit(value, new int[]{1, 2, 3, 4, 5});
                        if (check.isPresentValue(lay)) {
                            layout = lay;
                            System.out.println(("neighborhood = " + layout + " [Layout " + layout + "]"));
                        } else {
                            System.out.println("neighborhood = invalid | please type a number between 1 and 5.");
                        }
                        break;
                    case "p":
                        if (check.isValidPattern(value, width)) {
                            population.append(value.replace("\"", ""));
                            System.out.println("population = " + population);
                            missingParams.remove("population");
                        } else if (value.equals("rnd")) {
                            for (int i = 0; i < height; i++) {
                                StringBuilder line = new StringBuilder();
                                for (int j = 0; j < width; j++) {
                                    line.append(rand.nextInt(2));
                                }
                                if (i < height - 1) {
                                    line.append("#");
                                }
                                population.append(line);
                            }
                            System.out.println("Randomized population = " + population);
                            missingParams.remove("population");
                        } else {
                            System.out.println("population = invalid");
                        }
                        break;
                    default:
                        System.out.println("Unknown argument: " + key);
                        break;
                }
            }
        }

        // Verificar se há parâmetros faltando
        if (!missingParams.isEmpty()) {
            System.out.println("Parameters not reported:");
            for (String param : missingParams) {
                System.out.println(" - " + param);
            }
        }
    }

    /**
     * Retorna a largura da grid do jogo.
     *
     * @return a largura da grid.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retorna a altura da grid do jogo.
     *
     * @return a altura da grid.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retorna o número de gerações a serem simuladas.
     *
     * @return o número de gerações.
     */
    public int getGenerations() {
        return generations;
    }

    /**
     * Retorna a velocidade de atualização entre as gerações, em milissegundos.
     *
     * @return a velocidade de atualização.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Retorna o tipo de layout de vizinhança selecionado para o jogo.
     *
     * @return o tipo de vizinhança (1 a 5).
     */
    public int getLayout() {
        return layout;
    }

    /**
     * Retorna o padrão de população inicial como uma string.
     *
     * @return o padrão de população.
     */
    public String getPopulation() {
        return population.toString();
    }
}
