package com.nikitinsky.interactive.learning.app.presentation.screens.practice

import com.nikitinsky.interactive.learning.app.domain.entity.Kana

data class KanaCardViewModel(
    val kana: Kana,
    val status: ButtonStatus = ButtonStatus.NOT_PRESSED
)