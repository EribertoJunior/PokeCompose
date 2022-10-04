package br.com.estudos.pokecompose.di

import br.com.estudos.pokecompose.repository.Repository
import br.com.estudos.pokecompose.repository.RepositoryImpl
import br.com.estudos.pokecompose.repository.remote.PokemonDataSource
import br.com.estudos.pokecompose.repository.remote.RetrofitConfig
import br.com.estudos.pokecompose.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val PROPERTY_BASE_URL = "SERVER_URL"

val modules = module {

    factory { PokemonDataSource(get()) }
    factory<Repository> { RepositoryImpl(get()) }
    single {
        val baseUrl = getProperty<String>(PROPERTY_BASE_URL)
        RetrofitConfig(baseUrl, androidContext()).getPokeServide()
    }

    viewModel { HomeViewModel(get()) }
}