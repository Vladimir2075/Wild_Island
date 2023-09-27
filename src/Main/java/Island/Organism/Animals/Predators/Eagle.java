package Island.Organism.Animals.Predators;

import Island.Organism.Animals.RealAnimal;
@RealAnimal
public class Eagle extends PredatorAnimal implements Runnable{
    private static final String  LOGO = "\uD83E\uDD85";
    public static String getLogo() {
        return LOGO;
    }
    @Override
    public String toString() {
        return LOGO + super.toString();
    }
}
