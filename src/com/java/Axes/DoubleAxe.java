package src.com.java.Axes;

import src.com.java.Typer;
import src.com.java.Player;
import src.com.java.Enemy;

public class DoubleAxe extends Axe {

    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;


    public DoubleAxe(String name, int dmg, int speedPenalty) {
        super(name, dmg, "double axe", speedPenalty, 3);
        this.abilityName = "Double Swing";
        this.abilityDescription = "Utilize the second axehead and hit your opponent on the backswing.";
        this.abilityAttackDialogue = String.format("You swing your %s forwards.", name);
    }

    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        System.out.println("Subtype calss ability called!");
        if(this.currentAbilityCooldown <= 0) {
            this.currentAbilityCooldown = this.abilityCooldown;
            Typer.typeStringln(this.abilityAttackDialogue);
            plr.attackEnemyAbility(enemy, plr, enemyDodgeChance);
            Typer.typeStringln(String.format("You swing your %s back.", this.name));
            plr.attackEnemyAbility(enemy, plr, enemyDodgeChance);
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
