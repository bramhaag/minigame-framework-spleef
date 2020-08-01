package me.bramhaag.spleef

import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.spleef.util.AbstractConfig
import me.bramhaag.spleef.util.server.AbstractServer

class NukkitServer(private val plugin: SpleefPlugin) : AbstractServer() {
    private val config = NukkitConfig(plugin.config)

    override fun getOnlinePlayers(): List<AbstractPlayer> {
        return plugin.server.onlinePlayers.mapNotNull { AbstractPlayer.players[it.key] }
    }

    override fun sendMessage(receiver: AbstractPlayer, message: String) {
        plugin.server.getPlayer(receiver.getUUID()).ifPresent { it.sendMessage(message) }
    }

    override fun getConfig(): AbstractConfig {
        return config
    }

}