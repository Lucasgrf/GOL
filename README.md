# Capstone - Game Of Life

O **Game of Life** é uma simulação baseada em autômatos celulares criada por John Conway. Neste jogo, células em uma grade evoluem através de gerações de acordo com regras simples, criando padrões complexos ao longo do tempo. Esta implementação em Java utiliza **POO (Programação Orientada a Objetos)** e oferece uma interface gráfica com **Swing** para visualizar as gerações em tempo real.

---

## Funcionalidades

- Configuração flexível da grid (largura e altura).
- Definição de padrões iniciais de população.
- Controle do número de gerações a serem simuladas.
- Ajuste de velocidade para a evolução das gerações.
- Diferentes tipos de vizinhança (layouts).
- Interface gráfica usando **Swing** para visualizar o progresso do jogo.
- Arquitetura modular utilizando as seguintes classes principais:
  - `GameOfLife`: Classe principal que inicia o jogo.
  - `GameOfLifeConfig`: Processa e valida os parâmetros de entrada.
  - `GameOfLifeRunner`: Controla o fluxo do jogo.
  - `Grid`: Representa a grid e controla a evolução das células.
  - `Cell`: Representa cada célula na grid.
  - `SwingRenderer`: Renderiza a grid utilizando Swing.


---

## Regras do Jogo

1. **Qualquer célula viva com menos de 2 vizinhos vivos morre** (solidão).
2. **Qualquer célula viva com 2 ou 3 vizinhos vivos sobrevive** para a próxima geração.
3. **Qualquer célula viva com mais de 3 vizinhos vivos morre** (superpopulação).
4. **Qualquer célula morta com exatamente 3 vizinhos vivos se torna viva** (reprodução).

---

## Parâmetros de Configuração

Os parâmetros podem ser passados via linha de comando no seguinte formato:

#### java app.GameOfLife w=<largura, matriz> h=<altura, matriz> g=<número de gerações> s=<velocidade da geração> n=<tipo de layout, padrão é 3> p="<população inicial>"


### Descrição dos Parâmetros

- `w`: Largura da grid (Valores possíveis: 10, 20, 30, 40, 80)
- `h`: Altura da grid (Valores possíveis: 10, 20, 40)
- `g`: Número de gerações a serem simuladas
- `s`: Velocidade (delay em milissegundos entre gerações)
- `n`: Tipo de vizinhança/layout (Valores possíveis: 1, 2, 3, 4, 5)
- `p`: Padrão inicial de população, utilizando binários (0 = morta, 1 = viva)
  - Exemplo: `"010#101#010"` representa:
    ```
    010
    101
    010
    ```
  obs: a variável `p` tem um valor especial `"rnd"`, caso queira que isso seja aleatório.
---

## Exemplos de Execução

#### Compile e depois execute passando os paramêtros

##### Default
```bash
javac app/GameOfLife.java

java app.GameOfLife w=20 h=10 g=50 s=500 n=3 p="010#101#000"
```
##### Randomized
```bash
javac app/GameOfLife.java

java app.GameOfLife w=20 h=10 g=50 s=500 n=3 p="rnd"
```



