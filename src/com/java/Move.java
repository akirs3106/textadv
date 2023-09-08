package src.com.java;

public class Move {

    protected String moveName;
    protected String moveEffect;
    protected int moveDmg;
    protected String moveDialogue;

    public Move(String moveName, String moveEffect, int moveDmg, String moveDialogue) {
        this.moveName = moveName;
        this.moveEffect = moveEffect;
        this.moveDmg = moveDmg;
        this.moveDialogue = moveDialogue;
    }

    public String getMoveName(){
        return this.moveName;
    }

    public String getMoveEffect() {
        return this.moveEffect;
    }

    public int getMoveDmg() {
        return this.moveDmg;
    }

    public String getMoveDialogue() {
        return moveDialogue;
    }
    
}
