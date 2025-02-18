package config;

import util.Check;

import java.util.ArrayList;
import java.util.List;

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
                        System.out.println(width > 0 ? "width = " + width : "width = invalido");
                        missingParams.remove("width");
                        break;
                    case "h":
                        height = check.limit(value, new int[]{10, 20, 40});
                        System.out.println(height > 0 ? "height = " + height : "height = invalido");
                        missingParams.remove("height");
                        break;
                    case "g":
                        generations = check.generations(value);
                        System.out.println(generations > 0 ? "generations = " + generations : "generations = invalido");
                        missingParams.remove("generations");
                        break;
                    case "s":
                        speed = check.speed(value);
                        System.out.println(speed > 0 ? "speed = " + speed : "speed = invalido");
                        missingParams.remove("speed");
                        break;
                    case "n":
                        int lay = check.limit(value, new int[]{1, 2, 3, 4, 5});
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
                        } else {
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
