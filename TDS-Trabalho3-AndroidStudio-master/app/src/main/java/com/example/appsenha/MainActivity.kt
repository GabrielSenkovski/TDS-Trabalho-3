package com.example.appsenha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
import com.example.appsenha.ui.theme.AppSenhaTheme

// novas importacoes
import kotlin.random.Random
import androidx.compose.ui.Alignment
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppSenhaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding).fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Tela()
                    }
                }
            }
        }
    }
}

// --- FUNCAO LOGICA
fun gerarSenha(tamanho: Int): String { // parametros da funcao

    val caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*-_+="
    var senha = ""

    repeat(tamanho) { // loop para gerar cada caracter da senha
        senha += caracteres[Random.nextInt(caracteres.length)]
    }
    return senha
}


// --- INTERFACE
@Composable
fun Tela() {
    // atualizar a UI
    var senha by remember {mutableStateOf("")}
    var tamanho by remember {mutableStateOf("")}
    var mensagemErro by remember {mutableStateOf("")} //variavel para erros

    Column( // organizacao do layout em colunas
        modifier = Modifier.fillMaxSize().padding(35.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // campo para digitar entrada
        OutlinedTextField(
            value = tamanho,
            onValueChange = { tamanho = it },
            label = {Text("Tamanho da senha", fontSize = 20.sp)},
            textStyle = TextStyle(fontSize = 20.sp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // botão
        Button(onClick = {
            val t = tamanho.toIntOrNull()
            if (t == null){
                mensagemErro = "Digite um tamanho entre 8 e 20 caracteres"
            }
            else if (t < 8 || t > 20){
                mensagemErro = "Digite um tamanho entre 8 e 20 caracteres"
                senha = ""
            }
            else{
                senha = gerarSenha(t)
                mensagemErro = "" // limpa erro
            }
        }) {
            Text("Gerar Senha", fontSize = 30.sp)
        }

        Spacer(modifier = Modifier.height(30.dp))

        // mostra mensagem de erro ou senha
        if (mensagemErro.isNotEmpty()){
            Text(text = mensagemErro,
                color = androidx.compose.ui.graphics.Color.Red,
                fontSize = 25.sp,
                textAlign = TextAlign.Center)
        } else if (senha.isNotEmpty()){
            Text(text = senha, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
    }
}