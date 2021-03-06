package si.psrcek.clickableChatNames;


import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import si.psrcek.clickableChatNames.adapters.ClickableChatPacketAdapter;
import si.psrcek.clickableChatNames.commands.UserCommand;
import si.psrcek.clickableChatNames.listeners.InventoryClickListener;
import si.psrcek.clickableChatNames.misc.InventoryRegistry;
import si.psrcek.clickableChatNames.misc.MiscRegistry;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;

public class Main extends JavaPlugin implements Listener {
	public void onEnable() {
		MiscRegistry.init();
		InventoryRegistry.init();
		
		registerListeners();
		registerCommands();
		
		MiscRegistry.protocolManager.addPacketListener(new ClickableChatPacketAdapter(this, ListenerPriority.HIGH, PacketType.Play.Server.CHAT));
		
		getLogger().info("Enabled!");
	}
	
	public void onDisable() {
		getLogger().info("Disabled!");
	}

	public void registerListeners() {
		getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
	}

	public void registerCommands() {
		getCommand("user").setExecutor(new UserCommand());
	}
}
