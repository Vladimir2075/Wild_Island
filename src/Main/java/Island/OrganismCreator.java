package Island;

import Island.Organism.Animals.Animal;
import Island.Organism.Animals.RealAnimal;
import Island.Organism.Plants.Plant;
import Island.Organism.Plants.RealPlant;
import org.reflections.Reflections;

import java.util.Set;
import java.util.stream.Collectors;
public class OrganismCreator {
    public static final Reflections REFLECTIONS = new Reflections ("Island.Organism");
    private static Set<Class<? extends Animal>> animalsType = setAnimalType();;
    private static Set<Class<? extends Plant>> plantsType = setPlantType();
    private static Set<Class<? extends Animal>> setAnimalType(){
        Set<Class<? extends Animal>> tClass = REFLECTIONS. getSubTypesOf(Animal.class)
                .stream().
                filter(x-> x.isAnnotationPresent(RealAnimal.class))
                .collect(Collectors.toSet());
        return tClass;
    }
    private static Set<Class<? extends Plant>> setPlantType(){
        Set<Class<? extends Plant>> tClass = REFLECTIONS. getSubTypesOf(Plant.class)
                .stream()
                .filter(x-> x.isAnnotationPresent(RealPlant.class))
                .collect(Collectors.toSet());
        return tClass;
    }
    public static Set<Class<? extends Animal>> getAnimalsType() {
        return animalsType;
    }
    public static Set<Class<? extends Plant>> getPlantsTypeType() {
        return plantsType;
    }
}
