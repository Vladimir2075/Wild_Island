package Island.Organism.Animals.Herbivorous;

import Island.Organism.Animals.RealAnimal;
@RealAnimal
public class Horse extends HerbivorAnimal {
    private static final String logo = "\uD83D\uDC0E";
    public static String getLogo() {
        return logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
