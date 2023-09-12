package Island.Organism.Animals.Herbivorous;

import Island.Organism.Animals.RealAnimal;
@RealAnimal
public class Goat extends HerbivorAnimal {
    private static final String logo = "\uD83D\uDC10";
    public static String getLogo() {
        return logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
