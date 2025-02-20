package app;

import config.GameOfLifeConfig;
import util.GameOfLifeRunner;

/**
 * A classe {@code GameOfLife} é a aplicação principal do jogo Game of Life. Ela processa os parâmetros
 * de entrada via linha de comando, configura o jogo com base nas entradas fornecidas, e inicia a execução
 * da simulação através da classe {@code GameOfLifeRunner}.
 * <p>
 * O método {@code main} é o ponto de entrada para o programa e cuida da inicialização do jogo, passando
 * os parâmetros de configuração para a classe {@code GameOfLifeConfig}, que, por sua vez, é usada pela
 * classe {@code GameOfLifeRunner} para rodar a simulação.
 * </p>
 */
public class GameOfLife {

    /**
     * O método principal que executa o jogo Game of Life. Ele recebe os parâmetros de configuração via
     * linha de comando, cria uma instância da configuração {@code GameOfLifeConfig} e inicia a simulação
     * utilizando a classe {@code GameOfLifeRunner}.
     * <p>
     * O método processa os parâmetros fornecidos na linha de comando no formato chave=valor e os passa
     * para a classe {@code GameOfLifeConfig} para configurar o jogo. Após a configuração, o método cria
     * uma instância de {@code GameOfLifeRunner} e chama o método {@code run()} para iniciar a execução
     * da simulação.
     * </p>
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
