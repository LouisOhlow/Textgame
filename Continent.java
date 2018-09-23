import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Continent 
{
    public String description;
    public String detailedDescription;
    private Item item; 
    private HashMap<String, Continent> exits;
    private HashMap<String, Item> items;
    private HashMap<String, NPC> npcs;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Continent(String description, Item item) 
    {
        this.description = description;
        this.exits = new HashMap<String, Continent>();
        this.detailedDescription = description;
        this.item = item; 
        this.items = new HashMap<String, Item>(); 
        this.npcs = new HashMap<String, NPC>();
    }
    
    public void putNpcs(String name, NPC npc){
        npcs.put(name, npc);
    }
    
    public HashMap getItemMap(){
       return items;
    }
    
    public Item getItem()
    {
        return item; 
    }
    
    public void setDetailedDescription(String text){
       detailedDescription += "\n" + text;
    }
    
    public HashMap<String, Continent> getExits(){
      return exits;
    }
    
    /**
     * Define the exits of this Continent.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void addExit(String direction, Continent whereToGo) 
    {
        exits.put(direction, whereToGo);
    }
    
    public String getExitsAvailable(){
        String result = "";
        
        for(String key : exits.keySet()){
            result += key + " ";
        }
        return result;
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public void addItem(String itemName, Item item)
    {
        items.put(itemName, item);
    }
    
    public String getItem(String itemName)
    {
        return items.get(itemName).getItemName();
    }
        
    public String getAllItems()
    {
        String result = ""; 
        for(Item i : items.values())
        {
            result += i.getItemName() + "\n";
        }
        return result;
    }
    
    public String getAllItemDesc()
    {
        String result = ""; 
        for(Item i : items.values())
        {
            result += i.getItemDesc() + "\n";
        }
        
        return result; 
    }
    
    public String getAllNpcs(){
       String result = "";
       for(NPC i : npcs.values()){
           result += i.getName() + ", ";
        }
       return result;
    }
    
    public String getAllNpcDesc(){
       String result = ""; 
        for(NPC i : npcs.values())
        {
            result += i.getDescription() + "\n";
        }
        
        return result; 
    }
    
    public String getAllNpcAnsw(){
       String result = ""; 
        for(NPC i : npcs.values())
        {
            result += i.getAnswer();
        }
        
        return result; 
    }
}
