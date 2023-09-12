package Island.Organism.Animals;

import Island.Cell;
import Island.MapIsland;
import Island.Organism.Animals.Predators.PredatorAnimal;
import Island.Organism.Organism;
import Island.СharacteristicsOfAnimals;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;
public abstract class Animal extends Organism {
    private static final int ZERO_PROBABILITIES = 0;
    private static final int MAX_PROBABILITY=100;
    private static final int COUNT_DIRECTION = 4;
    private static final int UP_DIRECTION = 0;
    private static final int DOWN_DIRECTION = 1;
    private static final int LEFT_DIRECTION = 2;
    private static final int Max_COUNT_ATTEMPT = 5;
    private MapIsland mapIsland = MapIsland.getInstance();
    private float amountFoodPerIteration;
    private int maxSpeed;
    private float lifeLevel = 100f;
    private float lossEnergyLife = 20;
    private boolean isAlive =true;
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
    public Animal() {
        super();
        setMaxSpeed();
        setAmountFoodPerIteration();
    }
    public float getAmountFoodPerIteration() {
        return amountFoodPerIteration;
    }
    private void setAmountFoodPerIteration() {
        this.amountFoodPerIteration = characteristicsOfAnimals.getAmountFoodPerIterationOfTheAnimals().get(this.getClass().getSimpleName());
    }
    public int getProbabilityKillingAnimal(String classFood) {
        String classKiller = this.getClass().getSimpleName();
        Integer  res;
        HashMap <String,Integer> killerFood = (HashMap<String, Integer>) characteristicsOfAnimals.getEatAnimalProbabilityMatrix().get(classKiller);
        if (killerFood!=null) {
            res = killerFood.get(classFood);
        } else {
            res = ZERO_PROBABILITIES;
        }
        if (res==null) {res = ZERO_PROBABILITIES;}
        return res;
    }
    public void multiply (){
        Cell currentField = this.getFieldOrganism();
        int countRelatedAnimal = currentField.getNumberOfLivingOrganisms().get(this.getClass().getSimpleName());
        if ((countRelatedAnimal > 1) && Cell.getMaxCountOrganismPerCell(this.getClass().getSimpleName())>countRelatedAnimal) {
            try {
                Organism organism = (Organism) this.getClass().getDeclaredConstructor().newInstance();
                organism.setFieldOrganism(this.getFieldOrganism());
                this.getFieldOrganism().addToOrganisms(organism);
                mapIsland.birthOfOrganism(organism);
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
        Random  random = new Random();
        Cell currentField= this.getFieldOrganism();
        synchronized (this) {
            int x = currentField.getX();
            int y = currentField.getY();
            int speed = random.nextInt(this.getMaxSpeed());
            if (speed > 0) {
                int direction = random.nextInt(COUNT_DIRECTION);
                if (direction == UP_DIRECTION) {
                    y = (y - speed) > 0 ? (y - speed) : 0;
                } else if (direction == DOWN_DIRECTION) {
                    y = (y + speed) > mapIsland.getLengthIsland() - 1 ? mapIsland.getLengthIsland() : y + speed;
                } else if (direction == LEFT_DIRECTION) {
                    x = (x - speed) > 0 ? (x - speed) : 0;
                } else {
                    x = (x + speed) > mapIsland.getWidthIsland() - 1 ? mapIsland.getWidthIsland() : x + speed;
                }
            }
            if (x != currentField.getX() || y != currentField.getY()) {
                Cell newField = mapIsland.getListOfCellsIsland()[x][y];
                if (newField.addToOrganisms(this)) {
                    try {
                        currentField.removeFromOrganisms(this);
                        this.setFieldOrganism(newField);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    public void eat (){
        //this.
        if (this instanceof PredatorAnimal){
            int countOrganism = this.getFieldOrganism().getOrganisms().size();
            Random randomOrganism = new Random();
            int attempt = 0;
            while (attempt < Max_COUNT_ATTEMPT) {
                int indexFoodAnimal = randomOrganism.nextInt(countOrganism);
                Organism organismFood = this.getFieldOrganism().getOrganisms().get(indexFoodAnimal);
                int  probabilityKillingAnimal = getProbabilityKillingAnimal(organismFood.getClass().getSimpleName());
                if (probabilityKillingAnimal < randomOrganism.nextInt(MAX_PROBABILITY)){
                    synchronized (this) {
                        synchronized (organismFood) {
                            Animal organismFoodAnimal = (Animal) organismFood;
                            organismFoodAnimal.setAlive(false);
                            if ((this.getAmountFoodPerIteration()-organismFoodAnimal.getWeight()) > 0f) {
                                this.setLifeLevel(100f);
                            } else {

                            }

                        }
                    }
                }
                attempt ++;
            }
            if (this.lifeLevel<=0) {
                this.setAlive(false);
                try {
                    this.getFieldOrganism().removeFromOrganisms(this);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                mapIsland.DeathOfOrganism(this);
            }
        }
        if (this instanceof PredatorAnimal) {

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
