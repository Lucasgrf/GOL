package dom;

public class Cell {
    private boolean isAlive;
    private boolean nextState;

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
        this.nextState = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setNextState(boolean nextState) {
        this.nextState = nextState;
    }

    public void updateState() {
        this.isAlive = this.nextState;
        this.nextState = false;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}

