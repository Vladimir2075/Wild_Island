package Main.Island.Organism.Animals.Herbivorous;

public class Caterpillar extends HerbivorAnimal {
    String logo = "\uD83D\uDC1B";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
