public class Source {
    public static Integer decode(String nm) throws NumberFormatException {
        int radix = 10;
        int index = 0;
        boolean negative = false;
        Integer result;

        if (nm.length() == 0)
            throw new NumberFormatException("Zero length string"); // Проверка 0 нулевой длины
        char firstChar = nm.charAt(0);
        // Handle sign, if present
        if (firstChar == '-') { // Проверка отрицательного числа при символе -
            negative = true;
            index++;
        } else if (firstChar == '+') // Проверка положительного числа при символе +
            index++;
        // Проверка положительного числа без символа +
        // Проверка равенства +0 и -0

        // Handle radix specifier, if present
        if (nm.startsWith("0x", index) || nm.startsWith("0X", index)) { // Проверка системы счисления
            index += 2;
            radix = 16;
        }
        else if (nm.startsWith("#", index)) {
            index ++;
            radix = 16;
        }
        else if (nm.startsWith("0", index) && nm.length() > 1 + index) {
            index ++;
            radix = 8;
        }

        if (nm.startsWith("-", index) || nm.startsWith("+", index)) // Проверка неверной позиции знака
            throw new NumberFormatException("Sign character in wrong position");

        try {
            result = Integer.valueOf(nm.substring(index), radix);
            result = negative ? Integer.valueOf(-result.intValue()) : result; // Проверка Integer.MIN_VALUE
        } catch (NumberFormatException e) {
            // If number is Integer.MIN_VALUE, we'll end up here. The next line
            // handles this case, and causes any genuine format error to be
            // rethrown.
            String constant = negative ? ("-" + nm.substring(index))
                    : nm.substring(index);
            result = Integer.valueOf(constant, radix);
        }
        return result;
    }
}


