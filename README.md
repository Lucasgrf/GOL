# Game Of Life

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

### Controles
- **Espaço**: Pausar/Continuar a simulação.

---

## Parâmetros de Configuração

Os parâmetros podem ser passados via linha de comando no seguinte formato:

#### java app.GameOfLife w=<largura, matriz> h=<altura, matriz> g=<número de gerações> s=<velocidade da geração> n=<tipo de layout, padrão é 3> p="<população inicial>"


### Descrição dos Parâmetros

- `w`: Largura da grid (Qualquer valor inteiro >= 10 e <= 500)
- `h`: Altura da grid (Qualquer valor inteiro >= 10 e <= 500)
- `g`: Número de gerações a serem simuladas (0 ou não informado = Infinito)
- `s`: Velocidade (delay em milissegundos entre gerações, min 10ms)
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
javac -d out src/app/GameOfLife.java src/config/GameOfLifeConfig.java src/dom/Cell.java src/dom/Grid.java src/render/SwingRenderer.java src/util/Check.java src/util/GameOfLifeRunner.java

java -cp out app.GameOfLife w=40 h=40 s=100 p="rnd" n=3
```

### Executando com Maven

Se preferir, você pode compilar e rodar usando o Maven:

1. **Compilar e criar o JAR:**
   ```bash
   mvn clean package
   ```

2. **Rodar o JAR gerado:**
   ```bash
   # Exemplo Randomizado
   java -jar target/game-of-life-1.0-SNAPSHOT.jar w=50 h=50 s=50 p="rnd"

   # Exemplo com Padrão Manual
   java -jar target/game-of-life-1.0-SNAPSHOT.jar w=20 h=20 s=200 p="010#101#010"
   ```



