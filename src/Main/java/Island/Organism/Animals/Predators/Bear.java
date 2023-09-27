package Island.Organism.Animals.Predators;
import Island.Organism.Animals.RealAnimal;
@RealAnimal
public class Bear extends PredatorAnimal implements Runnable{
    private static final String  LOGO = "\uD83D\uDC3B";
    public static String getLogo() {
        return LOGO;
    }
    @Override
    public String toString() {
        return LOGO +super.toString();
    }
}
