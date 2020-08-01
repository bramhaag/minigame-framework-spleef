package me.bramhaag.spleef.features

import me.bramhaag.minigameframework.feature.AbstractFeature


class KitFeature : AbstractFeature() {

    override fun enable() {
        phase.game.players.forEach { for(i in 0..8) {
            it.setItem(i, "diamond_shovel")
        }}
    }
}