package me.bramhaag.spleef.util

import me.bramhaag.minigameframework.util.Location
import me.bramhaag.minigameframework.world.AbstractWorld

abstract class AbstractConfig {
    abstract fun getString(key: String): String?
    fun getString(key: String, default: String): String {
        return getString(key) ?: default
    }

    abstract fun getInt(key: String): Int?
    fun getInt(key: String, default: Int): Int {
        return getInt(key) ?: default
    }

    abstract fun getDouble(key: String): Double?
    fun getDouble(key: String, default: Double): Double {
        return getDouble(key) ?: default
    }

    abstract fun getFloat(key: String): Float?
    fun getFloat(key: String, default: Float): Float {
        return getFloat(key) ?: default
    }

    fun getLocation(key: String, world: AbstractWorld): Location {
        val x = getDouble("$key.x", 0.0)
        val y = getDouble("$key.y", 0.0)
        val z = getDouble("$key.z", 0.0)
        val yaw = getFloat("$key.yaw", 0f)
        val pitch = getFloat("$key.pitch", 0f)

        return Location(world, x, y, z, yaw, pitch)
    }

    abstract fun getLocations(key: String, world: AbstractWorld): List<Location>
}