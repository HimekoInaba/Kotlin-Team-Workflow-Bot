package me.syrym.service.impl

import me.ivmg.telegram.entities.Chat
import me.ivmg.telegram.entities.Message
import me.ivmg.telegram.entities.Update
import me.ivmg.telegram.entities.User
import me.syrym.configuration.config
import me.syrym.configuration.errMessage
import me.syrym.configuration.messageFormats
import me.syrym.configuration.messageWelcome
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import java.time.LocalTime
import java.util.concurrent.ScheduledExecutorService

class MessageProcessingServiceImplTest {
    @InjectMocks
    private lateinit var messageProcessingServiceImpl: MessageProcessingServiceImpl

    @Mock
    private lateinit var schedulerService: AnswerSchedulerServiceImpl

    private val executor = mock(ScheduledExecutorService::class.java)
    private val user = mock(User::class.java)
    private val chat = mock(Chat::class.java)
    private val date = 21052019

    @BeforeEach
    fun init() {
        initMocks(this)
    }

    @Test
    fun `test processMessage when message text is valid`() {
        val message = getMessage("2h 11m 23s")
        val update = getUpdate(message)
        messageProcessingServiceImpl.processMessage(update)
        verify(schedulerService).scheduleBotResponse(
            message.chat.id,
            LocalTime.of(2, 11, 23).toSecondOfDay()
        )
    }

    @Test
    fun `test processMessage when message is invalid`() {
        val message = getMessage("wrong message")
        val update = getUpdate(message)
    }

    private fun getMessage(text: String): Message {
        return Message(
            1L, user, date, chat, null, null, date, null, null, text,
            null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null,
            null, null, null, null, null,
            null, null, null, null,
            null, null
        )
    }

    private fun getUpdate(message: Message): Update {
        return Update(
            1L, message, null, null, null, null,
            null, null
        )
    }
}
