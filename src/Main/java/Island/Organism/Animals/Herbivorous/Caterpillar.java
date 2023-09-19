package Island.Organism.Animals.Herbivorous;

import Island.MapIsland;
import Island.Organism.Animals.RealAnimal;

import java.util.concurrent.ThreadLocalRandom;

@RealAnimal
public class Caterpillar extends HerbivorAnimal implements Runnable {
    private static final String LOGO = "\uD83D\uDC1B";
    private ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public static String getLogo() {
        return LOGO;
    }

    @Override
    public void run() {
        this.setRunThread(true);
        while (this.isAlive()) {
            try {
            int index = RANDOM.nextInt(COUNT_ACTIONS_ANIMAL);
            for (int i = 0; i < COUNT_ACTIONS_ANIMAL; i++) {
                switch (i + index) {
                    case 0:
                        if (this.isAlive()) {
                            this.eat();
                            break;
                        }
                    case 1:
                        if (this.isAlive()) {
                            this.move();
                            break;
                        }
                    case 2:
                        if (this.isAlive()) {
                            this.multiply();
                            break;
                        }
                    case 3:
                        if (this.isAlive()) {
                            this.eat();
                            break;
                        }
                    case 4:
                        if (this.isAlive()) {
                            this.move();
                            break;
                        }
                }
            }
                Thread.sleep(MapIsland.TIME_WAIT_BETWEEN_ACTIONS_ANIMAL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return LOGO + super.toString();
    }
}
