package Island.Organism.Animals.Herbivorous;
import Island.Organism.Animals.RealAnimal;
@RealAnimal
public class Boar extends HerbivorAnimal implements Runnable {
    public static final String LOGO = "\uD83D\uDC17";
    public static String getLogo() {
        return LOGO;
    }
    @Override
    public String toString() {
        return LOGO + super.toString();
    }
}