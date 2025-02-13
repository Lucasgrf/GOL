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

    public void getNeighbors(int x, int y) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        int neighbors = 0;
        //n = 1

        //n = 2

        //n = 3
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < line && ny >= 0 && ny < column) {
                if (grid[nx][ny].isAlive()) {
                    neighbors++;
                }
            }
        }
        //n = 4

        //n = 5

        System.out.println("Vizinhos de (" + x + "," + y + ") = " + neighbors);
    }
}
