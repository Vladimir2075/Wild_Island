package Island;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class Statistics implements Runnable{
    private static Statistics instance;
    private volatile HashMap<Class,Integer> statisticsOfAnimalPredators = new HashMap<>();
    private volatile HashMap<Class,Integer> statisticsOfAnimalHerbivorous = new HashMap<>();
    private volatile HashMap<Class,Integer> statisticsOfPlants = new HashMap<>();
    private volatile HashMap<Class,Integer> statisticsOfMovesAnimal = new HashMap<>();
    private volatile HashMap<Class,Integer> statisticsOfDeathOrganism = new HashMap<>();
    private volatile HashMap<Class,Integer> statisticsOfBirthAnimal = new HashMap<>();
    public HashMap<Class, Integer> getStatisticsOfAnimalPredators() {
        return statisticsOfAnimalPredators;
    }
    public HashMap<Class, Integer> getStatisticsOfAnimalHerbivorous() {
        return statisticsOfAnimalHerbivorous;
    }
    public HashMap<Class, Integer> getStatisticsOfPlants() {
        return statisticsOfPlants;
    }
    public HashMap<Class, Integer> getStatisticsOfMoves() {
        return statisticsOfMovesAnimal;
    }
    public HashMap<Class, Integer> getStatisticsOfDeath() {
        return statisticsOfDeathOrganism;
    }
    public HashMap<Class, Integer> getStatisticsOfBirth() {
        return statisticsOfBirthAnimal;
    }
    private Statistics() {
    }
    public static Statistics getInstance() {
        if (instance ==null) {
            instance = new Statistics();
        }
        return instance;
    }
    public synchronized void getStatistic (){
        try {
            System.out.println((getStatisticsOfAnimalPredators().size() > 0) ? "As of now, statistics on animal predators" :
                    "ALL PREDATORS ANIMAL ARE DEATH");
            for (Map.Entry<Class, Integer> classStatistic : getStatisticsOfAnimalPredators().entrySet()
            ) {
                try {
                    Method logo = classStatistic.getKey().getMethod("getLogo");
                    System.out.println(classStatistic.getKey().getSimpleName() + logo.invoke(null) + " count:" +
                            classStatistic.getValue());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(getStatisticsOfAnimalHerbivorous().size() > 0 ? "As of now, statistics on animal herbivorous" : " ");
            for (Map.Entry<Class, Integer> classStatistic : this.getStatisticsOfAnimalHerbivorous().entrySet()
            ) {
                try {
                    Method logo = classStatistic.getKey().getMethod("getLogo");
                    System.out.println(classStatistic.getKey().getSimpleName() + logo.invoke(null) + " count:" +
                            classStatistic.getValue());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(getStatisticsOfPlants().size() > 0 ? "As of now, the balances of the plants" : " ");
            for (Map.Entry<Class, Integer> classStatistic : this.getStatisticsOfPlants().entrySet()
            ) {
                try {
                    Method logo = classStatistic.getKey().getMethod("getLogo");
                    System.out.println(classStatistic.getKey().getSimpleName() + logo.invoke(null) + " count:" +
                            classStatistic.getValue());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(getStatisticsOfMoves().size() > 0 ? "As of now, there are statics of moves Animals " : " ");
            for (Map.Entry<Class, Integer> classStatistic : this.getStatisticsOfMoves().entrySet()
            ) {
                try {
                    Method logo = classStatistic.getKey().getMethod("getLogo");
                    System.out.println(classStatistic.getKey().getSimpleName() + logo.invoke(null) + " count:" +
                            classStatistic.getValue());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(getStatisticsOfBirth().size() > 0 ? "As of now, there are statics of birth Animals " : " ");
            for (Map.Entry<Class, Integer> classStatistic : this.getStatisticsOfBirth().entrySet()
            ) {
                try {
                    Method logo = classStatistic.getKey().getMethod("getLogo");
                    System.out.println(classStatistic.getKey().getSimpleName() + logo.invoke(null) + " count:" +
                            classStatistic.getValue());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(getStatisticsOfDeath().size() > 0 ? "As of now, there are statics of death Animals " : " ");
            for (Map.Entry<Class, Integer> classStatistic : this.getStatisticsOfDeath().entrySet()
            ) {
                try {
                    Method logo = classStatistic.getKey().getMethod("getLogo");
                    System.out.println(classStatistic.getKey().getSimpleName() + logo.invoke(null) + " count:" +
                            classStatistic.getValue());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        } catch (ConcurrentModificationException e){
            System.out.println("Statistics are updated");
        }
    }
    @Override
    public void run() {
        try {
            getStatistic();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
