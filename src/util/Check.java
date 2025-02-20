package util;

/**
 * A classe {@code Check} fornece métodos utilitários para validar diferentes tipos de entrada,
 * como números inteiros, limites predefinidos, valores de gerações, velocidade, e padrões de células.
 * Esses métodos ajudam a garantir que os dados fornecidos pelo usuário estejam dentro dos parâmetros válidos
 * para o funcionamento correto do jogo.
 */
public class Check {

    /**
     * Verifica se a string fornecida pode ser convertida para um número inteiro válido.
     *
     * @param s a string a ser verificada
     * @return {@code true} se a string for um número inteiro válido, caso contrário, {@code false}
     */
    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica se o valor fornecido está dentro de um conjunto de limites predefinidos.
     * O valor é comparado com os limites fornecidos e, se corresponder a um valor dentro
     * do conjunto de limites, o valor é retornado. Caso contrário, o valor 0 é retornado.
     *
     * @param valor   o valor a ser verificado
     * @param limitTo um array de inteiros que define os limites válidos
     * @return o valor fornecido se ele estiver dentro dos limites, caso contrário, retorna 0
     */
    public int limit(String valor, int[] limitTo) {
        if (isInteger(valor)) {
            int num = Integer.parseInt(valor);
            if (num > 0) {
                for (int limit : limitTo) {
                    if (num == limit) {
                        return num;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * Verifica se o valor fornecido representa um número válido de gerações.
     * As gerações devem ser um número inteiro maior ou igual a 0. Caso contrário, retorna -1.
     *
     * @param valor a string representando o valor de gerações
     * @return o valor de gerações se for válido, caso contrário, retorna -1
     */
    public int generations(String valor) {
        return (isInteger(valor) && Integer.parseInt(valor) >= 0) ? Integer.parseInt(valor) : -1;
    }

    /**
     * Verifica se o valor fornecido para a velocidade está dentro do intervalo válido (entre 250 e 1000).
     * Caso o valor esteja dentro desse intervalo, ele é retornado. Caso contrário, retorna 0.
     *
     * @param valor a string representando o valor da velocidade
     * @return o valor da velocidade se for válido, caso contrário, retorna 0
     */
    public int speed(String valor) {
        return (isInteger(valor) && Integer.parseInt(valor) >= 250 && Integer.parseInt(valor) <= 1000)
                ? Integer.parseInt(valor) : 0;
    }

    /**
     * Verifica se o valor fornecido é maior ou igual a 0.
     *
     * @param value o valor a ser verificado
     * @return {@code true} se o valor for maior ou igual a 0, caso contrário, {@code false}
     */
    public boolean isPresentValue(int value) {
        return value >= 0;
    }

    /**
     * Verifica se o padrão fornecido é válido. Um padrão é considerado válido se ele for composto por seções
     * separadas por '#', onde cada seção contém apenas os caracteres '0' e '1'. Além disso, o comprimento de
     * cada seção não pode exceder o número máximo de colunas permitido.
     *
     * @param p          o padrão a ser verificado
     * @param maxColumns o número máximo de colunas permitido em cada seção
     * @return {@code true} se o padrão for válido, caso contrário, {@code false}
     */
    public boolean isValidPattern(String p, int maxColumns) {
        if (p.isEmpty()) {
            return false;
        }

        String[] sections = p.split("#");

        for (String section : sections) {
            if (section.length() > maxColumns) {
                return false;
            }
            for (char c : section.toCharArray()) {
                if (c != '0' && c != '1') {
                    return false;
                }
            }
        }

        return true;
    }
}
