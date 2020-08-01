package me.bramhaag.spleef.features

import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.util.Tick
import me.bramhaag.spleef.phases.WinnerPhase
import me.bramhaag.spleef.util.ChatColor

class WinnerFeature : AbstractFeature() {

    var countdown = Tick.SECONDS.toTicks(10)

    override fun enable() {
        for (it in ArrayList(phase.game.players)) {
            phase.game.spectate(it)
        }

        val winnerPhase = phase as WinnerPhase
        val message = if (winnerPhase.winner == null) {
            "Times up! There is no winner"
        } else {
            "${(phase as WinnerPhase).winner?.getName()} has won!"
        }

        phase.game.allPlayers().forEach { sendTitle(it, message) }
    }

    override fun tick() {
        countdown--

        if (countdown > 0) return

        phase.game.endGame((phase as WinnerPhase).winner)
    }

    private fun sendTitle(player: AbstractPlayer, message: String) {
        player.sendTitle("${ChatColor.SUCCESS_COLOR}$message", 10, Tick.SECONDS.toTicks(5).toInt(), 10)
    }
}