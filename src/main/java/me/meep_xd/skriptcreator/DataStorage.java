package me.meep_xd.skriptcreator;

import org.bukkit.ChatColor;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DataStorage {
    public static HashMap<String, String> fileName = new HashMap<>();
    public static List<UUID> enterName = new ArrayList<>();
    public static List<UUID> urlChat = new ArrayList<>();
    public static int page = 1;
    public static String prefix = ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "[Skripty] " + ChatColor.RESET;

    public void setPageNumber(int p) {
        page = p;
    }
    public int getPageNumber() {
        return page;
    }

    public void setFileName(String name) {
        fileName.put("file", name);
    }
    public String getFileName() {
        return fileName.get("file");
    }

    //get files
    public List<String> getNames() {
        List<String> names = new ArrayList<String>();
        File folder = new File("plugins/Skript/scripts");

        for (File file : folder.listFiles()) {
            if (file.getName().endsWith("sk")) {
                names.add(file.getName().replace(".sk", ""));
            }
        }
        return names;
    }

    public void renameFile(String f, String name) {
        File file = new File("plugins/Skript/scripts/", f + ".sk");
        File newFile = new File("plugins/Skript/scripts/", name + ".sk");

        file.renameTo(newFile);
    }

    public Boolean writeFile(String url, String f) {
        try {
            URL u = new URL(url);

            BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
            String contents;

            PrintWriter file = new PrintWriter("plugins/Skript/scripts/" + f + ".sk");

            file.print("");

            while ((contents = in.readLine()) != null) {
                file.println(contents);
            }
            in.close();
            file.close();

            return true;
        } catch (IOException e){
            return false;
        }
    }

    public void copyContents(String f) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(("plugins/Skript/scripts/" + f + ".sk")));

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while ((line = file.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            file.close();

            StringSelection stringSelection = new StringSelection(stringBuilder.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String f) {
        File file = new File("plugins/Skript/scripts/" + f + ".sk");
        file.delete();
    }
}
