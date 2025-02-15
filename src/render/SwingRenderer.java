package render;

import dom.Grid;

import javax.swing.*;
import java.awt.*;

public class SwingRenderer extends JPanel {
    private Grid grid;

    public SwingRenderer(Grid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(grid.getColumn() * 20, grid.getLine() * 20));
    }

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

    public void update() {
        repaint();
    }
}
