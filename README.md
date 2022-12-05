![3Hero](https://user-images.githubusercontent.com/72557256/205545058-258cd8f0-9e3c-487a-9e92-bb91e24fc56d.gif)

<br>
<h1 align="center">Eight Queens Puzzle - AI</h1>

<img src="http://img.shields.io/static/v1?label=STATUS&message=FINALIZADO&color=green&style=for-the-badge"/> &nbsp;&nbsp;<img src="https://img.shields.io/badge/License-MIT-red.svg?style=for-the-badge" />


<br>

## Descrição

<p>O problema das oito damas é o problema matemático de dispor oito damas em um tabuleiro de xadrez de dimensão 8x8, de forma que nenhuma delas seja atacada por outra. Para tanto, é necessário que duas damas quaisquer não estejam numa mesma linha, coluna, ou diagonal.<br><br><em>Para a correção do problema considere a implementação em Python, porque, os testes foram feitos com esta implementação.<em></p>
<br>

## Implementações :snake:
* Evolutivo Python :heavy_check_mark:
* Subida da encosta Python :heavy_check_mark:
* Evolutivo Java [**extra**] :heavy_check_mark:

<br>

## Dependências Python :stop_sign:
```sh
$ sudo apt update
$ sudo apt install python3
```

<br>

## Uso :computer:
:cop: _Instale as dependências._ :cop:
```sh
# clonar e abrir o projeto
$ cd python
$ python3 evolve.py    # para o algoritmo evolutivo
$ python3 subida.py    # para a subida da encosta
```

<br>

## Resultados
Os testes foram executados com a implementação em Python do problema **_Eight Queens Puzzle_**.

### Evolutivo
| População  | Taxa de Mutação | Resultado |
| :--------: | :-------------: | :-------: |
| 100        |       0.2       | 3 / 10    |
| 150        |       0.2       | 4 / 10    |
| 200        |       0.2       | 9 / 10    |

<br>

### Subida da encosta
função que irá avaliar a qualidade dos sucessores é h(x) = quantidade de pares de rainhas que se atacam no tabuleiro

| Resultado |
| :-------: |
| 10%       |

<br>

<div align="center">
  <h2>Evolutivo   /   Subida da encosta</h1><img width="380px" height="180px" src="https://user-images.githubusercontent.com/72557256/205530426-f1a5dfea-8739-4e11-a33a-2ece32147b74.jpeg" />
  &nbsp;&nbsp;<img width="380px" height="180px" src="https://user-images.githubusercontent.com/72557256/205534110-cb595307-e8c7-4cbf-b1e5-af60022c2e20.jpeg" />
</div>


