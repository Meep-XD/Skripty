package me.meep_xd.skriptcreator.guis;

import me.meep_xd.skriptcreator.DataStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ScriptGUI extends DataStorage {
    private Inventory script;

    public Inventory getInventory() {
        return script;
    }

    public void setInventory(Inventory inv) {
        script = inv;
    }

    public void openInventory(Player player) {
        player.openInventory(script);
    }

    public void createInventory() {
        List<String> lore = new ArrayList<String>();

        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Editor");

        ItemStack item = new ItemStack(Material.LEGACY_BOOK_AND_QUILL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Set Name");
        lore.add(ChatColor.GREEN + "Name: " + getFileName());
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(12, item);

        //maybe change color concrete depending on enable/disable
        if (getFileName().startsWith("-")) {
            item = new ItemStack(Material.RED_CONCRETE);
            meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "Enable/Disable");
            lore.add(ChatColor.RED + "Disabled");
        }
        else {
            item = new ItemStack(Material.GREEN_CONCRETE);
            meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "Enable/Disable");
            lore.add(ChatColor.GREEN + "Enabled");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(13, item);

        item = new ItemStack(Material.COMMAND_BLOCK);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Edit");
        item.setItemMeta(meta);
        inv.setItem(14, item);

        item = new ItemStack(Material.TNT);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Delete");
        item.setItemMeta(meta);
        inv.setItem(22, item);

        item = new ItemStack(Material.BARRIER);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Back");
        item.setItemMeta(meta);
        inv.setItem(49, item);

        setInventory(inv);
    }
}
