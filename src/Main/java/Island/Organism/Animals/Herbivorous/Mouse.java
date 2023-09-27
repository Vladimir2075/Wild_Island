package Island.Organism.Animals.Herbivorous;
import Island.Organism.Animals.RealAnimal;
@RealAnimal
public class Mouse extends HerbivorAnimal implements Runnable{
    private static final String LOGO = "\uD83D\uDC01";
    public static String getLogo() {
        return LOGO;
    }
    @Override
    public String toString() {
        return LOGO + super.toString();
    }
}
