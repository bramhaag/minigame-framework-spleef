package me.bramhaag.spleef.phases

import me.bramhaag.minigameframework.feature.features.*
import me.bramhaag.minigameframework.phase.AbstractPhase
import me.bramhaag.minigameframework.player.GameMode
import me.bramhaag.spleef.features.LeaveFeature
import me.bramhaag.spleef.features.LobbyFeature
import me.bramhaag.spleef.util.server.Server

class LobbyPhase : AbstractPhase(
        allowSpectate = false,
        allowJoin = true
) {
    override fun init() {
        val config = Server.instance.getConfig()
        val worldFeature = createFeature<WorldFeature> {
            worldName = config.getString("spleef-world", "spleefworld")
            shouldUnload = false
            firstLoad = true
        }

        createFeature<SpawnFeature> {
            val location = Server.instance.getConfig().getLocation("lobby-spawn", worldFeature.world)
            addSpawn(location.x, location.y, location.z, location.yaw, location.pitch)
        }

        createFeature<GameModeFeature> {
            gameMode = GameMode.SURVIVAL
        }

        createFeature<HealFeature>()
        createFeature<NoDamageFeature>()
        createFeature<ClearInventoryFeature>()
        createFeature<NoBlockBreakFeature>()
        createFeature<NoBlockPlaceFeature>()
        createFeature<NoItemDropFeature>()
        createFeature<LobbyFeature>()
        createFeature<LeaveFeature>()
    }


}
