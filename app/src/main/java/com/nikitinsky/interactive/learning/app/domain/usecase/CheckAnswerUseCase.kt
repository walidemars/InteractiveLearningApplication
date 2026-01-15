package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import javax.inject.Inject

class CheckAnswerUseCase @Inject constructor (
    private val repository: KanaRepository
) {

    operator fun invoke(userAnswer: String, correctAnswer: String): Boolean {
        TODO()
    }
}