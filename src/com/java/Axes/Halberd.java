package src.com.java.Axes;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;

public class Halberd extends Axe {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public Halberd(String name, int dmg, int speedPenalty) {
        super(name, dmg, "halberd", speedPenalty, 2);
        this.abilityName = "Defensive Stance";
        this.abilityDescription = "Protect yourself using the halberd's extended shaft, negating a large chunk of the next attack's damage.";
        this.abilityAttackDialogue = String.format("You position your %s infront of you, ready to defend yourself.", name);
    }
    
    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        System.out.println("Subtype calss ability called!");
        if(this.currentAbilityCooldown <= 0) {
            this.currentAbilityCooldown = this.abilityCooldown;
            Typer.typeStringln(this.abilityAttackDialogue);
            plr.setDefensiveStance(true);
            return true;
        } else {
            if(this.currentAbilityCooldown == 1) {
                Typer.typeStringln(String.format("%s is on cooldown for 1 turn!", this.abilityName));
            } else {
                Typer.typeStringln(String.format("%s is on cooldown for %s more turns!", this.abilityName, this.currentAbilityCooldown));
            }
            return false;
        }
    }

    @Override protected String getAbilityDescription() {
        String turn = this.abilityCooldown == 1 ? "turn" : "turns";
        return String.format("Ability: %s\nAbility Description: %s\nCooldown: %s %s\n", this.abilityName, this.abilityDescription, this.abilityCooldown, turn);
    }

}
