package render;

import dom.Grid;

import javax.swing.*;
import java.awt.*;

/**
 * Renderiza a grade do jogo Game of Life utilizando a biblioteca Swing para
 * exibição gráfica.
 * Cada célula da grade é representada por um quadrado de 20x20 pixels. Células
 * vivas são desenhadas em verde e
 * células mortas são desenhadas em branco. As células são organizadas em uma
 * grade com bordas visíveis.
 *
 * <p>
 * Esta classe é responsável por representar graficamente a evolução do estado
 * do jogo, onde a
 * cada geração as células são desenhadas conforme sua condição (viva ou morta)
 * em uma interface gráfica simples.
 * </p>
 */
public class SwingRenderer extends JPanel {
    private Grid grid;
    private int cellSize;

    /**
     * Constrói um objeto SwingRenderer para a grade fornecida, configurando o
     * painel com o tamanho apropriado
     * com base no número de linhas e colunas da grade e uma resolução máxima de
     * tela.
     *
     * @param grid A grade que será renderizada neste painel.
     */
    public SwingRenderer(Grid grid) {
        this.grid = grid;
        calculateCellSize();
        setPreferredSize(new Dimension(grid.getColumn() * cellSize, grid.getLine() * cellSize));
    }

    private void calculateCellSize() {
        int maxW = 1000;
        int maxH = 800;
        int cols = grid.getColumn();
        int rows = grid.getLine();

        int cellW = maxW / cols;
        int cellH = maxH / rows;

        this.cellSize = Math.min(cellW, cellH);
        // Ensure minimum size for visibility
        if (this.cellSize < 2) {
            this.cellSize = 2;
        }
    }

    /**
     * Sobrescreve o método {@code paintComponent} para desenhar a grade do jogo de
     * forma gráfica.
     * Células vivas são desenhadas em verde, e células mortas são desenhadas em
     * branco. Além disso, bordas
     * pretas são desenhadas em torno de cada célula.
     *
     * <p>
     * Este método é invocado automaticamente pelo Swing sempre que é necessário
     * atualizar a exibição
     * da interface gráfica. Ele itera por todas as células da grade e desenha um
     * quadrado correspondente a cada
     * célula com a cor apropriada.
     * </p>
     *
     * @param g O contexto gráfico utilizado para desenhar os componentes na tela.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < grid.getLine(); i++) {
            for (int j = 0; j < grid.getColumn(); j++) {
                Color cellColor = grid.getCell(i, j).isAlive() ? Color.GREEN : Color.WHITE;
                g.setColor(cellColor);
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                // Only draw border if cell size is large enough to be useful
                if (cellSize > 5) {
                    g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    /**
     * Atualiza a exibição da grade chamando o método {@code repaint}, que força a
     * renderização da grade
     * atualizada na tela.
     *
     * <p>
     * Este método deve ser invocado sempre que houver uma mudança no estado da
     * grade (por exemplo, uma nova
     * geração foi gerada), forçando a atualização visual da interface gráfica.
     * </p>
     */
    public void update() {
        repaint();
    }
}
