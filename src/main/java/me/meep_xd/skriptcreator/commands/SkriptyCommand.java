package me.meep_xd.skriptcreator.commands;

import me.meep_xd.skriptcreator.DataStorage;
import me.meep_xd.skriptcreator.guis.MenuGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkriptyCommand extends DataStorage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("skripty")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + ChatColor.RED + "You cannot execute this command from the console!");
                return true;
            }
            if (sender.hasPermission("skripty.use")) {
                Player player = (Player) sender;
                MenuGUI menu = new MenuGUI();
                menu.openInventory(player);
                return true;
            }
        }
        return false;
    }
}
