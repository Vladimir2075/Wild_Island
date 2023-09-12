package Island;

import Island.Organism.Organism;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class OrganismInitialisation implements Runnable{

    private Class   classOfOrganism;
    private MapIsland   mapIsland = MapIsland.getInstance();
    private int countOrganismOnIsland;

    public OrganismInitialisation(Class classOfOrganism, MapIsland mapIsland, int countOrganismOnIsland) {
        this.classOfOrganism = classOfOrganism;
        this.mapIsland = mapIsland;
        this.countOrganismOnIsland = countOrganismOnIsland;
    }

    @Override
    public void run() {
        int maxI = mapIsland.getLengthIsland();
        int maxJ = mapIsland.getWidthIsland();
        Constructor constructor = classOfOrganism.getEnclosingConstructor();
        Random randomIJ = new Random();
        while (countOrganismOnIsland>0){
            int i = randomIJ.nextInt(maxI);
            int j = randomIJ.nextInt(maxJ);
            Cell cell = mapIsland.getListOfCellsIsland()[i][j];
            try {
                Organism organism = (Organism) classOfOrganism.getDeclaredConstructor().newInstance();
                organism.setFieldOrganism(cell);
                cell.addToOrganisms(organism);
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
            countOrganismOnIsland--;
        }

    }
}
