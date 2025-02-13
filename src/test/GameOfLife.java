package test;

import dom.Check;
import dom.Grid;
import java.util.ArrayList;
import java.util.List;

public class GameOfLife {
    public static void main(String[] args) {
        int w = 0, h = 0, g = 0, s = 0, n = 3;
        int[] limitToWidth = {10, 20, 30, 40, 80};
        int[] limitToHeight = {10, 20, 40};
        int[] typesOfLayout = {1, 2, 3, 4, 5};
        Grid grid;
        StringBuilder p = new StringBuilder();
        Check check = new Check();

        List<String> parametrosFaltantes = new ArrayList<>();
        parametrosFaltantes.add("width");
        parametrosFaltantes.add("height");
        parametrosFaltantes.add("generations");
        parametrosFaltantes.add("speed");
        parametrosFaltantes.add("population");

        for (String arg : args) {
            String[] split = arg.split("=", 2);
            if (split.length == 2) {
                String chave = split[0];
                String valor = split[1];
                switch (chave) {
                    case "w":
                        w = check.checkLimit(valor, limitToWidth);
                        System.out.println(w > 0 ? "width = " + w : "width = invalido");
                        parametrosFaltantes.remove("width");
                        break;
                    case "h":
                        h = check.checkLimit(valor, limitToHeight);
                        System.out.println(h > 0 ? "height = " + h : "height = invalido");
                        parametrosFaltantes.remove("height");
                        break;
                    case "g":
                        g = check.checkGenerations(valor);
                        System.out.println(g > 0 ? "generations = " + g : "generations = invalido");
                        parametrosFaltantes.remove("generations");
                        break;
                    case "s":
                        s = check.checkSpeed(valor);
                        System.out.println(s > 0 ? "speed = " + s : "speed = invalido");
                        parametrosFaltantes.remove("speed");
                        break;
                    case "n":
                        int num = check.checkLimit(valor, typesOfLayout);
                        if (check.isPresentValue(num)) {
                            n = num;
                        }
                        System.out.println((n == 3 ? "vizinhaça = " + n + " [Default]" :
                                        "vizinhaça = " + n + " [Layout " + n + "]"));
                        break;
                    case "p":
                        if (check.isValidPattern(valor, w)) {
                            p.append(valor.replace("\"", ""));
                            System.out.println("population = " + p);
                            parametrosFaltantes.remove("population");
                        } else {
                            System.out.println("population = invalido");
                        }
                        break;
                    default:
                        System.out.println("Argumento desconhecido: " + chave);
                        break;
                }
            }
        }
        if (n == 3) {
            System.out.println("vizinhaça = " + n + " [Default]");
        }
        
        if (!parametrosFaltantes.isEmpty()) {
            System.out.println("Parâmetros não informados:");
            for (String param : parametrosFaltantes) {
                System.out.println(" - " + param);
            }
        }

        if (w > 0 && h > 0 && !p.isEmpty()) {
            grid = new Grid(h, w);
            grid.initializeGrid(p.toString());
            System.out.println("Grid inicializada com sucesso!");
        } else {
            System.out.println("Erro ao inicializar a grid. Verifique os parâmetros.");
        }
    }
}
