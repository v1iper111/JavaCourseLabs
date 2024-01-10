package com.sikalo.university.lab4;

import com.sikalo.university.lab4.DecodeMethods.CaesarCipherDecoder;
import com.sikalo.university.lab4.DecodeMethods.RankCipherDecoder;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaDecoder {
    private final static List<Character> VOWELS = List.of('a', 'e', 'i', 'o', 'u', 'y');
    private final static List<Character> CONSONANTS = getConsonants();

    private final static Decoder vowelsDecoder = new RankCipherDecoder(VOWELS);
    private final static Decoder consonantDecoder = new CaesarCipherDecoder(CONSONANTS);

    public static String decode(String word) {
        if (vowelsDecoder.isEncoded(word)) {
            return vowelsDecoder.decode(word);
        } else if (consonantDecoder.isEncoded(word)) {
            return consonantDecoder.decode(word);
        }
        return word;
    }

    public static String decodeText(String text) {
        return Arrays.stream(text.split("\\s"))
                .map(JavaDecoder::decode)
                .collect(Collectors.joining(" "));
    }

    public static boolean isEncoded(String word) {
        return vowelsDecoder.isEncoded(word) || consonantDecoder.isEncoded(word);
    }

    private static List<Character> getConsonants() {
        return Stream.iterate('a', character -> (int) character <= (int) 'z', character -> (char) ((int) character + 1))
                .filter(Predicate.not(VOWELS::contains))
                .toList();
    }
}
