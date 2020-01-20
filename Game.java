import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
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
    private Item currentItem;
    private ArrayList<String> items; 
    private HashMap<String, Integer> itemValue;
    private int powerLevel = 0;
    //game items
    private int wood = 0;
    private int sand = 0;
    private int stone = 0;
    private int iron = 0;
    private int diamond = 0;
    private int stick = 0;
    private int wood_PickAxe = 0;
    private int emerald = 0;
    
    private Stack<Integer> stack = new Stack<Integer>();
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    private void pushStack(Stack stack, Room currentRoom) {
        stack.push(currentRoom.getRoomID());
        System.out.println("stack: " + stack);
    }
    
     private void popStack(Stack stack, Room currentRoom){
        stack.pop();
        System.out.println("stack: " + stack);
        int newRoomID = (Integer) stack.peek();

        System.out.println(currentRoom.getLongDescription());
        }
    
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room forest, winter, desert, jungle, jungle_tempel, village, savanna, house, cave, mineshaft, end, nether1, nether2, nether3, nether4, nether5, nether6;

        // create the rooms

        //wood = 1
        //sand = 2
        //stone = 3
        //diamond = 4
        //iron = 5
        //null = 0
        forest = new Room("You are now in the forest", 1, 1);
        winter= new Room("You are in a winter biome", 2, 1);
        desert = new Room("This is a sandy place, looks like a desert", 3, 2);
        jungle = new Room("this looks like a jungle", 4, 1);
        jungle_tempel = new Room("You have found a hidden jungle tempel, and you see a big Emerald, pick it up!.", 5, 4);
        village= new Room("You are now in a nice looking village, say hi to the people here!", 6, 4);
        savanna = new Room("You are in a Savanna", 7, 1);
        house = new Room("Welcome in you're own house", 8, 0);
        cave= new Room("You are under the ground, in a cave.", 9, 3);
        mineshaft= new Room("This is a MineShaft", 10, 5);
        end = new Room("This is the end, kill the dragon and finish the game.", 11, 0);
        nether1 = new Room("You are now in the Nether", 12, 0);
        nether2 = new Room("You are now in the Nether2", 13, 0);
        nether3 = new Room("You are now in the Nether3", 14, 0);
        nether4 = new Room("You are now in the Nether4", 15, 0);
        nether5 = new Room("You are now in the Nether", 16, 0);
        nether6 = new Room("Look an portal, a way out!", 17, 0);

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
        mineshaft.setExit("east", end);
        mineshaft.setExit("west", jungle_tempel);

        village.setExit("north", savanna);
        village.setExit("south", forest);

        savanna.setExit("east", nether1);
        savanna.setExit("south", village);

        nether1.setExit("north", nether2);
        nether1.setExit("east", nether3);
        nether1.setExit("south", nether4);

        nether2.setExit("east", nether5);
        nether2.setExit("south", nether1);

        nether3.setExit("north", nether5);
        nether3.setExit("east", nether6);
        nether3.setExit("west", nether1);

        nether4.setExit("north", nether1);

        nether5.setExit("south", nether3);
        nether5.setExit("west", nether2);

        nether6.setExit("south", savanna);
        nether6.setExit("west", nether3);

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
        System.out.println("Type 'help' if you need help.");
        System.out.println();
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
            increment(1);

        }
        else if (commandWord.equals("items")){
            ItemToGet();

        }
        else if (commandWord.equals("inv")){
            inventory();
        }
        else if (commandWord.equals("craft")){
            craft(command);
        }
        else if (commandWord.equals("back")){
            popStack(stack, currentRoom);
        }    
        
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */ 

    private void ItemToGet(){
        //if(currentRoom.getShortDescription().equals("You are now in the forest")){
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
        else if(currentRoom.getRoomID()==(15)){
            System.out.println("You can get here a potion");} //Speedpot
        else if(currentRoom.getRoomID()==(14)){
            System.out.println("You can get here a potion");} //Strengthpot
        else{System.out.println("You can't get anything here");}
    }

    private void printHelp() 
    {
        System.out.println(currentRoom.getLongDescription());
        System.out.println("You can try to get some objects");
        System.out.println("In everyroom there are different objects to farm");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("go quit help use items inv back craft");
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
            currentRoom = nextRoom;
            pushStack(stack, currentRoom);
            System.out.println(currentRoom.getLongDescription());
        }
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

    private void increment(int i){
        String melding = "Een succes bereikt";
        if(currentRoom.getItemID() == (1) && wood < 64){
            wood += i;
            System.out.println(melding);
        }
        else if(currentRoom.getItemID() == (2) && sand < 64){
            sand += i;
            System.out.println(melding);
        }
        else if(currentRoom.getItemID() == (3) && stone < 64){
            stone += i; 
            System.out.println(melding);
        }
        else if(currentRoom.getItemID() == (5) && iron < 32){
            iron += i;
            System.out.println(melding);
        }
        else if(currentRoom.getItemID() == (4) && diamond < 5){
            diamond  += i;
            System.out.println(melding);
            
        }
        else{ System.out.println("You can't carry this anymore");
        }

    }

    private void inventory(){
        HashMap<String, Integer> itemValue = new HashMap<String, Integer>();
        itemValue.put("wood", wood);
        itemValue.put("sand", sand);
        itemValue.put("stone", stone);
        itemValue.put("diamond", diamond);
        itemValue.put("iron", iron);
        itemValue.put("sticks", stick);
        itemValue.put("Wooden PickAxe", wood_PickAxe);
  
        for(String i : itemValue.keySet()){
            if(itemValue.get(i) > 0)
                System.out.println(i + " : "  + itemValue.get(i));
        }

        /*for(int i = 0; i < items.size(); i++){
        System.out.println(items.get(i) + " : " + show());
        }
         */
    }
    private void craft(Command command){
        if(!command.hasSecondWord()){
          System.out.println("What do you want to craft");
          return;   
        }
        String itemToCraft = command.getSecondWord();
        
        if(itemToCraft.equals("sticks")){
           if(wood >= 2){
           wood -= 2;
           stick += 4;
        } else{System.out.println("You cant craft this");
        }
        }
        else if(itemToCraft.equals("wood_PickAxe")){
         if(wood_PickAxe >= 1){
             System.out.println("You already got a Wooden Pickaxe");
            }
         else if(wood >= 3 && stick >= 2){
             wood -= 3;
             stick -= 2;
             wood_PickAxe += 1;
            }
            
             else{System.out.println("You cant craft this");
        }
    }
  
}
}


