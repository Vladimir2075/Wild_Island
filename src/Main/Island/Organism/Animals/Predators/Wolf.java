package Main.Island.Organism.Animals.Predators;
public class Wolf extends PredatorAnimal {
    String  logo = "\uD83D\uDC3A";

    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo +super.toString();
    }
}
