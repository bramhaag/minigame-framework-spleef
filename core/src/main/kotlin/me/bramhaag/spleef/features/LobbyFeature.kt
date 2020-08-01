package me.bramhaag.spleef.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.game.GameJoinEvent
import me.bramhaag.minigameframework.event.events.game.GameLeaveEvent
import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.minigameframework.util.Tick
import me.bramhaag.spleef.SpleefGame
import me.bramhaag.spleef.util.ChatColor
import me.bramhaag.spleef.util.server.Server

class LobbyFeature : AbstractFeature() {

    private val startDelay = Tick.SECONDS.toTicks(30).toInt()
    private val broadcastAt = intArrayOf(20, 15, 10, 5, 4, 3, 2, 1)

    private var starting = false
    private var currentTick = 0
    private var index = 0

    override fun enable() {
        currentTick = startDelay
    }

    override fun tick() {
        if(starting) {
            currentTick--

            val secondsLeft = Tick.SECONDS.fromTicks(currentTick).toInt()

            if(broadcastAt.size - 1 >= index && broadcastAt[index] == secondsLeft) {
                phase.game.broadcastMessage("${ChatColor.INFO_COLOR}The game is starting in $secondsLeft second(s)!")
                index++
            }

            if (currentTick <= 0) {
                phase.game.endPhase()
            }
        }
    }

    @GameEvent
    fun onJoin(e: GameJoinEvent) {
        val players = phase.game.players.map {  it.getUUID() }.toList()

        Server.instance.getOnlinePlayers().filter { !players.contains(it.getUUID()) }.forEach {
            Server.instance.sendMessage(it, "${ChatColor.INFO_COLOR}${e.player.getName()} is playing Spleef! Type ${ChatColor.COMMAND_COLOR}/spleef ${ChatColor.INFO_COLOR} to join!")
        }

        phase.game.broadcastMessage("${ChatColor.INFO_COLOR}${e.player.getName()} has joined (${phase.game.players.size + 1}/${phase.game.maxPlayers})")
        e.player.sendMessage("${ChatColor.INFO_COLOR}Welcome to Spleef. In this game you win by destroying the blocks underneath your opponents.")
        e.player.sendMessage("${ChatColor.INFO_COLOR}The game starts when there are at least ${SpleefGame.MIN_PLAYERS} players, so invite your friends to play with you!")
        e.player.sendMessage("${ChatColor.INFO_COLOR}To leave the game, type ${ChatColor.COMMAND_COLOR}/leave${ChatColor.INFO_COLOR}.")

        if(starting) return

        if(phase.game.players.size >= phase.game.minPlayers) {
            phase.game.broadcastMessage("${ChatColor.SUCCESS_COLOR}Spleef is starting in ${Tick.SECONDS.fromTicks(startDelay)} second(s)")
            starting = true
            currentTick = startDelay
        }
    }

    @GameEvent
    fun onLeave(e: GameLeaveEvent) {
        phase.game.broadcastMessage("${ChatColor.INFO_COLOR}${e.player.getName()} has left (${phase.game.players.size - 1}/${phase.game.maxPlayers})")
        if(phase.game.players.size < phase.game.minPlayers && starting) {
            starting = false
            phase.game.broadcastMessage("${ChatColor.ERROR_COLOR}There are no longer enough players to start the game.")
        }
    }
}