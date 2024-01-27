package me.meep_xd.skriptcreator.listeners;

import me.meep_xd.skriptcreator.DataStorage;
import me.meep_xd.skriptcreator.guis.ScriptGUI;
import me.meep_xd.skriptcreator.guis.ScriptListGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.io.IOException;

public class ChatEvent extends DataStorage implements Listener {
    private ScriptListGUI scriptListGUI = new ScriptListGUI();
    private ScriptGUI scriptGUI = new ScriptGUI();

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) throws IOException {
        Player player = event.getPlayer();
        if (enterName.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            if (getFileName().equals(event.getMessage())) {
                player.sendMessage(prefix + "No changes made.");
                scriptListGUI.createInventory();
                enterName.remove(event.getPlayer().getUniqueId());
                scriptListGUI.openInventory(player);
            }
            else if (getNames().contains(event.getMessage())) {
                player.sendMessage(prefix + "File already exists!");
            }
            else {
                renameFile(ChatColor.stripColor(getFileName()), event.getMessage());
                player.sendMessage(prefix + "Changed " + getFileName() + " to " + event.getMessage());
                scriptListGUI.createInventory();
                enterName.remove(event.getPlayer().getUniqueId());
                scriptListGUI.openInventory(player);
            }
        }
        if (urlChat.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            if (event.getMessage().equalsIgnoreCase("cancel")) {
                urlChat.remove(event.getPlayer().getUniqueId());
                player.sendMessage(prefix + "Edit cancelled.");
                scriptGUI.createInventory();
            }

            if (writeFile(event.getMessage(), getFileName()) && urlChat.contains(event.getPlayer().getUniqueId())) {
                urlChat.remove(event.getPlayer().getUniqueId());
                writeFile(event.getMessage(), getFileName());
                player.sendMessage(prefix + "Edited & saved " + getFileName() + "!");
                player.performCommand("skript reload " + getFileName() + ".sk");
                scriptGUI.createInventory();
            }
            else {
                player.sendMessage(prefix + "Not a valid URL!");
            }
        }
    }
}
