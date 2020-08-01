package me.bramhaag.spleef.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.game.GameLeaveEvent
import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.spleef.util.server.Server

class LeaveFeature : AbstractFeature() {

    @GameEvent
    fun onLeave(e: GameLeaveEvent) {
        val worldName = Server.instance.getConfig().getString("world", "world")
        val world = phase.game.gameHandler.worldHandler.loadWorld(worldName)
        e.player.setLocation(world.getSpawn())
    }
}