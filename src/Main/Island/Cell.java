package Main.Island;

import Main.Island.Organism.Organism;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cell {
    private final int X;
    private final int Y;

    private Map<String, Integer> numberOfLivingOrganisms = new HashMap<>();
    private  Map<String, Integer> maxCountAnimalInCell = new HashMap<>();
    private void initMaxCountAnimalInCell (){
        maxCountAnimalInCell.put("Wolf", 30);
        maxCountAnimalInCell.put("Boa", 30);
        maxCountAnimalInCell.put("Fox", 30);
        maxCountAnimalInCell.put("Bear", 5);
        maxCountAnimalInCell.put("Eagle", 20);
        maxCountAnimalInCell.put("Horse", 20);
        maxCountAnimalInCell.put("Deer", 20);
        maxCountAnimalInCell.put("Rabbit", 150);
        maxCountAnimalInCell.put("Mouse", 500);
        maxCountAnimalInCell.put("Goat", 140);
        maxCountAnimalInCell.put("Sheep", 140);
        maxCountAnimalInCell.put("Boar", 50);
        maxCountAnimalInCell.put("Buffalo", 10);
        maxCountAnimalInCell.put("Duck", 200);
        maxCountAnimalInCell.put("Caterpillar", 1000);
        maxCountAnimalInCell.put("Corn", 200);
        maxCountAnimalInCell.put("Grass", 200);
        maxCountAnimalInCell.put("Oats", 200);
        maxCountAnimalInCell.put("Rica", 200);
        maxCountAnimalInCell.put("Wheat", 200);
    }

    private Set<Organism> organisms = new HashSet<>();
    private static final Integer ZER0_COUNT =0;
    private static final Integer minCountAnimal =1;


    @Override
    public String toString() {
        return "Cell{" +
                "x=" + X +
                ", y=" + Y +
                ", numberOfLivingOrganisms=" + numberOfLivingOrganisms.toString() +
                '}';
    }
    public void addToOrganisms (Organism organism){
        this.organisms.add(organism);
        synchronized (numberOfLivingOrganisms) {
            Integer  varCount = numberOfLivingOrganisms.get(organism.getLogo());
            if (varCount ==ZER0_COUNT) {
                 numberOfLivingOrganisms.put(organism.getLogo(),minCountAnimal);
            } else {
                 if (maxCountAnimalInCell.get(organism.getClass().getSimpleName())-varCount>0)
                     numberOfLivingOrganisms.replace(organism.getLogo(),varCount,varCount+minCountAnimal);
            }
        }
    }
    public void removeFromOrganisms (Organism organism) throws Exception {
        synchronized (numberOfLivingOrganisms) {
            Integer varCount = numberOfLivingOrganisms.get(organism.getLogo());
            if (varCount == 0) {
                throw new Exception("Animals escaped from the island");
            } else {
                numberOfLivingOrganisms.replace(organism.getLogo(), varCount, varCount-minCountAnimal);
            }
        }
            this.organisms.remove(organism);
    }
    public Cell(int x, int y) {
        this.X = x;
        this.Y = y;
        initMaxCountAnimalInCell();
    }

}
