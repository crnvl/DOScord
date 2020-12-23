package doscord.tools;

import java.io.*;
import java.util.Properties;

public class Screen {

    private static Properties properties;
    private static String user;

    public Screen(String userId) {
        user = userId;
        properties = new Properties();
    }

    public void load(){
        File file = new File("screens/" + user + ".dc");
        try {
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            fileNotFoundAction(file);
            load();
        }
    }

    private void fileNotFoundAction(File f){
        try {
            properties.store(new FileOutputStream(f), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String hash, String name) {
        properties.put(hash, name);
    }

    public boolean isReal(String key) {
        return properties.getProperty(key) != null;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public void close() {
        File file = new File("screens/" + user + ".dc");
        try {
            properties.store(new FileOutputStream(file), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
