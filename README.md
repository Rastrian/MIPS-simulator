# MIPS CONVERTER
[![Build Status](https://travis-ci.org/Rastrian/MIPS-converter.svg?branch=master)](https://travis-ci.org/Rastrian/MIPS-converter)

Este projeto foi realizado como trabalho da disciplina Arquitetura de Computadores da PUC MG.
O mesmo se trata de um decodificador de comandos de linguagem de montagem para linguagem de maquina do MIPS.
Utilizamos o Java para realizar este projeto, incluindo um MakeFile para facilitar a instalação.

### Listas de instruções que podem ser utilizadas:

| Tipos de Função | Funções                    |
| ------------- | ------------------------------ |
| Aritméticas      | **add, addi sub, mult, div, neg.**       |
| Lógicas   | **and, andi, or, ori, xor, nor, slt, slti**     |
| Deslocamento Bit a Bit   | **sll, srl**     |
| Acesso à Memória   | **lw, sw**     |
| Desvio Condicional   | **beq, bne**     |
| Desvio Incondicional   | **j, jr, jal**     |
| Outras   | **nop**     |

## Instalação

Utilizamos o MakeFile para simplificar a instalação, ele irá baixar automaticamente as dependencias, realizar o build do código e executar.
Ele instalará automaticamente os seguintes pacotes: default-jre default-jdk (JDK JAVA 8)

```$ make```

**Plataformas Compativeis:** Linux Ubuntu 18.04

## UML (Diagrama de Classes)

<div style="text-align: center">
<a href='https://raw.githubusercontent.com/Rastrian/MIPS-converter/master/UML.jpg'><img src="https://raw.githubusercontent.com/Rastrian/MIPS-converter/master/UML.jpg"/></a>
</div>

## TRELLO

<div style="text-align: center">
<a href='https://raw.githubusercontent.com/Rastrian/MIPS-converter/master/trello.png'><img src="https://raw.githubusercontent.com/Rastrian/MIPS-converter/master/trello.png"/></a>
</div>