package src.com.java;

import java.util.Random;

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
        int position = 0;
        for(int i = 0; i < 4; i++) {
            Move move = moves[i];

            if(move.getMoveEffect() == "heal") {
                this.healMove = move;
                this.hasHealMove = true;
            } else {
                this.nonHealMoves[position] = move;
                position++;
            }
        }
    }

    public void useMove(Player plr, Move move) {
        System.out.println(String.format("\n%s %s", this.name, move.getMoveDialogue()));
            plr.takeDamage(move.getMoveDmg());
    }


    public void useHealMove() {
        System.out.println(String.format("\n%s %s", this.name, this.healMove.getMoveDialogue()));
        this.currentHealth += healMove.getMoveDmg();
        this.usedHeals++;
        if(this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        }
        System.out.println(String.format("%s healed for %s HP\n", this.name, this.healMove.getMoveDmg()));
    }

    public void chooseMove(Player plr) {
        Random random = new Random();
        int num = random.nextInt(3);

        if(this.currentHealth < this.maxHealth*0.5 && this.usedHeals < this.availableHeals) {
            useHealMove();
            return;
        } else {
            useMove(plr, this.nonHealMoves[num]);
        }
            
    }
    
}
