package br.com.estudos.pokecompose.di

import br.com.estudos.pokecompose.data.dataBase.local.RoomConfig
import br.com.estudos.pokecompose.data.dataBase.remote.PokemonPagingSource
import br.com.estudos.pokecompose.data.dataBase.remote.RetrofitConfig
import br.com.estudos.pokecompose.data.dataSource.local.LocalDataSource
import br.com.estudos.pokecompose.data.dataSource.local.LocalDataSourceImpl
import br.com.estudos.pokecompose.data.dataSource.remote.RemoteDataSource
import br.com.estudos.pokecompose.data.dataSource.remote.RemoteDataSourceImpl
import br.com.estudos.pokecompose.data.repository.*
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

    factory<HomeRepository> {
        HomeRepositoryImpl(
            get(),
            get()
        )
    }
    factory<DetailRepository> {
        DetailRepositoryImpl(
            get(),
            get()
        )
    }

    factory { get<RoomConfig>().pokemonDao() }
    factory { get<RoomConfig>().pokemonRemoteKeyDao() }
    factory { get<RoomConfig>().pokemonDetailDao() }
    factory { get<RoomConfig>().pokemonSpeciesDao() }
    factory { get<RoomConfig>().pokemonEvolutionChainDao() }

    factory<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    factory<LocalDataSource> { LocalDataSourceImpl(get(), get(), get(), get(), get()) }

    factory {
        PokemonRemoteMediator(
//            pokemonDao = get(),
//            pokemonDetailDao = get(),
//            pokemonRemoteKeyDao = get(),
//            pokemonService = get(),
//            pokemonSpeciesDao = get(),
//            evolutionChainDao = get()
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    viewModel { HomeViewModel(repository = get()) }
    viewModel { DetailsViewModel(detailRepository = get()) }
}