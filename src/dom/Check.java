package dom;

public class Check {
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int checkLimit(String valor, int[] limitTo) {
        if (isInteger(valor)) {
            int num = Integer.parseInt(valor);
            for (int limit : limitTo) {
                if (num == limit) {
                    return num;
                }
            }
        }
        return 0;
    }

    public static int checkGenerations(String valor) {
        return (isInteger(valor) && Integer.parseInt(valor) >= 0) ? Integer.parseInt(valor) : 0;
    }

    public static int checkSpeed(String valor) {
        return (isInteger(valor) && Integer.parseInt(valor) >= 250 && Integer.parseInt(valor) <= 1000)
                ? Integer.parseInt(valor) : 0;
    }

    public static boolean isPresentValue(int value) {
        return value > 0;
    }

    public static boolean isValidPattern(String p, int maxColumns) {
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
