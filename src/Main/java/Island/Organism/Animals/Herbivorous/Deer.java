package Island.Organism.Animals.Herbivorous;

import Island.Organism.Animals.RealAnimal;

@RealAnimal
public class Deer extends HerbivorAnimal {
    private static final String logo = "\uD83E\uDD8C";
    public static String getLogo() {
        return logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
