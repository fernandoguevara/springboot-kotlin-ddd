package com.example.api.infrastructure.pipelines

import an.awesome.pipelinr.Command
import an.awesome.pipelinr.Command.Middleware
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.slf4j.Logger;

@Component
@Order(1)
class LoggerPipepline : Middleware {
    private val logger: Logger = LoggerFactory.getLogger(LoggerPipepline::class.java)

    override fun <R, C : Command<R>?> invoke(
        command: C,
        next: Middleware.Next<R>
    ): R {
        logger.info("Sending command $command")
        val response = next.invoke()
        logger.info("handled command $command")
        return response
    }
}