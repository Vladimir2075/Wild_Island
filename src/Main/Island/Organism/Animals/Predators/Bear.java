package Main.Island.Organism.Animals.Predators;
public class Bear extends PredatorAnimal {
    String  logo = "\uD83D\uDC3B";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo +super.toString();
    }
}
