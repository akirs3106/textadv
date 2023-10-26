package src.com.java.PlayerClasses;

import src.com.java.*;
import src.com.java.Axes.*;

public class Barbarian extends Player {

    String abilityName;
    
    public Barbarian(String abilityName) {
        super("Barbarian", new Axe("Rusty Axe", 20, "rusty axe", 30, 0), 75, 125, 20);
        this.abilityName = abilityName;
    }

    public void setAbilityName(String x) {
        this.abilityName = x;
    }


}
