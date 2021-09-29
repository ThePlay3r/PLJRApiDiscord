package me.pljr.pljrapidiscord.interaction.button

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Emoji
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.components.Button
import net.dv8tion.jda.api.interactions.components.ButtonStyle
import net.dv8tion.jda.api.interactions.components.Component
import net.dv8tion.jda.api.utils.data.DataObject

abstract class ClickableButton(
    private val id: String,
    private val label: String,
    private val style: ButtonStyle,
    private val disabled: Boolean,
    private val emoji: Emoji?,
    private val onClick: (event: ButtonClickEvent) -> Unit
) : ListenerAdapter(), Button {

    override fun getType() = Component.Type.BUTTON
    override fun getId() = id
    override fun getLabel() = label
    override fun getStyle() = style
    override fun getUrl(): String? = null
    override fun getEmoji() = emoji
    override fun isDisabled() = disabled

    constructor(data: DataObject, onClick: (event: ButtonClickEvent) -> Unit) : this(
        data.getString("custom_id"),
        data.getString("label"),
        ButtonStyle.fromKey(data.getInt("style")),
        data.getBoolean("disabled"),
        data.optObject("emoji").map<Emoji?> { emoji: DataObject? -> Emoji.fromData(emoji!!) }.orElse(null),
        onClick)

    init {
        if (style == ButtonStyle.LINK) {
            throw IllegalArgumentException("Button cannot be a link.")
        }
    }

    override fun onButtonClick(event: ButtonClickEvent) {
        if ((event.button?.id ?: return) == id) {
            this.onClick(event)
        }
    }

    fun register(jda: JDA) {
        jda.addEventListener(this)
    }

    override fun toData(): DataObject {
        val json = DataObject.empty()
        json.put("type", 2)
        json.put("label", label)
        json.put("style", style.key)
        json.put("disabled", disabled)
        if (url != null) json.put("url", url) else json.put("custom_id", id)
        return json
    }
}