package Ne0nx3r0.BlueTelePads;

import org.bukkit.util.config.Configuration;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public class BlueTelePads extends JavaPlugin{
    private final BlueTelePadsPlayerListener playerListener = new BlueTelePadsPlayerListener(this);

    public void onDisable(){
        System.out.println("[BlueTelePads] Disabled");
    }

    public void onEnable(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);

        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println("[BlueTelePads] "+pdfFile.getName() + " version " + pdfFile.getVersion() + " ENABLED" );
    }
}