package Main.Island.Organism.Animals.Herbivorous;
public class Duck extends HerbivorAnimal {
    String logo = "\uD83E\uDD86";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
