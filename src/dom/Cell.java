package dom;

/**
 * Representa uma célula no Game of Life.
 */
public class Cell {
    private boolean isAlive;
    private boolean nextState;

    /**
     * Construtor da célula.
     * @param isAlive indica se a célula está viva.
     */
    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    /**
     * Verifica se a célula está viva.
     * @return true se estiver viva, false caso contrário.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Define o próximo estado da célula.
     * @param nextState próximo estado (true para viva, false para morta).
     */
    public void setNextState(boolean nextState) {
        this.nextState = nextState;
    }

    /**
     * Atualiza o estado da célula para o próximo estado.
     */
    public void updateState() {
        this.isAlive = this.nextState;
        this.nextState = false;
    }

    /**
     * Define o estado atual da célula.
     * @param isAlive true para viva, false para morta.
     */
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
