package util;

import config.GameOfLifeConfig;
import dom.Grid;
import render.SwingRenderer;
import javax.swing.*;

/**
 * A classe {@code GameOfLifeRunner} é responsável por rodar a simulação do jogo Game of Life com base
 * nas configurações fornecidas pela classe {@code GameOfLifeConfig}. Ela inicializa a grid do jogo,
 * configura a interface gráfica, renderiza as gerações e processa as atualizações a cada ciclo de geração.
 */
public class GameOfLifeRunner {
    private GameOfLifeConfig config;

    /**
     * Construtor que recebe a configuração do jogo para inicializar o {@code GameOfLifeRunner}.
     *
     * @param config a configuração do jogo {@code GameOfLifeConfig} que contém parâmetros como largura,
     *               altura, gerações, velocidade, layout e população.
     */
    public GameOfLifeRunner(GameOfLifeConfig config) {
        this.config = config;
    }

    /**
     * Inicia a execução do jogo Game of Life. Esta função configura a grid do jogo, cria a interface gráfica,
     * processa as gerações e atualiza a renderização a cada nova geração.
     */
    public void run() {
        if (config.getWidth() > 0 && config.getHeight() > 0 && config.getLayout() > 0) {
            Grid grid = new Grid(config.getHeight(), config.getWidth());
            grid.initializeGrid(config.getPopulation());

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
            waitForNextGeneration(config.getSpeed());

            // Processa as gerações
            for (int gen = 0; gen < config.getGenerations(); gen++) {
                System.out.println("Generation " + (gen) + ":");
                grid.updateGrid(config.getLayout());
                renderer.update(); // Atualiza a visualização a cada geração
                waitForNextGeneration(config.getSpeed());
            }
        } else {
            System.out.println("Error: initializing the grid. Check/Passing the corrects parameters.");
        }
    }

    /**
     * Aguarda a quantidade de tempo especificada pela velocidade entre as gerações.
     *
     * @param speed a velocidade (em milissegundos) de espera entre cada geração.
     */
    private void waitForNextGeneration(int speed) {
        if (speed > 0) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                System.err.println("The execution was stopped: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
