package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import javax.inject.Inject

class GetKanaForLevelUseCase @Inject constructor(
    private val kanaRepository: KanaRepository
) {
    operator fun invoke(levelId: Int) =
        kanaRepository.getKanaForLevel(levelId)
}