package util;

import config.GameOfLifeConfig;
import dom.Grid;
import render.SwingRenderer;

import javax.swing.*;

/**
 * A classe {@code GameOfLifeRunner} é responsável por executar a simulação do
 * jogo Game of Life com base
 * nas configurações fornecidas pela classe {@code GameOfLifeConfig}. Ela
 * inicializa a grid do jogo, configura a
 * interface gráfica, renderiza as gerações e processa as atualizações a cada
 * ciclo de geração, atualizando a
 * interface gráfica para refletir as mudanças no estado do jogo.
 */
public class GameOfLifeRunner {
    private GameOfLifeConfig config;
    private volatile boolean isPaused = false; // Flag to control pause state

    /**
     * Construtor que recebe a configuração do jogo para inicializar o
     * {@code GameOfLifeRunner}.
     *
     * @param config a configuração do jogo {@code GameOfLifeConfig} que contém
     *               parâmetros como largura,
     *               altura, gerações, velocidade, layout e população.
     */
    public GameOfLifeRunner(GameOfLifeConfig config) {
        this.config = config;
    }

    /**
     * Inicia a execução do jogo Game of Life. Este método configura a grid do jogo
     * com base nas configurações fornecidas,
     * cria a interface gráfica, processa as gerações e atualiza a renderização a
     * cada nova geração.
     * <p>
     * O fluxo do método é o seguinte:
     * <ul>
     * <li>Inicializa a grid com as configurações de tamanho e população.</li>
     * <li>Configura a interface gráfica com o Swing e exibe a janela.</li>
     * <li>Renderiza a primeira geração e aguarda o tempo configurado para a próxima
     * geração.</li>
     * <li>Executa um loop para processar as gerações, atualizando a renderização a
     * cada ciclo.</li>
     * </ul>
     * </p>
     */
    public void run() {
        if (config.getWidth() > 0 && config.getHeight() > 0 && config.getLayout() > 0 && config.getGenerations() >= 0) {
            Grid grid = new Grid(config.getHeight(), config.getWidth());
            grid.initializeGrid(config.getPopulation());

            // Configura a interface gráfica com o Swing
            JFrame frame = new JFrame("Game of Life");
            SwingRenderer renderer = new SwingRenderer(grid);
            frame.add(renderer);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Add KeyListener for Pause (Spacebar)
            frame.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent e) {
                    if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
                        isPaused = !isPaused;
                        System.out.println(isPaused ? "Game Paused" : "Game Resumed");
                    }
                }
            });

            frame.setVisible(true);

            // Atualiza a renderização da primeira geração
            renderer.update();

            // Adiciona um pequeno delay para exibir a geração inicial
            waitForNextGeneration(config.getSpeed());

            // Processa as gerações
            int maxGenerations = config.getGenerations();
            int currentGen = 0;

            while (maxGenerations == 0 || currentGen < maxGenerations) {
                if (!isPaused) {
                    System.out.println("Generation " + currentGen + ":");
                    grid.updateGrid(config.getLayout());
                    renderer.update(); // Atualiza a visualização a cada geração
                    currentGen++;
                } else {
                    // Small delay to prevent CPU spinning while paused
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
                waitForNextGeneration(config.getSpeed());
            }
        } else {
            System.out.println("Error: initializing the grid. Check/Passing the corrects parameters.");
        }
    }

    /**
     * Aguarda a quantidade de tempo especificada pela velocidade entre as gerações.
     * Este método usa o valor de velocidade (em milissegundos) para determinar
     * quanto tempo a execução deve
     * aguardar antes de avançar para a próxima geração. Se a velocidade for zero ou
     * negativa, não há espera
     * entre as gerações.
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
