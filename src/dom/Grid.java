package dom;

/**
 * Representa uma grade para o jogo Game of Life.
 * A grade é composta por células vivas ou mortas, e permite inicializar, atualizar e exibir o estado das células.
 */
public class Grid {
    private int line, column;
    private Cell[][] grid;

    /**
     * Constrói uma nova instância de Grid com as dimensões especificadas.
     *
     * @param line Número de linhas da grade.
     * @param column Número de colunas da grade.
     */
    public Grid(int line, int column) {
        this.line = line;
        this.column = column;
        this.grid = new Cell[line][column];
    }

    /**
     * Inicializa a grade com um padrão fornecido como uma string.
     * O padrão é uma sequência de linhas representadas por '1' (viva) e '0' (morta), separadas por '#'.
     *
     * @param pattern String representando o padrão inicial da grade.
     */
    public void initializeGrid(String pattern) {
        String[] rows = pattern.split("#");
        grid = new Cell[line][column];

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j] = new Cell(false);
            }
        }

        int startRow = (line - rows.length) / 2;
        int startCol = (column - rows[0].length()) / 2;

        for (int i = 0; i < rows.length && (startRow + i) < line; i++) {
            char[] cells = rows[i].toCharArray();
            for (int j = 0; j < cells.length && (startCol + j) < column; j++) {
                grid[startRow + i][startCol + j] = new Cell(cells[j] == '1');
            }
        }
    }

    /**
     * Retorna o número de vizinhos vivos de uma célula dada, de acordo com o layout de vizinhança.
     *
     * @param x A coordenada da linha da célula.
     * @param y A coordenada da coluna da célula.
     * @param layout O tipo de layout de vizinhança (1, 2, 3, 4, 5).
     * @return O número de vizinhos vivos.
     */
    public int getNeighbors(int x, int y, int layout) {
        int[] dx, dy;
        int neighbors = 0;
        String layoutName = typeOfNeighborhood(layout);

        if (layoutName == null) {
            return 0;
        }

        switch (layout) {
            case 1 -> {
                dx = new int[]{0, 0, -1, 1};
                dy = new int[]{-1, 1, 0, 0};
            }
            case 2 -> {
                dx = new int[]{0, 0, -1, 1, 1, -1};
                dy = new int[]{-1, 1, 0, 0, -1, 1};
            }
            case 3 -> {
                dx = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
                dy = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
            }
            case 4 -> {
                dx = new int[]{-1, -1, 1, 1};
                dy = new int[]{-1, 1, -1, 1};
            }
            case 5 -> {
                dx = new int[]{-1, -1, 1, 1, 0, 0};
                dy = new int[]{-1, 1, -1, 1, -1, 1};
            }
            default -> {
                return 0;
            }
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

        return neighbors;
    }

    /**
     * Retorna o nome do layout de vizinhança de acordo com o código fornecido.
     *
     * @param n O código do layout (1 a 5).
     * @return O nome do layout, ou null se o código não for válido.
     */
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

    /**
     * Atualiza o estado da grade de acordo com o layout de vizinhança especificado.
     * As células vivas ou mortas são atualizadas conforme as regras do jogo.
     *
     * @param layout O tipo de layout de vizinhança (1, 2, 3, 4, 5).
     */
    public void updateGrid(int layout) {
        for (int x = 0; x < line; x++) {
            for (int y = 0; y < column; y++) {
                int neighbors = getNeighbors(x, y, layout);
                boolean isAlive = grid[x][y].isAlive();

                if (isAlive) {
                    grid[x][y].setNextState(neighbors >= 2 && neighbors <= 3);
                } else if (neighbors == 3) {
                    grid[x][y].setNextState(true);
                }
            }
        }

        for (int x = 0; x < line; x++) {
            for (int y = 0; y < column; y++) {
                grid[x][y].updateState();
            }
        }
    }

    /**
     * Exibe a grade no console, representando as células vivas com "1" e as células mortas com "0".
     */
    public void printGrid() {
        for (int x = 0; x < line; x++) {
            for (int y = 0; y < column; y++) {
                System.out.print(grid[x][y].isAlive() ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

    /**
     * Retorna o número de linhas da grade.
     *
     * @return O número de linhas.
     */
    public int getLine() {
        return line;
    }

    /**
     * Retorna o número de colunas da grade.
     *
     * @return O número de colunas.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Retorna a célula na posição especificada.
     *
     * @param x A coordenada da linha.
     * @param y A coordenada da coluna.
     * @return A célula na posição (x, y).
     */
    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
}
