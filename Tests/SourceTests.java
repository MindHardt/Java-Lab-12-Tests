import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class SourceTests {

    @Test
    public void test() {
        // Проверка 0 нулевой длины
        Assert.assertThrows(NumberFormatException.class, () -> Source.decode(""));

        // Проверка отрицательного числа при символе -
        Assert.assertTrue(Source.decode("-1") < 0);
        // Проверка положительного числа при символе +
        Assert.assertTrue(Source.decode("+1") > 0);
        // Проверка положительного числа без символа +
        Assert.assertTrue(Source.decode("1") > 0);

        // Проверка равенства +0 и -0
        Assert.assertEquals(Source.decode("+0"), Source.decode("-0"));

        // Проверка системы счисления
        Assert.assertEquals(Source.decode("16"), Source.decode("0X10"));
        Assert.assertEquals(Source.decode("16"), Source.decode("0x10"));
        Assert.assertEquals(Source.decode("16"), Source.decode("#10"));
        Assert.assertEquals(Source.decode("-32"), Source.decode("-0X20"));
        Assert.assertEquals(Source.decode("-32"), Source.decode("-0x20"));
        Assert.assertEquals(Source.decode("-32"), Source.decode("-#20"));
        Assert.assertEquals(Source.decode("8"), Source.decode("010"));
        Assert.assertEquals(Source.decode("8"), Source.decode("010"));
        Assert.assertEquals(Source.decode("-16"), Source.decode("-020"));
        Assert.assertEquals(Source.decode("-16"), Source.decode("-020"));

        // Проверка неверной позиции знака
        Assert.assertThrows(NumberFormatException.class, () -> Source.decode("0x-16"));
        Assert.assertThrows(NumberFormatException.class, () -> Source.decode("#+32"));

        // Проверка Integer.MIN_VALUE
        Assert.assertEquals(Optional.of(Integer.MIN_VALUE), Optional.of(Source.decode("-2147483648")));

        // Проверка Integer.MAX_VALUE
        Assert.assertEquals(Optional.of(Integer.MAX_VALUE), Optional.of(Source.decode("2147483647")));
    }
}