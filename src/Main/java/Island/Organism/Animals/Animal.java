package Island.Organism.Animals;

import Island.Cell;
import Island.MapIsland;
import Island.Organism.Organism;
import Island.Statistics;
import Island.СharacteristicsOfAnimals;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism implements Runnable {
    private static final int ZERO_PROBABILITIES = 0;
    protected static final int MAX_PROBABILITY = 100;
    private static final int COUNT_DIRECTION = 4;
    private static final int UP_DIRECTION = 0;
    private static final int DOWN_DIRECTION = 1;
    private static final int LEFT_DIRECTION = 2;
    private static final int MIN_COUNT_ORGANISM = 1;
    protected static final int COUNT_ACTIONS_ANIMAL = 3;
    private static final int PROBABILITY_DO_NOT_FIND_FIFE = 80;
    private Statistics statistics = Statistics.getInstance();
    private boolean isRunThread = false;
    private Thread currentTread = null;
    private MapIsland mapIsland = MapIsland.getInstance();
    private float amountFoodPerIteration;
    private int maxSpeed;
    private volatile float lifeLevel = 100f;
    private float lossEnergyLife = 20;
    private volatile boolean isAlive = true;
    private ThreadLocalRandom random = ThreadLocalRandom.current();
    public Animal() {
        super();
        setMaxSpeed();
        setAmountFoodPerIteration();
    }
    public void setLifeLevel(float lifeLevel) {
        this.lifeLevel = lifeLevel;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    public float getLifeLevel() {
        return lifeLevel;
    }
    public boolean isAlive() {
        return isAlive;
    }
    private СharacteristicsOfAnimals characteristicsOfAnimals = СharacteristicsOfAnimals.getInstant();
    public int getMaxSpeed() {
        return maxSpeed;
    }
    private void setMaxSpeed() {
        this.maxSpeed = characteristicsOfAnimals.getMaxSpedOfTheAnimals().get(this.getClass().getSimpleName());
    }
    public boolean isRunThread() {
        return isRunThread;
    }
    public void setRunThread(boolean runThread) {
        isRunThread = runThread;
        if (runThread) {
            currentTread = Thread.currentThread();
        } else {
            currentTread = null;
        }
    }
    public float getAmountFoodPerIteration() {
        return amountFoodPerIteration;
    }
    private void setAmountFoodPerIteration() {
        this.amountFoodPerIteration = characteristicsOfAnimals.getAmountFoodPerIterationOfTheAnimals().get(this.getClass().getSimpleName());
    }
    public Thread getCurrentTread() {
        return currentTread;
    }
    public int getProbabilityKillingAnimal(String classFood) {
        String classKiller = this.getClass().getSimpleName();
        Integer res;
        HashMap<String, Integer> killerFood = (HashMap<String, Integer>) characteristicsOfAnimals.getEatAnimalProbabilityMatrix().get(classKiller);
        if (killerFood != null) {
            res = killerFood.get(classFood);
        } else {
            res = ZERO_PROBABILITIES;
        }
        if (res == null) {
            res = ZERO_PROBABILITIES;
        }
        return res;
    }
    public void multiply() {
        if (ThreadLocalRandom.current().nextInt(100) < PROBABILITY_DO_NOT_FIND_FIFE) {
            return;
        }
        if (!isAlive()) {
            return;
        }
        Cell currentField = this.getFieldOrganism();
        int countRelatedAnimal = (currentField.getNumberOfLivingOrganisms().get(this.getClass().getSimpleName()) == null) ? 0 :
                currentField.getNumberOfLivingOrganisms().get(this.getClass().getSimpleName());
        if ((countRelatedAnimal > 1) && Cell.getMaxCountOrganismPerCell(this.getClass().getSimpleName()) > countRelatedAnimal) {
            try {
                Organism organism = this.getClass().getDeclaredConstructor().newInstance();
                organism.setFieldOrganism(this.getFieldOrganism());
                this.getFieldOrganism().addToOrganisms(organism);
                mapIsland.birthOfOrganism(organism);
                if (statistics.getStatisticsOfBirth().get(organism.getClass()) == null) {
                    statistics.getStatisticsOfBirth().put(organism.getClass(), MIN_COUNT_ORGANISM);
                } else {
                    statistics.getStatisticsOfBirth().put(organism.getClass(), statistics.getStatisticsOfBirth().get(organism.getClass()) + MIN_COUNT_ORGANISM);
                }

            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void move() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Cell currentField = this.getFieldOrganism();
        synchronized (this) {
            if (!isAlive()) {
                return;
            }
            int x = currentField.getX();
            int y = currentField.getY();
            int speed;
            if (this.getMaxSpeed() == 0) {
                speed = 0;
            } else {
                speed = random.nextInt(this.getMaxSpeed());
            }
            if (speed > 0) {
                int direction = random.nextInt(COUNT_DIRECTION);
                switch (direction) {
                    case UP_DIRECTION -> y = (y - speed) > 0 ? (y - speed) : 0;
                    case DOWN_DIRECTION ->
                            y = (y + speed) > mapIsland.getLengthIsland() - 1 ? mapIsland.getLengthIsland() - 1 : y + speed;
                    case LEFT_DIRECTION -> x = (x - speed) > 0 ? (x - speed) : 0;
                    default ->
                            x = (x + speed) > mapIsland.getWidthIsland() - 1 ? mapIsland.getWidthIsland() - 1 : x + speed;
                }
            }
            if (x != currentField.getX() || y != currentField.getY()) {
                Cell newField = mapIsland.getListOfCellsIsland()[x][y];
                if (newField.addToOrganisms(this)) {
                    try {
                        currentField.removeFromOrganisms(this);
                        this.setFieldOrganism(newField);
                        if (statistics.getStatisticsOfMoves().get(this.getClass()) == null) {
                            statistics.getStatisticsOfMoves().put(this.getClass(), MIN_COUNT_ORGANISM);
                        } else {
                            statistics.getStatisticsOfMoves().put(this.getClass(), statistics.getStatisticsOfMoves().get(this.getClass()) + MIN_COUNT_ORGANISM);
                        }
                    } catch (Exception e) {
                        System.out.println("Animal das not exists in this cell");
                        ;
                    }
                }
            }
        }
    }
    public abstract void eat();
    @Override
    public void run() {
        this.setRunThread(true);
        while (this.isAlive()) {
            int index = random.nextInt(COUNT_ACTIONS_ANIMAL);
            try {
                for (int i = 0; i < COUNT_ACTIONS_ANIMAL; i++) {
                    switch (i + index) {
                        case 0: if (this.isAlive()) {this.eat();break;}
                        case 1: if (this.isAlive()) {this.move();break;}
                        case 2: if (this.isAlive()) {this.multiply();break;}
                        case 3: if (this.isAlive()) {this.eat();break;}
                        case 4: if (this.isAlive()) { this.move(); break; }
                    }
                }
                Thread.sleep(MapIsland.TIME_WAIT_BETWEEN_ACTIONS_ANIMAL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    @Override
    public String toString() {
        return "{" +
                "amountFoodPerIteration=" + amountFoodPerIteration +
                ", maxSpeed=" + maxSpeed +
                ", lifeLevel=" + lifeLevel +
                ", isAlive=" + isAlive +
                ", weight=" + String.valueOf(this.getWeight()) +
                '}';
    }
}