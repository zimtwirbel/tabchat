package de.zimtwirbel.configuration;

import de.zimtwirbel.registration.event.BukkitPluginEnableEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TabChatConfiguration {

    private final ApplicationEventPublisher eventPublisher;

    @Getter
    private JavaPlugin plugin;

    public void startBukkitPlugin(ApplicationContext context, JavaPlugin plugin) {
        this.plugin = plugin;
        this.eventPublisher.publishEvent(new BukkitPluginEnableEvent(context, plugin));
    }

}
