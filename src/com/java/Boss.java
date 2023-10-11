package src.com.java;

import java.util.Random;
import java.util.ArrayList;

public class Boss extends Enemy {

    protected Move move1;
    protected Move move2;
    protected Move move3;
    protected Move move4;
    protected Move healMove;
    protected Move powerMove;
    protected boolean hasHealMove = false;
    protected boolean hasPowerMove = false;
    protected int availableHeals;
    protected int usedHeals;
    private ArrayList<Move> genericMoves;
    private int genericMovesUsed;
    private int genericMovesRequired;

    public Boss(String name, Weapon weapon, String race, int maxHealth, int speed, double xpValue, Move move1, Move move2, Move move3, Move move4, int availableHeals, int genericMovesRequired) {
        super(name, weapon, race, maxHealth, speed, xpValue, "boss");
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
        this.availableHeals = availableHeals;
        this.usedHeals = 0;
        this.genericMoves = new ArrayList<Move>();
        this.genericMovesUsed = 0;
        this.genericMovesRequired = genericMovesRequired;

        Move moves[] = {move1, move2, move3, move4};
        for (Move move : moves) {
            if(move.getMoveType().equals("heal")) {
                this.healMove = move;
                this.hasHealMove = true;
            } else if (move.getMoveType().equals("power")) {
                this.powerMove = move;
                this.hasPowerMove = true;
            } else {
                this.genericMoves.add(move);
            }
        }
    }

    public void useMove(Player plr, Move move) {
        Typer.typeStringln(String.format("\n%s %s", this.name, move.getMoveDialogue()));
        this.genericMovesUsed += 1;
        plr.takeDamage(move.getMoveDmg());
    }

    public void usePowerMove(Player plr) {
        Typer.typeStringln(String.format("\n%s %s", this.name, this.powerMove.getMoveDialogue()));
        this.genericMovesUsed = 0;
        useMove(plr, powerMove);
    }


    public void useHealMove() {
        Typer.typeStringln(String.format("\n%s %s", this.name, this.healMove.getMoveDialogue()));
        this.currentHealth += healMove.getMoveDmg();
        this.usedHeals++;
        if(this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        }
        Typer.typeStringln(String.format("%s healed for %s HP\n", this.name, this.healMove.getMoveDmg()));
    }

    public void chooseMove(Player plr) {
        Random random = new Random();

        if(this.currentHealth < this.maxHealth * 0.5 && this.usedHeals < this.availableHeals && this.hasHealMove) {
            useHealMove();
            return;
        } 
            System.out.println(genericMovesUsed);
        if(this.hasPowerMove && this.genericMovesUsed >= genericMovesRequired) {
            int moveChosen = random.nextInt(genericMoves.size()+2);
            if(moveChosen >= genericMoves.size()) {
                usePowerMove(plr);
            } else {
                useMove(plr, genericMoves.get(moveChosen));
            }

        } else {
            int moveChosen = random.nextInt(genericMoves.size());
            useMove(plr, genericMoves.get(moveChosen));
        }


            
    }
    
}
