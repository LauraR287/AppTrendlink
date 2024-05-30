package com.example.apptrendlink_valentinahernandez

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdaptador(
    private val chats: List<Chat>,
    private val listener: OnChatClickListener
) : RecyclerView.Adapter<ChatAdaptador.ChatViewHolder>() {

    interface OnChatClickListener {
        fun onChatClick(chat: Chat)
    }

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreChatTextView: TextView = itemView.findViewById(R.id.nombreChatTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val chat = chats[position]
                    listener.onChatClick(chat)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_item, parent, false)
        return ChatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chats[position]
        holder.nombreChatTextView.text = chat.nombre
    }

    override fun getItemCount() = chats.size
}
