package me.bramhaag.spleef

import me.bramhaag.minigameframework.game.AbstractGame
import me.bramhaag.minigameframework.game.GameMode
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.spleef.phases.LobbyPhase
import me.bramhaag.spleef.phases.SpleefPhase
import me.bramhaag.spleef.phases.WinnerPhase
import me.bramhaag.spleef.util.ChatColor
import me.bramhaag.spleef.util.server.Server

class SpleefGame : AbstractGame(MIN_PLAYERS, MAX_PLAYERS) {
    override fun init(player: AbstractPlayer) {
        val lobbyPhase = createPhase<LobbyPhase>()
        val spleefPhase = createPhase<SpleefPhase>()
        val winnerPhase = createPhase<WinnerPhase>()

        lobbyPhase.nextPhase = spleefPhase
        spleefPhase.nextPhase = winnerPhase

        setActive(lobbyPhase)

        Server.instance.getOnlinePlayers().filter { it.getUUID() != player.getUUID() }.forEach {
            it.sendMessage("${ChatColor.INFO_COLOR}${player.getName()} started a Spleef game! Type /spleef to join.")
        }
    }

    companion object {
        val GAMEMODE = GameMode("Spleef", SpleefGame::class.java)

        val MIN_PLAYERS = Server.instance.getConfig().getInt("min-players", 1)
        val MAX_PLAYERS = Server.instance.getConfig().getInt("max-players", 4)
    }
}
