package Island.Organism.Animals.Predators;

import Island.Organism.Animals.RealAnimal;

@RealAnimal
public class Boa extends PredatorAnimal {
    private static final String  logo = "\uD83D\uDC17";
    public static String getLogo() {
        return logo;
    }
    @Override
    public String toString() {
        return logo +super.toString();
    }
}
