package src.com.java.Swords;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;

public class Longsword extends Sword {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public Longsword(String name, int dmg, int speedPenalty) {
        super(name, dmg, "longsword", speedPenalty, 1);
        this.abilityName = "Riposte";
        this.abilityDescription = "If attacked, you have a chance to block incoming damage and deal double damage to your opponent.";
        this.abilityAttackDialogue = "You ready your longsword in a defensive stance.";
        
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
