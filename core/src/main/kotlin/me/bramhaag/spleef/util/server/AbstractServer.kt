package me.bramhaag.spleef.util.server

import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.spleef.util.AbstractConfig

abstract class AbstractServer {
    abstract fun getOnlinePlayers(): List<AbstractPlayer>
    abstract fun sendMessage(receiver: AbstractPlayer, message: String)
    abstract fun getConfig() : AbstractConfig
}