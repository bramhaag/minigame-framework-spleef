package me.bramhaag.spleef

import cn.nukkit.Player
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import cn.nukkit.plugin.PluginBase
import com.sun.corba.se.impl.activation.CommandHandler
import me.bramhaag.minigameframework.game.AbstractGameHandler
import me.bramhaag.minigameframework.nukkit.NukkitGameHandler
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.spleef.util.server.Server
import java.lang.IllegalStateException

class SpleefPlugin : PluginBase() {
    private lateinit var gameHandler: AbstractGameHandler

    override fun onEnable() {
        Server.instance = NukkitServer(this)

        gameHandler = NukkitGameHandler(this)
        gameHandler.enable()
        gameHandler.registerGameMode(SpleefGame.GAMEMODE)

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