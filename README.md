
Hunt The Wumpus game created as the final project for my CS231: Data Structures and Algorithms course 
Language: Java
Fall 2016

Introduction

For this project, the main goal was to expose us to the use of graphs through implementing a game called "Hunt the Wumpus",  a classic role-playing game with very simple rules, and the basic rule for this project can be found here http://cs.colby.edu/courses/F16/cs231-labs/labs/lab09/assignment.php .  This is a game which there is a hunter, searching for a wumpus ( deadly animal), and the objective is to kill it before it actually kills the hunter. Given that instruction, our role for the project was integrating the different aspects of data structures over the course of the semester, with an emphasis on graphs, in an attempt to create a game of this nature. The data structures I used mainly to implement this project were mainly, Doubly Linked lists, arraylists, arrays, Binary search tree map (BSTMap), priority que from last week, and a graph. The project was broken down into different aspects, and our objective was to follow the guidance we have been given. The game design was for a user, and it needed an active listener interface to imlemented, a JFrame for display, and then a KeyActionListener, for conditions of the game. After doing all the HARD WORK, the final project for the semester resulted in the I am about to elaborate on more below.

Project outline

Using the doubly linked lists, priority que heap, and BST maps from earlier projects, these were copied together with an agent class from project 5. I create multiple classes( and enum class) which interact with each other, and the main class being my HuntTheWumpus.java, whose main method advances the game, emulating the wumpus hunt. My game implementation is highlighted below, through my descriptions of the tasks.




