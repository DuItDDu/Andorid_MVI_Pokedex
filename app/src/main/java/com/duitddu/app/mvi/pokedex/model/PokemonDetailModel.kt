package com.duitddu.app.mvi.pokedex.model

data class PokemonDetailModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val weight: Int,
    val height: Int,
    val stats: List<PokemonStatModel>,
    val types: List<PokemonType>
)

data class PokemonStatModel(
    val name: String,
    val stat: Int
) {
    val progress: Float
        get() = stat.div(300f)
}

enum class PokemonType(
    val type: String
) {
    NORMAL("normal"),
    FIRE("fire"),
    WATER("water"),
    ELECTRIC("electric"),
    GRASS("grass"),
    ICE("ice"),
    FIGHTING("fighting"),
    POISON("poison"),
    GROUND("ground"),
    FLYING("flying"),
    PSYCHIC("psychic"),
    BUG("bug"),
    ROCK("rock"),
    GHOST("ghost"),
    DRAGON("dragon"),
    DARK("dark"),
    STEEL("steel"),
    FAIRY("fairy")
}