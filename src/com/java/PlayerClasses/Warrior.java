package src.com.java.PlayerClasses;

import src.com.java.*;
import src.com.java.Swords.*;

public class Warrior extends Player {

    String abilityName;

    public Warrior() {
        super("Warrior", new Sword("Rusty Sword", 10, "rusty sword", 20, 0), 100, 100, 30);
    }

    public void setAbilityName(String x) {
        this.abilityName = x;
    }

}
