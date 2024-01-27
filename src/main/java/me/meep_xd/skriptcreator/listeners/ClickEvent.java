package me.meep_xd.skriptcreator.listeners;

import me.meep_xd.skriptcreator.DataStorage;
import me.meep_xd.skriptcreator.guis.ConfirmGUI;
import me.meep_xd.skriptcreator.guis.MenuGUI;
import me.meep_xd.skriptcreator.guis.ScriptGUI;
import me.meep_xd.skriptcreator.guis.ScriptListGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClickEvent extends DataStorage implements Listener{
    private MenuGUI menu;
    private ScriptGUI scriptGUI = new ScriptGUI();
    private ScriptListGUI scriptListGUI = new ScriptListGUI();
    private ConfirmGUI confirmGUI = new ConfirmGUI();

    public ClickEvent() {
        menu = new MenuGUI();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) throws IOException {
        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null || event.getClickedInventory().equals(player.getInventory()) || event.getCurrentItem() == null) {
            //this is new??
            event.setCancelled(true);
            return;
        }

        // Cancel moving items and handle clicks in MenuGUI
        if (event.getInventory().equals(menu.getInventory())) {
            event.setCancelled(true);

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Create Script")) {
                File file = new File("plugins/Skript/scripts", "untitled.sk");
                for (int i = 1; i >= 1; i++) {
                    if (file.exists()) {
                        file = new File("plugins/Skript/scripts", "untitled(" + i + ").sk");
                    }
                    else {
                        file.createNewFile();
                        Path p = Paths.get(file.getPath());
                        String name = p.getFileName().toString();
                        player.sendMessage("Created new script '" + name + "'!");
                        setFileName(name);
                        scriptListGUI.createInventory();
                        scriptListGUI.openInventory(player);
                        return;
                    }
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "View Scripts")) {
                scriptListGUI.createInventory();
                scriptListGUI.openInventory(player);
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Close")) {
                player.closeInventory();
            }
        }

        // Cancel moving items and handle clicks in ScriptGUI
        if (event.getInventory().equals(scriptGUI.getInventory())) {
            event.setCancelled(true);

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Set Name")) {
                player.sendMessage(prefix + ChatColor.GREEN + "Please enter the name for the script:");
                enterName.add(event.getWhoClicked().getUniqueId());
                player.closeInventory();
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Enable/Disable")) {
                if (getFileName().startsWith("-")) {
                    player.performCommand("skript enable " + getFileName());
                    setFileName(getFileName().substring(1));
                }
                else {
                    player.performCommand("skript disable " + getFileName());
                    setFileName("-" + getFileName());
                }
                scriptGUI.createInventory();
                scriptGUI.openInventory(player);
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Delete")) {
                confirmGUI.createInventory();
                confirmGUI.openInventory(player);
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Edit")) {
                player.closeInventory();
                urlChat.add(event.getWhoClicked().getUniqueId());
                copyContents(getFileName());
                player.sendMessage(prefix + ChatColor.GREEN + "Copied '" + getFileName() + "' contents to clipboard!\n" +
                        prefix + "Edit the script by clicking on this link: " + ChatColor.GOLD + "https://paste.helpch.at/.\n" + ChatColor.GREEN +
                        prefix + "Once done, click 'Save' & 'Just Text'.\n" +
                        prefix + "Copy and paste the saved link below or type 'cancel' to stop editing.:");
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Back")) {
                menu.openInventory(player);
            }
        }

        // Cancel moving items and handle clicks in ScriptListGUI
        if (event.getInventory().equals(scriptListGUI.getInventory())) {
            event.setCancelled(true);

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Right")) {
                setPageNumber(getPageNumber()+1);
                scriptListGUI.createInventory();
                scriptListGUI.openInventory(player);
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Left")) {
                if (getPageNumber() != 1) {
                    setPageNumber(getPageNumber()-1);
                }
                scriptListGUI.createInventory();
                scriptListGUI.openInventory(player);
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Back")) {
                menu.openInventory(player);
            }

            if (event.getCurrentItem().getType().equals(Material.BOOK)) {
                setFileName(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
                scriptGUI.createInventory();
                scriptGUI.openInventory(player);
            }
        }

        if (event.getInventory().equals(confirmGUI.getInventory())) {
            event.setCancelled(true);

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "CONFIRM")) {
                deleteFile(getFileName());
                player.sendMessage(prefix + ChatColor.GREEN + "'" + getFileName() + "' successfully deleted!");
                scriptListGUI.createInventory();
                scriptListGUI.openInventory(player);
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "GO BACK")) {
                scriptGUI.openInventory(player);
            }
        }
    }
}
