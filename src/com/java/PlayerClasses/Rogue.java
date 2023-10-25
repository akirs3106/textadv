package src.com.java.PlayerClasses;

import src.com.java.*;
import src.com.java.Daggers.*;

public class Rogue extends Player {

    String abilityName;
    
    public Rogue() {
        super("Rogue", new Dagger("Rusty Dagger", 8, "rusty dagger", 0, 0), 125, 75, 40);
    }

    public void setAbilityName(String x) {
        this.abilityName = x;
    }


}
