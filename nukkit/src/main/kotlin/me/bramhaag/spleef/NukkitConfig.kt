package me.bramhaag.spleef

import cn.nukkit.utils.Config
import me.bramhaag.minigameframework.util.Location
import me.bramhaag.minigameframework.world.AbstractWorld
import me.bramhaag.spleef.util.AbstractConfig

class NukkitConfig(private val config: Config) : AbstractConfig() {
    override fun getString(key: String): String? {
        return config.getString(key)
    }

    override fun getInt(key: String): Int? {
        return config.getInt(key)
    }

    override fun getDouble(key: String): Double? {
        return config.getDouble(key)
    }

    override fun getFloat(key: String): Float? {
        return config.getDouble(key).toFloat()
    }

    override fun getLocations(key: String, world: AbstractWorld): List<Location> {
        val section = config.getSection(key) ?: return emptyList()
        return section.getKeys(false).map { getLocation("$key.$it", world) }
    }

}