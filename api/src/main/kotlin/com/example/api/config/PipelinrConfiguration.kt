package com.example.api.config

import an.awesome.pipelinr.*
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class PipelinrConfiguration {
    @Bean
    fun pipeline(
        commandHandlers: ObjectProvider<Command.Handler<*, *>?>,
        notificationHandlers: ObjectProvider<Notification.Handler<*>?>,
        middlewares: ObjectProvider<Command.Middleware?>
    ): Pipeline {
        return Pipelinr()
            .with(CommandHandlers { commandHandlers.stream() })
            .with(NotificationHandlers { notificationHandlers.stream() })
            .with(Command.Middlewares { middlewares.orderedStream() })
    }
}