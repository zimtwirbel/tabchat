package de.zimtwirbel.content.chat;

import de.zimtwirbel.rank.Rank;
import de.zimtwirbel.utils.NameUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ChatService implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Rank rank = Rank.getRank(event.getPlayer());
        String message = event.getMessage().replace("%", " Prozent");
        if (rank.isHigherThanOrEquals(Rank.ADMIN))
            message = ChatColor.translateAlternateColorCodes('&', message);
        String name = NameUtils.getPrefix(event.getPlayer().getUniqueId()) + " §7" + event.getPlayer().getName();
        event.setFormat(name + " §8»§7 " + message);
    }

}
