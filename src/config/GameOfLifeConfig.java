package config;

import dom.Grid;
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

    private int width = 20;
    private int height = 20;
    private int generations = 0;
    private int speed = 1000;
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
        missingParams.add("layout");

        System.out.println("Parameters in the args: ");
        for (String arg : args) {
            String[] split = arg.split("=", 2);
            if (split.length == 2) {
                String key = split[0];
                String value = split[1];

                // Processar parâmetros via linha de comando
                switch (key) {
                    case "w":
                        width = check.limit(value, new int[]{10, 20, 30, 40, 80});
                        if (check.isPresentValue(width)) {
                            System.out.println("width = " + width);
                            missingParams.remove("width");
                        } else {
                            System.out.println("width = invalid | please type 10,20,30,40 or 80.");
                        }
                        break;
                    case "h":
                        height = check.limit(value, new int[]{10, 20, 40});
                        if (check.isPresentValue(height)) {
                            System.out.println("height = " + height);
                            missingParams.remove("height");
                        } else {
                            System.out.println("height = invalid | please type 10,20 or 40.");
                        }
                        break;
                    case "g":
                        generations = check.generations(value);
                        if (check.isPresentValue(generations) || generations == 0) {
                            System.out.println("generations = " + generations);
                            missingParams.remove("generations");
                        } else {
                            System.out.println("generations = invalid | please type a number positive.");
                        }
                        break;
                    case "s":
                        speed = check.speed(value);
                        if (check.isPresentValue(speed)) {
                            System.out.println("speed = " + speed);
                            missingParams.remove("speed");
                        } else {
                            System.out.println("speed = invalid  | please type a number positive between 250 and 1000.");
                        }
                        break;
                    case "n":
                        layout = check.limit(value, new int[]{1, 2, 3, 4, 5});
                        if (check.isPresentValue(layout)) {
                            System.out.println(("neighborhood = " + layout + " [Layout " + Grid.typeOfNeighborhood(layout) + "]"));
                            missingParams.remove("layout");
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
                            if(population.isEmpty()) {
                                System.out.println("population = population is empty");
                            }else{
                                System.out.println("Randomized population = " + population);
                                missingParams.remove("population");
                            }
                        } else {
                            System.err.println("population = invalid | please follow this model(0 - dead, 1 - alive): "
                                    + "\n101...#010...#100..." + "\n*If you want randomized, try passing to p 'rnd'");
                        }
                        break;
                    default:
                        System.out.println("Unknown argument: " + key);
                        break;
                }
            }
        }
        System.out.println();

        // Verificar se há parâmetros faltando
        if (!missingParams.isEmpty() && args.length != 0) {
            printDefaultValues(missingParams, width, height, generations, speed, layout);
            System.out.println("Parameters not found: ");
            for (String param : missingParams) {
                System.out.println(" - " + param);
            }
            System.out.println();
        } else if (args.length == 0) {
            printDefaultValues(missingParams, width, height, generations, speed, layout);
        }

    }

    /**
     * Exibe os valores padrão dos parâmetros que não foram informados pelo usuário.
     *
     * <p>O método recebe uma lista de parâmetros ausentes e imprime apenas aqueles que não foram fornecidos,
     * mostrando os valores padrão correspondentes.</p>
     *
     * @param missingParams Lista de strings contendo os nomes dos parâmetros que não foram passados.
     * @param width         Valor padrão para a largura da grade, caso não tenha sido informado.
     * @param height        Valor padrão para a altura da grade, caso não tenha sido informado.
     * @param generations   Valor padrão para a quantidade de gerações, caso não tenha sido informado.
     * @param speed         Valor padrão para a velocidade de execução, caso não tenha sido informado.
     * @param layout        Valor padrão para o layout inicial da grade, caso não tenha sido informado.
     */
    private void printDefaultValues(List<String> missingParams, int width, int height, int generations, int speed, int layout) {
        Check check = new Check();
        System.out.println("Default values: ");
        if (missingParams.contains("width")) {
            System.out.println(" - width = " + width);
        }
        if (missingParams.contains("height")) {
            System.out.println(" - height = " + height);
        }
        if (missingParams.contains("generations") && generations != 0) {
            System.out.println(" - generations = " + generations);
        }
        if (missingParams.contains("speed")) {
            System.out.println(" - speed = " + speed);
        }
        if (missingParams.contains("layout") || layout == 3) {
            System.out.println(" - layout = " + layout);
        }
        if (missingParams.contains("population")) {
            System.out.println(" - population = all dead");
        }
        if(!missingParams.isEmpty()) {
            System.out.println("Caution: Please, pass the correct values in CLI for running the game of life in another configs\n");
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
