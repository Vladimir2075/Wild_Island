package Island;
import Island.Organism.Organism;
import java.util.*;
public class Cell {
    private static final Integer ZER0_COUNT =0;
    private static final Integer MIN_COUNT_ANIMAL = 1;
    private volatile List<Organism> organisms = new LinkedList<>();
    private int X;
    private int Y;
    private Map<String, Integer> numberOfLivingOrganisms = new HashMap<>();
    private static  Map<String, Integer> maxCountOrganismInCell = new HashMap<>();
    private static void initMaxCountOrganismInCell (){
        maxCountOrganismInCell.put("Wolf", 30);
        maxCountOrganismInCell.put("Boa", 30);
        maxCountOrganismInCell.put("Fox", 30);
        maxCountOrganismInCell.put("Bear", 5);
        maxCountOrganismInCell.put("Eagle", 20);
        maxCountOrganismInCell.put("Horse", 20);
        maxCountOrganismInCell.put("Deer", 20);
        maxCountOrganismInCell.put("Rabbit", 150);
        maxCountOrganismInCell.put("Mouse", 500);
        maxCountOrganismInCell.put("Goat", 140);
        maxCountOrganismInCell.put("Sheep", 140);
        maxCountOrganismInCell.put("Boar", 50);
        maxCountOrganismInCell.put("Buffalo", 10);
        maxCountOrganismInCell.put("Duck", 200);
        maxCountOrganismInCell.put("Caterpillar", 1000);
        maxCountOrganismInCell.put("Corn", 200);
        maxCountOrganismInCell.put("Broccoli", 200);
        maxCountOrganismInCell.put("Oats", 200);
        maxCountOrganismInCell.put("Rice", 200);
        maxCountOrganismInCell.put("Herb", 200);
    }
    static {
        initMaxCountOrganismInCell();
    }
    public  Map<String, Integer> getNumberOfLivingOrganisms() {
        return numberOfLivingOrganisms;
    }
    public static Map<String, Integer> getMaxCountOrganismInCell() {
        return maxCountOrganismInCell;
    }
    @Override
    public String toString() {
        return "Cell{" +
                "x=" + X +
                ", y=" + Y +
                ", numberOfLivingOrganisms=" + numberOfLivingOrganisms +
                '}';
    }
    public static int getMaxCountOrganismPerCell (String className) {
        return maxCountOrganismInCell.get(className);
    }
    public boolean addToOrganisms (Organism organism){
        synchronized (numberOfLivingOrganisms) {
            Integer  varCount = numberOfLivingOrganisms.get(organism.getClass().getSimpleName()) == null ? ZER0_COUNT
                                :numberOfLivingOrganisms.get(organism.getClass().getSimpleName());
            if (varCount ==ZER0_COUNT) {
                 numberOfLivingOrganisms.put(organism.getClass().getSimpleName(),MIN_COUNT_ANIMAL);
            } else if (getMaxCountOrganismPerCell(organism.getClass().getSimpleName())-varCount>0) {
                     numberOfLivingOrganisms.replace(organism.getClass().getSimpleName(),varCount,varCount+MIN_COUNT_ANIMAL);
            } else {
                return false;
            }
            this.organisms.add(organism);
            return true;
        }
    }
    public void removeFromOrganisms (Organism organism) throws Exception {
        synchronized (numberOfLivingOrganisms) {
            Integer  varCount = numberOfLivingOrganisms.get(organism.getClass().getSimpleName()) == null ? ZER0_COUNT
                                :numberOfLivingOrganisms.get(organism.getClass().getSimpleName());
            if (varCount == 0) {
                throw new Exception("Animals escaped from the island");
            } else {
                numberOfLivingOrganisms.replace(organism.getClass().getSimpleName(), varCount, varCount-MIN_COUNT_ANIMAL);
            }
        }
        this.organisms.remove(organism);
    }
    public List<Organism> getOrganisms() {
        return organisms;
    }
    public Cell(int x, int y) {
        this.X = x;
        this.Y = y;
    }
    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
}
