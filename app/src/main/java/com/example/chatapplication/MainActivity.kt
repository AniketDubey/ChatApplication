package com.example.chatapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ChatAdapter
    private var toSent = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ChatAdapter(getChatDetails())

        binding.enterMsgTxt.setEndIconOnClickListener {
            adapter.setUpChatList(
                ChatDetails(
                    toSent,
                    binding.enterMsgTxt.editText?.text.toString()
                )
            )
            binding.enterMsgTxt.editText?.text?.clear()
            toSent = if (toSent == 0)
                1
            else
                0
        }

        binding.chatRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun getChatDetails(): ArrayList<ChatDetails> {
        return arrayListOf(
            ChatDetails(0, "Hi!"),
            ChatDetails(1, "Hello!"),
            ChatDetails(0, "My Name is Aniket!"),
            ChatDetails(0, "What about you"),
            ChatDetails(1, "Why should i tell you"),
            ChatDetails(0, "Are aise hi"),
        )
    }
}