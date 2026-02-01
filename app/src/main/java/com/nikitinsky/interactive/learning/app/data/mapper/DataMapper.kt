package com.nikitinsky.interactive.learning.app.data.mapper

import com.nikitinsky.interactive.learning.app.data.local.model.KanaDbModel
import com.nikitinsky.interactive.learning.app.data.local.model.LevelDbModel
import com.nikitinsky.interactive.learning.app.data.local.model.LevelWithKanaDbModel
import com.nikitinsky.interactive.learning.app.domain.entity.Kana
import com.nikitinsky.interactive.learning.app.domain.entity.Level

fun KanaDbModel.toEntity(): Kana {
    return Kana(japaneseSymbol, romaji, kanaType, levelId)
}

fun List<KanaDbModel>.toKanaEntities(): List<Kana> {
    return map { it.toEntity() }
}

//fun LevelDbModel.toEntity(): Level {
//    return Level(id, kanaList, kanaType)
//}

fun LevelWithKanaDbModel.toEntity(): Level {
    return Level(
        id = levelDbModel.id,
        kanaList = kanaContent.toKanaEntities(),
        kanaType = levelDbModel.kanaType
    )
}

fun List<LevelWithKanaDbModel>.toLevelEntities(): List<Level> {
    return map { it.toEntity() }
}