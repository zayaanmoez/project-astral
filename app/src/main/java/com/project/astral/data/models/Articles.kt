package com.project.astral.data.models

import java.time.OffsetDateTime
import java.time.Period
import java.time.ZoneId
import java.time.Duration
import java.time.format.DateTimeFormatter

data class Article(
    val title: String,
    val url: String,
    val imageUrl: String,
    val summary: String,
    val publishedAt: String,
    val source: String
) {
    fun getPublishedDuration(): String {
        val odtPublished = OffsetDateTime.parse(publishedAt)
        val odtNow = OffsetDateTime.now()

        /* Formatted date
        return odtPublished.atZoneSameInstant(ZoneId.systemDefault()).format(
            DateTimeFormatter.ofPattern("MMM dd, hh:mm a")
        )
        */

        // Period since published
        val period = Period.between(odtPublished.toLocalDate(), odtNow.toLocalDate())

        // Duration since published
        val duration = Duration.between(odtPublished, odtNow)

        return if (period.months >= 1)
            period.months.toString() + "M"
        else if (period.days >= 1)
            period.days.toString() + "d"
        else if (duration.toHours() >= 1)
            duration.toHours().toString() + "h"
        else
            duration.toMinutes().toString() + "m"
    }
}