package ru.jb.ushaev.literals;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that performs the counting of repeated
 * string literals in the code of some programming language
 */
public class RepCounter {

    private final BufferedReader br;
    private final Pattern searchPattern;

    /**
     * Constructor that creates default regexp that matches
     * string literals in single or double quotes
     *
     * @param br initialized <code>BufferedReader</code> mapped to the file to read from
     */
    public RepCounter(BufferedReader br) {
        this.br = br;
        this.searchPattern = Pattern.compile("\"(\\\\.|[^\"])*\"|'(\\\\.|[^'])*'");
    }

    /**
     * Constructor that provides the ability to use custom regexp
     * for string literals matching
     *
     * @param br initialized <code>BufferedReader</code> mapped to the file to read from
     * @param searchPattern <code>Pattern</code> regexp to use for matching
     */
    public RepCounter(BufferedReader br, Pattern searchPattern) {
        this.br = br;
        this.searchPattern = searchPattern;
    }

    /**
     * Method for finding duplicate string literals
     *
     * @return <code>HashMap</code> with string literal as key and <code>Set</code> with line numbers as value
     * @throws IOException if any file IO problem occurs
     */
    public Map<String, Set<Integer>> findReps() throws IOException {

        Map<String, Set<Integer>> literalMap = new HashMap<>();
        int curLine = 0;
        String line;

        while ((line = br.readLine()) != null) {
            Matcher m = searchPattern.matcher(line);

            while (m.find()) {
                String literal = m.group(0);

                // remove outer quotes
                literal = literal.substring(1, literal.length() - 1);

                int finalCurLine = curLine;
                literalMap.computeIfAbsent(literal, k -> new HashSet<>(Collections.singletonList(finalCurLine))).add(finalCurLine);
            }
            curLine++;
        }

        return literalMap;
    }
}
