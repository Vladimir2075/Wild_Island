package Main.Island.Organism.Animals.Predators;
public class Boar extends PredatorAnimal {
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
