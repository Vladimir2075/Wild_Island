package Main.Island;

import Main.Island.Organism.Organism;

import java.util.HashMap;
import java.util.HashSet;

public class MapIsland {
    private int lengthIsland = 100;
    private int widthIsland = 20;
    private int durationSimulationCycle = 20000;
    private int proportionNumberAnimalsFromMax = 30;

    private HashSet <Cell>   map;

    public MapIsland() {
        map  = new HashSet<>();
        for (int i = 0; i < lengthIsland; i++) {
            for (int j=0; j<widthIsland; j++) {
                map.add(new Cell(i,j));

            }

        }
    }

    public MapIsland(int lengthIsland, int widthIsland, int durationSimulationCycle, int proportionNumberAnimalsFromMax) {
        this.lengthIsland = lengthIsland;
        this.widthIsland = widthIsland;
        this.durationSimulationCycle = durationSimulationCycle;
        this.proportionNumberAnimalsFromMax = proportionNumberAnimalsFromMax;
    }
}
