package me.bramhaag.spleef.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.player.PlayerBlockBreakEvent
import me.bramhaag.minigameframework.feature.AbstractFeature

class SpleefFeature : AbstractFeature() {

    @GameEvent
    fun onBlockBreak(e: PlayerBlockBreakEvent) {
        if(e.blockName.toUpperCase() != "SNOW_BLOCK") {
            e.cancelled = true
            return
        }

        e.dropItems = false
    }
}