import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.lang.reflect.*;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private ArrayList<String> items; 
    private HashMap<String, Integer> itemValue;
    private Inventory inv;
    private PowerLvl powerLvl;
    private HashMap<Integer, Room> rooms = new HashMap<>();
    private static int numberOfMoves;
    private static int limitOfMoves;
    

    private Stack<Integer> stack = new Stack<Integer>();

    /**
     * Create the game and initialise its internal map, and set limit of moves, powerLevel and inventory.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        this.inv = new Inventory();
        this.inv.setHouse(false);
        this.powerLvl = new PowerLvl(0);
        numberOfMoves = 0;
        limitOfMoves = 50;
    }
    
    public static void main (String[] args)
    {
        Game game = new Game();
        game.play();
    }

    /**
     * Create all the rooms, put the rooms in a hashmap and link their exits together.
     */
    private void createRooms()
    {
        Room forest, winter, desert, jungle, jungle_tempel, village, savanna, house, cave, mineshaft, mineshaft2, end, nether1, nether2, nether3, nether4, nether5, nether6, nether7, nether8;

        // create the rooms

        //wood = 1
        //sand = 2
        //stone = 3
        //diamond = 4
        //iron 5

        //null = 0
        rooms.put(1, forest = new Room("You are now in the forest", 1, 1));
        rooms.put(2, winter= new Room("You are in a winter biome", 2, 1));
        rooms.put(3, desert = new Room("This is a sandy place, looks like a desert", 3, 2));
        rooms.put(4, jungle = new Room("this looks like a jungle", 4, 1));
        rooms.put(5, jungle_tempel = new Room("You have found a hidden jungle tempel. Be carefull there is a zombie boss nearby, you can choose to run away or fight", 5, 4));
        rooms.put(6, village= new Room("You are now in a nice looking village, say hi to the people here! You see an emerald, don't steal it!", 6, 6));
        rooms.put(7, savanna = new Room("You are in a Savanna", 7, 1));
        rooms.put(8, house = new Room("This looks like the best place to build a house", 8, 0));
        rooms.put(9, cave= new Room("You are under the ground, in a cave.", 9, 3));
        rooms.put(10, mineshaft= new Room("This is a MineShaft", 10, 5));
        rooms.put(11, mineshaft2= new Room("You are really close to the end now, once you are in the end, you cant go back. Go east if you want to go to the end", 18, 4));
        rooms.put(12, end = new Room("This is the end, kill the dragon and finish the game. Before you fight him, equip your gear and use all your potions!", 11, 0));
        rooms.put(13, nether1 = new Room("You are now in the Nether. Here are a couple of chests that you can only open with a diamond pickaxe", 12, 0));
        rooms.put(14, nether2 = new Room("You are now in the Nether", 13, 20));
        rooms.put(15, nether3 = new Room("You are now in the Nether", 14, 0));
        rooms.put(16, nether4 = new Room("You are now in the Nether", 15, 0));
        rooms.put(17,nether5 = new Room("That looks like an Nether Wither, try to fight him or run away", 16, 0));
        rooms.put(18,nether6 = new Room("You are now in the Nether", 17, 0));
        rooms.put(19, nether7 = new Room("You are now in the Nether", 18, 0));
        rooms.put(20, nether8 = new Room("There is a chest on the ground, but there is also a portal on your west side. You sure you wanna leave?", 19, 21));

        // initialise room exits
        forest.setExit("north", village);
        forest.setExit("east", winter);
        forest.setExit("south", desert);
        forest.setExit("west", forest);

        winter.setExit("east", cave);
        winter.setExit("west", forest);

        desert.setExit("north", forest);
        desert.setExit("south", jungle);

        jungle.setExit("north", desert);
        jungle.setExit("south", jungle_tempel);
        jungle.setExit("west", jungle);

        jungle_tempel.setExit("north", jungle);

        cave.setExit("south", mineshaft);
        cave.setExit("west", winter);

        mineshaft.setExit("north", cave);
        mineshaft.setExit("south", mineshaft2);
        mineshaft.setExit("west", jungle_tempel);

        mineshaft2.setExit("north", mineshaft);
        mineshaft2.setExit("east", end);

        village.setExit("north", savanna);
        village.setExit("south", forest);

        savanna.setExit("south", village);
        savanna.setExit("north", house);

        nether1.setExit("north", nether2);
        nether1.setExit("east", nether3);
        nether1.setExit("south", nether4);

        nether2.setExit("east", nether5);
        nether2.setExit("south", nether1);

        nether3.setExit("north", nether5);
        nether3.setExit("east", nether6);
        nether3.setExit("east", nether7);
        nether3.setExit("west", nether1);

        nether4.setExit("north", nether1);
        nether4.setExit("south", nether8);

        nether5.setExit("south", nether3);
        nether5.setExit("west", nether2);

        nether6.setExit("south", savanna);
        nether6.setExit("west", nether3);

        nether7.setExit("north", nether3);
        nether7.setExit("west", nether4);

        nether8.setExit("north", nether4);
        nether8.setExit("west", jungle);

            
        house.setExit("south", savanna);
        house.setExit("west", nether1);

        currentRoom = forest;  // start game outside     
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome in your new survival world");
        System.out.println("Textcraft is a survival game, where you need to collect stuff.");
        System.out.println("Eventually you have to go to the end, and kill the Ender Dragon.");
        System.out.println("Before you go to the end you first have to complete some challenges");
        System.out.println("You can open your challenge list by using the command: challenges");
        System.out.println("If you completed a challenge, you will get a message");
        System.out.println();
 
        inv.showCraftingTableStatus();
        inv.printPowerLvl();
        System.out.println();
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are currently homeless");
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("use")){
            use(command);

        }
        else if (commandWord.equals("items")){
            ItemToGet();

        }
        else if (commandWord.equals("inv")){
            this.inv.showInv();
        }
        else if (commandWord.equals("craft")){
            craft(command);
        }
        else if (commandWord.equals("back")){
            if(stack.size() == 1){
                System.out.println("Je kan niet verder terug");
                System.out.println(currentRoom.getLongDescription());
            } else {
            //if (popStack(stack, currentRoom) != 0) {
                goRoom(popStack(stack, currentRoom));
                
                
            //} else {
            }
        }
        else if (commandWord.equals("equip")){
            this.inv.equip();
        }
        else if (commandWord.equals("fight")){
            fight();
        }
        else if (commandWord.equals("challenges")){
            this.inv.showChallenges();
        }

        // else command not recognised.
        return wantToQuit;
    }


    /**
     * Print out which item can be picked up in which particular room.
     * else print else message
     * 
     */ 

    private void ItemToGet(){
        if(currentRoom.getRoomID()==(1)){
            System.out.println("You can get here " + currentRoom.getItemName());}
        else if(currentRoom.getRoomID()==(2)){
            System.out.println("You can get here " + currentRoom.getItemName());}
        else if(currentRoom.getRoomID()==(3)){
            System.out.println("You can get here " + currentRoom.getItemName());}
        else if(currentRoom.getRoomID()==(5)){
            System.out.println("You can get here " + currentRoom.getItemName());}
        else if(currentRoom.getRoomID()==(4)){
            System.out.println("You can get here " + currentRoom.getItemName());}
        else if(currentRoom.getRoomID()==(9)){
            System.out.println("You can get here " + currentRoom.getItemName());}
        else if(currentRoom.getRoomID()==(10)){
            System.out.println("You can get here " + currentRoom.getItemName());}
        else if(currentRoom.getRoomID()==(18)){
            System.out.println("You can get here " + currentRoom.getItemName());}
        else{System.out.println("You can't get anything here");}
    }
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */ 
    private void printHelp() 
    {
        System.out.println(currentRoom.getLongDescription());
        System.out.println("You can try to get some objects");
        System.out.println("In everyroom there are different objects to farm");
        System.out.println();
        inv.showCraftingTableStatus();
        System.out.println();
        inv.printPowerLvl();
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("go quit help use items inv back craft equip fight");
        if(currentRoom.getRoomID()==(11)){
            System.out.println("You can fight the Ender Dragon by using the command: fight");
        }
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;

        }
        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("You are just running around");
        }
        else {
            boolean decision = Game.countMove();
            currentRoom = nextRoom;
            pushStack(stack, currentRoom);
            System.out.println(currentRoom.getLongDescription());
        }
    }
    private void goRoom(int roomID) 
    {
        currentRoom = rooms.get(roomID);
        System.out.println(currentRoom.getLongDescription());
    }

    /** x
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    /**
     * @param Room currentRoom
     * 
     * @return push latest room onto the stack 
     */
    private void pushStack(Stack stack, Room currentRoom) {
        stack.push(currentRoom.getRoomID());
    }
    
     private int popStack(Stack stack, Room currentRoom){
                
       if(stack.size() == 1){
           return 0;
        } else {
            stack.pop();
            return (Integer) stack.peek();
       }
    }
    /**
     *  @param item = int i                             
     *  do a ++ on the item from the room where you were in.
     * 
     */

    private void increment(int i){
        this.inv.increment(currentRoom.getItemID(), i);
    }
    /**
     * @param secondWord
     * if the second word is an item craft it
     * else you can't craft this
     * 
     */
    private void craft(Command command){
        if(!command.hasSecondWord()){
            System.out.println("What do you want to craft");
            return;   
        }
        String itemToCraft = command.getSecondWord();

        if(itemToCraft.equals("sticks")){
            inv.craft(6);
        }
        else if(itemToCraft.equals("wood_pickaxe")){
            inv.craft(7); 
        }
        else if(itemToCraft.equals("craftingtable")){
            inv.craft(8);
        }
        else if(itemToCraft.equals("stone_axe")){
            inv.craft(9);
        }
        else if(itemToCraft.equals("iron_pickaxe")){
            inv.craft(11);
        }
        else if(itemToCraft.equals("iron_sword")){
            inv.craft(17);
        }
        else if(itemToCraft.equals("diamond_pickaxe")){
            inv.craft(18);
        }
        else if(itemToCraft.equals("diamond_sword")){
            inv.craft(12);
        }
        else if(itemToCraft.equals("helmet")){
            inv.craft(13);
        }
        else if(itemToCraft.equals("chestplate")){
            inv.craft(14);
        }
        else if(itemToCraft.equals("leggings")){
            inv.craft(15);
        }
        else if(itemToCraft.equals("boots")){
            inv.craft(16);
        }
        else if(itemToCraft.equals("house")){
            if(currentRoom.getRoomID() == 8){
                inv.craft(10);}
            else{
                System.out.println("You need 50 wood, 30 stone and 20 glass to build a house.");
                System.out.println("The best place to build a house is at the northside from the Savanna!");
                System.out.println("Try it there");}
        }

    
        else{System.out.println("You cant craft this");
        }
    }
    /**
     * @param second word
     * If the second word is an item that is useable, use the item
     * else ask what he wants to use
     */
    private void use(Command command){

        if(!command.hasSecondWord()){
            System.out.println("what do you want to use");
            return;
        }
        String itemToUse = command.getSecondWord();
        if(itemToUse.equals("hand") && currentRoom.getItemID() == 1){
            increment(1);
            System.out.println("Wood: +1");
        } else if(currentRoom.getItemID() != 1){
            System.out.println("You can't use that here"); 
        }

        if(itemToUse.equals("wood_pickaxe") && currentRoom.getItemID() == 3){
            inv.use(7);
        }else if(currentRoom.getItemID() != 3){
                     System.out.println("You can't use that here");
                 }
        if(itemToUse.equals("stone_axe") && currentRoom.getItemID() == 1){
            inv.use(9);
        }else if(currentRoom.getItemID() != 1){
                     System.out.println("You can't use that here");
                 }
        if(itemToUse.equals("iron_pickaxe") && currentRoom.getItemID() == 5){
            inv.use(11);
        }else if(currentRoom.getItemID() != 5){
                     System.out.println("You can't use that here");
                 }
        if(itemToUse.equals("diamond_pickaxe"))
            if(currentRoom.getItemID() == 4){
                inv.use(18);}
            else if(currentRoom.getItemID() == 20){
                inv.use(22);
            }
            else if(currentRoom.getItemID() == 21){
                inv.use(23);
            } else{
                System.out.println("You can't use that here"); 
            }
        if(itemToUse.equals("strength_potion")){
            inv.use(19);
        }    
        if(itemToUse.equals("potions")){
            inv.use(20);
        }
        if(itemToUse.equals("dev")){
            inv.use(999);
        }
    }
    /**
     * if the room ID has a boss you can fight it.
     * else There is nothing to fight
     * 
     */
    private void fight(){
        if(currentRoom.getRoomID() == 11){

            this.inv.fightBoss("end");}
        else if(currentRoom.getRoomID() == 5){            
            this.inv.fightBoss("zombie");}
        else if(currentRoom.getRoomID() == 15){          
            this.inv.fightBoss("nether_wither");
        }else{System.out.println("There is nothing to fight");
        }
    
   }
   /**
    * count move at every goroom command
    * print current moves and moves left
    * if maximum is reached print game over
    */
   
   public static boolean countMove(){
        // tel de stappen
        numberOfMoves++;
        
        // Informatie over hoeveel stappen je hebt gezet / over hebt
        if (numberOfMoves < limitOfMoves) {
            System.out.println("Current number of moves : " + numberOfMoves);
            System.out.println("Moves left : " + (limitOfMoves - numberOfMoves));
            return false;
            // Eind als het het maximum is bereikt
        } else {
            System.out.println("You have reached the maximum amount of moves");
            System.out.println("Game over");
            System.out.println();
            System.out.println();
            System.exit(0);
            
            return true;
        }
    }
}
