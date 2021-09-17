package me.pljr.pljrapidiscord.command

import net.dv8tion.jda.api.interactions.commands.build.CommandData

interface Command {

    val name: String

    val description: String

    fun getData() = CommandData(name, description)

    fun onCommand(context: CommandContext)
}