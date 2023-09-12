package Island;


public class Main {
    public static void main(String[] args) {
         MapIsland mapIsland = MapIsland.getInstance();
         new СreatingIslandWithLivingCreatures().initialisationIsland();
         for (int i = 0; i < mapIsland.getLengthIsland(); i++)
             for (int j = 0; j < mapIsland.getWidthIsland();j++){
                 System.out.println(mapIsland.getListOfCellsIsland()[i][j].toString());
         }
     /*   Class clazz = null;
        try {
            clazz = Class.forName("Wolf");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Organism organism = (Organism) clazz.getDeclaredConstructor().newInstance();
            System.out.println(organism);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        */
        //   тестирование функций




        mapIsland.getStatistic();
        /*
        Organism wolf = new Wolf();
        Organism wolf1 = new Wolf();
        Organism horse = new Horse();
        Organism boa = new Boar();

        if (wolf instanceof Animal) {
            Animal animal = (Animal) wolf;
            System.out.println("Вероятность что " + wolf.getClass().getSimpleName() + " съест " +
                    horse.getClass().getSimpleName() + " = " +
                    animal.getProbabilityKillingAnimal(horse.getClass().getSimpleName()));
        }
        if (boa instanceof Animal) {
            Animal animal = (Animal) boa;
            System.out.println("Вероятность что " + boa.getClass().getSimpleName() + " съест " +
                    horse.getClass().getSimpleName() + " = " +
                    animal.getProbabilityKillingAnimal(horse.getClass().getSimpleName()));
        }
            Animal  animal = (Animal) horse;
        System.out.println("Вероятность что "+ horse.getClass().getSimpleName() + " съест "+
                "Rice"+" = " +
                animal.getProbabilityKillingAnimal("Rice"));
        System.out.println(wolf.toString());
        System.out.println(wolf1.toString());
        System.out.println(horse.toString());
        System.out.println(boa.toString());

        map.birthOfOrganism(horse);
        map.birthOfOrganism(horse);
        map.birthOfOrganism(wolf);
        map.getStatistic();
        map.DeathOfOrganism(horse);
        map.DeathOfOrganism(horse);
        map.getStatistic();
        for (Class classAnimal:OrganismCreator.getAnimalsType()
             ) {
            System.out.println(classAnimal.getSimpleName());
        }
        for (Class classPlants:OrganismCreator.getPlantsTypeType()
        ) {
            try {
                Method logo =  classPlants.getMethod("getLogo");
                System.out.println(classPlants.getSimpleName() + logo.invoke(null));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

    */
    }
}