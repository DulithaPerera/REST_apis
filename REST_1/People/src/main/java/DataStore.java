import java.util.*;

//this class is a Singleton design pattern class
public class DataStore {

    private static DataStore instance = new DataStore();
    private Map<String, People> storedData = new HashMap<>();

    private DataStore () {
        storedData.put("dilantha", new People("dilantha", "student", 19, "dilantha" ) );
        storedData.put("mayantha", new People("mayantha", "doctor", 31, "mayantha") );
        storedData.put("rahula", new People("rahula", "nurse", 24, "rahula") );
        storedData.put("saman", new People("saman", "journalist", 40, "saman") );
    }

    public static DataStore getInstance(){
        return instance;
    }

    public People getDataRow(String name) {
        return storedData.get(name);
    }

    public String addPerson (String name, String job, int age, String password) {
        try {
            storedData.put(name, new People(name, job, age, password) );
            return "new person " + name + " was added successfully!";
        }
        catch (Exception e){
            return "Adding new person "+name+" failed";
        }
    }

    public String editPerson (String name, String job, int age, String password) {
        try{
            storedData.replace(name, new People(name, job, age, password) );
            return "editing  person "+name+" was successful";
        }
        catch (Exception e){
            return "editing person "+name+" failed";
        }
    }

}
