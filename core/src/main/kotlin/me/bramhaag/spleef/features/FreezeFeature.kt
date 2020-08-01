package me.bramhaag.spleef.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.player.PlayerMoveEvent
import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.minigameframework.game.GameConstants
import me.bramhaag.minigameframework.util.Tick

class FreezeFeature : AbstractFeature() {

    var counter = Tick.SECONDS.toTicks(5).toInt()
    var state = 0

    override fun tick() {
        if(counter-- <= 0) return

        if (counter <= 0) {
            phase.game.players.forEach { it.sendTitle("Go!", 5, 20, 5) }
        } else if (counter % GameConstants.TPS == 0) {
            phase.game.players.forEach { it.sendTitle("${counter % GameConstants.TPS}", 5, 10, 5) }
        }
    }

    @GameEvent
    fun onPlayerMove(e: PlayerMoveEvent) {
        e.cancelled = counter > 0
    }
}
