# MIPS CONVERTER

Este projeto foi realizado como trabalho da disciplina Arquitetura de Computadores da PUC MG.
O mesmo se trata de um decodificador de comandos de linguagem de montagem para linguagem de maquina do MIPS.
Utilizamos o Java com Maven para realizar este projeto, incluindo um MakeFile para facilitar a instalação.

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

## UML (Diagrama de Classes)

<div style="text-align: center">
<a href='https://raw.githubusercontent.com/Rastrian/MIPS-converter/master/UML.jpg'><img src="https://raw.githubusercontent.com/Rastrian/MIPS-converter/master/UML.jpg"/></a>
</div>

## Instalação

Utilizamos o MakeFile para simplificar a instalação, ele irá baixar automaticamente as dependencias, realizar o build do código e executar.
Ele instalará automaticamente os seguintes pacotes: unzip default-jre default-jdk maven

```$ make```

**Plataformas Compativeis:** Linux Ubuntu 18.04