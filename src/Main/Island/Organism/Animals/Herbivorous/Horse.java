package Main.Island.Organism.Animals.Herbivorous;
public class Horse extends HerbivorAnimal {
    String logo = "\uD83D\uDC0E";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
