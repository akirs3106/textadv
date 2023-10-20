package src.com.java.Swords;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;

public class Rapier extends Sword {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public Rapier(String name, int dmg, int speedPenalty) {
        super(name, dmg, "rapier", speedPenalty, 3);
        this.abilityName = "Weakpoint Precision";
        this.abilityDescription = "Attempt to aim for vital weakpoints of the opponent, decreasing their speed and dealing significant damage.";
        this.abilityAttackDialogue = String.format("You lunge forward with your %s.", name);
    }
    
    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        if(this.abilityCooldown <= 0) {
            this.abilityCooldown = 3;
            return true;
        } else {
            if(this.abilityCooldown == 1) {
                Typer.typeStringln(String.format("%s is on cooldown for 1 turn!", this.abilityName));
            } else {
                Typer.typeStringln(String.format("%s is on cooldown for %s more turns!", this.abilityName, this.currentAbilityCooldown));
            }
            return false;
        }
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\nCooldown: %s turns\n", this.abilityName, this.abilityDescription, this.abilityCooldown);
    }
    
}
