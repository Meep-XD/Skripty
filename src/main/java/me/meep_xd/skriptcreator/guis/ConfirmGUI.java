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

public class ConfirmGUI extends DataStorage {
    private static Inventory confirm;
    public Inventory getInventory() {
        return confirm;
    }

    public void setInventory(Inventory inv) {
        confirm = inv;
    }

    public void openInventory(Player player) {
        player.openInventory(confirm);
    }

    public void createInventory() {
        List<String> lore = new ArrayList<>();

        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "" + ChatColor.BOLD + "Confirm Menu");

        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "CONFIRM");
        lore.add(ChatColor.RED + "(Delete script)");
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(11, item);

        item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "GO BACK");
        lore.add(ChatColor.GREEN + "(Keep script)");
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(15, item);

        for (int i = 0; i < 27; i++) {
            if (i != 11 && i != 15) {
                item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                meta = item.getItemMeta();
                meta.setDisplayName(null);
                item.setItemMeta(meta);
                inv.setItem(i, item);
            }
            else {
                continue;
            }
        }

        setInventory(inv);
    }
}
