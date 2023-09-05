package Main.Island.Organism.Animals.Herbivorous;
public class Boar extends HerbivorAnimal {
    String  logo = "\uD83D\uDC17";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo +super.toString();
    }
}
