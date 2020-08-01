package me.bramhaag.spleef

import me.bramhaag.minigameframework.game.AbstractGameHandler
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.spigot.SpigotGameHandler
import me.bramhaag.spleef.util.server.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.lang.IllegalStateException

class SpleefPlugin : JavaPlugin() {

    private lateinit var gameHandler: AbstractGameHandler

    override fun onEnable() {
        Server.instance = SpigotServer(this)

        gameHandler = SpigotGameHandler(this)
        gameHandler.enable()
        gameHandler.registerGameMode(SpleefGame.GAMEMODE)

        getCommand("spleef")?.setExecutor(this)

        saveDefaultConfig()
    }

    override fun onDisable() {
        gameHandler.disable()
    }

    override fun onCommand(sender: CommandSender, cmd: Command, cmdLabel: String, args: Array<String>): Boolean {
        if (cmd.name != "spleef") {
            return true
        }

        if (sender !is Player) {
            sender.sendMessage("Only players can use this command")
            return true
        }

        val player = AbstractPlayer.players[sender.uniqueId] ?: throw IllegalStateException("Cannot find AbstractPlayer")

        if (args.isEmpty()) {
            gameHandler.getGames(SpleefGame.GAMEMODE)?.first()?.join(player)
            return true
        }

        if ("leave".equals(args.first(), true)) {
            gameHandler.getGames(SpleefGame.GAMEMODE)?.first()?.leave(player)
            return true
        }

        return true
    }
}