package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.model.KanaSymbol
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import javax.inject.Inject

class GetKanaByIdUseCase @Inject constructor (
    private val repository: KanaRepository
) {
    operator fun invoke(kanaId: Int): KanaSymbol {
        return repository.getKanaById(kanaId)
    }
}