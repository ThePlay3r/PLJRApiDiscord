package me.pljr.pljrapidiscord.command

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

abstract class GlobalCommand(override val name: String, override val description: String) : Command, ListenerAdapter() {

    override fun onSlashCommand(event: SlashCommandEvent) {
        if (event.name == name) {
            event.deferReply()
            this.onCommand(DefaultCommandContext(event, getData()))
        }
    }

    fun register(jda: JDA) {
        jda.upsertCommand(getData())
    }
}