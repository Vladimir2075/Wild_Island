package Main.Island.Organism.Animals;

import Main.Island.Organism.Organism;
import Main.Island.СharacteristicsOfAnimals;

import java.util.HashMap;

public abstract class Animal extends Organism {
    private float amountFoodPerIteration;
    private int maxSpeed;
    private static final int ZERO_PROBABILITIES = 0;
    private float lifeLevel = 100f;
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
    private final СharacteristicsOfAnimals characteristicsOfAnimals = СharacteristicsOfAnimals.getInstant();
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

    }
    public void move() {

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
