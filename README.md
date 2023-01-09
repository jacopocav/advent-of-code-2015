# Advent of Code 2015

My solutions for [Advent of Code 2015](https://adventofcode.com/2015), written in Kotlin.

## Requirements

- Java 17
- Gradle (use the included gradlew)

## Structure

Every day of the challenge is separated into its own sub-module (`day01`, `day02`,..., `day25`).
For every day, there are two different input files: one is the **example** input, usually as
specified
in the description of the problem (`##-example.txt`, where `##` is the day number). The other file
is the **real** input that I downloaded from the AOC site (`##-input.txt`).

The module named `common` contains utilities used by many (if not all) days.

The root module contains the `main` function that can run any single day.

## How to run

### Arguments

The project has a single `main` function through which any day of the challenge can be run.

The main function takes the following positional arguments:

1. the day number, from 1 to 25 (this is the only **mandatory** argument)
2. the input file used to run the challenge. Must be either `example` or `real`. If not specified,
   the day will be executed on both files.
3. `debug`: if specified, the program might print additional information during execution.

Some example arguments:

- `16 example debug`
- `14`
- `9 debug`
- `25 real`

### Execution

The program can be run in two ways:

- through Gradle: `gradlew run --args <arguments>`
- through the fat jar: `java -jar build/libs/aoc2015-all.jar <arguments>` (must be built first
  with `gradlew build`)