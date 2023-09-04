package Main.Island.Organism.Animals.Herbivorous;
public class Mouse extends HerbivorAnimal {
    String logo = "\uD83D\uDC01";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
