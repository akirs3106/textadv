package src.com.java.Swords;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;

public class Excalibur extends Sword {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public Excalibur() {
        super("Excalibur", 40, "excalibur", 35, 4);
        this.abilityName = "Holy Imbuement";
        this.abilityDescription = "Using this will imbue the sword with holy power, doubling its damage for its next attack.";
        this.abilityAttackDialogue = "Excalibur begins to glow with a holy aura, as it is imbued with holy power.";
    }
    
    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        System.out.println("Subtype calss ability called!");
        if(this.currentAbilityCooldown <= 0) {
            this.currentAbilityCooldown = this.abilityCooldown;
            Typer.typeStringln(abilityAttackDialogue);
            plr.setDamageMultiplier(2.5);
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
        String turn = this.abilityCooldown == 1 ? "turn" : "turns";
        return String.format("Ability: %s\nAbility Description: %s\nCooldown: %s %s\n", this.abilityName, this.abilityDescription, this.abilityCooldown, turn);
    }
    
}
