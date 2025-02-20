package render;

import dom.Grid;

import javax.swing.*;
import java.awt.*;

/**
 * Renderiza a grade do jogo Game of Life utilizando a biblioteca Swing para exibição gráfica.
 * Cada célula da grade é representada por um quadrado de 20x20 pixels. Células vivas são desenhadas em verde e
 * células mortas são desenhadas em branco. As células são organizadas em uma grade com bordas visíveis.
 *
 * <p>Esta classe é responsável por representar graficamente a evolução do estado do jogo, onde a
 * cada geração as células são desenhadas conforme sua condição (viva ou morta) em uma interface gráfica simples.</p>
 */
public class SwingRenderer extends JPanel {
    private Grid grid;

    /**
     * Constrói um objeto SwingRenderer para a grade fornecida, configurando o painel com o tamanho apropriado
     * com base no número de linhas e colunas da grade.
     *
     * @param grid A grade que será renderizada neste painel.
     */
    public SwingRenderer(Grid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(grid.getColumn() * 20, grid.getLine() * 20));
    }

    /**
     * Sobrescreve o método {@code paintComponent} para desenhar a grade do jogo de forma gráfica.
     * Células vivas são desenhadas em verde, e células mortas são desenhadas em branco. Além disso, bordas
     * pretas são desenhadas em torno de cada célula.
     *
     * <p>Este método é invocado automaticamente pelo Swing sempre que é necessário atualizar a exibição
     * da interface gráfica. Ele itera por todas as células da grade e desenha um quadrado correspondente a cada
     * célula com a cor apropriada.</p>
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
                g.fillRect(j * 20, i * 20, 20, 20);
                g.setColor(Color.BLACK);
                g.drawRect(j * 20, i * 20, 20, 20);
            }
        }
    }

    /**
     * Atualiza a exibição da grade chamando o método {@code repaint}, que força a renderização da grade
     * atualizada na tela.
     *
     * <p>Este método deve ser invocado sempre que houver uma mudança no estado da grade (por exemplo, uma nova
     * geração foi gerada), forçando a atualização visual da interface gráfica.</p>
     */
    public void update() {
        repaint();
    }
}
