package src.com.java.Axes;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;

public class FellingAxe extends Axe {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public FellingAxe(String name, int dmg, int speedPenalty) {
        super(name, dmg, "felling axe", speedPenalty);
        this.abilityName = "Skull Splitter";
        this.abilityDescription = "When used, you have a small chance of killing an enemy immediately.";
        this.abilityAttackDialogue = String.format("You raise your %s high above your head.", name);
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
