package de.zimtwirbel;

import de.zimtwirbel.application.TabChatSpringApplication;
import de.zimtwirbel.configuration.TabChatConfiguration;
import de.zimtwirbel.utils.NameUtils;
import lombok.Getter;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class TabChat extends JavaPlugin {

    @Getter
    private static AnnotationConfigApplicationContext context;

    @Override
    public void onEnable() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null)
            NameUtils.setLuckPerms(provider.getProvider());
        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
        context = new AnnotationConfigApplicationContext(TabChatSpringApplication.class);
        TabChatConfiguration tabChatConfiguration = context.getBean(TabChatConfiguration.class);
        tabChatConfiguration.startBukkitPlugin(context, this);
        context.registerShutdownHook();
    }
}
