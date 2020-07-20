package com.example.kotlin.chat.controller

import com.example.kotlin.chat.service.MessageService
import com.example.kotlin.chat.service.vm.MessageVM
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexHtmlController(val messageService: MessageService) {

    @GetMapping("/")
    suspend fun index(model: Model): String {
        val messages: List<MessageVM> = messageService.latest().toList()

        model["messages"] = messages
        model["lastMessageId"] = messages.lastOrNull()?.id ?: ""

        return "chat"
    }

}