package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import javax.inject.Inject

class GetLevelUseCase @Inject constructor(
    private val kanaRepository: KanaRepository
){
    suspend operator fun invoke(levelId: Int) =
        kanaRepository.getLevel(levelId)
}