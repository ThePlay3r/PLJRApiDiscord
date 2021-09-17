package me.pljr.pljrapidiscord.message

import net.dv8tion.jda.api.entities.Message
import java.util.concurrent.TimeUnit

fun addTempMessageReaction(message: Message, unicode: String, keepFor: Long, timeUnit: TimeUnit) {
    message.addReaction(unicode).queue()
    message.delete().queueAfter(keepFor, timeUnit)
}