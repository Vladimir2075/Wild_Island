package Island;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class СharacteristicsOfAnimals {
    private static СharacteristicsOfAnimals instant;
    private Map<String, Map<String, Integer>> eatAnimalProbabilityMatrix;
    private Map<String, Integer> maxSpedOfTheAnimals = new HashMap<String, Integer>();
    private Map<String, Float> amountFoodPerIterationOfTheAnimals = new HashMap<String, Float>();
    private СharacteristicsOfAnimals() {
        initializationEatAnimalProbabilityMatrix ();
        initializationMaxSpedAnimal ();
        initializationAmountFoodAnimals();
    };
    public static СharacteristicsOfAnimals getInstant() {
        if (instant ==null) {
            instant = new СharacteristicsOfAnimals();
        }
        return instant;
    }
    public Map<String, Map<String, Integer>> getEatAnimalProbabilityMatrix() {
        return eatAnimalProbabilityMatrix;
    }
    public Map<String, Integer> getMaxSpedOfTheAnimals() {
        return maxSpedOfTheAnimals;
    }
    public Map<String, Float> getAmountFoodPerIterationOfTheAnimals() {
        return amountFoodPerIterationOfTheAnimals;
    }
    private void initializationEatAnimalProbabilityMatrix ()
    {
        eatAnimalProbabilityMatrix = new HashMap<>(
                Map.of("Wolf", new HashMap<>(Map.of("Horse", 10, "Deer", 15, "Rabbit", 60, "Mouse", 80,
                                "Goat", 60, "Sheep", 70, "Boar", 15, "Buffalo", 10, "Duck", 40)),
                        "Boa", new HashMap<>(Map.of( "Fox", 100,"Rabbit", 20, "Mouse", 40, "Duck", 40)),
                        "Fox", new HashMap<>(Map.of("Rabbit", 70, "Mouse", 90, "Duck", 60, "Caterpillar", 40)),
                        "Bear", new HashMap<>(Map.of("Boa", 80, "Horse", 40, "Deer", 80, "Rabbit", 80,
                                "Mouse", 90, "Goat", 70, "Sheep", 70, "Boar", 50, "Buffalo", 20, "Duck", 10)),
                        "Eagle", new HashMap<>(Map.of("Fox", 10, "Rabbit", 90, "Mouse", 90, "Duck", 80)),
                        "Horse", new HashMap<>(Map.of("Corn", 100, "Grass", 100, "Oats", 100, "Rice", 100,
                                "Wheat", 100)),
                        "Deer", new HashMap<>(Map.of("Corn", 100, "Grass", 100, "Oats", 100, "Rice", 100,
                                "Wheat", 100)),
                        "Rabbit", new HashMap<>(Map.of("Corn", 100, "Grass", 100, "Oats", 100, "Rice", 100,
                                "Wheat", 100)),
                        "Mouse", new HashMap<>(Map.of("Caterpillar",90,"Corn", 100, "Grass", 100, "Oats", 100, "Rice", 100,
                                "Wheat", 100)),
                        "Goat", new HashMap<>(Map.of("Corn", 100, "Grass", 100, "Oats", 100, "Rice", 100,
                                "Wheat", 100))));
        eatAnimalProbabilityMatrix.put(
               "Sheep",new HashMap<>(Map.of("Corn",100,"Grass",100,"Oats", 100,"Rice",100,
                      "Wheat",100)));
        eatAnimalProbabilityMatrix.put("Boar",new HashMap<>(Map.of("Mouse", 50,"Caterpillar",90,"Corn",100,"Grass",100,"Oats", 100,"Rice",100,
                        "Wheat",100)));
        eatAnimalProbabilityMatrix.put(       "Buffalo",new HashMap<>(Map.of("Corn",100,"Grass",100,"Oats", 100,"Rice",100,
                        "Wheat",100)));
        eatAnimalProbabilityMatrix.put("Duck",new HashMap<>(Map.of("Caterpillar",90,"Corn",100,"Grass",100,"Oats", 100,"Rice",100,
                        "Wheat",100)));
        eatAnimalProbabilityMatrix.put("Caterpillar",new HashMap<>(Map.of("Corn",100,"Grass",100,"Oats", 100,"Rice",100,
                        "Wheat",100)));
    }
    private void initializationMaxSpedAnimal ()
    {
        maxSpedOfTheAnimals.put("Wolf", 3);
        maxSpedOfTheAnimals.put("Boa", 1);
        maxSpedOfTheAnimals.put("Fox", 2);
        maxSpedOfTheAnimals.put("Bear", 2);
        maxSpedOfTheAnimals.put("Eagle", 3);
        maxSpedOfTheAnimals.put("Horse", 4);
        maxSpedOfTheAnimals.put("Deer", 4);
        maxSpedOfTheAnimals.put("Rabbit", 2);
        maxSpedOfTheAnimals.put("Mouse", 1);
        maxSpedOfTheAnimals.put("Goat", 3);
        maxSpedOfTheAnimals.put("Sheep", 3);
        maxSpedOfTheAnimals.put("Boar", 2);
        maxSpedOfTheAnimals.put("Buffalo", 3);
        maxSpedOfTheAnimals.put("Duck", 4);
        maxSpedOfTheAnimals.put("Caterpillar",0);
    }
    private void initializationAmountFoodAnimals(){
        amountFoodPerIterationOfTheAnimals.put("Wolf", 8f);
        amountFoodPerIterationOfTheAnimals.put("Boa", 3f);
        amountFoodPerIterationOfTheAnimals.put("Fox", 2f);
        amountFoodPerIterationOfTheAnimals.put("Bear", 80f);
        amountFoodPerIterationOfTheAnimals.put("Eagle", 1f);
        amountFoodPerIterationOfTheAnimals.put("Horse", 60f);
        amountFoodPerIterationOfTheAnimals.put("Deer", 50f);
        amountFoodPerIterationOfTheAnimals.put("Rabbit", 0.45f);
        amountFoodPerIterationOfTheAnimals.put("Mouse", 0.01f);
        amountFoodPerIterationOfTheAnimals.put("Goat", 10f);
        amountFoodPerIterationOfTheAnimals.put("Sheep", 15f);
        amountFoodPerIterationOfTheAnimals.put("Boar", 50f);
        amountFoodPerIterationOfTheAnimals.put("Buffalo", 100f);
        amountFoodPerIterationOfTheAnimals.put("Duck", 0.15f);
        amountFoodPerIterationOfTheAnimals.put("Caterpillar", 0f);
    }
}