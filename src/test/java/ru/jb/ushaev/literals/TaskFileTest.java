package ru.jb.ushaev.literals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class TaskFileTest {

    private BufferedReader br;

    @Before
    public void setup() throws URISyntaxException, FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource("test.py");
        assert resource != null;
        br = new BufferedReader(new FileReader(new File(resource.toURI())));
    }

    @Test
    public void taskFileTest() throws IOException {
        RepCounter repCounter = new RepCounter(br);
        Map<String, Set<Integer>> literals = repCounter.findReps();

        Map<String, Set<Integer>> reference = new HashMap<>();
        reference.put("Hello world!", new HashSet<>(Collections.singletonList(2)));
        reference.put("id", new HashSet<>(Arrays.asList(0, 5)));
        reference.put("value", new HashSet<>(Arrays.asList(0, 6)));

        Assert.assertEquals(literals, reference);
    }

    @Test
    public void testWithCustomRegexp() throws IOException {
        RepCounter repCounter = new RepCounter(br, Pattern.compile("'([^']*)'"));
        Map<String, Set<Integer>> literals = repCounter.findReps();

        Map<String, Set<Integer>> reference = new HashMap<>();
        reference.put("id", new HashSet<>(Arrays.asList(0, 5)));
        reference.put("value", new HashSet<>(Collections.singletonList(6)));

        Assert.assertEquals(literals, reference);
    }
}
