package me.pljr.pljrapidiscord.listener

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.hooks.EventListener

abstract class DiscordListener<T : GenericEvent>(private val tClass: Class<T>) : EventListener {

    override fun onEvent(event: GenericEvent) {
        if (tClass.isInstance(event)) {
            event(event as T)
        }
    }

    abstract fun event(event: T)
}