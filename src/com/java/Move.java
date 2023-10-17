package src.com.java;

public class Move {

    protected String moveName;
    protected String moveType;
    protected int moveDmg;
    protected String moveDialogue;

    public Move(String moveName, String moveType, int moveDmg, String moveDialogue) {
        this.moveName = moveName;
        this.moveType = moveType;
        this.moveDmg = moveDmg;
        this.moveDialogue = moveDialogue;
    }

    public String getMoveName(){
        return this.moveName;
    }

    public String getMoveType() {
        return this.moveType;
    }

    public int getMoveDmg() {
        return this.moveDmg;
    }

    public String getMoveDialogue() {
        return moveDialogue;
    }
    
}
