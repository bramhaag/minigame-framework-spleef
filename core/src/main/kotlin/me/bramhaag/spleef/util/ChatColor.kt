package me.bramhaag.spleef.util

/**
 * A wrapper for chat colors.
 *
 * @param char The character for this color.
 */
enum class ChatColor(private val char: Char) {
    BLACK('0'),
    DARK_BLUE('1'),
    DARK_GREEN('2'),
    DARK_AQUA('3'),
    DARK_RED('4'),
    DARK_PURPLE('5'),
    GOLD('6'),
    GRAY('7'),
    DARK_GRAY('8'),
    INDIGO('9'),
    GREEN('a'),
    AQUA('b'),
    RED('c'),
    PINK('d'),
    YELLOW('e'),
    WHITE('f'),
    STRIKE_THROUGH('m'),
    UNDERLINED('n'),
    BOLD('l'),
    RANDOM('k'),
    ITALIC('o'),
    RESET('r');

    override fun toString(): String {
        return "§$char"
    }

    companion object {
        val COMMAND_COLOR = AQUA
        val ERROR_COLOR = DARK_RED
        val SUCCESS_COLOR = DARK_GREEN
        val INFO_COLOR = INDIGO
    }
}