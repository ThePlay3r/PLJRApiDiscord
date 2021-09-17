package me.pljr.pljrapidiscord.command

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

abstract class GuildCommand(override val name: String, override val description: String, private val role: Int = -1) : Command, ListenerAdapter() {

    override fun onSlashCommand(event: SlashCommandEvent) {
        event.deferReply()
        val context = DefaultCommandContext(event, this.data)
        val member = event.member ?: return
        if (checkPerm(member)) {
            if (event.name == name) {
                this.onCommand(context)
            }
        } else {
            noPerm(context)
        }
    }

    open fun noPerm(context: CommandContext) {
        context.channel.sendMessage("You don't have sufficient role.")
    }

    private fun checkPerm(member: Member) : Boolean {
        member.roles.forEach { role ->
            if (role.position >= this.role) {
                return true
            }
        }
        return false
    }

    fun register(guild: Guild) {
        guild.upsertCommand(this.data)
    }
}