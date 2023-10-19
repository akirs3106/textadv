package src.com.java.Axes;

import src.com.java.Typer;
import src.com.java.Player;
import src.com.java.Enemy;

public class DoubleAxe extends Axe {

    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;


    public DoubleAxe(String name, int dmg, int speedPenalty) {
        super(name, dmg, "double axe", speedPenalty);
        this.abilityName = "Double Swing";
        this.abilityDescription = "Utilize the second axehead and hit your opponent on the backswing.";
        this.abilityAttackDialogue = String.format("You swing your %s forwards.", name);
    }

    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        if(this.abilityCooldown <= 0) {
            this.abilityCooldown = 3;
            plr.attackEnemyAbility(enemy, plr, enemyDodgeChance);
            Typer.typeStringln(String.format("You swing your %s back.", this.name));
            plr.attackEnemyAbility(enemy, plr, enemyDodgeChance);
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
