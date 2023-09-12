package Island.Organism.Animals.Predators;

import Island.Organism.Animals.RealAnimal;

@RealAnimal
public class Bear extends PredatorAnimal {
    private static final String  logo = "\uD83D\uDC3B";
    public static String getLogo() {
        return logo;
    }
    @Override
    public String toString() {
        return logo +super.toString();
    }
}
