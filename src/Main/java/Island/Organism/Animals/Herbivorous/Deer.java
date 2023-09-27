package Island.Organism.Animals.Herbivorous;
import Island.Organism.Animals.RealAnimal;
@RealAnimal
public class Deer extends HerbivorAnimal implements Runnable {
    private static final String LOGO = "\uD83E\uDD8C";
    public static String getLogo() {
        return LOGO;
    }
    @Override
    public String toString() {
        return LOGO + super.toString();
    }
}
