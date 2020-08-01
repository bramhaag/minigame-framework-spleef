package me.bramhaag.spleef.phases

import me.bramhaag.spleef.conditions.VictoryCondition
import me.bramhaag.minigameframework.feature.features.*
import me.bramhaag.minigameframework.game.GameConstants
import me.bramhaag.minigameframework.phase.TimedPhase
import me.bramhaag.minigameframework.player.GameMode
import me.bramhaag.minigameframework.util.Tick
import me.bramhaag.spleef.features.*
import me.bramhaag.spleef.util.ChatColor
import me.bramhaag.spleef.util.server.Server


class SpleefPhase : TimedPhase(duration = Tick.MINUTES.toTicks(5)) {
    private val broadcastAt = intArrayOf(60, 30, 20, 10, 5, 4, 3, 2, 1)
    private var index = 0

    override fun disable() {
        val winner = getCondition(VictoryCondition::class.java)?.winner ?: return
        (nextPhase as WinnerPhase).winner = winner
    }

    override fun init() {
        val config = Server.instance.getConfig()

        val worldFeature = createFeature<WorldFeature> {
            worldName = config.getString("spleef-world", "spleefworld")
            shouldUnload = false
        }

        createFeature<SpawnFeature> {
            config.getLocations("spawns", worldFeature.world).forEach {
                addSpawn(it.x, it.y, it.z, it.yaw, it.pitch)
            }
        }

        createFeature<GameModeFeature> {
            gameMode = GameMode.SURVIVAL
        }

        createFeature<NoBlockPlaceFeature>()
        createFeature<FallingFeature>()
        createFeature<FreezeFeature>()
        createFeature<KitFeature>()
        createFeature<SpectateFeature>()
        createFeature<HealFeature>()
        createFeature<NoDamageFeature>()
        createFeature<SpleefFeature>()
        createFeature<NoItemDropFeature>()
        createFeature<LeaveFeature>()

        createCondition<VictoryCondition>()
    }

    override fun tick() {
        super.tick()

        val secondsLeft = Tick.SECONDS.fromTicks(duration - currentTick).toInt()

        if(broadcastAt.size - 1 >= index && broadcastAt[index] == secondsLeft) {
            game.broadcastMessage("${ChatColor.INFO_COLOR}The game ends in $secondsLeft second(s)!")
            index++
        }
    }
}