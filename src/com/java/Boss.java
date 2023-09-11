package src.com.java;

public class Boss extends Enemy {

    protected Move move1;
    protected Move move2;
    protected Move move3;
    protected Move move4;
    protected Move healMove;
    protected boolean hasHealMove;
    protected int availableHeals;
    protected int usedHeals;
    protected Move[] nonHealMoves;

    public Boss(String name, Weapon weapon, String race, int maxHealth, int speed, double xpValue, Move move1, Move move2, Move move3, Move move4, int availableHeals) {
        super(name, weapon, race, maxHealth, speed, xpValue, "boss");
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
        this.availableHeals = availableHeals;
        this.usedHeals = 0;
        this.nonHealMoves = new Move[4];

        Move moves[] = {this.move1, this.move2, this.move3, this.move4};
        for(int i = 0; i < 4; i++) {
            Move move = moves[i];

            if(move.getMoveEffect() == "heal") {
                this.healMove = move;
                this.hasHealMove = true;
            } else {
                this.nonHealMoves[i] = move;
            }
        }
    }

    public void useMove1(Player plr) {
        System.out.println(String.format("\n%s %s", this.name, this.move1.getMoveDialogue()));
            plr.takeDamage(move1.getMoveDmg());
    }

    public void useMove2(Player plr) {
        System.out.println(String.format("\n%s %s", this.name, this.move2.getMoveDialogue()));
            plr.takeDamage(move2.getMoveDmg());
    }
    
    public void useMove3(Player plr) {
        System.out.println(String.format("\n%s %s", this.name, this.move3.getMoveDialogue()));
            plr.takeDamage(move3.getMoveDmg());
    }

    public void useMove4(Player plr) {
        System.out.println(String.format("\n%s %s", this.name, this.move4.getMoveDialogue()));
            plr.takeDamage(move4.getMoveDmg());
    }

    public void useHealMove() {
        System.out.println(String.format("\n%s %s", this.name, this.healMove.getMoveDialogue()));
        this.currentHealth += healMove.getMoveDmg();
        this.usedHeals++;
        if(this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        }
        System.out.println(String.format("%s healed for %s\n", this.name, this.healMove.getMoveDmg()));
    }
    
}
