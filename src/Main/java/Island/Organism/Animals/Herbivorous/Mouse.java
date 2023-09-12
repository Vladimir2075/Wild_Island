package Island.Organism.Animals.Herbivorous;

import Island.Organism.Animals.RealAnimal;

@RealAnimal
public class Mouse extends HerbivorAnimal {
    private static final String logo = "\uD83D\uDC01";
    public static String getLogo() {
        return logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
