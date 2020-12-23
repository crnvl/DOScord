package doscord.config;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.*;
import java.util.Date;
import java.util.Properties;

public class Config {

    private static Properties properties;
    static File file = new File("save.dc");

    public static void load(){
        properties = new Properties();
        try {
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            fileNotFoundAction(file);
            load();
        }
    }

    public static boolean isReal(String key) {
        return properties.getProperty(key) != null;
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static void save(String hash, String name) {
        properties.put(hash, name);
    }

    public static void delete(String hash) {
        properties.remove(hash);
    }

    private static void fileNotFoundAction(File f){
        try {
            properties.store(new FileOutputStream(f), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String backup() {
        StringBuilder completed = new StringBuilder();
        System.out.println("[INFO] Starting backup...");
        try {
            properties.store(new FileOutputStream(file), "");
            completed.append("S1");
        } catch (IOException e) {
            e.printStackTrace();
            completed.append("S0");
        }
        Date date = new Date();
        try {
            FileUtils.copyFile(file, new File("backups/backup" + date.getTime() + ".dc"));
            System.out.println("[INFO] Saved " + "backups/backup" + date.getTime() + ".dc");
            completed.append("B1");
        } catch (IOException e) {
            e.printStackTrace();
            completed.append("B0");
        }
        return completed.toString();
    }

    public static void backupService() {
        Timer save = new Timer(1800 * 1000, e -> {
            System.out.println(backup());
        }
        );
        save.start();
    }

}
