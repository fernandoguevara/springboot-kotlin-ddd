package com.example.background.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class ReportingManagerService(
    private val reportService: IReportService
) {

    private val logger: Logger = LoggerFactory.getLogger(ReportingManagerService::class.java)

    private val dateFormat = SimpleDateFormat("HH:mm:ss")

    //i think it means every day at 7 am
    @Scheduled(cron = "* 0 7 * * *")
    fun reportCurrentTime() {
        logger.info("Notes Report is starting.")
        reportService.createNotesReport()
        logger.info("Notes Report created.")
    }
}