package Main.Island.Organism.Animals.Herbivorous;
public class Goat extends HerbivorAnimal {
    String logo = "\uD83D\uDC10";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
