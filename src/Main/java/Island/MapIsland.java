package Island;

import Island.Organism.Animals.Animal;
import Island.Organism.Animals.Herbivorous.HerbivorAnimal;
import Island.Organism.Animals.Predators.PredatorAnimal;
import Island.Organism.Organism;
import Island.Organism.Plants.Plant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapIsland {
    private static MapIsland instance;
    private Statistics statistics = Statistics.getInstance();
    public static final int TIME_WAIT_BETWEEN_ACTIONS_ANIMAL = 5;
    private int lengthIsland = 4;
    private int widthIsland = 4;
    private int durationSimulationCycle = 5000;
    private float proportionNumberAnimalsFromMax = 0.1f;
    private int minimumCountOrganism = 1;
    private volatile List <Animal>  listOfAnimal = new ArrayList<>();
    private volatile List <Plant>  listOfPlants = new ArrayList<>();
    private Cell[][] listOfCellsIsland;
    public int getLengthIsland() {
        return lengthIsland;
    }
    public int getWidthIsland() {
        return widthIsland;
    }
    public static MapIsland getInstance(){
       if (instance==null) {
           instance = new MapIsland();
       }
       return instance;
    }
    private MapIsland() {
        initialisationCellsOfIsland ();
    }
    public static MapIsland getInstance(int lengthIsland, int widthIsland, int durationSimulationCycle, int proportionNumberAnimalsFromMax){
        if (instance==null) {
            instance = new MapIsland(lengthIsland, widthIsland, durationSimulationCycle, proportionNumberAnimalsFromMax);
        }
        return instance;
    }
    public float getProportionNumberAnimalsFromMax() {
        return proportionNumberAnimalsFromMax;
    }
    public List<Animal> getListOfAnimal() {
        return listOfAnimal;
    }
    public List<Animal> getListOfAnimalForRunThread (){
        List<Animal> result = new ArrayList<>();
        synchronized (listOfAnimal) {
            for (Animal animal : listOfAnimal) {
                if (!animal.isRunThread()) {
                    result.add(animal);
                }
            }
        }
        return result;
    }
    public List<Plant> getListOfPlants(Cell cell)
    {
        List<Plant> result = new ArrayList<>();
        for (Plant plant :listOfPlants
             ) {
            if (plant.getFieldOrganism() == cell && plant.isAlive()) {
                result.add(plant);
            }
        }
        return result;
    }
    public int getDurationSimulationCycle() {
        return durationSimulationCycle;
    }
    public Cell[][] getListOfCellsIsland() {
        return listOfCellsIsland;
    }
    public void birthOfOrganism (Organism organism) {
        HashMap<Class,Integer> map;
                if (organism instanceof PredatorAnimal ) {
                    map = statistics.getStatisticsOfAnimalPredators();
                    addToAnimalList(organism);
                } else if (organism instanceof HerbivorAnimal) {
                    map = statistics.getStatisticsOfAnimalHerbivorous();
                    addToAnimalList(organism);
                } else {
                    map = statistics.getStatisticsOfPlants();
                    addToPlantsList (organism);
                }
        synchronized (map) {
            if (map.get(organism.getClass())==null) {
                map.put(organism.getClass(),minimumCountOrganism);
            } else {
                map.put(organism.getClass(),map.get(organism.getClass())+minimumCountOrganism);
            }
        }
    }
    public void DeathOfOrganism (Organism organism) {
        HashMap<Class,Integer> map;
        if (organism instanceof PredatorAnimal ) {
            map = statistics.getStatisticsOfAnimalPredators();
        } else if (organism instanceof HerbivorAnimal) {
            map = statistics.getStatisticsOfAnimalHerbivorous();
        } else {
            map = statistics.getStatisticsOfPlants();
        }
        synchronized (map) {
            if (map.get(organism.getClass())>minimumCountOrganism) {
                map.put(organism.getClass(),map.get(organism.getClass())-minimumCountOrganism);
            } else {
                if (map.get(organism.getClass())!=null) {
                    map.remove(organism.getClass());
                }
            }
        }
        HashMap<Class,Integer> mapDeathOrganism =statistics.getStatisticsOfDeath();
            if (mapDeathOrganism.get(organism.getClass()) == null) {
                mapDeathOrganism.put(organism.getClass(), minimumCountOrganism);
            } else {
                mapDeathOrganism.put(organism.getClass(), mapDeathOrganism.get(organism.getClass()) + minimumCountOrganism);
            }
    }
    public MapIsland(int lengthIsland, int widthIsland, int durationSimulationCycle, int proportionNumberAnimalsFromMax) {
        this.lengthIsland = lengthIsland;
        this.widthIsland = widthIsland;
        this.durationSimulationCycle = durationSimulationCycle;
        this.proportionNumberAnimalsFromMax = proportionNumberAnimalsFromMax;
        initialisationCellsOfIsland();
    }
    private void initialisationCellsOfIsland (){
        this.listOfCellsIsland  = new Cell[lengthIsland][widthIsland];
        for (int i = 0; i < lengthIsland; i++) {
            for (int j=0; j<widthIsland; j++) {
                listOfCellsIsland[i][j] =new Cell(i,j);
            }
        }
    }
    public void addToAnimalList(Organism organism) {
        synchronized (listOfAnimal) {
            this.listOfAnimal.add((Animal) organism);
        }
    }
    public void addToPlantsList(Organism organism) {
        synchronized (listOfPlants) {
            this.listOfPlants.add((Plant) organism);
        }
    }
}
