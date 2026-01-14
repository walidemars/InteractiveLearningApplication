package com.nikitinsky.interactive.learning.app.data.mapper

import com.nikitinsky.interactive.learning.app.data.local.model.KanaSymbolDbModel
import com.nikitinsky.interactive.learning.app.data.local.model.LevelDbModel
import com.nikitinsky.interactive.learning.app.data.local.model.WordDbModel
import com.nikitinsky.interactive.learning.app.domain.model.KanaSymbol
import com.nikitinsky.interactive.learning.app.domain.model.Level
import com.nikitinsky.interactive.learning.app.domain.model.Word

fun KanaSymbol.toDbModel(): KanaSymbolDbModel {
    return KanaSymbolDbModel(id, japaneseChar, romaji, type, levelId)
}

fun KanaSymbolDbModel.toEntity(): KanaSymbol {
    return KanaSymbol(id, japaneseChar, romaji, type, levelId)
}

fun List<KanaSymbolDbModel>.toEntities(): List<KanaSymbol> {
    return map { it.toEntity() }
}

fun Word.toDbModel(): WordDbModel {
    return WordDbModel(id, kanji, kana, translation, levelId)
}

fun WordDbModel.toEntity(): Word {
    return Word(id, kanji, kana, translation, levelId)
}

fun List<WordDbModel>.toEntities(): List<Word> {
    return map { it.toEntity() }
}

fun Level.toDbModel(): LevelDbModel {
    return LevelDbModel(id, title, kanaIds, wordIds, isUnlocked, kanaType)
}

fun LevelDbModel.toEntity(): Level {
    return Level(id, title, kanaIds, wordIds, isUnlocked, kanaType)
}

fun List<LevelDbModel>.toEntities(): List<Level> {
    return map { it.toEntity() }
}

