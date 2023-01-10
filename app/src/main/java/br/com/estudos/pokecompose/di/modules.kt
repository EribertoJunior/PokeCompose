package br.com.estudos.pokecompose.di

import br.com.estudos.pokecompose.repository.DetailRepository
import br.com.estudos.pokecompose.repository.DetailRepositoryImpl
import br.com.estudos.pokecompose.repository.PokemonRemoteMediator
import br.com.estudos.pokecompose.repository.HomeRepository
import br.com.estudos.pokecompose.repository.HomeRepositoryImpl
import br.com.estudos.pokecompose.repository.local.RoomConfig
import br.com.estudos.pokecompose.repository.remote.PokemonPagingSource
import br.com.estudos.pokecompose.repository.remote.RetrofitConfig
import br.com.estudos.pokecompose.viewmodels.DetailsViewModel
import br.com.estudos.pokecompose.viewmodels.HomeViewModel
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

    factory<HomeRepository> { HomeRepositoryImpl(get(), get()) }
    factory<DetailRepository> { DetailRepositoryImpl(get(), get()) }

    factory { get<RoomConfig>().pokemonDao() }
    factory { get<RoomConfig>().pokemonRemoteKeyDao() }
    factory { get<RoomConfig>().pokemonDetailDao() }
    factory {
        PokemonRemoteMediator(
            pokemonDao = get(),
            pokemonDetailDao = get(),
            pokemonRemoteKeyDao = get(),
            pokemonService = get()
        )
    }

    viewModel { HomeViewModel(repository = get()) }
    viewModel { DetailsViewModel(detailRepository = get()) }
}