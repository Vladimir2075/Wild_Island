package Main.Island.Organism.Animals.Herbivorous;
public class Buffalo extends HerbivorAnimal {
    String  logo = "Buffalo ";
    @Override
    public String getLogo() {
        return this.logo;
    }
    @Override
    public String toString() {
        return logo +super.toString();
    }
}