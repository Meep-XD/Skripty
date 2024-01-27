package me.meep_xd.skriptcreator.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuGUI {
    private static Inventory menu;

    public Inventory getInventory() {
        return menu;
    }

    public void setInventory(Inventory inv) {
        menu = inv;
    }

    public void openInventory(Player player) {
        player.openInventory(menu);
    }

    public void createInventory() {
        // Create the Menu GUI
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Menu");

        // Create the "Create Script" button
        ItemStack item = new ItemStack(Material.LEGACY_BOOK_AND_QUILL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Create Script");
        item.setItemMeta(meta);
        inv.setItem(3, item);

        // Create the "View Scripts" button
        item = new ItemStack(Material.BOOKSHELF);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "View Scripts");
        item.setItemMeta(meta);
        inv.setItem(4, item);

        // Create the "Close" button
        item = new ItemStack(Material.BARRIER);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Close");
        item.setItemMeta(meta);
        inv.setItem(5, item);

        // Set the Menu GUI inventory
        setInventory(inv);
    }
}
