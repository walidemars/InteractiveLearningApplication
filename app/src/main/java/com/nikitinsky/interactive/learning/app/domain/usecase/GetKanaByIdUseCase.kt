package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.model.KanaSymbol
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository

class GetKanaByIdUseCase(
    private val repository: KanaRepository
) {
    operator fun invoke(kanaId: Int): KanaSymbol {
        return repository.getKanaById(kanaId)
    }
}