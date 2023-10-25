package src.com.java.PlayerClasses;

import src.com.java.*;
import src.com.java.Axes.*;

public class Barbarian extends Player {

    String abilityName;
    
    public Barbarian() {
        super("Barbarian", new Axe("Rusty Axe", 20, "rusty axe", 30, 0), 75, 125, 20);
    }

    public void setAbility(String x) {
        this.abilityName = x;
    }



}
