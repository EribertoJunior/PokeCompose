# Poke Compose

> Uma pokedex feita usando o jetpack Compose

O desafio do projeto foi a construção de uma pokédex, realizando uma busca paginada na pokeApi [https://pokeapi.co/] e armazenada localmente, implementar gerenciamento de estado das view e testes instrumentados usando Jetpack Compose.
A arquitetura MVVM foi usada para separação de escopos e camadas.

Se houvesse mais tempo, eu teria invertido em mais informações na tela de detalhes do pokémon selecionado, como a mudança dos dados apresentados quando fosse clicado em alguma das evoluções apresentadas no detalhamento. Também ficou faltando testes instrumentados para a HomeScreen.kt.

## Bibliotecas usadas
| Lib       | README                                                                        |
|-----------|-------------------------------------------------------------------------------|
| Retrofit2 | https://square.github.io/retrofit/                                            |
| Okhttp3   | https://square.github.io/okhttp/                                              |
| Paging    | https://developer.android.com/topic/libraries/architecture/paging/v3-overview |
| Room      | https://developer.android.com/training/data-storage/room                      |
| Koin      | https://insert-koin.io/docs/quickstart/android                                |
| Coil-kt   | https://coil-kt.github.io/coil/compose/                                       |                                    |