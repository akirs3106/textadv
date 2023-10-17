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
    protected ArrayList<Move> genericMoves;
    protected int genericMovesUsed;
    protected int genericMovesRequired;
    protected String powerMoveReady;
    protected String powerMoveStillReady;
    protected String powerMoveUsed;

    /**
     * Used for bosses that do NOT have a power move.
     * @param name
     * @param weapon
     * @param race
     * @param maxHealth
     * @param speed
     * @param xpValue
     * @param move1
     * @param move2
     * @param move3
     * @param move4
     * @param availableHeals
     * @param genericMovesRequired
     */
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

    /**
     * Used for bosses that DO have a power move.
     * @param name
     * @param weapon
     * @param race
     * @param maxHealth
     * @param speed
     * @param xpValue
     * @param move1
     * @param move2
     * @param move3
     * @param move4
     * @param availableHeals
     * @param genericMovesRequired
     * @param powerMoveReady
     * @param powerMoveStillReady
     * @param powerMoveUsed
     */
    public Boss(String name, Weapon weapon, String race, int maxHealth, int speed, double xpValue, Move move1, Move move2, Move move3, Move move4, int availableHeals, int genericMovesRequired, String powerMoveReady, String powerMoveStillReady, String powerMoveUsed) {
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

        this.powerMoveReady = powerMoveReady;
        this.powerMoveStillReady = powerMoveStillReady;
        this.powerMoveUsed = powerMoveUsed;

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



    /**
     * Uses one of the boss's moves, guaranteed to hit the player.
     * Should mainly be used for a boss's power move.
     * @param plr
     * @param move
     */
    public void useMove(Player plr, Move move) {
        Typer.typeStringln(String.format("\n%s %s", this.name, move.getMoveDialogue()));
        this.genericMovesUsed += 1;
        plr.takeDamage(move.getMoveDmg());
    }

    /**
     * Uses one of the boss's moves, takes into account the player's dodge chance.
     * Should mainly be used for generic attacks chosen through chooseMove();
     * @param plr
     * @param move
     * @param playerDodgeChance
     */
    public void useMove(Player plr, Move move, int playerDodgeChance) {

        Typer.typeStringln("%s prepares to attack!");

        Random random = new Random();
        int dodgeDecider = random.nextInt(100) + 1;
        Main.wait(500);
        if(dodgeDecider <= playerDodgeChance) {
            Typer.typeStringln("You dodged %s's attack!");
            return;
        } else {
            Typer.typeStringln(String.format("\n%s %s", this.name, move.getMoveDialogue()));
            plr.takeDamage(move.getMoveDmg());
        }
        this.genericMovesUsed += 1;
    }

    public void usePowerMove(Player plr) {
        this.genericMovesUsed = 0;
        useMove(plr, powerMove);
        Typer.typeStringln(this.powerMoveUsed);
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

    public void chooseMove(Player plr, int playerDodgeChance) {
        Random random = new Random();

        if(this.currentHealth < this.maxHealth * 0.5 && this.usedHeals < this.availableHeals && this.hasHealMove) {
            useHealMove();
            return;
        } 
        if(this.hasPowerMove && this.genericMovesUsed >= genericMovesRequired) {

            if(this.genericMovesUsed == this.genericMovesRequired) {
                Typer.typeStringln(this.powerMoveReady);
            }    
            if(this.genericMovesUsed > this.genericMovesRequired) {
                Typer.typeStringln(this.powerMoveStillReady);
            }

            int moveChosen = random.nextInt(genericMoves.size()+2);
            if(moveChosen >= genericMoves.size()) {
                usePowerMove(plr);
            } else {
                useMove(plr, genericMoves.get(moveChosen), playerDodgeChance);
            }

        } else {
            int moveChosen = random.nextInt(genericMoves.size());
            useMove(plr, genericMoves.get(moveChosen), playerDodgeChance);
        }


            
    }
    
}
