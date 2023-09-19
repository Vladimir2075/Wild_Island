package Island;

import java.util.ArrayList;
import java.util.List;

public class СreatingIslandWithLivingCreatures {
    private static MapIsland mapIsland = MapIsland.getInstance();
    public СreatingIslandWithLivingCreatures() {
    }
    public void initialisationIsland (){
        List<Thread> threadList = new ArrayList<>();
        for (Class classAnimal:OrganismCreator.getAnimalsType()) {
            int countAnimals = (int)(Cell.getMaxCountOrganismPerCell(classAnimal.getSimpleName()) * mapIsland.getLengthIsland()* mapIsland.getWidthIsland()*
                               (mapIsland.getProportionNumberAnimalsFromMax()));
            OrganismInitialisation organismInitialisation = new OrganismInitialisation(classAnimal, mapIsland, countAnimals);
            Thread thread = new Thread(organismInitialisation);
            threadList.add(thread);
            thread.start();
        }
        for (int i=0; i< threadList.size();i++){
            try {
                threadList.get(i).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (Class classPlants:OrganismCreator.getPlantsTypeType()) {
            int countAnimals = Cell.getMaxCountOrganismPerCell(classPlants.getSimpleName()) * mapIsland.getLengthIsland()* mapIsland.getWidthIsland()/4;
            OrganismInitialisation organismInitialisation = new OrganismInitialisation(classPlants, mapIsland, countAnimals);
            Thread thread = new Thread(organismInitialisation);
            threadList.add(thread);
            thread.start();
        }
        for (int i=0; i< threadList.size();i++){
            try {
                threadList.get(i).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

