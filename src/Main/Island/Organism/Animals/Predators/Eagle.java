package Main.Island.Organism.Animals.Predators;
public class Eagle extends PredatorAnimal {
    String  logo = "\uD83E\uDD85";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo +super.toString();
    }
}
