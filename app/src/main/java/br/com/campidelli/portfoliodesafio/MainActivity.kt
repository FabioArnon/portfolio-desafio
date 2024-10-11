package br.com.campidelli.portfoliodesafio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import br.com.campidelli.portfoliodesafio.service.State
import br.com.campidelli.portfoliodesafio.ui.theme.PortfolioDesafioTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val newsViewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            newsViewModel.initialPageNews.collect { state ->
                when (state) {
                    is State.Loading -> {
                        // Mostrar indicador de carregamento
                    }

                    is State.Success -> {
                        // Atualize a UI com o resultado de sucesso
                        val data = state.data
                        //data.feed.falkor?.nextPage
                    }

                    is State.Error -> {
                        // Atualize a UI com o resultado de falha
                        val exception = state.exception
                    }
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            PortfolioDesafioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        newsViewModel.fetchInitialPageNews("g1")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PortfolioDesafioTheme {
        Greeting("Android")
    }
}