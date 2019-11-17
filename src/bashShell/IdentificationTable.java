package bashShell;
import java.util.*;

public class IdentificationTable {

    private HashMap<String, Attribute> table;
    private int level;

    public IdentificationTable(){
        table = new HashMap<>();
    }

    /**
     * Insert new global variable into identification table, or updates it if it's already there
     * @param id the id to be inserted
     * @param attribute the id's corresponding attribute (type)
     */
    public void enter(String id, Attribute attribute){
        attribute.setLevel(0);
        table.put(id, attribute);
    }

    /**
     * Insert a new scoped variable into the identification table, or updates it if it's already there
     * Insert at the current scope
     * @param id the id to be inserted
     * @param attribute the id's corresponding attribute (type) - scope level is added here
     */
    public void enterScoped(String id, Attribute attribute) {
        attribute.setLevel(this.level);
        table.put(id, attribute);
    }

    /**
     * Get entry of a certain ID
     * @param id The id to be retrieved
     * @return the corresponding attribute
     */
    public Attribute retrieve(String id){
        return table.get(id);
    }

    /**
     * Open a new scope level
     */
    public void openScope(){
        this.level++;
    }

    /**
     * Close current scope level
     * Will remove all variables of that scope level
     */
    public void closeScope(){
        ArrayList<String> keysToRemove = new ArrayList<>();
        for (String key : table.keySet()) {
            if(table.get(key).getLevel() == this.level){
                keysToRemove.add(key);
            }
        }
        for (String key : keysToRemove){
            table.remove(key);
        }
        this.level --;
    }
}
