package me.pljr.pljrapidiscord.command

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

abstract class GlobalCommand(final override val name: String, final override val description: String) : Command, ListenerAdapter() {

    override fun onSlashCommand(event: SlashCommandEvent) {
        if (event.name == name) {
            event.deferReply().queue()
            this.onCommand(DefaultCommandContext(event, this.data))
        }
    }

    fun register(jda: JDA) {
        jda.addEventListener(this)
        jda.upsertCommand(this.data).queue()
    }
}