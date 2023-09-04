package Main.Island.Organism.Animals.Herbivorous;
public class Sheep extends HerbivorAnimal {
    String logo = "\uD83D\uDC11";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
