package util;

import config.GameOfLifeConfig;
import dom.Grid;
import render.SwingRenderer;
import javax.swing.*;

public class GameOfLifeRunner {
    private GameOfLifeConfig config;

    public GameOfLifeRunner(GameOfLifeConfig config) {
        this.config = config;
    }

    public void run() {
        if (config.getWidth() > 0 && config.getHeight() > 0 && !config.getPopulation().isEmpty()) {
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
            System.out.println("Erro ao inicializar a grid. Verifique os parâmetros.");
        }
    }

    private void waitForNextGeneration(int speed) {
        if (speed > 0) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                System.err.println("A execução foi interrompida: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
