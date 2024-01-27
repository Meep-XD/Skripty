package me.meep_xd.skriptcreator.guis;

import me.meep_xd.skriptcreator.DataStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ScriptListGUI extends DataStorage {
    private static Inventory scriptlist;

    public Inventory getInventory() {
        return scriptlist;
    }

    public void setInventory(Inventory inv) {
        scriptlist = inv;
    }

    public void openInventory(Player player) {
        player.openInventory(scriptlist);
    }

    public void createInventory() {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Script List (Page: " + getPageNumber() + ")");

        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Back");
        item.setItemMeta(meta);
        inv.setItem(49, item);

        if (getPageNumber() > 1) {
            item = new ItemStack(Material.DARK_OAK_BUTTON);
            meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "Left");
            item.setItemMeta(meta);
            inv.setItem(48, item);
        }

        if (getPageNumber()*45 < getNames().toArray().length) {
            item = new ItemStack(Material.DARK_OAK_BUTTON);
            meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "Right");
            item.setItemMeta(meta);
            inv.setItem(50, item);
        }

        ItemStack[] items = new ItemStack[getNames().toArray().length];

        for (int i = 0; (i + (getPageNumber()-1)*45) < items.length && i < 45; i++) {
            item = new ItemStack(Material.BOOK);
            meta.setDisplayName(ChatColor.GREEN + getNames().get(i + (getPageNumber()-1)*45));
            item.setItemMeta(meta);
            inv.setItem(i, item);
        }

        setInventory(inv);
    }
}
