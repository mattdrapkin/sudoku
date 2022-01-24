Link to project video: https://www.loom.com/share/d98d8551543a49ebb8f363b1505fed51

Our project is a Sudoku game. The game starts with our program giving the user a
randomly selected unfilled sudoku board (read from StdIn as a .txt file).
After each move, the program checks if the entry has immediate conflicts in the
row, column, and mini-square for the position that entry was made for. The program
notifies if there is a conflict, and will not allow incorrect entries. Users have
the ability to delete previous moves.

To compile the program, execute "javac-introcs Sudoku.java" at the command line.
To run the program, execute "java-introcs Sudoku" at the command line. Or, the
name of the .txt file containing an unfilled sudoku board may be provided as a
command-line argument, giving the user the control to select the desired starting
board — for instance, execute "java-introcs Sudoku s1.txt".

------------------------
e.g. java-introcs Sudoku

Welcome to Sudoku! To begin, please select:
(1) Start - enter "s"
(2) Quit - enter "q"
s

8 _ 2 |_ 5 _ |7 _ 1
_ _ 7 |_ 8 2 |4 6 _
_ 1 _ |9 _ _ |_ _ _
--------------------
6 _ _ |_ _ 1 |8 3 2
5 _ _ |_ _ _ |_ _ 9
1 8 4 |3 _ _ |_ _ 6
--------------------
_ _ _ |_ _ 4 |_ 2 _
_ 9 5 |6 1 _ |3 _ _
3 _ 8 |_ 9 _ |6 _ 7

Number:
------------------------
