package me.bramhaag.spleef.phases

import me.bramhaag.minigameframework.feature.features.*
import me.bramhaag.minigameframework.phase.AbstractPhase
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.player.GameMode
import me.bramhaag.spleef.features.LeaveFeature
import me.bramhaag.spleef.features.SpectateFeature
import me.bramhaag.spleef.features.WinnerFeature

class WinnerPhase : AbstractPhase(
        allowSpectate = true,
        allowJoin = false
) {

    var winner: AbstractPlayer? = null

    override fun init() {
        createFeature<WorldFeature> {
            worldName = "spleefworld"
            shouldUnload = true
        }

        createFeature<GameModeFeature> {
            gameMode = GameMode.SURVIVAL
        }

        createFeature<SpectateFeature>()
        createFeature<WinnerFeature>()
        createFeature<HealFeature>()
        createFeature<NoDamageFeature>()
        createFeature<ClearInventoryFeature>()
        createFeature<NoBlockBreakFeature>()
        createFeature<NoBlockPlaceFeature>()
        createFeature<NoItemDropFeature>()
        createFeature<NoBlockPlaceFeature>()
        createFeature<LeaveFeature>()
    }
}