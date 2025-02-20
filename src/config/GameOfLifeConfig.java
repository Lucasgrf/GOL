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
 * <p>
 * A classe garante que os parâmetros fornecidos sejam válidos e ajustados conforme necessário, fornecendo
 * valores padrão para os parâmetros ausentes. Ela também valida e processa a configuração de população,
 * aceitando tanto padrões predefinidos quanto a geração aleatória de células.
 * </p>
 */
public class GameOfLifeConfig {

    private int width = 80;
    private int height = 40;
    private int generations = 0;
    private int speed = 1000;
    private int layout = 3;
    private StringBuilder population = new StringBuilder();
    Random rand = new Random();

    /**
     * Constrói um objeto {@code GameOfLifeConfig} que processa os parâmetros fornecidos via linha de comando
     * e valida suas configurações. Os parâmetros são esperados no formato chave=valor (ex.: "w=20", "h=20", etc.).
     * <p>
     * O construtor valida cada parâmetro e ajusta os valores conforme necessário, exibindo mensagens de erro
     * para parâmetros inválidos ou ausentes. Se algum parâmetro obrigatório estiver faltando, o método
     * exibirá seus valores padrão.
     * </p>
     *
     * @param args os parâmetros fornecidos via linha de comando no formato chave=valor.
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
        Grid grid = new Grid(width, height);

        System.out.print("Parameters in the args: ");
        System.out.println((args.length > 0) ? " " : "Empty, please pass the values to w,h,g,s,p and n");
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
                            System.err.println("width = invalid | please type 10,20,30,40 or 80.");
                        }
                        break;
                    case "h":
                        height = check.limit(value, new int[]{10, 20, 40});
                        if (check.isPresentValue(height)) {
                            System.out.println("height = " + height);
                            missingParams.remove("height");
                        } else {
                            System.err.println("height = invalid | please type 10,20 or 40.");
                        }
                        break;
                    case "g":
                        generations = check.generations(value);
                        if (check.isPresentValue(generations)) {
                            System.out.println("generations = " + generations);
                            missingParams.remove("generations");
                        } else {
                            System.err.println("generations = invalid | please type a number positive.");
                        }
                        break;
                    case "s":
                        speed = check.speed(value);
                        if (check.isPresentValue(speed)) {
                            System.out.println("speed = " + speed);
                            missingParams.remove("speed");
                        } else {
                            System.err.println("speed = invalid  | please type a number positive between 250 and 1000.");
                        }
                        break;
                    case "n":
                        layout = check.limit(value, new int[]{1, 2, 3, 4, 5});
                        if (check.isPresentValue(layout)) {
                            System.out.println(("neighborhood = " + layout + " [Layout " + Grid.typeOfNeighborhood(layout) + "]"));
                            missingParams.remove("layout");
                        } else {
                            System.err.println("neighborhood = invalid | please type a number between 1 and 5.");
                        }
                        break;
                    case "p":
                        if (check.isValidPattern(value, width)) {
                            population.append(value.replace("\"", ""));
                            System.out.println("population = " + population);
                            missingParams.remove("population");
                        } else if (value.equals("rnd")) {
                            for (int i = 0; i < Math.min(height, 40); i++) {
                                StringBuilder line = new StringBuilder();
                                for (int j = 0; j < Math.min(width, 80); j++) {
                                    line.append(rand.nextInt(2));
                                }
                                if (i < height - 1) {
                                    line.append("#");
                                }
                                population.append(line);
                            }
                            if (population.isEmpty()) {
                                System.err.println("population = population is empty");
                            } else {
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
            System.err.println("\nParameters not found: ");
            for (String param : missingParams) {
                System.err.println(" - " + param);
            }
            System.out.println();
        } else if (args.length == 0) {
            printDefaultValues(missingParams, width, height, generations, speed, layout);
        }

    }

    /**
     * Exibe os valores padrão dos parâmetros que não foram informados pelo usuário.
     * <p>
     * Este método recebe uma lista de parâmetros ausentes e imprime apenas aqueles que não foram fornecidos,
     * mostrando os valores padrão correspondentes. Se nenhum parâmetro for fornecido, os valores padrão
     * são exibidos para todos os parâmetros.
     * </p>
     *
     * @param missingParams Lista de parâmetros ausentes.
     * @param width         Largura padrão da grade, caso não tenha sido informado.
     * @param height        Altura padrão da grade, caso não tenha sido informado.
     * @param generations   Número padrão de gerações, caso não tenha sido informado.
     * @param speed         Velocidade padrão de execução, caso não tenha sido informado.
     * @param layout        Tipo padrão de vizinhança, caso não tenha sido informado.
     */
    private void printDefaultValues(List<String> missingParams, int width, int height, int generations, int speed, int layout) {
        Check check = new Check();
        System.out.println("Default values: ");
        if (missingParams.contains("width") && width == 20) {
            System.out.println(" - width = " + width);
        }
        if (missingParams.contains("height") && height == 20) {
            System.out.println(" - height = " + height);
        }
        if (missingParams.contains("generations") && generations == 0) {
            System.out.println(" - generations = " + generations);
        }
        if (missingParams.contains("speed") && speed == 1000) {
            System.out.println(" - speed = " + speed);
        }
        if (missingParams.contains("layout") && layout == 3) {
            System.out.println(" - layout = " + layout);
        }
        if (missingParams.contains("population")) {
            System.out.println(" - population = all dead");
        }
        if (!missingParams.isEmpty()) {
            System.err.println("Caution: Please, pass the correct values in CLI for running the game of life in another configs");
        }
    }

    /**
     * Retorna a largura da grid do jogo.
     *
     * @return A largura da grid. O valor padrão é 20 se não for especificado.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retorna a altura da grid do jogo.
     *
     * @return A altura da grid. O valor padrão é 20 se não for especificado.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retorna o número de gerações a serem simuladas.
     *
     * @return O número de gerações. O valor padrão é 0 se não for especificado.
     */
    public int getGenerations() {
        return generations;
    }

    /**
     * Retorna a velocidade de atualização entre as gerações, em milissegundos.
     *
     * @return A velocidade de atualização. O valor padrão é 1000 ms se não for especificado.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Retorna o tipo de layout de vizinhança selecionado para o jogo.
     *
     * @return O tipo de vizinhança (1 a 5). O valor padrão é 3 se não for especificado.
     */
    public int getLayout() {
        return layout;
    }

    /**
     * Retorna o padrão de população inicial como uma string.
     *
     * @return O padrão de população. O valor padrão é "todas as células mortas" se não for especificado.
     */
    public String getPopulation() {
        return population.toString();
    }
}
