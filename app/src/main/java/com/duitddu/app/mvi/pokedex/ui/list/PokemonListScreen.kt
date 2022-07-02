package com.duitddu.app.mvi.pokedex.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.duitddu.app.mvi.pokedex.model.PokemonModel
import com.duitddu.app.mvi.pokedex.ui.component.ErrorTextView
import com.duitddu.app.mvi.pokedex.ui.component.LoadingIndicator
import com.duitddu.app.mvi.pokedex.ui.component.PokemonImageView
import com.duitddu.app.mvi.pokedex.ui.theme.PokedexColorPalette
import com.duitddu.app.mvi.pokedex.ui.utils.extension.items
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
    onEffect: (PokemonListSideEffect) -> Unit
) {
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.onEach { onEffect(it) }.collect()
    }
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxSize()
    ) {
        when (val state = viewModel.uiState.value) {
            is PokemonListState.Loading -> {
                LoadingIndicator()
            }
            is PokemonListState.Loaded -> {
                PokemonGrid(
                    items = state.pokemonPaging.collectAsLazyPagingItems(),
                    onPokemonClick = {
                        viewModel.onEvent(PokemonListEvent.SelectPokemon(it))
                    }
                )
            }
            is PokemonListState.Error -> {
                ErrorTextView(throwable = state.throwable)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonGrid(
    items: LazyPagingItems<PokemonModel>,
    onPokemonClick: (PokemonModel) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        cells = GridCells.Fixed(2),
        content = {
            items(items) { pokemon ->
                if (pokemon != null) {
                    PokemonGridItem(pokemon, onPokemonClick)
                }
            }
        }
    )
}

@Composable
fun PokemonGridItem(
    pokemon: PokemonModel,
    onPokemonClick: (PokemonModel) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.large)
            .clickable { onPokemonClick.invoke(pokemon) },
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.wrapContentWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = PokedexColorPalette.get(case = PokedexColorPalette.Case.PokemonNumber),
                text = "#${pokemon.id}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.body1
            )

            PokemonImageView(
                imageUrl = pokemon.imageUrl,
                modifier = Modifier
                    .fillMaxHeight(0.7f)
                    .aspectRatio(1f)
            )
            Text(
                color = PokedexColorPalette.get(case = PokedexColorPalette.Case.PokemonName),
                text = pokemon.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1
            )
        }
    }
}