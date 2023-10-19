package src.com.java.Axes;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;

public class Labrys extends Axe {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public Labrys() {
        super("The Labrys", 55, "the labrys", 45);
        this.abilityName = "Thunderous Call";
        this.abilityDescription = "Channel the power of The Labrys to summon a lightning bolt to strike your enemy.";
        this.abilityAttackDialogue = "You raise The Labrys upwards as it begins to channel the energy of a storm.";
    }
    
    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        if(this.abilityCooldown <= 0) {
            this.abilityCooldown = 3;
            return true;
        } else {
            if(this.abilityCooldown == 1) {
                Typer.typeStringln(String.format("%s is on cooldown for 1 turn!", this.abilityName));
            } else {
                Typer.typeStringln(String.format("%s is on cooldown for %s more turns!", this.abilityName, this.abilityCooldown));
            }
            return false;
        }
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }

}
