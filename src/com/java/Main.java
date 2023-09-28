package src.com.java;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
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
    
    public static void main(String args[]) {


        String plrClasses[] = {"Warrior" , "Barbarian", "Rogue"};

        Player plr;

        do {
            choosing = true;
            System.out.print("Classes:\n\n1. Warrior\n2. Barbarian\n3. Rogue\n\nChoose your class: ");
            
        
            int input = scanner.nextInt()-1;

            if(input >= 0 && input < plrClasses.length) {

                String plrClass = plrClasses[input];

                plr = new Player(plrClass);
                choosing = false;


            } else {
                plr = null;
                System.out.println("Please input the number next to the class you wish to choose!");
            }

        } while(choosing);

        do {
            String dungeons[] = {"Underground Ruins"};
            choosing = true;

            System.out.print("\nDungeons:\n\n1. Underground Ruins\n\nChoose a dungeon: ");
            int input = scanner.nextInt()-1;

            if(input >= 0 && input < dungeons.length) {
                String startRoomDesc;
                String bossRoomDesc;
                String restRoomDescs[] = new String[3];
                String dungeonName = dungeons[input];
                Room[] rooms = null;

                choosing = false;
                switch(dungeonName) {
                    case "Underground Ruins": 
                    startRoomDesc = "As you descend into the cold and dark ruins, the ancient entryway collapses behind you.";
                    bossRoomDesc = "The room is lined with an abundance of ritualistic symbols and candles.\nYou notice a mutlitude of human skulls in piles around the room.\nAnd in the center, stands a figure wearing a cloak infused with dark magic, wielding a unique dagger.";
                    restRoomDescs[0] = "This room seems to be in much better condition than the others and feels much warmer.";
                    restRoomDescs[1] = "The roof of this room seems to have collapsed, however the night sky shines above you, comforting you for a moment.";
                    restRoomDescs[2] = "A sweet smell comes from this room, making you realize how hungry you actually are.";
                    Weapon bossWeapon = new Dagger("Sacrificial Dagger", 30, "sacrificial dagger", 0);
                    Move bossMove1 = new Move("Dark Pulse", "damage", 15, "Casts Dark Pulse!");
                    Move bossMove2 = new Move("Bonematter Rejuvination", "heal", 50, "Casts Bonematter Rejuvination, absorbing nearby bonemass!");
                    Move bossMove3 = new Move("Summon Undead Army", "damage", 50, "Summons an Undead Army, and you are assaulted by multiple skeletons!");
                    Move bossMove4 = new Move("Sacrificial Slash", "damage", bossWeapon.getDmg(), "Rushes you with its Sacrificial Dagger!");
                    boss = new Boss("The Necromancer", bossWeapon, "skeleton", 300, 0, 1000.00, bossMove1, bossMove2, bossMove3, bossMove4, 3);
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
                        String mapSizes[] = {"Small", "Medium", "Large"};
                        System.out.print("\nChoose a map size:\n\n1. Small\n2. Medium\n3. Large\n\n> ");
                        int mapSizeChoice = scanner.nextInt()-1;
                        if(mapSizeChoice < 3) {
                            String mapSize = mapSizes[mapSizeChoice];
                            choosing = false;
                            Random random = new Random();
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
                                default:
                                    rooms = new Room[3];
                                break;
                            }
                            int restRoomDesc = 0;
                            int roomLootDropLevel = 0;
                            for(int i = 0; i < rooms.length; i++) {
                                if(i == 0) {
                                    rooms[i] = new Room(startRoomDesc);
                                } else if (i == rooms.length-1) {
                                    rooms[i] = new Room(bossRoomDesc, "boss");
                                } else if (i == rooms.length-2) {
                                    rooms[i] = new Room("Upon entering the room, you feel as though a great challenge is approaching.", "rest");
                                    roomLootDropLevel += 1;
                                } else if (i % 5 == 0) {
                                    rooms[i] = new Room(restRoomDescs[restRoomDesc], "rest");
                                    restRoomDesc++;
                                    if(restRoomDesc >= 3) {
                                        restRoomDesc = 0;
                                    }
                                } else {
                                    rooms[i] = createRandomRoom(plr, roomLootDropLevel);
                                }
                            }
                        }
                        
                        
                } while(choosing);
                Dungeon dungeon = new Dungeon(rooms, dungeonName);
                System.out.println("\nYou are now entering the " + dungeonName + ".");
                choosing = false;
                boolean gameActive = true;
                System.out.println(startRoomDesc);
                while(gameActive) {
                    choosing = true;
                    while(choosing) {
                        String question = "What would you like to do?\n";
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
                        System.out.print(question);
                        try {
                            int numChoice = 999;
                            try {
                                numChoice = Integer.parseInt(scanner.next())-1;
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
                            System.out.println("Please enter the number next to your desired choice.");
                        }
                    }
                    
                    
                }
            } else {
                System.out.println("Please input then number next to the dungeon you wish to enter!");
            }

        } while (choosing);

    }

    public static void startEncounter(Enemy enemy, Player plr) {

        System.out.println(String.format("%s approaches you!", enemy.getName()));

            //Enemy moves first if speed in greater than player's
        if(plr.getSpeed() < enemy.getSpeed()) { 
            while(plr.getHp() > 0 && enemy.getHp() > 0) {
                choosing = true;
                enemy.attackPlayer(plr);
                if(plr.getHp() <= 0) {
                    System.out.println("You died to a " + enemy.getName() + ".");
                    scanner.next();
                    System.exit(0);
                }

                while(choosing) {
                    System.out.print("\n1. Attack\n2. Heal\n3. View Stats\nChoose your next move: ");

                    String in = scanner.next();
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
                        System.out.println("\nPut in the number next to the option you wish to choose!");
                    }


                }
                if(enemy.getHp() <= 0) {
                    System.out.println("You won the fight!");
                    plr.setUsedHeals(0);
                    break;
                }
            }
            //Player moves first if speed is greater than enemy's
        } else if (plr.getSpeed() > enemy.getSpeed()) {
            while(plr.getHp() > 0 && enemy.getHp() > 0) {
                choosing = true;
                while(choosing) {
                    System.out.print("\n1. Attack\n2. Heal\n3. View Stats\nChoose your next move: ");

                    String in = scanner.next();
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
                        System.out.println("\nPut in the number next to the option you wish to choose!");
                    }


                }
                

                if(enemy.getHp() <= 0) {
                    System.out.println("You won the fight!");
                    break;
                }
                enemy.attackPlayer(plr);
                if(plr.getHp() <= 0) {
                    System.out.println("You died to a " + enemy.getName() + ".");
                    scanner.next();
                    System.exit(0);
                    
                }
            }
            //Player takes priority over moving first is speeds are equal
        } else {
            while(plr.getHp() > 0 && enemy.getHp() > 0) {
                choosing = true;
                while(choosing) {
                    System.out.print("\n1. Attack\n2. Heal\n3. View Stats\nChoose your next move: ");

                    String in = scanner.next();
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
                        System.out.println("\nPut in the number next to the option you wish to choose!");
                    }


                }


                // plr.attackEnemy(enemy, plr);
                if(enemy.getHp() <= 0) {
                    System.out.println("You won the fight!");
                    break;
                }
                enemy.attackPlayer(plr);
                if(plr.getHp() <= 0) {
                    System.out.println("You died to a " + enemy.getName() + ".");
                    scanner.next();
                    System.exit(0);
                    
                }
            }

        }

    }

    public static void startBossEncounter(Player plr) {
        System.out.println("\nYou have initiated a bossfight against " + boss.getName() + "!\n");
        while(plr.getHp() > 0 && boss.getHp() > 0) {
            choosing = true;
            while(choosing) {
                System.out.print("\n1. Attack\n2. Heal\n3. View Stats\nChoose your next move: ");

                    String in = scanner.next();
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
                        System.out.println("\nPut in the number next to the option you wish to choose!");
                    }


            }
            if(boss.getHp() <= 0) {
                System.out.println("You won the fight!");
                break;
            }

            boss.chooseMove(plr);
            
            if(plr.getHp() <= 0) {
                System.out.println("You died to " + boss.getName() + ".");
                scanner.next();
                System.exit(0);
            }
        }
    }

    public static Weapon createRandomWeapon(String type) {

        Random rand = new Random();

        int randWeaponSubTypeSelector = rand.nextInt((2-0) + 1);
        int randWeaponTierSelector = rand.nextInt((2-0) + 1);

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

    public static Enemy createRandomSkeleton() {



        String skeletonNames[] = {"Skeleton Warrior", "Skeleton Axeman", "Skeleton Assassin"};
        Random random = new Random();
        //Used to determine the name of the skeleton, as well as the type of weapon they're using
        int randSelector = random.nextInt((2-0) + 1);

        Weapon skelWeapon;
        String skelName;
        int skelHp;
        int skelSpeed;
        double skelXp;

        switch(randSelector) {
            case 0:
                skelWeapon = createRandomWeapon("sword");
                skelHp = random.nextInt((100 - 85) + 1) + 85;
                skelSpeed = random.nextInt((115 - 85) + 1) + 85;
                skelName = skeletonNames[randSelector];
            break;
            case 1: 
                skelWeapon = createRandomWeapon("axe");
                skelHp = random.nextInt((125 - 110) + 1) + 110;
                skelSpeed = random.nextInt((90 - 60) + 1) + 60;
                skelName = skeletonNames[randSelector];
            break;
            case 2:
                skelWeapon = createRandomWeapon("dagger");
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
                    weapon = new Dagger("Sacrifical Dagger", 30, "sacrifical dagger", 20);
                break;
                default:
                weapon = null;
            }
        }
        
        return new Chest(weapon, chestName, rarity);


    }

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

    public static Room createRandomRoom(Player plr, int dropLevel) {
        String[] genericRoomDescriptions = {
            "The room is incredibly dark and damp. your eyes slowly adjust to the lack of light as you remain in it.",
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
            enemies[i] = createRandomSkeleton();
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
}