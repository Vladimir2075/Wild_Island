package Island.Organism.Animals.Herbivorous;

import Island.Cell;
import Island.MapIsland;
import Island.Organism.Animals.Animal;
import Island.Organism.Organism;
import Island.Organism.Plants.Plant;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class HerbivorAnimal extends Animal {
    private final int VALUE_DEATH_LEVEL = 0;
    private final int VALUE_MAXIMUM_LIFE_LEVEL = 100;
    private final int VALUE_MINIMUM_Probability = 49;
    private ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    protected MapIsland mapIsland = MapIsland.getInstance();

    private void deathAnimal() {
        try {
            this.setAlive(false);
            this.getFieldOrganism().removeFromOrganisms(this);
            mapIsland.DeathOfOrganism(this);
        } catch (Exception e) {
            System.out.println("Animal was Death");
        }
    }

    @Override
    public void eat() {
        Cell cell = this.getFieldOrganism();
        float currentFoodEat = VALUE_DEATH_LEVEL;
        synchronized (this) {
            if (isAlive()) {
                int index = 0;
                List<Plant> organismList = mapIsland.getListOfPlants(this.getFieldOrganism());
                while (index < this.getAmountFoodPerIteration() && organismList.size() > 0) {
                    int count = organismList.size();
                    Plant plant = organismList.get(RANDOM.nextInt(count));
                    if (plant != null) {
                            synchronized (plant) {
                                if (plant.isAlive()) {
                                    try {
                                        plant.setAlive(false);
                                        cell.removeFromOrganisms(plant);
                                        mapIsland.DeathOfOrganism(plant);
                                        currentFoodEat++;
                                    } catch (Exception e) {
                                        System.out.println(" was not in this cell");
                                    }
                                }
                            }
                    }
                    if (this.getAmountFoodPerIteration() <= currentFoodEat) {
                        this.setLifeLevel(VALUE_MAXIMUM_LIFE_LEVEL);
                        break;
                    }
                    index++;
                }
                if (currentFoodEat > VALUE_DEATH_LEVEL) {
                    this.setLifeLevel(VALUE_MAXIMUM_LIFE_LEVEL);
                } else if ((this instanceof Mouse) ||
                        (this instanceof Boar) ||
                        (this instanceof Duck)
                ) { try {
                    int indexAnimalFood = RANDOM.nextInt(cell.getOrganisms().size());
                    Organism organismFood = cell.getOrganisms().get(indexAnimalFood);
                    if (getProbabilityKillingAnimal(organismFood.getClass().getSimpleName()) > VALUE_MINIMUM_Probability &&
                            organismFood instanceof Animal) {
                        synchronized (organismFood) {
                            if (((Animal) organismFood).isAlive()) {
                                try {
                                    cell.removeFromOrganisms(organismFood);
                                    ((Animal) organismFood).setAlive(false);
                                    mapIsland.DeathOfOrganism(organismFood);
                                } catch (Exception e) {
                                    System.out.println(organismFood.getClass().getSimpleName() + " was not in this cell");
                                }
                            }
                            this.setLifeLevel(VALUE_MAXIMUM_LIFE_LEVEL);
                        }
                    } else {
                        deathAnimal();
                    }
                } catch (NullPointerException e ) {
                    deathAnimal();
                }
                } else {
                    deathAnimal();
                }
            }
        }
    }
}