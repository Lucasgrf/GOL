package render;

import dom.Grid;

import javax.swing.*;
import java.awt.*;

/**
 * Renderiza a grade do Game of Life utilizando Swing para exibição gráfica.
 * Cada célula é representada como um quadrado de 20x20 pixels, sendo verde para vivas e branca para mortas.
 */
public class SwingRenderer extends JPanel {
    private Grid grid;

    /**
     * Constrói um SwingRenderer para a grade especificada.
     * Define o tamanho preferido do painel com base no número de linhas e colunas da grade.
     * @param grid A grade a ser renderizada.
     */
    public SwingRenderer(Grid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(grid.getColumn() * 20, grid.getLine() * 20));
    }

    /**
     * Sobrescreve o método paintComponent para desenhar a grade na tela.
     * Células vivas são desenhadas em verde e células mortas em branco.
     * Também desenha bordas pretas em torno de cada célula.
     *
     * @param g O contexto gráfico para desenhar os componentes.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < grid.getLine(); i++) {
            for (int j = 0; j < grid.getColumn(); j++) {
                Color cellColor = grid.getCell(i, j).isAlive() ? Color.GREEN : Color.WHITE;
                g.setColor(cellColor);
                g.fillRect(j * 20, i * 20, 20, 20);
                g.setColor(Color.BLACK);
                g.drawRect(j * 20, i * 20, 20, 20);
            }
        }
    }

    /**
     * Atualiza a tela chamando repaint(), forçando a renderização da grade atualizada.
     */
    public void update() {
        repaint();
    }
}
