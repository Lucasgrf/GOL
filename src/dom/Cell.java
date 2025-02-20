package dom;

/**
 * Representa uma célula no Game of Life. Cada célula pode estar viva ou morta e tem um próximo estado que
 * será atualizado a cada iteração do jogo.
 */
public class Cell {
    private boolean isAlive;
    private boolean nextState;

    /**
     * Constrói uma célula com um estado inicial.
     * @param isAlive Indica se a célula está viva (true) ou morta (false) no início.
     */
    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    /**
     * Verifica se a célula está viva.
     * @return true se a célula estiver viva, false caso contrário.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Define o próximo estado da célula.
     * @param nextState O próximo estado da célula, onde true representa viva e false representa morta.
     */
    public void setNextState(boolean nextState) {
        this.nextState = nextState;
    }

    /**
     * Atualiza o estado da célula para o próximo estado, alterando o estado atual e resetando o próximo estado.
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