package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository

class CheckAnswerUseCase(
    private val repository: KanaRepository
) {

    operator fun invoke(userAnswer: String, correctAnswer: String): Boolean {
        TODO()
    }
}