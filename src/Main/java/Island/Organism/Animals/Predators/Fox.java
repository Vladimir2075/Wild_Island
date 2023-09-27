package Island.Organism.Animals.Predators;
import Island.Organism.Animals.RealAnimal;
@RealAnimal
public class Fox extends PredatorAnimal implements Runnable{
    private static final String LOGO = "\uD83E\uDD8A";
    public static String getLogo() {
        return LOGO;
    }
    @Override
    public String toString() {
        return LOGO +super.toString();
    }
}
