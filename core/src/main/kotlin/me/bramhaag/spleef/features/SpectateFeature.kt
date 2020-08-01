package me.bramhaag.spleef.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.game.GameSpectateEvent
import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.minigameframework.feature.features.WorldFeature
import me.bramhaag.spleef.util.server.Server

class SpectateFeature : AbstractFeature() {

    @GameEvent
    fun onSpectate(e: GameSpectateEvent) {
        val worldFeature = phase.getFeature(WorldFeature::class.java) ?: throw IllegalStateException("WorldFeature is null")
        e.player.setLocation(Server.instance.getConfig().getLocation("spectate-spawn", worldFeature.world))}
}