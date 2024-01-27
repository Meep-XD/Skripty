package me.meep_xd.skriptcreator;

import me.meep_xd.skriptcreator.commands.SkriptyCommand;
import me.meep_xd.skriptcreator.guis.MenuGUI;
import me.meep_xd.skriptcreator.listeners.ChatEvent;
import me.meep_xd.skriptcreator.listeners.ClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkriptCreator extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info(ChatColor.GREEN + "Skripty has been enabled!");

        getCommand("skripty").setExecutor(new SkriptyCommand());

        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);

        MenuGUI menu = new MenuGUI();
        menu.createInventory();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
