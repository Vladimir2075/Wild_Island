package Island;

import Island.Organism.Animals.Animal;
import Island.Organism.Animals.Predators.PredatorAnimal;
import java.util.concurrent.*;
public class LifeOnTheIsland {
    private MapIsland map = MapIsland.getInstance();
    private boolean checkExitConditional() {
        synchronized (map.getListOfAnimal()) {
            if (map.getListOfAnimal().size() ==0) {return true;}
            for (Animal animal : map.getListOfAnimal()) {
                if (animal instanceof PredatorAnimal) {
                    if (animal.isAlive()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    class IslandUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
        }
    }
    class HandlerThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread =new Thread(r);
            thread.setUncaughtExceptionHandler(new IslandUncaughtExceptionHandler());
            return thread;
        }
    }
    public void runLifeOnTheIsland () {
        new СreatingIslandWithLivingCreatures().initialisationIsland();
        Statistics statistics = Statistics.getInstance();
        ScheduledExecutorService executorServiceStatistic = Executors.newScheduledThreadPool(1);
        executorServiceStatistic.scheduleWithFixedDelay(statistics, 0, map.getDurationSimulationCycle(), TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ExecutorService executorServiceAnimalLifeCycle = Executors.newCachedThreadPool(new HandlerThreadFactory());
        while (true) {
            try {
                for (Animal animal:map.getListOfAnimalForRunThread()) {
                    if (!animal.isRunThread()) {
                        executorServiceAnimalLifeCycle.submit(animal);
                    }
                }
                Thread.sleep(map.getDurationSimulationCycle());
                if (checkExitConditional()) {break;}
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executorServiceAnimalLifeCycle.close();
        try {
            Thread.sleep(map.getDurationSimulationCycle());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorServiceStatistic.close();
    }
}
