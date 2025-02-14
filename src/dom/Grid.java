package dom;

public class Grid {
    private int line, column;
    private Cell[][] grid;

    public Grid(int line, int column) {
        this.line = line;
        this.column = column;
        this.grid = new Cell[line][column];
    }

    public void initializeGrid(String pattern) {
        String[] rows = pattern.split("#");

        grid = new Cell[line][column];

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j] = new Cell(false);
            }
        }

        for (int i = 0; i < rows.length; i++) {
            char[] cells = rows[i].toCharArray();

            for (int j = 0; j < cells.length; j++) {
                grid[i][j] = new Cell(cells[j] == '1');
            }
        }

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(grid[i][j].isAlive() ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

    public void getNeighbors(int x, int y, int layout) {
        int[] dx, dy;
        int neighbors = 0;
        String layoutName = typeOfNeighborhood(layout);

        if (layoutName == null) {
            System.out.printf("Layout inválido! \nEscolha um número entre 1 e 5.");
            return;
        }

        /*
        {0, 1},    Direita
        {1, 0},    Abaixo
        {0, -1},   Esquerda
        {-1, 0},   Acima
        {-1, -1},  Superior Esquerdo
        {-1, 1},   Superior Direito
        {1, -1},   Inferior Esquerdo
        {1, 1}     Inferior Direito
         */

        switch (layout) {
            case 1:
                dx = new int[]{0, 0, -1, 1};
                dy = new int[]{-1, 1, 0, 0};
                break;

            case 2:
                dx = new int[]{0, 0, -1, 1, 1, -1};
                dy = new int[]{-1, 1, 0, 0, -1, 1};
                break;

            case 3:
                dx = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
                dy = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
                break;

            case 4:
                dx = new int[]{-1, -1, 1, 1};
                dy = new int[]{-1, 1, -1, 1};
                break;

            case 5:
                dx = new int[]{-1, -1, 1, 1, 0, 0};
                dy = new int[]{-1, 1, -1, 1, -1, 1};
                break;

            default:
                return;
        }

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < line && ny >= 0 && ny < column) {
                if (grid[nx][ny].isAlive()) {
                    neighbors++;
                }
            }
        }

        System.out.println("Vizinhos (" + layoutName + ") de (" + x + "," + y + ") = " + neighbors);
    }

    public String typeOfNeighborhood(int n) {
        return switch (n) {
            case 1 -> "Jala University";
            case 2 -> "Programming 1";
            case 3 -> "Moore";
            case 4 -> "Reverse";
            case 5 -> "Custom Jala University";
            default -> null;
        };
    }
}
