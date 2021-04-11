package ru.jb.ushaev.literals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EmptyFileTest {

    private BufferedReader br;

    @Before
    public void setup() throws URISyntaxException, FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource("empty.py");
        assert resource != null;
        br = new BufferedReader(new FileReader(new File(resource.toURI())));
    }

    @Test
    public void shouldFindNothing() throws IOException {
        RepCounter repCounter = new RepCounter(br);
        Map<String, Set<Integer>> literals = repCounter.findReps();
        Map<String, Set<Integer>> reference = new HashMap<>();

        Assert.assertEquals(literals, reference);
    }
}
