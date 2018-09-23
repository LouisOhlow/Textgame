/**
 * Write a description of class Items here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    private String  itemName, itemDesc;
    private int     weight; 

    /**
     * Creates the item.
     */
    public Item(String itemName, String itemDesc, int weight)
    {
        this.itemName   = itemName; 
        this.itemDesc   = itemDesc;
        this.weight     = weight; 
    }
    
    public String getItemDesc()
    {
        return itemDesc;   
    }
    
    public String getItemName()
    {
        return itemName;  
    }
    
    public int getItemWeight()
    {
        return weight; 
    }
}
