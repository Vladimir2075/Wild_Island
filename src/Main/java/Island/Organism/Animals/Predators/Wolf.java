package Island.Organism.Animals.Predators;

import Island.Organism.Animals.RealAnimal;

@RealAnimal
public class Wolf extends PredatorAnimal {
    private static final String  logo = "\uD83D\uDC3A";
    public static String getLogo() {
        return logo;
    }
    @Override
    public String toString() {
        return logo +super.toString();
    }
}
