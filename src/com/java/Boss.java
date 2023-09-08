package src.com.java;

public class Boss extends Enemy {

    protected Move move1;
    protected Move move2;
    protected Move move3;
    protected Move move4;
    protected int availableHeals;
    protected int usedHeals;

    public Boss(String name, Weapon weapon, String race, int maxHealth, int speed, double xpValue, Move move1, Move move2, Move move3, Move move4, int availableHeals) {
        super(name, weapon, race, maxHealth, speed, xpValue, "boss");
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
        this.availableHeals = availableHeals;
        this.usedHeals = 0;
    }

    public void useMove1(Player plr) {
        System.out.println(String.format("\n%s %s", this.name, this.move1.getMoveDialogue()));
        if(move1.getMoveEffect() == "heal" && availableHeals > usedHeals) {
            this.currentHealth += move1.getMoveDmg();
            System.out.println(String.format("%s healed for %s!\n", this.name, this.move1.getMoveDmg()));
        } else {
            plr.takeDamage(move1.getMoveDmg());
        }
    }

    public void useMove2(Player plr) {
        System.out.println(String.format("\n%s %s", this.name, this.move2.getMoveDialogue()));
        if(move2.getMoveEffect() == "heal" && availableHeals > usedHeals) {
            this.currentHealth += move2.getMoveDmg();
            System.out.println(String.format("%s healed for %s!\n", this.name, this.move2.getMoveDmg()));
        } else {
            plr.takeDamage(move2.getMoveDmg());
        }
    }
    
    public void useMove3(Player plr) {
        System.out.println(String.format("\n%s %s", this.name, this.move3.getMoveDialogue()));
        if(move3.getMoveEffect() == "heal" && availableHeals > usedHeals) {
            this.currentHealth += move3.getMoveDmg();
            System.out.println(String.format("%s healed for %s!\n", this.name, this.move3.getMoveDmg()));
        } else {
            plr.takeDamage(move3.getMoveDmg());
        }
    }

    public void useMove4(Player plr) {
        System.out.println(String.format("\n%s %s", this.name, this.move4.getMoveDialogue()));
        if(move4.getMoveEffect() == "heal" && availableHeals > usedHeals) {
            this.currentHealth += move4.getMoveDmg();
            System.out.println(String.format("%s healed for %s!\n", this.name, this.move4.getMoveDmg()));
        } else {
            plr.takeDamage(move4.getMoveDmg());
        }
    }
    
}
