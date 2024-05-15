[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/2tQa3OSu)
# CS214 Spring2024 - Final PA - Now What?

## Due: May 10th

## Objective

Produce new functionality using the code we have been working on all semester. Your code must have features that are objective improvements on the current codebase. See rubric in Canvas for more grading details.

## Submitting your work

* Must provide your own Project Description file by **April 29th** on `GitHub`.
* Your code submission must be in the main branch of your `GitHub` repository by the due date.
* Your video submission must be in the main branch of your `GitHub` repository by the due date.

# Example Project Description

## Motivation

In the previous assignments, you wrote a system that clustered songs according to (normalized)
user responses. Now a new user enters the system, and tells you they like songs X, Y and Z. You
program for an internet radio station called Quagga-ora. You need to quickly generate a list of
songs this user might like.

Pedagogically, you are encouraged to use java libraries for (merge) sorting and set union.


## Task

You will write a program that takes 5 or more arguments. The first 4
arguments are song titles, rankings, the output file, and K). The remaining arguments are the indices of song titles that the
new user likes. (Note: there must be at least 5 arguments, i.e. the new user must provide at least
one song they like. Otherwise it is an error.)

Your job is to cluster the songs into K clusters, as in Assignments 6 & 7. You will then open the
output file and write the names of songs that share a cluster with one of the songs indicated by the
new user. (Remember, the new user provides song indices on the command line.) You will not
write the names of all songs that share a cluster with a song designated by the user, however. You
will write the names of (up to) the 20 “best” song (in order), where the quality of a song is the
inverse of its Euclidean distance to a song designated by the user. (If the user designates two or
more songs in the same cluster, use the Euclidean distance to the nearest.)

To be clear, you should follow the following pseudo-algorithm:
```
For every song s indicated by the user on the command line
  For every song t in the same cluster as s
    If (t != s)
       Let d = 1.0 / dist(t,s)
  Sort list of t’s by corresponding d values
Merge lists of songs and distances
Sort list, write out up to the 20 best.
```


## How to check code

Run the code with the following command:

```
gradle run -q --args="'input_files/songFileName' 'input_files/ratingsFileName' 'output_files/songOut' K songIndex1 songIndex2 songIndex3"
```


# Policies

All work you submit must be your own. You may not submit code written by a
peer, a former student, or anyone else. You may not copy or buy code from the
web. The department academic integrity policies apply.

You may not submit your program late. To receive credit, it must be submitted
by the due date (with a 7 day extension). The exception is an unforeseeable
emergency, for example a medical crisis or a death in the immediate family.
If an unforeseeable emergency arises, talk to the instructor.

