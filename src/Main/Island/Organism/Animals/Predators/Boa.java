package Main.Island.Organism.Animals.Predators;
public class Boa extends PredatorAnimal {
    String  logo = "\uD83D\uDC0D";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo +super.toString();
    }
}
