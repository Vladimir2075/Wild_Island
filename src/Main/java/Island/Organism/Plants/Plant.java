package Island.Organism.Plants;

import Island.Organism.Organism;
public abstract class Plant extends Organism {
    private volatile boolean isAlive =true;
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }


}
