# Python string finder
An application that searches for string literals in a Python file and prints to stdout a list of literals that are used more than once.

To build the `jar` use `gradle jar`

Usage example:
```
> java -jar build\libs\string_counter-1.0-SNAPSHOT.jar test.py

Lines with 'id': 0, 5
Lines with 'value': 0, 6
```