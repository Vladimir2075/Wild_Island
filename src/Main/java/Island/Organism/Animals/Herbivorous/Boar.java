package Island.Organism.Animals.Herbivorous;

import Island.Organism.Animals.RealAnimal;

@RealAnimal
public class Boar extends HerbivorAnimal {
    public static final String  logo = "\uD83D\uDC17";
    public static String getLogo() {
        return logo;
    }
    @Override
    public String toString() {
        return logo +super.toString();
    }
}
