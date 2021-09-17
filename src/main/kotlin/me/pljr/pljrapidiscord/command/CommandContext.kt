package me.pljr.pljrapidiscord.command

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.interactions.InteractionHook
import net.dv8tion.jda.api.interactions.commands.Command
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.sharding.ShardManager

interface CommandContext {

    val event: SlashCommandEvent

    val commandData: CommandData

    val guild: Guild?
        get() = event.guild

    val channel: MessageChannel
        get() = event.channel

    val user: User
        get() = event.user

    val member: Member?
        get() = event.member

    val jda: JDA
        get() = event.jda

    val hook: InteractionHook
        get() = event.hook

    fun getOption(name: String) = event.getOption(name)

    fun getOptionsByType(type: OptionType) = event.getOptionsByType(type)
}