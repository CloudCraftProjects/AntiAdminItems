package tk.booky.antiadminitems;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import tk.booky.antiadminitems.listener.BlockListener;
import tk.booky.antiadminitems.listener.MiscListener;

public final class AntiAdminItemsMain extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new MiscListener(), this);
    }
}
