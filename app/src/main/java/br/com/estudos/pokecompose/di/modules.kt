package br.com.estudos.pokecompose.di

import br.com.estudos.pokecompose.repository.RepositoryImpl
import br.com.estudos.pokecompose.repository.Repository
import br.com.estudos.pokecompose.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    factory <Repository> { RepositoryImpl() }

    viewModel { HomeViewModel(get()) }
}