package me.pljr.pljrapidiscord.command

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData

class DefaultCommandContext(override val event: SlashCommandEvent, override val commandData: CommandData) : CommandContext