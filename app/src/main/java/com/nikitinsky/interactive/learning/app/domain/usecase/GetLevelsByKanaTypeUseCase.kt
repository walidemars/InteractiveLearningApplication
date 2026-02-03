package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.entity.KanaType
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import javax.inject.Inject

class GetLevelsByKanaTypeUseCase @Inject constructor(
    private val kanaRepository: KanaRepository
){
    operator fun invoke(kanaType: KanaType) =
        kanaRepository.getLevelsByKanaType(kanaType)
}