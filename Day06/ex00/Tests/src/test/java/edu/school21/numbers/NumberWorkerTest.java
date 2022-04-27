package edu.school21.numbers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {
    @ParameterizedTest
    @ValueSource(ints = {95219, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47})
    void isPrimeForPrimes(int prime) throws Exception {
        assertTrue(new NumberWorker().isPrime(prime));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 10, 122, 6666666, 23232344, 14, 46, 99, 49})
    void isPrimeForNotPrimes(int prime) throws Exception {
        assertFalse(new NumberWorker().isPrime(prime));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -123})
    void isPrimeForIncorrectNumbers(int incorrect) throws Exception {
        assertThrows(RuntimeException.class, () -> new NumberWorker().isPrime(incorrect));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", delimiter = ',')
    void isSumIncorrect(int input, int expected) throws Exception {
        assertEquals(expected, new NumberWorker().digitsSum(input));
    }

    @Test
    void negativeSum() throws Exception {
        assertEquals(-10, new NumberWorker().digitsSum(-154));
    }
}
