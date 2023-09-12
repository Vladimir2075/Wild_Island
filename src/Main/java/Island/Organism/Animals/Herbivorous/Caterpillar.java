package Island.Organism.Animals.Herbivorous;

import Island.Organism.Animals.RealAnimal;

@RealAnimal
public class Caterpillar extends HerbivorAnimal {
    private static final String logo = "\uD83D\uDC1B";
    public static String getLogo() {
        return logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
