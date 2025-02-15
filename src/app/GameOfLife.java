package app;

import config.GameOfLifeConfig;
import util.GameOfLifeRunner;

/**
 * A classe {@code GameOfLife} é a aplicação principal que executa o jogo Game of Life. Ela processa
 * os parâmetros de entrada via linha de comando, configura o jogo com base nas entradas e inicia a execução
 * da simulação através da classe {@code GameOfLifeRunner}.
 */
public class GameOfLife {

    /**
     * O método principal que executa o jogo Game of Life. Ele recebe os parâmetros de configuração via
     * linha de comando, cria uma instância da configuração {@code GameOfLifeConfig} e inicia a simulação
     * utilizando a classe {@code GameOfLifeRunner}.
     *
     * @param args os parâmetros de linha de comando no formato chave=valor para configurar o jogo,
     *             como largura, altura, número de gerações, velocidade, etc.
     */
    public static void main(String[] args) {
        GameOfLifeConfig config = new GameOfLifeConfig(args);
        GameOfLifeRunner runner = new GameOfLifeRunner(config);
        runner.run();
    }
}
