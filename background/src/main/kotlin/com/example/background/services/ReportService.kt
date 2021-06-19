package com.example.background.services

import com.example.api.application.services.IEmailService
import com.example.infrastructure.repositories.INoteRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*


@Service
class ReportService(
    private val emailService: IEmailService,
    private val noteRepository: INoteRepository
) : IReportService {

    private val logger: Logger = LoggerFactory.getLogger(ReportService::class.java)

    @Value("\${spring.mail.reportemail}")
    private val reportEmail: String = ""

    override fun createNotesReport() {
        val formatter = SimpleDateFormat("yyyy-MM-dd-HH-mm")
        val now = Date.from(Instant.now())
        val fileName = formatter.format(now) + ".xlsx"
        logger.info("Creating Notes Report $fileName")
        val notes = noteRepository.getNotes()
        createExcelFile()

        logger.info("Sending Notes Report $fileName Email")
        emailService.sendEmail(reportEmail, "Report", "Notes Report", fileName)
        logger.info("Sent Notes Report $fileName Email")

        val myFile = File(fileName)
        if (myFile.exists()) myFile.delete()
        logger.info("Created Notes Report $fileName")
    }

    //imagine this method creates a xlsx file...
    private fun createExcelFile() {
        TODO("im sorry, i need to do this part...")
    }
}