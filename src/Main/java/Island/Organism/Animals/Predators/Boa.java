package Island.Organism.Animals.Predators;
import Island.Organism.Animals.RealAnimal;
@RealAnimal
public class Boa extends PredatorAnimal implements Runnable{
    private static final String  LOGO = "\uD83D\uDC0D";
    public static String getLogo() {
        return LOGO;
    }
    @Override
    public String toString() {
        return LOGO +super.toString();
    }
}
