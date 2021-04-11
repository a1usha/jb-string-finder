package ru.jb.ushaev.literals;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.err.println("Specify file path to parse");
            System.exit(1);
        }

        File file = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(file));

        RepCounter repCounter = new RepCounter(br);
        Map<String, Set<Integer>> literals = repCounter.findReps();

        for (Map.Entry<String, Set<Integer>> entry : literals.entrySet()) {
            if (entry.getValue().size() != 1) {

                String lines = entry.getValue().stream().map(Object::toString)
                        .collect(Collectors.joining(", "));

                System.out.printf("Lines with '%s': ", entry.getKey());
                System.out.println(lines);
            }
        }
    }
}
