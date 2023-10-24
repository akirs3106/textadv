package src.com.java.Axes;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;

public class Labrys extends Axe {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public Labrys() {
        super("The Labrys", 55, "the labrys", 45, 5);
        this.abilityName = "Thunderous Call";
        this.abilityDescription = "Channel the power of The Labrys to summon a lightning bolt to strike your enemy.";
        this.abilityAttackDialogue = "You raise The Labrys upwards as it begins to channel the energy of a storm.";
    }
    
    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        System.out.println("Subtype calss ability called!");
        if(this.currentAbilityCooldown <= 0) {
            this.currentAbilityCooldown = this.abilityCooldown;
            Typer.typeStringln(this.abilityAttackDialogue);
            Typer.typeStringln(String.format("The room brightens to a bright blue as %s is hit with a powerful thunderbolt.", enemy.getName()));
            enemy.takeRawDamage(70, plr);
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
