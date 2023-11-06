package src.com.java.Swords;

import src.com.java.Main;
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
        if(Main.devMode) {
            System.out.println("Entered rapier ability");
        }
        if(this.currentAbilityCooldown <= 0) {
            if(Main.devMode) {System.out.println("Passed ablility cooldown!");}
            this.currentAbilityCooldown = this.abilityCooldown;
            Typer.typeStrings(new String[] {
                this.abilityAttackDialogue,
                String.format("Your %s hit %s for %s damage!", plr.getWeapon().getName(), enemy.getName(), plr.getWeapon().getDmg()*1.2),
                String.format("You lowered %s's speed by 20%!", enemy.getName())
            }, 300);
            enemy.setBaseSpeed((int)(enemy.getSpeed()*0.8));
            enemy.calculateDodgeChance(plr);
            plr.calculateDodgeChance(enemy);
            enemy.takeRawDamage((int)(plr.getWeapon().getDmg()*1.2), plr);
            return true;
        } else {
            if(Main.devMode) {System.out.println("Cooldown activated");}
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
