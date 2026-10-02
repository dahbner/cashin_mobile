```markdown
# Plantilla Maestra - Examen KMP (Arquitectura Limpia)

**Instrucciones de uso:**
1. Crea un paquete principal con el nombre de tu funcionalidad en minúsculas (reemplaza `[entidad]`).
2. Reemplaza `[Entidad]` por el nombre de la entidad en formato PascalCase (ejemplo: si la API es de perros, usa `Dog`).
3. Reemplaza `[entidad]` por el nombre en minúsculas (ejemplo: `dog`).
4. Modifica los campos de los DTOs según el JSON exacto que te entreguen en el examen.
5. Cambia el paquete base `org.ucb.proyecto_prueba` por el paquete real de tu aplicacion si es distinto.

---

## 1. Capa: Data (Consumo de API)
Esta capa contiene cinco subcarpetas: `dto`, `datasource`, `service`, `mapper` y `repository`.

### Subcarpeta: data/dto
Contiene las clases de datos puros que modelan la respuesta cruda de la API.

**Crear Data Class: `[Entidad]ResponseDto.kt`**
```kotlin
package org.ucb.proyecto_prueba.[entidad].data.dto

import kotlinx.serialization.Serializable

@Serializable
data class [Entidad]ResponseDto(
    // Verifica si el JSON principal devuelve un arreglo llamado "results" u otro nombre
    val results: List<[Entidad]Dto> 
)

```

**Crear Data Class: `[Entidad]Dto.kt**`

```kotlin
package org.ucb.proyecto_prueba.[entidad].data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class [Entidad]Dto(
    // Modifica estos campos para que coincidan EXACTAMENTE con el JSON de la API
    val id: Int? = null,
    val title: String? = null,
    @SerialName("poster_path") val imagePath: String? = null
)

```

### Subcarpeta: data/datasource

Contiene la definicion de las operaciones de red.

**Crear Interfaz: `[Entidad]RemoteDatasource.kt**`

```kotlin
package org.ucb.proyecto_prueba.[entidad].data.datasource

import org.ucb.proyecto_prueba.[entidad].data.dto.[Entidad]ResponseDto

interface [Entidad]RemoteDatasource {
    suspend fun getList(): List<[Entidad]Dto>
}

```markdown
### Subcarpeta: data/service

Contiene la logica de Ktor para realizar la peticion HTTP con manejo de excepciones.

**Crear Clase: `[Entidad]Service.kt`**

```kotlin
package org.ucb.proyecto_prueba.[entidad].data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.ucb.proyecto_prueba.[entidad].data.datasource.[Entidad]RemoteDatasource
import org.ucb.proyecto_prueba.[entidad].data.dto.[Entidad]Dto

class [Entidad]Service : [Entidad]RemoteDatasource {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun getList(): List<[Entidad]Dto> {
        val response = client.get("[https://api.tuexamen.com/v1/](https://api.tuexamen.com/v1/)[entidad]")
        try {
            val body = response.body<List<[Entidad]Dto>>()
            return body
        } catch (e: Exception) {
            throw e
        }
    }
}

```

### Subcarpeta: data/mapper

Contiene las conversiones directas entre el DTO y el modelo limpio utilizando la sintaxis de igualación.

**Crear Archivo (Funcion de extension): `[Entidad]Mapper.kt**`

```kotlin
package org.ucb.proyecto_prueba.[entidad].data.mapper

import org.ucb.proyecto_prueba.[entidad].data.dto.[Entidad]Dto
import org.ucb.proyecto_prueba.[entidad].domain.model.[Entidad]Model

fun [Entidad]Dto.toModel() = [Entidad]Model(
    id = id ?: "",
    title = title ?: "",
    imageUrl = imagePath ?: ""
)

```

### Subcarpeta: data/repository

Contiene la implementacion del repositorio que delega la obtencion de datos.

**Crear Clase: `[Entidad]RepositoryImpl.kt**`

```kotlin
package org.ucb.proyecto_prueba.[entidad].data.repository

import org.ucb.proyecto_prueba.[entidad].data.datasource.[Entidad]RemoteDatasource
import org.ucb.proyecto_prueba.[entidad].domain.model.[Entidad]Model
import org.ucb.proyecto_prueba.[entidad].domain.repository.[Entidad]Repository

class [Entidad]RepositoryImpl(
    val remote: [Entidad]RemoteDatasource
): [Entidad]Repository {
    override suspend fun get[Entidad]s(): Result<List<[Entidad]Model>> {
        return remote.fetchdata()
    }
}

```

---

## 2. Capa: Domain (Reglas de Negocio)

Esta capa es independiente de dependencias externas. Contiene `model`, `repository` y `usecase`.

### Subcarpeta: domain/model

Contiene la estructura de datos que usara toda la aplicacion.

**Crear Data Class: `[Entidad]Model.kt**`

```kotlin
package org.ucb.proyecto_prueba.[entidad].domain.model

data class [Entidad]Model(
    val id: Int,
    val title: String,
    val imageUrl: String
)

```

### Subcarpeta: domain/repository

Contiene los contratos para acceder a los datos.

**Crear Interfaz: `[Entidad]Repository.kt**`

```kotlin
package org.ucb.proyecto_prueba.[entidad].domain.repository

import org.ucb.proyecto_prueba.[entidad].domain.model.[Entidad]Model

interface [Entidad]Repository {
    suspend fun get[Entidad]s(): Result<List<[Entidad]Model>>
}

```

### Subcarpeta: domain/usecase

Contiene las acciones concretas de negocio.

**Crear Clase: `Get[Entidad]sUseCase.kt**`

```kotlin
package org.ucb.proyecto_prueba.[entidad].domain.usecase

import org.ucb.proyecto_prueba.[entidad].domain.model.[Entidad]Model
import org.ucb.proyecto_prueba.[entidad].domain.repository.[Entidad]Repository

class Get[Entidad]sUseCase(
    private val repository: [Entidad]Repository
) {
    suspend operator fun invoke(): Result<List<[Entidad]Model>> {
        return repository.get[Entidad]s()
    }
}

```

---

## 3. Capa: Presentation (Interfaz y Estado)

Esta capa contiene la vista y el manejo de estado dividida en `state`, `viewmodel`, `composable` y `screen`.

### Subcarpeta: presentation/state

Maneja el estado unidireccional y eventos.

**Crear Data Class: `CoinUiState.kt`**

```kotlin
package org.ucb.proyecto_prueba.coin.presentation.state

import org.ucb.proyecto_prueba.coin.domain.model.CoinModel

data class CoinUiState(
    val isLoading: Boolean = false,
    val list: List<CoinModel> = emptyList(),
    val error: String? = null
)
```

**Crear Sealed Interface: `CoinEvent.kt`**

```kotlin
package org.ucb.proyecto_prueba.coin.presentation.state

sealed interface CoinEvent {
    data class OnShowDetail(val id: String) : CoinEvent
}
```

**Crear Sealed Interface: `CoinEffect.kt`**

```kotlin
package org.ucb.proyecto_prueba.coin.presentation.state

sealed interface CoinEffect {
    data class ShowToast(val message: String) : CoinEffect
    object NavigateToDetail : CoinEffect
}
```

### Subcarpeta: presentation/viewmodel

Conecta la interfaz grafica con los casos de uso.

**Crear Clase: `CoinViewModel.kt`**

```kotlin
package org.ucb.proyecto_prueba.coin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.proyecto_prueba.coin.domain.usecase.GetCoinsUseCase
import org.ucb.proyecto_prueba.coin.presentation.state.CoinEffect
import org.ucb.proyecto_prueba.coin.presentation.state.CoinEvent
import org.ucb.proyecto_prueba.coin.presentation.state.CoinUiState

class CoinViewModel(
    val usecase: GetCoinsUseCase
) : ViewModel() {

    private val _effect = MutableSharedFlow<CoinEffect>()
    val effect = _effect.asSharedFlow()

    private val _state = MutableStateFlow(CoinUiState())
    val state = _state.asStateFlow()

    init {
        load()
    }

    fun emitEvent(event: CoinEvent) {
        when (event) {
            is CoinEvent.OnShowDetail -> {
                emitEffect(CoinEffect.NavigateToDetail)
            }
        }
    }

    private fun emitEffect(effect: CoinEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    fun load() {
        _state.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            val result = usecase.invoke()

            result.onSuccess { data ->
                _state.update {
                    it.copy(list = data, isLoading = false)
                }
            }
        }
    }
}
```

### Subcarpeta: presentation/composable

Componentes graficos aislados.

**Crear Archivo (Funcion Composable): `CardCoin.kt`**

```kotlin
package org.ucb.proyecto_prueba.coin.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.ucb.proyecto_prueba.coin.domain.model.CoinModel

@Composable
fun CardCoin(model: CoinModel) {
    val colors = MaterialTheme.colorScheme

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {
            AsyncImage(
                model = model.imageUrl,
                contentDescription = model.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = model.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
```

### Subcarpeta: presentation/screen

Pantalla principal.

**Crear Archivo (Funcion Composable): `CoinScreen.kt`**

```kotlin
package org.ucb.proyecto_prueba.coin.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.proyecto_prueba.coin.presentation.composable.CardCoin
import org.ucb.proyecto_prueba.coin.presentation.viewmodel.CoinViewModel

@Composable
fun CoinScreen(viewModel: CoinViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()

    if(state.value.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.value.list.size) {
                CardCoin(state.value.list[it])
            }
        }
    }
}
```

---

## 4. Inyeccion de Dependencias (Koin)

Estos archivos suelen ubicarse en una carpeta llamada `di` en el nivel raiz de la carpeta `shared/src/commonMain/kotlin/...`.

**Crear Archivo (Variable Module): `DataModule.kt**`

```kotlin
package org.ucb.proyecto_prueba.di

import org.koin.dsl.module
import org.ucb.proyecto_prueba.[entidad].data.datasource.[Entidad]RemoteDatasource
import org.ucb.proyecto_prueba.[entidad].data.repository.[Entidad]RepositoryImpl
import org.ucb.proyecto_prueba.[entidad].data.service.[Entidad]Service
import org.ucb.proyecto_prueba.[entidad].domain.repository.[Entidad]Repository

val dataModule = module {
    single<[Entidad]RemoteDatasource> { [Entidad]Service() }
    single<[Entidad]Repository> { [Entidad]RepositoryImpl(get()) }
}

```

**Crear Archivo (Variable Module): `DomainModule.kt**`

```kotlin
package org.ucb.proyecto_prueba.di

import org.koin.dsl.module
import org.ucb.proyecto_prueba.[entidad].domain.usecase.Get[Entidad]sUseCase

val domainModule = module {
    single { Get[Entidad]sUseCase(get()) }
}

```

**Crear Archivo (Variable Module): `PresentationModule.kt**`

```kotlin
package org.ucb.proyecto_prueba.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.ucb.proyecto_prueba.[entidad].presentation.viewmodel.[Entidad]ViewModel

val presentationModule = module {
    viewModelOf(::[Entidad]ViewModel)
}

```
