package Island.Organism;

import Island.Cell;

import java.util.HashMap;
import java.util.Map;

public abstract class Organism {
    private static Map<String, Float> weightMatrixOfOrganisms=new HashMap<String, Float>();
    private float weight;
    private Cell fieldOrganism;
    private static String logo;
    static {
        weightMatrixOfOrganisms.put("Wolf", 50f);
        weightMatrixOfOrganisms.put("Boa", 15f);
        weightMatrixOfOrganisms.put("Fox", 8f);
        weightMatrixOfOrganisms.put("Bear", 500f);
        weightMatrixOfOrganisms.put("Eagle", 6f);
        weightMatrixOfOrganisms.put("Horse", 400f);
        weightMatrixOfOrganisms.put("Deer", 300f);
        weightMatrixOfOrganisms.put("Rabbit", 2f);
        weightMatrixOfOrganisms.put("Mouse", 0.05f);
        weightMatrixOfOrganisms.put("Goat", 60f);
        weightMatrixOfOrganisms.put("Sheep", 70f);
        weightMatrixOfOrganisms.put("Boar", 400f);
        weightMatrixOfOrganisms.put("Buffalo", 700f);
        weightMatrixOfOrganisms.put("Duck", 1f);
        weightMatrixOfOrganisms.put("Caterpillar", 0.01f);
        weightMatrixOfOrganisms.put("Corn", 1f);
        weightMatrixOfOrganisms.put("Broccoli", 1f);
        weightMatrixOfOrganisms.put("Oats", 1f);
        weightMatrixOfOrganisms.put("Rice", 1f);
        weightMatrixOfOrganisms.put("Herb", 1f);
    }
    public Cell getFieldOrganism() {
        return fieldOrganism;
    }
    public void setFieldOrganism(Cell fieldOrganism) {
        this.fieldOrganism = fieldOrganism;
    }
    public static String getLogo() {
        return logo;
    }

    public float getWeight() {
        return weight;
    }
    public Organism (){
        setWeight();
    }
    public void setWeight() {
        this.weight = weightMatrixOfOrganisms.get(this.getClass().getSimpleName());
    }


}
