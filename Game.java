import java.util.HashMap;
import java.util.ArrayList;
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
 *  Continents, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class Game 
{
    private boolean canPickUp;
    private Parser parser;
    private Continent currentContinent;
    private Item item; 
    private boolean rich;  
    private int money;
    boolean isfound;
    private CommandWords commandwords;
    private Continent previousContinent;
    public ArrayList<Item> Backpack;
    private int totalWeight;
    private final int MaxWeight;
    boolean home;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createContinents();
        parser = new Parser();
        rich = true;
        money = 3000;
        isfound = false;
        commandwords = new CommandWords();
        /*ArrayList<Item>*/Backpack = new ArrayList<Item>();
        canPickUp = true;
        MaxWeight = 30;
        home = true;
    }
    
    private void checkCapicity(){
       totalWeight = 0;
        for(Item i : Backpack){
           totalWeight += i.getItemWeight();
        }
        if(totalWeight > MaxWeight){
           canPickUp = false;
        }
        else{
           canPickUp = true;
        }
    }

    /**
     * Create all the Continents and link their exits together.
     */
    private void createContinents()
    {
        Continent Australia, Antarctica, SouthAmerica, NorthAmerica, Asia, Europe, Africa;
        
        Item Apple, Bananas, Knife, BlueJ, Ledder, Yeezys, Jacket, Gun, Noodles, Monkey;
        
        NPC Beecher;
        
        Beecher = new NPC();
        Apple   = new Item  ("Apple", "A juicy red apple", 3);
        Bananas = new Item  ("Bananas","A sweet Bananas, tastymasty",7);
        Knife   = new Item  ("Knife","A sharp knie", 5);
        BlueJ   = new Item  ("BlueJ","The Objects first with BlueJ book, your holy bible", 13);
        Ledder  = new Item  ("Ledder", "A heavy ledder, you can reach higher places with this gadget", 20);
        Yeezys  = new Item  ("Yeezys", "Yeezys?! Damn, how did you cop those boiii?", 8);
        Jacket  = new Item  ("Jacket", "A boring jacket", 14);
        Gun     = new Item  ("Gun", "Be carefull with that gun dude.", 9);
        Noodles = new Item  ("Noodles", "Mhhhh YumYum Noodles...", 2); 
        Monkey = new Item ("Monkey","a Monkey, would look better if it wears a hat", 19);
        // create the Continents
        Australia = new Continent("Australia, are you tough enough? " + "\n" + "if not you can flee north to Asia, west to Africa" + "\n"
        + "or south to the Antarctica", Apple);
        Antarctica = new Continent("the Antarctica, nothing but pengiuns" + "\n" +
        "if you're afraid of them just swim east to Australia, north to Africa or west to South America", Bananas);
        SouthAmerica = new Continent("South America, lucky there's not a wall to the US yet" + "\n" + 
        "So you can still go there by typing go north, or east to Africa and south to the Antarctica", Knife);
        NorthAmerica = new Continent("North America, don't stay here or you'll get fat " + "\n" + 
        "it's possible to take off east to europe, or south to South America", BlueJ);
        Asia = new Continent("Asia, got your chopsticks with you?" + "\n" +
        "If u don't u can still go west, back to the Europe", Ledder);
        Europe = new Continent("Europe, but you can't find motivation right here" + "\n" +
        "you can go east to Asia, or go north to the cold Antarctica," + "\n" +
        "west to the US or south and explore Africa", Yeezys);
        Africa = new Continent("Africa, as long as you're not hungry ur fine right here" + "\n" + 
        "but if you don't want to starve just go south to the Antarctica, west to South America, north to get home: Europe" + "\n" + 
        "or east to visit Asia", Jacket);
        // initialise Continent exits
        
        
        //Items to continent
        Australia.addItem("Apple", Apple);
        Australia.addItem("BlueJ", BlueJ);
                      
        Antarctica.addItem("Ledder", Ledder);
        Antarctica.addItem("Jacket", Jacket);
                       
        SouthAmerica.addItem("Apple", Apple);
        
        Africa.addItem("Bananas", Bananas);
        
        NorthAmerica.addItem("Yeezys", Yeezys);
        NorthAmerica.addItem("Bananas", Bananas);
        NorthAmerica.addItem("Apple", Apple);                
        
        Asia.addItem("Noodles", Noodles);
               
        Europe.addItem("Gun", Gun);
        Europe.addItem("Apple", Apple);
        Europe.addItem("Bananas", Bananas);
        
        
        //Exits to continent
        Antarctica.addExit("north", Africa);
        Antarctica.addExit("east", Australia);
        Antarctica.addExit("west", SouthAmerica);
        
        SouthAmerica.addExit("north", NorthAmerica);
        SouthAmerica.addExit("east", Africa);
        SouthAmerica.addExit("south", Antarctica);
        
        NorthAmerica.addExit("east", Europe);
        NorthAmerica.addExit("south", SouthAmerica);
        
        Asia.addExit("west", Europe);
        
        Europe.addExit("north", Antarctica);
        Europe.addExit("east", Asia);
        Europe.addExit("south", Africa);
        Europe.addExit("west", NorthAmerica);
        
        Africa.addExit("north", Europe);
        Africa.addExit("east", Asia);
        Africa.addExit("south", Antarctica);
        Africa.addExit("west", SouthAmerica);
        
        Australia.addExit("north", Asia);
        Australia.addExit("south", Antarctica);
        Australia.addExit("west", Africa);
        
        NorthAmerica.putNpcs("Beecher", Beecher);
        
        currentContinent = Europe;  // start game at home
        
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
           if(isfound==true){
            System.out.println("YEEEYEEYES Australia! guess what you've found? " +  "\n" + "nothing, but it's pretty chill here so" + "\n" 
            + "you just decide to live here and breed some Kangaroos or whatever - you won!");
            finished=true;
        }
        else if(rich == true){
            Command command = parser.getCommand();
            finished = processCommand(command);
            
        }
        
        else{
         System.out.println("I'm sorry but you can't travel without any money...looks like you have to die here");
         finished= true;
        }
        }
        System.out.println("Thank you for playing.  Gut Kick.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Wow, exams are almost there, can't find any motivation");
        System.out.println("Do you think you can travel the world and find it somewhere?");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        
        printLocationInfo();
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
        if (commandWord.equals("help")){
            printHelp();
        }
            
        else if (commandWord.equals("go")){
            goContinent(command);
            System.out.println("you still have " + money + "€ left");
        }
        
        else if (commandWord.equals("quit")){
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("look")){
            look();
        }   
        else if(commandWord.equals("dig")){
            System.out.println("You digged a hole but didn't find anything useful..");
        }
        else if(commandWord.equals("ask")){
            ask();
        }
        else if(commandWord.equals("back")){
            back();
        }
        else if(commandWord.equals("take")){
            takeItem(command);
        }
        else if(commandWord.equals("drop")){
            dropItem(command);
        }
        else if(commandWord.equals("items")){
            getAllCarriedItems();
        }
        return wantToQuit;
    }
    // implementations of user commands:        
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("not motivated yet.. will you ever be?");
        System.out.println("travel somewhere you haven't been before");
        System.out.println("just by typing go and the cardinal direction like 'go west'");
        System.out.println("Your Commandwords are");
        commandwords.commandSum();
        printLocationInfo();
        
    }
    
    private void ask(){
        if(currentContinent.getAllNpcAnsw() == ""){
            System.out.println("There is no one around to ask");
        }
        else{
        System.out.println(currentContinent.getAllNpcAnsw());
       }
    }
    
    private void getAllCarriedItems(){
        System.out.print("At the moment, you are carrying: " + "\n");
        for(Item i : Backpack){
           System.out.println(i.getItemName() + ", it's weigth is " + i.getItemWeight());
        }
        System.out.println("The maximum amount you can carry is " + MaxWeight + "\n" + 
        "and you currently have " + totalWeight);
        
    }
    
    private void dropItem(Command command){
        Item removedItem = null;
        if(!command.hasSecondWord()){
           System.out.println("drop what?");
           }
        else if(command.getSecondWord().equals("all")){
        for(Item i : Backpack){
         currentContinent.getItemMap().put(i.getItemName(), i);
         
        }
        Backpack.clear();
        System.out.println("You just dropped all you have...why's that?");
        }
        else{
        String chooseToDrop = command.getSecondWord();
        for(Item i : Backpack){
         if(chooseToDrop.equals(i.getItemName())){
             removedItem = i;
             String itemKey = removedItem.getItemName();
             
             currentContinent.getItemMap().put(itemKey, i);
             System.out.println("You've dropped the item " + itemKey);
            }
         else{
            
            }
        }
        if(removedItem == null){
           System.out.println("You don't carry any Items like that"); 
        }
        else{
         Backpack.remove(removedItem);
        }
       }
       checkCapicity();
    }
    
    private void takeItem(Command command){ 
      if(canPickUp == true){
        if(!command.hasSecondWord()){
           System.out.println("take what?");
           }
        else{
        String ItemSearched = command.getSecondWord();
        
        HashMap <String, Item> ItemsAvailable = currentContinent.getItemMap();
      
        Item takenItem = ItemsAvailable.get(ItemSearched);
 
        if(takenItem == null){
         System.out.println("There is no such thing around here, you'll have to look somewhere else");
        }
        else {
          Backpack.add(takenItem);
          System.out.println("You did now collect the item " + takenItem.getItemName());
          currentContinent.getItemMap().remove(ItemSearched);
        }
        
      }
      
      }
      else{
      int tooMuch = -MaxWeight + totalWeight;
      System.out.println("Your Backpack is already full!" + "\n" + "you carry " + tooMuch + 
      " above your capicity, look what weighs how much with 'items' " + "\n" + "or drop an Item with the drop (all) command");
     }
      checkCapicity();
    }   
    
    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new Continent, otherwise print an error message.
     */
    private void goContinent(Command command) 
    { 
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current Continent.
        HashMap<String, Continent> possibleExits = currentContinent.getExits();
        
        previousContinent = currentContinent; 
        Continent nextContinent = possibleExits.get(direction);
        
        if (nextContinent == null) {
            System.out.println("There is nothing but salty Water");
        }
       
        
        /*else {
            
            if(nextContinent.getDescription().equals("In Australia, are you tough enough? " + "\n" + "if not you can flee north to Asia, west to Africa" + "\n"
            + "or south to the Antarctica")){
                money = money- 400;
                isfound = true;
                currentContinent = nextContinent;
                if(money == 0){
                 rich = false;}
        }*/
        else{ 
            money = money - 400;
            currentContinent = nextContinent;
            printLocationInfo();
            home = false;
            if(money == 0){
              rich = false;
            }
        }
    }

    /** 
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
   
    private void printLocationInfo(){
        System.out.println("You are currently in " + currentContinent.getDescription() + "\n");
        if(currentContinent.getAllItems() != ""){
        System.out.println("The items you can find around here are " + currentContinent.getAllItems() + "\n");
     }
        System.out.println("possible exits are: " + currentContinent.getExitsAvailable() + "\n");
     if(currentContinent.getAllNpcs() != ""){
         System.out.println("the following people can be met around here: " + currentContinent.getAllNpcs() + "\n");
        }
    }
    
    private void look()
    {
        System.out.println("\n You can pick up these items:\n " + "\n" 
        + currentContinent.getAllItemDesc() + "\n" + currentContinent.getAllNpcDesc());
    }
    
    private void back()
    {
        if(previousContinent != null){
        currentContinent = previousContinent;
        printLocationInfo();
     }
     else if(home==true){
         System.out.println("Back? How about going somewhere first");
        }
     else{
        System.out.println("You can't go back twice...");
     }
     
    }
}

