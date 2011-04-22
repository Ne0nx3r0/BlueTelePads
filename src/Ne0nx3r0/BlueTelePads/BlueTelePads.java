package Ne0nx3r0.BlueTelePads;

import org.bukkit.util.config.Configuration;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;

public class BlueTelePads extends JavaPlugin{
    private final BlueTelePadsPlayerListener playerListener = new BlueTelePadsPlayerListener(this);
    
    public static PermissionHandler Permissions;

    public int MAX_DISTANCE = 500;
    public boolean USE_PERMISSIONS = false;
    public boolean OP_ONLY = false;

    public void onDisable(){
        System.out.println("[BlueTelePads] Disabled");
    }

    public void onEnable(){
        Configuration config = getConfiguration();

        //set default values if necessary
        if(config.getInt("max_telepad_distance",-1) == -1){
            System.out.println("[BlueTelepads] Creating default config file...");

            MAX_DISTANCE = 150;
            config.setProperty("max_telepad_distance",MAX_DISTANCE);
            config.setProperty("use_permissions",USE_PERMISSIONS);
            config.setProperty("op_only",OP_ONLY);

            config.save();
        }

        MAX_DISTANCE = config.getInt("max_telepad_distance",MAX_DISTANCE);
        USE_PERMISSIONS = config.getBoolean("use_permissions",USE_PERMISSIONS);
        OP_ONLY = config.getBoolean("op_only",OP_ONLY);

        if(USE_PERMISSIONS){
            Plugin perm = this.getServer().getPluginManager().getPlugin("Permissions");
            if(perm != null){
                System.out.println("[BlueTelePads] Permissions integration enabled");
                this.Permissions = ((Permissions) perm).getHandler();
            }else{
                System.out.println("[BlueTelePads] Permissions integration could not be enabled!");
            }
        }

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);

        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println("[BlueTelePads] "+pdfFile.getName() + " version " + pdfFile.getVersion() + " ENABLED" );
    }
}