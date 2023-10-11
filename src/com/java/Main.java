package src.com.java;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Thread;
class Main {

    public static final String[][] swordNames  = {
        {"Rusty Longsword", "Bronze Longsword", "Steel Longsword"},
        {"Rusty Shortsword", "Bronze Shortsword", "Steel Shortsword"},
        {"Rusty Rapier", "Bronze Rapier", "Steel Rapier"}
    };

    public static final String[][] axeNames = {
        {"Rusty Battleaxe", "Bronze Battleaxe", "Steel Battleaxe"},
        {"Rusty Felling Axe", "Bronze Felling Axe", "Steel Felling Axe"},
        {"Rusty Halberd", "Bronze Halberd", "Steel Halberd"}
    };

    public static final String[][] daggerNames = {
        {"Rusty Rondel Dagger", "Bronze Rondel Dagger", "Steel Rondel Dagger"},
        {"Rusty Stiletto Dagger", "Bronze Stiletto Dagger", "Steel Stiletto Dagger"},
        {"Rusty Castillon Dagger", "Bronze Castillon Dagger", "Steel Castillon Dagger"}
    };

    public static final String[][] legendaryNames = {
        {"Excalibur"}, 
        {"The Labrys"}, 
        {"Sacrifical Dagger"}
    };

    public static boolean choosing;
    public static Scanner scanner = new Scanner(System.in);

    public static Boss boss;
    public static int enemyDiffLevel = 0;
    
    public static void main(String args[]) {


        String plrClasses[] = {"Warrior" , "Barbarian", "Rogue"};

        Player plr;

        do {
            choosing = true;
            Typer.typeStringln("Classes:");
            Typer.typeString("\n1. Warrior\n2. Barbarian\n3. Rogue\n\nChoose your class: ", 10);
            
        
            int input = scanner.nextInt()-1;
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //Clears the console.

            if(input >= 0 && input < plrClasses.length) {

                String plrClass = plrClasses[input];

                plr = new Player(plrClass);
                Typer.typeStringln("Class selected: " + plrClass);
                choosing = false;


            } else {
                plr = null;
                Typer.typeStringln("Please input the number next to the class you wish to choose!");
            }

        } while(choosing);

        do {
            String dungeons[] = {"Underground Ruins"};
            choosing = true;
            Typer.typeStringln("\nDungeons:");
            Typer.typeString("\n1. Underground Ruins\n\nChoose a dungeon: ", 10);
            int input = scanner.nextInt()-1;

            if(input >= 0 && input < dungeons.length) {
                String startRoomDesc;
                String bossRoomDesc;
                String restRoomDescs[] = new String[3];
                String dungeonName = dungeons[input];
                Room[] rooms = null;
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                Typer.typeStringln("Selected dungeon: " + dungeonName);

                choosing = false;
                switch(dungeonName) {
                    case "Underground Ruins": 
                    startRoomDesc = "As you descend into the cold and dark ruins, the ancient entryway collapses behind you.";
                    bossRoomDesc = "The room is lined with an abundance of ritualistic symbols and candles.\nYou notice a mutlitude of human skulls in piles around the room.\nAnd in the center, stands a figure wearing a cloak infused with dark magic, wielding a unique dagger.";
                    restRoomDescs[0] = "This room seems to be in much better condition than the others and feels much warmer.";
                    restRoomDescs[1] = "The roof of this room seems to have collapsed, however the night sky shines above you, comforting you for a moment.";
                    restRoomDescs[2] = "A sweet smell comes from this room, making you realize how hungry you actually are.";
                    Weapon bossWeapon = new Dagger("The Necromancer's Dagger", 30, "necromancer dagger", 15);
                    Move bossMove1 = new Move("Dark Pulse", "damage", 15, "Casts Dark Pulse!");
                    Move bossMove2 = new Move("Bonematter Rejuvination", "heal", 50, "Casts Bonematter Rejuvination, absorbing nearby bonemass!");
                    Move bossMove3 = new Move("Summon Undead Army", "power", 50, "Summons an Undead Army, and you are assaulted by multiple skeletons!");
                    Move bossMove4 = new Move("Sacrificial Slash", "damage", bossWeapon.getDmg(), "Rushes you with its Sacrificial Dagger!");
                    boss = new Boss("The Necromancer", bossWeapon, "skeleton", 300, 0, 1000.00, bossMove1, bossMove2, bossMove3, bossMove4, 3, 3);
                break;
                default:
                    dungeonName = null;
                    startRoomDesc = null;
                    bossRoomDesc = null;
                    rooms = new Room[0];
                break;
                }
                do {
                        choosing = true;
                        String mapSizes[] = {"Small", "Medium", "Large", "Custom"};
                        Typer.typeStringln("\nChoose a map size:");
                        Typer.typeString("\n1. Small\n2. Medium\n3. Large\n4. Custom\n\n> ", 10);
                        int mapSizeChoice = scanner.nextInt()-1;
                        if(mapSizeChoice < 4) {
                            String mapSize = mapSizes[mapSizeChoice];
                            choosing = false;
                            Random random = new Random();
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            Typer.typeStringln("Selected size: " + mapSize);
                            switch(mapSize) {
                                case "Small":
                                    rooms = new Room[random.nextInt((15 - 10) + 1) + 10];
                                break;
                                case "Medium":
                                    rooms = new Room[random.nextInt((25 - 20) + 1) + 20];
                                break;
                                case "Large":
                                    rooms = new Room[random.nextInt((35 - 30) + 1) + 30];
                                break;
                                case "Custom":
                                    boolean customChoosing;
                                    do {
                                        customChoosing = true;
                                        Typer.typeString("Input the number of rooms you want:\n> ");
                                        int customSize = scanner.nextInt();
                                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                        if(customSize >= 2) {
                                            rooms = new Room[customSize];
                                            customChoosing = false;
                                        } else {
                                            Typer.typeStringln("Please input a number greater than or equal to 2.");
                                        }
                                    } while (customChoosing);
                                break;
                                default:
                                    rooms = new Room[3];
                                break;
                            }
                            int restRoomDesc = 0;
                            int roomLootDropLevel = 0;
                            for(int i = 0; i < rooms.length; i++) {
                                if (i > 0 && i % 3 == 0) {
                                    enemyDiffLevel += 1;
                                }
                                if(i == 0) {
                                    rooms[i] = new Room(startRoomDesc);
                                } else if (i == rooms.length-1) {
                                    rooms[i] = new Room(bossRoomDesc, "boss");
                                } else if (i == rooms.length-2) {
                                    rooms[i] = new Room("Upon entering the room, you feel as though a great challenge is approaching.", "rest");
                                } else if (i % 5 == 0) {
                                    rooms[i] = new Room(restRoomDescs[restRoomDesc], "rest");
                                    roomLootDropLevel += 1;
                                    restRoomDesc++;
                                    if(restRoomDesc >= 3) {
                                        restRoomDesc = 0;
                                    }
                                } else {
                                    rooms[i] = createRandomRoom(plr, roomLootDropLevel, enemyDiffLevel, (i+1));
                                }
                            }
                        }
                        
                        
                } while(choosing);

                Dungeon dungeon = new Dungeon(rooms, dungeonName);
                Typer.typeStrings(new String[] {"You are now entering the " + dungeonName + ".", startRoomDesc});
                choosing = false;
                boolean gameActive = true;
                while(gameActive) {
                    choosing = true;
                    while(choosing) {
                        String question = "";
                        String[] allQuestions = {"Search", "Open Chest", "Rest", "Heal", "Enter next room", "Enter previous room", "View stats"};
                        ArrayList<String> curatedQuestions = new ArrayList<String>();
                        int choiceNumber = 1;
                        if(!(dungeon.getActiveRoom().getSearched())) {
                            question += String.format("\n%s. %s", choiceNumber, allQuestions[0]);
                            curatedQuestions.add(allQuestions[0]);
                            choiceNumber++;
                        } else if(dungeon.getActiveRoom().hasChests) {
                            question += String.format("\n%s. %s", choiceNumber, allQuestions[1]);
                            curatedQuestions.add(allQuestions[1]);
                            choiceNumber++;
                        } else if (dungeon.getActiveRoom().type.equals("rest")) {
                            question += String.format("\n%s. %s", choiceNumber, allQuestions[2]);
                            curatedQuestions.add(allQuestions[2]);
                            choiceNumber++;
                        }
                        question += String.format("\n%s. %s", choiceNumber, allQuestions[3]);
                        curatedQuestions.add(allQuestions[3]);
                        choiceNumber++;
                        question += String.format("\n%s. %s", choiceNumber, allQuestions[4]);
                        curatedQuestions.add(allQuestions[4]);
                        choiceNumber++;
                        question += String.format("\n%s. %s", choiceNumber, allQuestions[5]);
                        curatedQuestions.add(allQuestions[5]);
                        choiceNumber++;
                        question += String.format("\n%s. %s\n> ", choiceNumber, allQuestions[6]);
                        curatedQuestions.add(allQuestions[6]);
                        choiceNumber++;
                        Typer.typeStringln("What would you like to do?");
                        Typer.typeString(question, 10);
                        try {
                            int numChoice = 999;
                            try {
                                numChoice = Integer.parseInt(scanner.next())-1;
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            } catch (NumberFormatException e) {
                                numChoice = 999;
                            }
                            if(numChoice >= 0 && numChoice < curatedQuestions.size()){
                                switch(curatedQuestions.get(numChoice)) {
                                    case "Search":
                                        dungeon.getActiveRoom().searchRoom(plr);
                                        choosing = false;
                                    break;
                                    case "Open Chest":
                                        dungeon.getActiveRoom().getChest().interact(plr);
                                        choosing = false;
                                    break;
                                    case "Rest":
                                        dungeon.getActiveRoom().useRestRoom(plr);
                                        choosing = false;
                                    break;
                                    case "Heal":
                                        plr.heal();
                                        choosing = false;
                                    break;
                                    case "Enter next room":
                                        dungeon.enterNextRoom(plr);
                                        choosing = false;
                                    break;
                                    case "Enter previous room":
                                        dungeon.enterPreviousRoom(plr);
                                        choosing = false;
                                    break;
                                    case "View stats":
                                        plr.viewStats();
                                        plr.inspectWeapon();
                                        choosing = false;
                                    break;
                                    default: 
                                        System.out.println("\n\nIf you see this I'm a bad programmer :D");
                                        scanner.next();
                                        System.exit(0);
                                    break;
                                }
                            } else {
                                throw new Exception("Choice not in range of permitted options.");
                            }
                        
                        } catch (Exception e) {
                            Typer.typeStringln("Please enter the number next to your desired choice.");
                        }
                    }
                    
                    
                }
            } else {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                Typer.typeStringln("Please input then number next to the dungeon you wish to enter!");
            }

        } while (choosing);

    }

    /**
     * Starts an combat encounter with a provided enemy. Should only be used in Room.playerDecideEncounter().
     * @param enemy
     * @param plr
     */
    public static void startEncounter(Enemy enemy, Player plr) {

        
        Typer.typeStringln(String.format("%s approaches you!", enemy.getName()));

            //Enemy moves first if speed in greater than player's
        if(plr.getSpeed() < enemy.getSpeed()) { 
            while(plr.getHp() > 0 && enemy.getHp() > 0) {
                choosing = true;
                enemy.attackPlayer(plr);
                if(plr.getHp() <= 0) {
                    Typer.typeStringln("You died to a " + enemy.getName() + ".");
                    scanner.next();
                    System.exit(0);
                }

                while(choosing) {
                    Typer.typeString("\n1. Attack\n2. Heal\n3. View Stats\nChoose your next move: ", 10);

                    String in = scanner.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    if(in.equals("1") || in.equals("2") || in.equals("3")) {
                        switch(Integer.parseInt(in)) {
                            case 1:
                                plr.attackEnemy(enemy, plr);
                                choosing = false;
                            break;
                            case 2: 
                                if(plr.heal()) {
                                    choosing = false;
                                } else {
                                    choosing = true;
                                }
                            break;
                            case 3:
                            plr.viewStats();
                            plr.inspectWeapon();
                            break;
                        }
                        
                    } else {
                        Typer.typeStringln("\nPut in the number next to the option you wish to choose!");
                    }


                }
                if(enemy.getHp() <= 0) {
                    Typer.typeStringln("You won the fight!");
                    wait(100);
                    Random random = new Random();
                    if(random.nextInt(10) + 1 <= 1 && plr.getUsedHeals() > 0) {
                        Typer.typeStrings(new String[] {
                            "You look down and find a small amount of medical supplies.",
                            "You have regained one heal!"
                        }, 500);
                        plr.setUsedHeals(plr.getUsedHeals()-1);
                    }
                    break;
                }
            }
            //Player moves first if speed is greater than enemy's
        } else if (plr.getSpeed() > enemy.getSpeed()) {
            while(plr.getHp() > 0 && enemy.getHp() > 0) {
                choosing = true;
                while(choosing) {
                    Typer.typeString("\n1. Attack\n2. Heal\n3. View Stats\nChoose your next move: ", 10);

                    String in = scanner.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    if(in.equals("1") || in.equals("2") || in.equals("3")) {
                        switch(Integer.parseInt(in)) {
                            case 1:
                                plr.attackEnemy(enemy, plr);
                                choosing = false;
                            break;
                            case 2: 
                                if(plr.heal()) {
                                    choosing = false;
                                } else {
                                    choosing = true;
                                }
                            break;
                            case 3:
                            plr.viewStats();
                            plr.inspectWeapon();
                            break;
                        }
                        
                    } else {
                        Typer.typeStringln("\nPut in the number next to the option you wish to choose!");
                    }


                }
                

                if(enemy.getHp() <= 0) {
                    Typer.typeStringln("You won the fight!");
                    wait(100);
                    Random random = new Random();
                    if(random.nextInt(10) + 1 <= 1 && plr.getUsedHeals() > 0) {
                        Typer.typeStrings(new String[] {
                            "You look down and find a small amount of medical supplies.",
                            "You have regained one heal!"
                        }, 500);
                        plr.setUsedHeals(plr.getUsedHeals()-1);
                    }
                    break;
                }
                enemy.attackPlayer(plr);
                if(plr.getHp() <= 0) {
                    Typer.typeStringln("You died to a " + enemy.getName() + ".");
                    scanner.next();
                    System.exit(0);
                    
                }
            }
            //Player takes priority over moving first is speeds are equal
        } else {
            while(plr.getHp() > 0 && enemy.getHp() > 0) {
                choosing = true;
                while(choosing) {
                    Typer.typeString("\n1. Attack\n2. Heal\n3. View Stats\nChoose your next move: ", 10);

                    String in = scanner.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    if(in.equals("1") || in.equals("2") || in.equals("3")) {
                        switch(Integer.parseInt(in)) {
                            case 1:
                                plr.attackEnemy(enemy, plr);
                                choosing = false;
                            break;
                            case 2: 
                                plr.heal();
                                choosing = false;
                            break;
                            case 3:
                            plr.viewStats();
                            plr.inspectWeapon();
                            break;
                        }
                        
                    } else {
                        Typer.typeStringln("\nPut in the number next to the option you wish to choose!");
                    }


                }


                // plr.attackEnemy(enemy, plr);
                if(enemy.getHp() <= 0) {
                    Typer.typeStringln("You won the fight!");
                    wait(100);
                    Random random = new Random();
                    if(random.nextInt(10) + 1 <= 1 && plr.getUsedHeals() > 0) {
                        Typer.typeStrings(new String[] {
                            "You look down and find a small amount of medical supplies.",
                            "You have regained one heal!"
                        }, 500);
                        plr.setUsedHeals(plr.getUsedHeals()-1);
                    }
                    break;
                }
                enemy.attackPlayer(plr);
                if(plr.getHp() <= 0) {
                    Typer.typeStringln("You died to a " + enemy.getName() + ".");
                    scanner.next();
                    System.exit(0);
                    
                }
            }

        }

    }

    /**
     * Starts the boss encounter for the dungeon. Should only be called in Dungeon.enterNextRoom().
     * @param plr
     */
    public static void startBossEncounter(Player plr) {
        Typer.typeStringln("\nYou have initiated a bossfight against " + boss.getName() + "!\n");
        while(plr.getHp() > 0 && boss.getHp() > 0) {
            choosing = true;
            while(choosing) {
                Typer.typeString("\n1. Attack\n2. Heal\n3. View Stats\nChoose your next move: ", 10);

                    String in = scanner.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    if(in.equals("1") || in.equals("2") || in.equals("3")) {
                        switch(Integer.parseInt(in)) {
                            case 1:
                                plr.attackEnemy(boss, plr);
                                choosing = false;
                            break;
                            case 2: 
                                if(plr.heal()) {
                                    choosing = false;
                                } else {
                                    choosing = true;
                                }
                            break;
                            case 3:
                            plr.viewStats();
                            plr.inspectWeapon();
                            break;
                        }
                        
                    } else {
                        Typer.typeStringln("\nPut in the number next to the option you wish to choose!");
                    }


            }
            if(boss.getHp() <= 0) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                finishGame(plr);
                break;
            }

            boss.chooseMove(plr);
            
            if(plr.getHp() <= 0) {
                Typer.typeStringln("You died to " + boss.getName() + ".");
                scanner.next();
                System.exit(0);
            }
        }
    }

    public static void finishGame(Player plr) {
        switch(boss.getName()) {
            case "The Necromancer": 
                switch(plr.getPlayerClass()) {
                    case "Warrior":
                        Typer.typeStrings(new String[] {
                                                "You slash The Necromancer with your " + plr.getWeapon().getName() + " knocking him backwards and onto the ground.",
                                                "You stare at what remains of the struggling creature, as its reduced to a pile of bones once again.",
                                                "As you raise your " + plr.getWeapon().getName() + " in celebration, you begin to hear cackling.",
                                                "You look back down towards the ground, and see the remains of The Necromancer reconstructing themselves back into their functional form.",
                                                "Suddenly, an army of skeletons begins to rise around you, all of which are cackling as well.",
                                                "As the undead army begins to close in around you, you notice The Necromancer is wielding its Sacrifical Dagger once again.",
                                                "Cutting through the many skeletons, you rush towards The Necromancer, and force the Sacrifical Dagger out of his hand.",
                                                "You firmly grip the dagger, and lodge it into The Necromancer's skull.",
                                                "As the dagger penetrates its skull, you can see dark energy flowing out of The Necromancer's skull and the knife.",
                                                "The Necromancer lets out one final scream before slowly being turned into bonedust, along with its army of skeletons."
                                                });
                    break;
                    case "Barbarian":
                        if(plr.getWeapon().getName().equals("The Labrys")) {
                            Typer.typeStrings(new String[] {
                                "While he is dazed from your previous attacks, you harshly kick The Necromancer to the floor.",
                                "As he lays on the ground, you lift up your foot and slam it down onto his ribcage, shattering it into millions of pieces.",
                                "The Necromancer lets out a horrifying screech.",
                                "You raise The Labrys into the air in celebration above the suffering creature.",
                                "As you look down, you can see The Necromancer reaching for his Sacrificial Dagger.",
                                "As he gets ahold of it, you promptly slam your foot down onto his wrist, crushing it just like his ribcage.",
                                "You reach your arm out and grab the Sacrifical Dagger amongst the many particles of shattered bone.",
                                "You look at it for a moment, only to snap it in half with your bare hands.",
                                "The Necromancer yells out in anger at this sight of you snapping his beloved weapon.",
                                "You place your foot on The Necromancer's skull, before promptly crushing it beneath your boots."
                            });
                        } else {
                            Typer.typeStrings(new String[] {
                                "While he is dazed from your previous attacks, you harshly kick The Necromancer to the floor.",
                                "As he lays on the ground, you lift up your foot and slam it down onto his ribcage, shattering it into millions of pieces.",
                                "The Necromancer lets out a horrifying screech.",
                                "You raise your " + plr.getWeapon().getName() + " into the air in celebration above the suffering creature.",
                                "As you look down, you can see The Necromancer reaching for his Sacrificial Dagger.",
                                "As he gets ahold of it, you promptly slam your foot down onto his wrist, crushing it just like his ribcage.",
                                "You reach your arm out and grab the Sacrifical Dagger amongst the many particles of shattered bone.",
                                "You look at it for a moment, only to snap it in half with your bare hands.",
                                "The Necromancer yells out in anger at the sight of you snapping his beloved weapon.",
                                "You place your foot on The Necromancer's skull, before promptly crushing it beneath your boots."
                            });
                        }
                    break;
                    case "Rogue":
                        Typer.typeStrings(new String[] {
                            "You slash at The Necromancer with your " + plr.getWeapon().getName() + ", causing him to stumble backwards.",
                            "The Necromancer clutches at his partially broken ribcage.",
                            "While he is attempting to recover, you sprint forwards and swipe the Sacrificical Dagger straight from his hand.",
                            "You knock over a bookshelf, causing a gust of wind that extinguishes all the candles that are producing light in the room.",
                            "As the room fades to complete darkness, you begin to stalk around The Necromancer.",
                            "The Necromancer gazes around the room, desperately attempting to pinpoint your location.",
                            "However, before he can even hear you, you lunge forwards and stab him over and over again.",
                            "The Necromancer falls to the floor, and collapses into a lifeless pile of bones once again."
                        });
                        choosing = true;
                        while(choosing) {
                            Typer.typeString("Would you like to keep The Necromancer's Dagger? (Y/N)\n> ");
                            String input = scanner.next().toLowerCase();
                            if(input.equals("y")) {
                                choosing = false;
                                plr.setWeapon(boss.getWeapon());
                                Typer.typeStringln("You equipped " + plr.getWeapon().getName() + "!");
                                wait(2000);
                            } else if (input.equals("n")){
                                choosing = false;
                                Typer.typeStringln("You throw The Necromancer's Dagger on the floor with all your strength, shattering it into pieces as it hits the cold stone floor.");
                            } else {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                Typer.typeStringln("Please input \"Y\" or \"N\".");
                            }
                        }
                    break;
                }
                Typer.typeStrings(new String[] {
                    "As you defeated The Necromancer once and for all, a large section of the wall begins to slide open at the back of the room.",
                    "You enter the now open passageway and emerge from the dungeon, back into the world.",
                    "Congratulations, you have succesfully emerged victorious from the dungeon!",
                    "Final Stats: "
                });
                plr.viewStats();
                plr.inspectWeapon();
                scanner.next();
                System.exit(0);
        }
    }

    /**
     * Creates a completely random weapon, deprecated. Instead, use createRandomWeapon(String type, int rarity).
     * @param type | "sword" | "axe" | "dagger" |
     * @return Weapon
     */
    public static Weapon createRandomWeapon(String type) {

        Random rand = new Random();

        int randWeaponSubTypeSelector = rand.nextInt((2-0) + 1);
        int randWeaponTierSelector = rand.nextInt(101) + 1;

        Weapon weapon;
        String weaponName;
        String weaponSubType;
        int weaponDamage;
        int weaponSpeedPenalty;



        switch(type) {
            case "sword": 
                weaponName = swordNames[randWeaponSubTypeSelector][randWeaponTierSelector];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "longsword";
                    break;
                    case 1:
                        weaponSubType = "shortsword";
                    break;
                    case 2: 
                        weaponSubType = "rapier";
                    break;
                    default:
                        weaponSubType = null;
                    
                }

                switch(randWeaponTierSelector) {
                    case 0: 
                        weaponDamage = rand.nextInt((15 - 10) + 1) + 10;
                        weaponSpeedPenalty = rand.nextInt((30 - 20) + 1) + 20;
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((20 - 15) + 1) + 15;
                        weaponSpeedPenalty = rand.nextInt((35 - 25) + 1) + 25;
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((25 - 20) + 1) + 20;
                        weaponSpeedPenalty = rand.nextInt((40 - 30) + 1) + 30;
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;

                }
                weapon = new Sword(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
                break;
            case "axe":
                weaponName = axeNames[randWeaponSubTypeSelector][randWeaponTierSelector];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "battleaxe";
                    break;
                    case 1:
                        weaponSubType = "felling axe";
                    break;
                    case 2: 
                        weaponSubType = "halberd";
                    break;
                    default:
                        weaponSubType = null;
                }

                switch(randWeaponTierSelector) {
                    case 0: 
                        weaponDamage = rand.nextInt((25 - 20) + 1) + 20;
                        weaponSpeedPenalty = rand.nextInt((40 - 30) + 1) + 30;
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((30 - 25) + 1) + 25;
                        weaponSpeedPenalty = rand.nextInt((45 - 35) + 1) + 35;
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((35 - 30) + 1) + 30;
                        weaponSpeedPenalty = rand.nextInt((50 - 45) + 1) + 45;
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;
                }

                weapon = new Axe(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
                break;
            case "dagger": 
                weaponName = daggerNames[randWeaponSubTypeSelector][randWeaponTierSelector];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "rondel dagger";
                    break;
                    case 1:
                        weaponSubType = "stiletto dagger";
                    break;
                    case 2: 
                        weaponSubType = "castillon dagger";
                    break;
                    default:
                        weaponSubType = null;
                }

                switch(randWeaponTierSelector) {
                    case 0: 
                        weaponDamage = rand.nextInt((13 - 8) + 1) + 8;
                        weaponSpeedPenalty = rand.nextInt((15 - 10) + 1) + 10;
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((18 - 13) + 1) + 13;
                        weaponSpeedPenalty = rand.nextInt((20 - 15) + 1) + 15;
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((23 - 18) + 1) + 18;
                        weaponSpeedPenalty = rand.nextInt((25 - 20) + 1) + 20;
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;
                }
                weapon = new Dagger(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
            break;
            default:
                weapon = null;
        }
        return weapon;
    }

    /**
     * Creates a random weapon based on a given rarity tier
     * @param type | "sword" | "axe" | "dagger" |
     * @param rarity | 0 | 1 | 2 |
     * @return Weapon
     */
    public static Weapon createRandomWeapon(String type, int rarity) {

        Random rand = new Random();

        int randWeaponSubTypeSelector = rand.nextInt((2-0) + 1);
        int weaponTier = rarity;

        Weapon weapon;
        String weaponName;
        String weaponSubType;
        int weaponDamage;
        int weaponSpeedPenalty;

        switch(type) {
            case "sword": 
                weaponName = swordNames[randWeaponSubTypeSelector][weaponTier];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "longsword";
                    break;
                    case 1:
                        weaponSubType = "shortsword";
                    break;
                    case 2: 
                        weaponSubType = "rapier";
                    break;
                    default:
                        weaponSubType = null;
                    
                }

                switch(weaponTier) {
                    case 0: 
                        weaponDamage = rand.nextInt((15 - 10) + 1) + 10;
                        weaponSpeedPenalty = rand.nextInt((30 - 20) + 1) + 20;
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((20 - 15) + 1) + 15;
                        weaponSpeedPenalty = rand.nextInt((35 - 25) + 1) + 25;
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((25 - 20) + 1) + 20;
                        weaponSpeedPenalty = rand.nextInt((40 - 30) + 1) + 30;
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;

                }
                weapon = new Sword(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
                break;
            case "axe":
                weaponName = axeNames[randWeaponSubTypeSelector][weaponTier];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "battleaxe";
                    break;
                    case 1:
                        weaponSubType = "felling axe";
                    break;
                    case 2: 
                        weaponSubType = "halberd";
                    break;
                    default:
                        weaponSubType = null;
                }

                switch(weaponTier) {
                    case 0: 
                        weaponDamage = rand.nextInt((25 - 20) + 1) + 20;
                        weaponSpeedPenalty = rand.nextInt((40 - 30) + 1) + 30;
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((30 - 25) + 1) + 25;
                        weaponSpeedPenalty = rand.nextInt((45 - 35) + 1) + 35;
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((35 - 30) + 1) + 30;
                        weaponSpeedPenalty = rand.nextInt((50 - 45) + 1) + 45;
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;
                }

                weapon = new Axe(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
                break;
            case "dagger": 
                weaponName = daggerNames[randWeaponSubTypeSelector][weaponTier];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "rondel dagger";
                    break;
                    case 1:
                        weaponSubType = "stiletto dagger";
                    break;
                    case 2: 
                        weaponSubType = "castillon dagger";
                    break;
                    default:
                        weaponSubType = null;
                }

                switch(weaponTier) {
                    case 0: 
                        weaponDamage = rand.nextInt((13 - 8) + 1) + 8;
                        weaponSpeedPenalty = rand.nextInt((15 - 10) + 1) + 10;
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((18 - 13) + 1) + 13;
                        weaponSpeedPenalty = rand.nextInt((20 - 15) + 1) + 15;
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((23 - 18) + 1) + 18;
                        weaponSpeedPenalty = rand.nextInt((25 - 20) + 1) + 20;
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;
                }
                weapon = new Dagger(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
            break;
            default:
                weapon = null;
        }
        return weapon;
    }

    /**
     * Creates a random skeleton, whose weapon rarity is decided on the dungeon's enemyLevel variable on generation.
     * @param enemyLevel
     * @return Enemy
     */
    public static Enemy createRandomSkeleton(int enemyLevel) {



        String skeletonNames[] = {"Skeleton Warrior", "Skeleton Axeman", "Skeleton Assassin"};
        Random random = new Random();
        //Used to determine the name of the skeleton, as well as the type of weapon they're using
        int randSelector = random.nextInt((2-0) + 1);
        int weaponRaritySelector = random.nextInt(101) + 1;

        int weaponTier;
        Weapon skelWeapon;
        String skelName;
        int skelHp;
        int skelSpeed;
        double skelXp;

        //Rusty Weapon Skeleton 85% base
        //Bronze Weapon Skeleton 10% base
        //Steel Weapon Skeleton 5% base
        if(weaponRaritySelector <= 5 + (5*enemyLevel)) {
            weaponTier = 2;
        } else if (weaponRaritySelector <= 15 + (15*enemyLevel)) {
            weaponTier = 1;
        } else {
            weaponTier = 0;
        }

        switch(randSelector) {
            case 0:
                skelWeapon = createRandomWeapon("sword", weaponTier);
                skelHp = random.nextInt((100 - 85) + 1) + 85;
                skelSpeed = random.nextInt((115 - 85) + 1) + 85;
                skelName = skeletonNames[randSelector];
            break;
            case 1: 
                skelWeapon = createRandomWeapon("axe", weaponTier);
                skelHp = random.nextInt((125 - 110) + 1) + 110;
                skelSpeed = random.nextInt((90 - 60) + 1) + 60;
                skelName = skeletonNames[randSelector];
            break;
            case 2:
                skelWeapon = createRandomWeapon("dagger", weaponTier);
                skelHp = random.nextInt((90 - 45) + 1) + 45;
                skelSpeed = random.nextInt((140 - 110) + 1) + 115;
                skelName = skeletonNames[randSelector];
            break;
            default:
            skelHp = 0;
            skelSpeed = 0;
            skelWeapon = createRandomWeapon(null);
            throw new IllegalStateException("randSelector is not within required range.");

        }


        skelXp = Math.round(((skelHp + skelSpeed + skelWeapon.getDmg()) / 3) * 100) / 100;



        return new Enemy(skelName, skelWeapon, "skeleton", skelHp, skelSpeed, skelXp, "normal");

        
    }

    /**
     * Creates a chest with a randomly decided weapon inside of it.
     * @param plr | "Warrior" | "Barbarian" | "Rogue" |
     * @param dropLevel
     * @return Chest
     */
    public static Chest createRandomChest(Player plr, int dropLevel) {
        String commonChestNames[] = {"Rotted Chest", "Rusty Chest", "Scratched Chest"};
        String uncommonChestNames[] = {"Oak Chest", "Maple Chest", "Stone Chest"};
        String rareChestNames[] = {"Silver Chest", "Large Oak Chest"};
        String legendaryChestNames[] = {"Gilded Chest", "Regal Chest"};

        int rarity;
        String chestName;
        Weapon weapon;
        Random rand = new Random();
        int result = rand.nextInt(100) + 1;
        String plrClass = plr.getPlayerClass();
        
        //Common 50% chance base 
        //Uncommon 30% chance base 
        //Rare 15% chance base 
        //Legendary 5% chance base
        if(result <= 50 - (10*dropLevel)) {
            rarity = 0;
            chestName = commonChestNames[rand.nextInt(3)];
        } else if(result <= 80 - (5*dropLevel)) {
            rarity = 1;
            chestName = uncommonChestNames[rand.nextInt(3)];
        } else if(result <= 95 - (3*dropLevel)){
            rarity = 2;
            chestName = rareChestNames[rand.nextInt(2)];
        } else {
            rarity = 3;
            chestName = legendaryChestNames[rand.nextInt(2)];
        }

        if(rarity < 3) {
            switch(plrClass) {
                case "Warrior": 
                    weapon = createRandomWeapon("sword", rarity);
                break;
                case "Barbarian":
                    weapon = createRandomWeapon("axe", rarity);
                break;
                case "Rogue":
                    weapon = createRandomWeapon("dagger", rarity);
                break;
                default:
                    weapon = null;
            }
        } else {
            switch(plrClass) {
                case "Warrior":
                    weapon = new Sword("Excalibur", 40, "excalibur", 35);
                break;
                case "Barbarian":
                    weapon = new Axe("The Labrys", 55, "the labrys", 45);
                break;
                case "Rogue":
                    weapon = new Dagger("Sacrifical Dagger", 30, "sacrificial dagger", 20);
                break;
                default:
                weapon = null;
            }
        }
        
        return new Chest(weapon, chestName, rarity);


    }

    /**
     * Creates a random chest with a set rarity level.
     * @param plr
     * @param rarity | 0 | 1 | 2 |
     * @return Chest
     */
    public static Chest createRandomChestRarity(Player plr, int rarity) {
        String commonChestNames[] = {"Rotted Chest", "Rusty Chest", "Scratched Chest"};
        String uncommonChestNames[] = {"Oak Chest", "Maple Chest", "Stone Chest"};
        String rareChestNames[] = {"Silver Chest", "Large Oak Chest"};
        String legendaryChestNames[] = {"Gilded Chest", "Regal Chest"};

        String chestName;
        Weapon weapon;
        Random rand = new Random();
        String plrClass = plr.getPlayerClass();
        
        //Common 50% chance
        //Uncommon 30% chance
        //Rare 15% chance
        //Legendary 5% chance
        if(rarity == 0) {
            chestName = commonChestNames[rand.nextInt(3)];
        } else if(rarity == 1) {
            chestName = uncommonChestNames[rand.nextInt(3)];
        } else if(rarity == 2){
            chestName = rareChestNames[rand.nextInt(2)];
        } else {
            chestName = legendaryChestNames[rand.nextInt(2)];
        }

        if(rarity < 3) {
            switch(plrClass) {
                case "Warrior": 
                    weapon = createRandomWeapon("sword", rarity);
                break;
                case "Barbarian":
                    weapon = createRandomWeapon("axe", rarity);
                break;
                case "Rogue":
                    weapon = createRandomWeapon("dagger", rarity);
                break;
                default:
                    weapon = null;
            }
        } else {
            switch(plrClass) {
                case "Warrior":
                    weapon = new Sword("Excalibur", 40, "excalibur", 35);
                break;
                case "Barbarian":
                    weapon = new Axe("The Labrys", 55, "the labrys", 45);
                break;
                case "Rogue":
                    weapon = new Dagger("Sacrifical Dagger", 30, "sacrifical dagger", 20);
                break;
                default:
                weapon = null;
            }
        }
        
        return new Chest(weapon, chestName, rarity);


    }

    /**
     * Creates a random room for the dungeon.
     * @param plr
     * @param dropLevel
     * @param enemyLevel
     * @return Room
     */
    public static Room createRandomRoom(Player plr, int dropLevel, int enemyLevel, int roomNumber) {
        String[] genericRoomDescriptions = {
            "The room is incredibly dark and damp. Your eyes slowly adjust to the lack of light as you remain in it.",
            "The room contains various cells with decomposed corpses inside of them, infested with rats.",
            "You can feel the room's rotted wood floor beneath your feet.",
            "The room's cold stone brick walls appear to extend upwards forever.",
            "In the room, you notice an iron grate beneath your feet. As you peer down into the pit below it, you can make out spikes with impaled corpses on them.",
            "As you gaze around the room, you notice multiple torture devices lining the walls, along with a wodden chair with steel cuffs in the center of the room.",
            "Peering upwards, you notice cages hanging from the room's ceiling, some containing decayed corpses. Near them are also what seem to be broken spears.",
            "An assortment of pots and pans litters the floor of this room, accompanied by the smell of rotted food.",
            "The room is mostly empty, besides a few ritualistic symbols and candles lining the floor and walls."
        };
        Random random = new Random();

        //Up to 3 enemies in a single room.
        Enemy[] enemies = new Enemy[random.nextInt(3) + 1];
        for(int i = 0; i < enemies.length; i++) {
            enemies[i] = createRandomSkeleton(enemyLevel);
        }
        Chest chest;
        int chestChance = random.nextInt(100) + 1;
        if(chestChance <= 75) {
            chest = createRandomChest(plr, dropLevel);
        } else {
            chest = null;
        }

            return new Room(enemies, chest, genericRoomDescriptions[random.nextInt(genericRoomDescriptions.length)], "generic");
        
        
    }

    public static void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // public static void typeString(String str) {
    //     String[] strChars = str.split("", 0);
    //     for(String character : strChars) {
    //         System.out.print(character);
    //         wait(20);
    //     }
    // }

    // public static void typeStringln(String str) {
    //     String[] strChars = str.split("", 0);
    //     for(String character : strChars) {
    //         System.out.print(character);
    //         wait(20);
    //     }
    //     System.out.println("\n");
    // }


    // public static void typeStrings(String[] arr) {
    //     for(String str : arr) {
    //         typeString(str);
    //         wait(2000);
    //         System.out.println("\n");
    //     }
    // }
}