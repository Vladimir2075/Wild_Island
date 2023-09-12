package Island;

import Island.Organism.Animals.Animal;
import Island.Organism.Animals.Herbivorous.HerbivorAnimal;
import Island.Organism.Animals.Predators.PredatorAnimal;
import Island.Organism.Organism;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapIsland {
    private static MapIsland instance;
    private  int lengthIsland = 4;
    private  int widthIsland = 4;
    private int durationSimulationCycle = 5000;
    private float proportionNumberAnimalsFromMax = 0.25f;
    private HashMap<Class,Integer> listOfPlants = new HashMap<>();
    private HashMap<Class,Integer> statisticsOfAnimalPredators = new HashMap<>();
    private HashMap<Class,Integer> statisticsOfAnimalHerbivorous = new HashMap<>();
    private HashMap<Class,Integer> statisticsOfPlants = new HashMap<>();
    private List <Animal>  animalList = new ArrayList<>();
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
    public static MapIsland getInstance(int lengthIsland, int widthIsland, int durationSimulationCycle, int proportionNumberAnimalsFromMax){
        if (instance==null) {
            instance = new MapIsland(lengthIsland, widthIsland, durationSimulationCycle, proportionNumberAnimalsFromMax);
        }
        return instance;
    }
    public HashMap<Class, Integer> getStatisticsOfAnimalPredators() {
        return statisticsOfAnimalPredators;
    }
    public HashMap<Class, Integer> getStatisticsOfAnimalHerbivorous() {
        return statisticsOfAnimalHerbivorous;
    }
    public HashMap<Class, Integer> getStatisticsOfPlants() {
        return statisticsOfPlants;
    }
    public Cell[][] getListOfCellsIsland() {
        return listOfCellsIsland;
    }
    public void birthOfOrganism (Organism organism) {
        HashMap<Class,Integer> map;
                if (organism instanceof PredatorAnimal ) {
                    map = statisticsOfAnimalPredators;
                    addAnimalList((Animal) organism);
                } else if (organism instanceof HerbivorAnimal) {
                    map = statisticsOfAnimalHerbivorous;
                    addAnimalList((Animal) organism);
                } else {
                    map = statisticsOfPlants;
                }
        synchronized (map) {
            if (map.get(organism.getClass())==null) {
                map.put(organism.getClass(),1);
            } else {
                map.put(organism.getClass(),map.get(organism.getClass())+1);
            }
        }
    }
    public void DeathOfOrganism (Organism organism) {
        HashMap<Class,Integer> map;
        if (organism instanceof PredatorAnimal ) {
            map = statisticsOfAnimalPredators;
        } else if (organism instanceof HerbivorAnimal) {
            map = statisticsOfAnimalHerbivorous;
        } else {
            map = statisticsOfPlants;
        }
        synchronized (map) {
            if (map.get(organism.getClass())>1) {
                map.put(organism.getClass(),map.get(organism.getClass())-1);
            } else {
                map.remove(organism.getClass());
            }
        }
    }
    private MapIsland() {
        initialisationCellsOfIsland ();
    }
    public MapIsland(int lengthIsland, int widthIsland, int durationSimulationCycle, int proportionNumberAnimalsFromMax) {
        this.lengthIsland = lengthIsland;
        this.widthIsland = widthIsland;
        this.durationSimulationCycle = durationSimulationCycle;
        this.proportionNumberAnimalsFromMax = proportionNumberAnimalsFromMax;
        initialisationCellsOfIsland();
    }

    private void  initialisationCellsOfIsland (){
        this.listOfCellsIsland  = new Cell[lengthIsland][widthIsland];
        for (int i = 0; i < lengthIsland; i++) {
            for (int j=0; j<widthIsland; j++) {
                listOfCellsIsland[i][j] =new Cell(i,j);
            }
        }
    }

    public void addAnimalList(Organism organism) {
        this.animalList.add((Animal)organism);
    }

    public void getStatistic (){
        System.out.println((getStatisticsOfAnimalPredators().size() > 0)? "As of now, statistics on animal predators":" ");
        for (Map.Entry<Class,Integer> classStatistic: getStatisticsOfAnimalPredators().entrySet()
           ) {
            try {
                Method logo =  classStatistic.getKey().getMethod("getLogo");
                System.out.println(classStatistic.getKey().getSimpleName() + logo.invoke(null) +" count:"+
                                   classStatistic.getValue());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println( getStatisticsOfAnimalHerbivorous().size()>0 ? "As of now, statistics on animal herbivorous":" ");
        for (Map.Entry<Class,Integer> classStatistic: this.statisticsOfAnimalHerbivorous.entrySet()
        ) {
            try {
                Method logo =  classStatistic.getKey().getMethod("getLogo");
                System.out.println(classStatistic.getKey().getSimpleName() + logo.invoke(null) +" count:"+
                        classStatistic.getValue());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(getStatisticsOfPlants().size()>0?"As of now, the balances of the plants":" ");
        for (Map.Entry<Class,Integer> classStatistic: this.statisticsOfPlants.entrySet()
        ) {
            try {
                Method logo =  classStatistic.getKey().getMethod("getLogo");
                System.out.println(classStatistic.getKey().getSimpleName() + logo.invoke(null) +" count:"+
                        classStatistic.getValue());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
