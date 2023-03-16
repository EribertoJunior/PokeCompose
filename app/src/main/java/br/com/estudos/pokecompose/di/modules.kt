package br.com.estudos.pokecompose.di

import br.com.estudos.pokecompose.data.dataBase.local.RoomConfig
import br.com.estudos.pokecompose.data.dataBase.remote.PokemonPagingSource
import br.com.estudos.pokecompose.data.dataBase.remote.RetrofitConfig
import br.com.estudos.pokecompose.ui.viewmodels.DetailsViewModel
import br.com.estudos.pokecompose.ui.viewmodels.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val PROPERTY_BASE_URL = "SERVER_URL"

val modules = module {
    single {
        val baseUrl = getProperty<String>(PROPERTY_BASE_URL)
        RetrofitConfig(baseUrl, androidContext()).getPokeServide()
    }
    factory { RoomConfig.getDataBase(androidContext()) }

    factory { PokemonPagingSource(get()) }

    factory<br.com.estudos.pokecompose.data.repository.HomeRepository> {
        br.com.estudos.pokecompose.data.repository.HomeRepositoryImpl(
            get(),
            get()
        )
    }
    factory<br.com.estudos.pokecompose.data.repository.DetailRepository> {
        br.com.estudos.pokecompose.data.repository.DetailRepositoryImpl(
            get(),
            get()
        )
    }

    factory { get<RoomConfig>().pokemonDao() }
    factory { get<RoomConfig>().pokemonRemoteKeyDao() }
    factory { get<RoomConfig>().pokemonDetailDao() }
    factory { get<RoomConfig>().pokemonSpeciesDao() }
    factory { get<RoomConfig>().pokemonEvolutionChainDao() }

    factory {
        br.com.estudos.pokecompose.data.repository.PokemonRemoteMediator(
            pokemonDao = get(),
            pokemonDetailDao = get(),
            pokemonRemoteKeyDao = get(),
            pokemonService = get(),
            pokemonSpeciesDao = get(),
            evolutionChainDao = get()
        )
    }

    viewModel { HomeViewModel(repository = get()) }
    viewModel { DetailsViewModel(detailRepository = get()) }
}