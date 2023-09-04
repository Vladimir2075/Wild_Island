package Main.Island.Organism.Animals.Herbivorous;
public class Rabbit extends HerbivorAnimal{
    String logo = "\uD83D\uDC07";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo + super.toString();
    }
}
