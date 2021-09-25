package me.pljr.pljrapidiscord.command

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter


abstract class GuildCommand(override val name: String, override val description: String, private val roleId: Long) : Command, ListenerAdapter() {
    private lateinit var guild: Guild

    override fun onSlashCommand(event: SlashCommandEvent) {
        val eventGuild = event.guild ?: return
        if (eventGuild == guild) {
            if (event.name == name) {
                event.deferReply().queue()
                val context = DefaultCommandContext(event, this.data)
                val member = event.member ?: return
                if (checkPerm(member)) {
                    this.onCommand(context)
                } else {
                    noPerm(context)
                }
            }
        }
    }

    open fun noPerm(context: CommandContext) {
        context.channel.sendMessage("You don't have sufficient role.").queue()
    }

    private fun checkPerm(member: Member) : Boolean {
        val requiredRole = guild.getRoleById(roleId) ?: return true
        member.roles.forEach { role ->
            if (role.position >= requiredRole.position) {
                return true
            }
        }
        return false
    }

    fun register(guild: Guild) {
        this.guild = guild
        guild.jda.addEventListener(this)
        guild.upsertCommand(this.data).queue()
    }
}