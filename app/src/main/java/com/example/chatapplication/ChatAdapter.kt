package com.example.chatapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(var chatDetails: ArrayList<ChatDetails>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_SENT = 1
        const val VIEW_TYPE_RECEIVED = 2
    }

    fun setUpChatList(updatedChatDetails: ChatDetails) {
        chatDetails.add(updatedChatDetails)
        notifyItemInserted(chatDetails.size)
        notifyItemRangeChanged(0, chatDetails.size)
    }

    override fun getItemViewType(position: Int): Int {
        if (chatDetails[position].userType == 0)
            return VIEW_TYPE_SENT
        return VIEW_TYPE_RECEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_SENT) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.sender_layout, parent, false)
            SentMsgViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.receiver_layout, parent, false)
            ReceiverMsgViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = chatDetails[position].chatData
        if (holder is SentMsgViewHolder)
            holder.bind(msg)
        else if (holder is ReceiverMsgViewHolder)
            holder.bind(msg)
    }

    override fun getItemCount(): Int {
        return chatDetails.size
    }

    inner class SentMsgViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sentTxt = itemView.findViewById<TextView>(R.id.senderTxt)
        fun bind(msg: String) {
            sentTxt.text = msg
        }
    }

    inner class ReceiverMsgViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receiverTxt = itemView.findViewById<TextView>(R.id.receiverTxt)
        fun bind(msg: String) {
            receiverTxt.text = msg
        }
    }
}