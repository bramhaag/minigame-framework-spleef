package me.bramhaag.spleef

import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.spigot.SpigotPlayer
import me.bramhaag.spleef.util.AbstractConfig
import me.bramhaag.spleef.util.server.AbstractServer
import org.bukkit.Bukkit

class SpigotServer(plugin: SpleefPlugin) : AbstractServer() {
    private val config = SpigotConfig(plugin.config)

    override fun getOnlinePlayers(): List<AbstractPlayer> {
        return Bukkit.getOnlinePlayers().mapNotNull { AbstractPlayer.players[it.uniqueId] }
    }

    override fun sendMessage(receiver: AbstractPlayer, message: String) {
        Bukkit.getServer().getPlayer(receiver.getUUID())?.sendMessage(message)
    }

    override fun getConfig(): AbstractConfig {
        return config
    }
}