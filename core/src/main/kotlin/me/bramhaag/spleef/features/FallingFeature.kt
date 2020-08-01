package me.bramhaag.spleef.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.player.PlayerMoveEvent
import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.spleef.conditions.VictoryCondition
import me.bramhaag.spleef.util.ChatColor

class FallingFeature : AbstractFeature() {

    private val minY = 20

    @GameEvent
    fun onMoveEvent(e: PlayerMoveEvent) {
        if(e.from.y <= e.to.y) return

        if(e.player.getLocation().y <= minY) {
            phase.game.spectate(e.player)
            e.player.sendTitle("You fell into the void!", 10, 60, 10)
            phase.game.broadcastMessage("${ChatColor.INFO_COLOR}${e.player.getName()} fell into the void!")

            val players = phase.game.players
            if(players.size == 1) {
                val winner = players.first()

                //TODO use condition
                phase.getCondition(VictoryCondition::class.java)?.winner = winner
                phase.game.endPhase()
            }
        }
    }
}