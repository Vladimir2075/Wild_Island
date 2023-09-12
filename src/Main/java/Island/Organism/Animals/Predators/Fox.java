package Island.Organism.Animals.Predators;

import Island.Organism.Animals.RealAnimal;

@RealAnimal
public class Fox extends PredatorAnimal {
    private static final String logo = "\uD83E\uDD8A";
    public static String getLogo() {
        return logo;
    }
    @Override
    public String toString() {
        return logo +super.toString();
    }
}
