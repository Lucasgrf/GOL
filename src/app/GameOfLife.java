package app;

import util.Check;
import dom.Grid;
import render.SwingRenderer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe {@code GameOfLife} é a aplicação principal para executar o jogo da vida (Game of Life).
 * Ela inicializa a grid, recebe parâmetros de configuração via linha de comando e renderiza as gerações
 * do jogo com base nas entradas fornecidas. A interface gráfica usa o Swing para renderizar o jogo.
 */
public class GameOfLife {

    /**
     * O método principal que executa o jogo da vida, inicializando os parâmetros e realizando o processamento das gerações.
     *
     * <p>O método recebe argumentos de linha de comando para configurar parâmetros como largura, altura, número de
     * gerações, velocidade, tipo de vizinhança e padrão da população. A partir desses parâmetros, o jogo é iniciado
     * e renderizado até o número especificado de gerações.</p>
     *
     * @param args os parâmetros de linha de comando no formato chave=valor para configurar o jogo.
     */
    public static void main(String[] args) {
        int w = 0, h = 0, g = 0, s = 0, n = 3;
        int[] limitToWidth = {10, 20, 30, 40, 80};
        int[] limitToHeight = {10, 20, 40};
        int[] typesOfLayout = {1, 2, 3, 4, 5};
        Grid grid;
        StringBuilder p = new StringBuilder();
        Check check = new Check();

        List<String> parametrosFaltantes = new ArrayList<>();
        parametrosFaltantes.add("width");
        parametrosFaltantes.add("height");
        parametrosFaltantes.add("generations");
        parametrosFaltantes.add("speed");
        parametrosFaltantes.add("population");

        for (String arg : args) {
            String[] split = arg.split("=", 2);
            if (split.length == 2) {
                String chave = split[0];
                String valor = split[1];

                // Processamento dos parâmetros fornecidos via linha de comando
                switch (chave) {
                    case "w":
                        w = check.checkLimit(valor, limitToWidth);
                        System.out.println(w > 0 ? "width = " + w : "width = invalido");
                        parametrosFaltantes.remove("width");
                        break;
                    case "h":
                        h = check.checkLimit(valor, limitToHeight);
                        System.out.println(h > 0 ? "height = " + h : "height = invalido");
                        parametrosFaltantes.remove("height");
                        break;
                    case "g":
                        g = check.checkGenerations(valor);
                        System.out.println(g > 0 ? "generations = " + g : "generations = invalido");
                        parametrosFaltantes.remove("generations");
                        break;
                    case "s":
                        s = check.checkSpeed(valor);
                        System.out.println(s > 0 ? "speed = " + s : "speed = invalido");
                        parametrosFaltantes.remove("speed");
                        break;
                    case "n":
                        int num = check.checkLimit(valor, typesOfLayout);
                        if (check.isPresentValue(num)) {
                            n = num;
                        }
                        System.out.println(("vizinhaça = " + n + " [Layout " + n + "]"));
                        break;
                    case "p":
                        if (check.isValidPattern(valor, w)) {
                            p.append(valor.replace("\"", ""));
                            System.out.println("population = " + p);
                            parametrosFaltantes.remove("population");
                        } else {
                            System.out.println("population = invalido");
                        }
                        break;
                    default:
                        System.out.println("Argumento desconhecido: " + chave);
                        break;
                }
            }
        }

        // Verifica se todos os parâmetros obrigatórios foram fornecidos
        if (!parametrosFaltantes.isEmpty()) {
            System.out.println("Parâmetros não informados:");
            for (String param : parametrosFaltantes) {
                System.out.println(" - " + param);
            }
        }

        // Inicializa a grid e a interface gráfica
        if (w > 0 && h > 0 && !p.isEmpty()) {
            grid = new Grid(h, w);
            grid.initializeGrid(p.toString());

            // Configura a interface gráfica com o Swing
            JFrame frame = new JFrame("Game of Life");
            SwingRenderer renderer = new SwingRenderer(grid);
            frame.add(renderer);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            // Atualiza a renderização da primeira geração
            renderer.update();

            // Adiciona um pequeno delay para exibir a geração inicial
            if (s > 0) {
                try {
                    Thread.sleep(s);  // Espera o tempo de delay configurado
                } catch (InterruptedException e) {
                    System.err.println("A execução foi interrompida: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }

            // Processa as gerações
            for (int gen = 0; gen < g; gen++) {
                System.out.println("Generation " + (gen) + ":");
                grid.updateGrid(n);
                renderer.update(); // Atualiza a visualização a cada geração

                // Aguarda a velocidade definida, se válida
                if (s > 0) {
                    try {
                        Thread.sleep(s);
                    } catch (InterruptedException e) {
                        System.err.println("A execução foi interrompida: " + e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                } else {
                    System.out.println("Valor de velocidade inválido. A velocidade deve ser um número positivo.");
                }
            }
        } else {
            System.out.println("Erro ao inicializar a grid. Verifique os parâmetros.");
        }
    }
}
