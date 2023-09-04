package Main.Island.Organism.Animals.Predators;
public class Fox extends PredatorAnimal {
    String logo = "\uD83E\uDD8A";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo +super.toString();
    }
}
