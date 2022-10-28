package br.com.estudos.pokecompose.di

import br.com.estudos.pokecompose.repository.PokemonRemoteMediator
import br.com.estudos.pokecompose.repository.Repository
import br.com.estudos.pokecompose.repository.RepositoryImpl
import br.com.estudos.pokecompose.repository.local.RoomConfig
import br.com.estudos.pokecompose.repository.remote.PokemonPagingSource
import br.com.estudos.pokecompose.repository.remote.RetrofitConfig
import br.com.estudos.pokecompose.viewmodel.HomeViewModel
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
    factory<Repository> { RepositoryImpl(get(), get()) }
    factory { get<RoomConfig>().pokemonDao() }
    factory { get<RoomConfig>().pokemonRemoteKeyDao() }
    factory { PokemonRemoteMediator(get(), get(), get()) }

    viewModel { HomeViewModel(get()) }
}