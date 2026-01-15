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

fun List<KanaSymbolDbModel>.toKanaEntities(): List<KanaSymbol> {
    return map { it.toEntity() }
}

fun Word.toDbModel(): WordDbModel {
    return WordDbModel(id, kanji, kana, translation, levelId)
}

fun WordDbModel.toEntity(): Word {
    return Word(id, kanji, kana, translation, levelId)
}

fun List<WordDbModel>.toWordEntities(): List<Word> {
    return map { it.toEntity() }
}

fun Level.toDbModel(): LevelDbModel {
    return LevelDbModel(
        id = id,
        title = title,
        kanaIds = kanaIds.joinToString(" "),
        wordIds = wordIds.joinToString(" "),
        isUnlocked = isUnlocked,
        kanaType = kanaType
    )
}

fun LevelDbModel.toEntity(): Level {
    return Level(
        id,
        title,
        kanaIds.split(" ").map { it.toInt() },
        wordIds.split(" ").map { it.toInt() },
        isUnlocked,
        kanaType
    )
}

fun List<LevelDbModel>.toLevelEntities(): List<Level> {
    return map { it.toEntity() }
}

