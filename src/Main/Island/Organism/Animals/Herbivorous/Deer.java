package Main.Island.Organism.Animals.Herbivorous;
public class Deer extends HerbivorAnimal {
    String logo = "\uD83E\uDD8C";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
