package ru.jb.ushaev.literals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class EscapeFileTest {

    private BufferedReader br;

    @Before
    public void setup() throws URISyntaxException, FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource("with_escape.py");
        assert resource != null;
        br = new BufferedReader(new FileReader(new File(resource.toURI())));
    }

    @Test
    public void shouldCountEmptyStrings() throws IOException {
        RepCounter repCounter = new RepCounter(br);
        Map<String, Set<Integer>> literals = repCounter.findReps();

        Map<String, Set<Integer>> reference = new HashMap<>();
        reference.put("a \\\" \\\" \\\" b", new HashSet<>(Arrays.asList(3, 4)));
        reference.put("a \\' \\' \\' b", new HashSet<>(Arrays.asList(0, 1)));

        Assert.assertEquals(literals, reference);
    }
}
