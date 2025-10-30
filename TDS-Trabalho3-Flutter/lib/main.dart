import 'package:flutter/material.dart';
import 'dart:math'; // Precisamos disso para o Random

void main() {
  runApp(const MyApp()); // Adicionado 'const'
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key); // <-- CORREÇÃO 1

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Gerador de Senha',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const Tela(), // Adicionado 'const'
    );
  }
}

// --- INTERFACE (Widget com Estado) ---
class Tela extends StatefulWidget {
  const Tela({Key? key}) : super(key: key); // <-- CORREÇÃO 2

  @override
  State<Tela> createState() => _TelaState(); // <-- CORREÇÃO 3
}

// A classe de Estado que guarda as variáveis
class _TelaState extends State<Tela> {
  // Variáveis de estado
  String _senha = "";
  String _mensagemErro = "";

  // Controlador para ler o texto do campo de entrada
  final TextEditingController _tamanhoController = TextEditingController();

  // --- FUNCAO LOGICA ---
  String _gerarSenha(int tamanho) {
    const String caracteres =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#\$%^&*-_+=";
    final random = Random();

    return List.generate(tamanho, (index) {
      return caracteres[random.nextInt(caracteres.length)];
    }).join();
  }

  // --- FUNÇÃO DE VALIDAÇÃO E AÇÃO DO BOTÃO ---
  void _validarEGerarSenha() {
    final int? t = int.tryParse(_tamanhoController.text);

    setState(() {
      if (t == null) {
        _mensagemErro = "Digite um tamanho entre 8 e 20 caracteres";
        _senha = "";
      } else if (t < 8 || t > 20) {
        _mensagemErro = "Digite um tamanho entre 8 e 20 caracteres";
        _senha = "";
      } else {
        _senha = _gerarSenha(t);
        _mensagemErro = "";
      }
    });
  }

  // O método build descreve como a UI deve ser construída
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Gerador de Senha Flutter'), // Adicionado 'const'
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(35.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[

              TextField(
                controller: _tamanhoController,
                decoration: const InputDecoration( // Adicionado 'const'
                  labelText: "Tamanho da senha",
                  labelStyle: TextStyle(fontSize: 20.0),
                  border: OutlineInputBorder(),
                ),
                style: const TextStyle(fontSize: 20.0), // Adicionado 'const'
                keyboardType: TextInputType.number,
              ),

              const SizedBox(height: 30.0), // Adicionado 'const'

              ElevatedButton(
                onPressed: _validarEGerarSenha,
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15), // Adicionado 'const'
                ),
                child: const Text( // Adicionado 'const'
                  "Gerar Senha",
                  style: TextStyle(fontSize: 30.0),
                ),
              ),

              const SizedBox(height: 30.0), // Adicionado 'const'

              if (_mensagemErro.isNotEmpty)
                Text(
                  _mensagemErro,
                  style: const TextStyle( // Adicionado 'const'
                    color: Colors.red,
                    fontSize: 25.0,
                  ),
                  textAlign: TextAlign.center,
                )
              else if (_senha.isNotEmpty)
                Text(
                  _senha,
                  style: const TextStyle( // Adicionado 'const'
                    fontSize: 25.0,
                    fontWeight: FontWeight.bold,
                  ),
                ),
            ],
          ),
        ),
      ),
    );
  }

  @override
  void dispose() {
    _tamanhoController.dispose();
    super.dispose();
  }
}