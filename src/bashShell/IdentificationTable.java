package bashShell;
import java.util.*;

public class IdentificationTable {

    private HashMap<String, Attribute> table;
    private int level;

    public IdentificationTable(){
        table = new HashMap<>();
    }

    /**
     * Inserts new variable into identification table, or updates it if it's already there
     * @param id the id to be inserted
     * @param attribute the id's corresponding attribute (type)
     */
    public void enter(String id, Attribute attribute){
        attribute.setLevel(0);
        table.put(id, attribute);
    }

    public void enterScoped(String id, Attribute attribute) {
        attribute.setLevel(this.level);
        table.put(id, attribute);
    }

    public Attribute retrieve(String id){
        return table.get(id);
    }

    public void openScope(){
        this.level++;
    }

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
