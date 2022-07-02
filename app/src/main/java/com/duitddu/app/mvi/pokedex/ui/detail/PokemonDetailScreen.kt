package com.duitddu.app.mvi.pokedex.ui.detail

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.duitddu.app.mvi.pokedex.model.PokemonDetailModel
import com.duitddu.app.mvi.pokedex.model.PokemonStatModel
import com.duitddu.app.mvi.pokedex.model.PokemonType
import com.duitddu.app.mvi.pokedex.ui.component.ErrorTextView
import com.duitddu.app.mvi.pokedex.ui.component.LoadingIndicator
import com.duitddu.app.mvi.pokedex.ui.component.PokemonImageView
import com.duitddu.app.mvi.pokedex.ui.theme.PokedexColorPalette
import com.duitddu.app.mvi.pokedex.ui.utils.getColor
import com.duitddu.app.mvi.pokedex.ui.utils.getStatName

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel
) {
    Surface(
        color = MaterialTheme.colors.surface
    ) {
        when (val state = viewModel.uiState.value) {
            is PokemonDetailState.Loading -> {
                LoadingIndicator()
            }
            is PokemonDetailState.Loaded -> {
                PokemonDetailView(state.pokemon)
            }
            is PokemonDetailState.Error -> {
                ErrorTextView(state.throwable)
            }
        }
    }
}

@Composable
fun PokemonDetailView(
    pokemon: PokemonDetailModel
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(scrollState, Orientation.Vertical),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PokemonImageView(
            imageUrl = pokemon.imageUrl,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        PokemonBasicInfoView(pokemon.id, pokemon.name, pokemon.types)
        Spacer(modifier = Modifier.padding(8.dp))
        PokemonPhysicalInfoView(pokemon.height, pokemon.weight)
        Spacer(modifier = Modifier.padding(16.dp))
        PokemonStatList(pokemon.stats)
    }
}

@Composable
fun PokemonBasicInfoView(
    id: Int,
    name: String,
    types: List<PokemonType>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = PokedexColorPalette.get(case = PokedexColorPalette.Case.PokemonNumber),
            text = "#$id",
            style = MaterialTheme.typography.h4
        )
        Text(
            color = PokedexColorPalette.get(case = PokedexColorPalette.Case.PokemonName),
            text = name,
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.padding(top = 4.dp))
        PokemonTypeList(types)
    }
}

@Composable
fun PokemonTypeList(
    types: List<PokemonType>
) {
    LazyRow(
        content = {
            items(types) {
                PokemonTypeListItem(it)
            }
        }
    )
}

@Composable
fun PokemonTypeListItem(
    type: PokemonType
) {
    Card(
        backgroundColor = type.getColor(),
        contentColor = Color.White,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(50))
    ) {
        Text(
            color = Color.White,
            text = type.type,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 20.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun PokemonPhysicalInfoView(
    height: Int,
    weight: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        PokemonPhysicalInfoText("Height", height.div(10f), "m")
        Spacer(modifier = Modifier.padding(horizontal = 48.dp))
        PokemonPhysicalInfoText("Weight", weight.div(10f), "kg")
    }
}

@Composable
fun PokemonPhysicalInfoText(
    label: String,
    value: Float,
    unit: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            color = PokedexColorPalette.get(case = PokedexColorPalette.Case.PokemonPhysicalValue),
            text = "$value$unit",
            style = MaterialTheme.typography.h4
        )
        Text(
            color = PokedexColorPalette.get(case = PokedexColorPalette.Case.PokemonPhysicalName),
            text = label,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun PokemonStatList(
    stats: List<PokemonStatModel>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .padding(16.dp),
        content = {
            items(stats) { stat ->
                PokemonStatListItem(stat)
            }
        }
    )
}

@Composable
fun PokemonStatListItem(
    stat: PokemonStatModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PokemonStatNameText(stat)
        PokemonStatProgress(progress = stat.progress)
    }
}

@Composable
fun PokemonStatNameText(
    stat: PokemonStatModel
) {
    Text(
        color = PokedexColorPalette.get(case = PokedexColorPalette.Case.PokemonStatName),
        text = stat.getStatName(),
        style = MaterialTheme.typography.h5
    )
}

@Composable
fun PokemonStatProgress(
    progress: Float
) {
    LinearProgressIndicator(
        progress = progress,
        modifier = Modifier
            .height(8.dp)
            .clip(RoundedCornerShape(50)),
        color = PokedexColorPalette.get(case = PokedexColorPalette.Case.PokemonStatProgressValue),
        backgroundColor = PokedexColorPalette.get(case = PokedexColorPalette.Case.PokemonStatProgress)
    )
}