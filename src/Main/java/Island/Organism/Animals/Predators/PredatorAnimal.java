package Island.Organism.Animals.Predators;
import Island.MapIsland;
import Island.Organism.Animals.Animal;
import Island.Organism.Organism;
import java.util.concurrent.ThreadLocalRandom;
public abstract class PredatorAnimal extends Animal {
    private final int VALUE_DEATH_LEVEL = 10;
    private final int VALUE_MAXIMUM_LIFE_LEVEL = 100;
    private final int VALUE_MINIMUM_WEIGHT = 0;
    private final int VALUE_INCREASE_LIFE_LEVEL = 35;
    private final int VALUE_DECREASE_LIFE_LEVEL = 20;
    private final int MAX_COUNT_ATTEMPT = 3;
    private ThreadLocalRandom RANDOM_ORGANISM = ThreadLocalRandom.current();
    protected MapIsland mapIsland = MapIsland.getInstance();

    private void sedDecreaseLifeLevel (){
        this.setLifeLevel((this.getLifeLevel()-VALUE_DECREASE_LIFE_LEVEL) >= VALUE_DEATH_LEVEL?
                (this.getLifeLevel()-VALUE_DECREASE_LIFE_LEVEL) :VALUE_DEATH_LEVEL);
        if (this.getLifeLevel() == VALUE_DEATH_LEVEL) {
            try {
                this.setAlive(false);
                this.getFieldOrganism().removeFromOrganisms(this);
                mapIsland.DeathOfOrganism(this);
            } catch (Exception e) {
                System.out.println("Animal was removed");
                throw new RuntimeException();
            }
        }
    }
    @Override
    public void eat() {
        if (!this.isAlive()) {return;}
        int countOrganism ;
        Organism organismFood;
        int attempt = 0;
        while (attempt <  MAX_COUNT_ATTEMPT) {
            countOrganism = this.getFieldOrganism().getOrganisms().size();
            if (countOrganism <=1 ) {
                synchronized (this) {
                        if (this.isAlive()) {
                            sedDecreaseLifeLevel();
                        }
                }
                return;
            }
            int  probabilityKillingAnimal;
                countOrganism = this.getFieldOrganism().getOrganisms().size();
                if (countOrganism<2) {
                    organismFood = null;
                    probabilityKillingAnimal = 0;
                } else {
                    try {
                    int index = RANDOM_ORGANISM.nextInt(countOrganism);
                    int indexFoodAnimal = index > this.getFieldOrganism().getOrganisms().size() - 1 ?
                            this.getFieldOrganism().getOrganisms().size() - 1 : index;
                    organismFood = this.getFieldOrganism().getOrganisms().get(indexFoodAnimal);
                    probabilityKillingAnimal = getProbabilityKillingAnimal(organismFood.getClass().getSimpleName());
                    } catch (NullPointerException e ) {
                        organismFood = null;
                        probabilityKillingAnimal = 0;
                    }
                }

            if ((MAX_PROBABILITY - probabilityKillingAnimal) <= RANDOM_ORGANISM.nextInt(MAX_PROBABILITY) &&  this!=organismFood && organismFood!=null) {
                Organism   organismHasMaxHashCode = this.hashCode() > organismFood.hashCode()? this:organismFood;
                Organism   organismHasMinHashCode = this.hashCode() > organismFood.hashCode()? organismFood:this;
                synchronized (organismHasMaxHashCode) {
                    synchronized (organismHasMinHashCode) {
                        if (this.isAlive() && ((Animal) organismFood).isAlive() &&
                            this.getFieldOrganism()==organismFood.getFieldOrganism()) {
                            Animal organismFoodAnimal = (Animal) organismFood;
                            try {
                                organismFoodAnimal.setAlive(false);
                                this.getFieldOrganism().removeFromOrganisms(organismFood);
                                organismFoodAnimal.setLifeLevel(VALUE_DEATH_LEVEL);
                                mapIsland.DeathOfOrganism(organismFoodAnimal);
                            } catch (Exception e) {
                                System.out.println("Animal was eating");
                            }
                            if (((this.getAmountFoodPerIteration() - organismFoodAnimal.getWeight()) < VALUE_MINIMUM_WEIGHT) ||
                                    (this.getLifeLevel() + VALUE_INCREASE_LIFE_LEVEL) >= VALUE_MAXIMUM_LIFE_LEVEL) {
                                this.setLifeLevel(VALUE_MAXIMUM_LIFE_LEVEL);
                                return;
                            } else {
                                this.setLifeLevel(this.getLifeLevel() + VALUE_INCREASE_LIFE_LEVEL);
                            }
                        }
                    }
                }
            } else {
                synchronized (this) {
                    if (this.isAlive()) {
                        this.setLifeLevel((this.getLifeLevel() - VALUE_DECREASE_LIFE_LEVEL) >= VALUE_DEATH_LEVEL ?
                                (this.getLifeLevel() - VALUE_DECREASE_LIFE_LEVEL) : VALUE_DEATH_LEVEL);
                        if (this.getLifeLevel() == VALUE_DEATH_LEVEL) {
                            this.setAlive(false);
                            try {
                                this.getFieldOrganism().removeFromOrganisms(this);
                                mapIsland.DeathOfOrganism(this);
                                return;
                            } catch (Exception e) {
                                System.out.println("Animal was removed");
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
            attempt ++;
        }
    }
}
