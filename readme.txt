COS126 Final Project: Implementation

Please complete the following questions and upload this readme.txt to the
TigerFile assignment for the "Final Project Implementation".


/**********************************************************************
 * Basic Information                                                  *
 **********************************************************************/

Name 1: Matt Drapkin

NetID 1: md5324

Name 2: Skyla Qian

NetID 2: sq7046

Project preceptor name: Esin Tureci

Project title: Sudoku

CodePost link for proposal feedback: https://codepost.io/code/488303

Link to project video: https://www.loom.com/share/d98d8551543a49ebb8f363b1505fed51

Approximate number of hours to complete the final project
Number of hours: 20

/**********************************************************************
 * Required Questions                                                 *
 **********************************************************************/

Describe your project in a few sentences.

Our project is a Sudoku game. The game starts with our program giving the user a
randomly selected unfilled sudoku board (read from StdIn as a .txt file).
After each move, the program checks if the entry has immediate conflicts in the
row, column, and mini-square for the position that entry was made for. The program
notifies if there is a conflict, and will not allow incorrect entries. Users have
the ability to delete previous moves.


Feature 1: Display the board Feature 2: Check if entry is "valid" by checking for immediate conflicts as described above. Feature 3: Allow deletion or undo.

Describe in detail your three features and where in your program
they are implemented (e.g. starting and ending line numbers,
function names, etc.).

1. The readBoard method (lines 54-71) and the printBoard method (lines 73-92) work
together to accomplish our first feature, displaying a randomly selected board.
The argument passed to the readBoard method is generated in one of our two
constructors (lines 34-43), which selects from 1 of 46 different unfilled sudoku
boards to give the user to begin the game with.

2. The isAccurate method (lines 135-190) checks the immediate conflicts after
the user makes a move (in rows, columns, and mini-squares). The checkMiniSquare
method (lines 119-133) helps organize the checking of each mini 3x3 square.  The
updateBoard method (lines 94-98) handles the updating of the board after a player
makes a move.

3. The deleteBoard method (lines 100-109) allows for users to delete spots that
they previously filled during the game.  A 9x9 boolean array keeps track of
which values were a part of the original board, ensuring that only user-selected
values may be removed.


Describe in detail how to compile and run your program. Include a few example
run commands and the expected results of running your program. For non-textual
outputs (e.g. graphical or auditory), feel free to describe in words what the
output should be or reference output files (e.g. images, audio files) of the
expected output.

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

Describe how your program accepts user input and mention the line number(s) at
which your program accepts user input.

Our program allows for the user to input desired coordinates and values to either
insert into the unfilled sudoku board (lines 225, 237, 238), or delete a spot in
the board (lines 269, 272, 273).  The program also allows for the user to decide
whether or not to quit the current game, make a move, or delete a spot at each term
by inputting a command (line 300).


Describe how your program produces output based on user input (mention line
numbers).

Our updateBoard method (lines 94-98) takes in 3 arguments that are passed
as integers received from StdIn.  The values are used to specify the coordinates
of the sudoku board, and the value which the user wants to insert at those
coordinates.  The printBoard method (lines 73-92) then prints the updated board.
The deleteBoard method works similarly (lines 100-109).  The user's input is passed
to the method; the coordinates the user enters are found in the current state
of the board and replaced with an empty space ("_"), so once the printBoard method
is called, that spot is no longer filled with a value.


Describe the data structure your program uses and how it supports your program's
functionality (include the variable name and the line number(s) at which it is
declared and initialized).

2D array - board (declared line 28, initialized line 36).  The board variable holds the
current state of the Sudoku board.  Two boolean 2D arrays are also used (lines 29-30
declared, lines 37-38 initialized).  These keep track of the spots which are currently
empty/filled and which spots were a part of the original board before any moves were
made (respectively).


List the two custom functions written by your project group, including function
signatures and line numbers; if your project group wrote more than two custom
functions, choose the two functions that were most extensively tested.

1. public static boolean isAccurate(int col, int row) lines 135-203

2. public static void printBoard() lines 73-92

List the line numbers where you test each of your two custom functions twice.
For each of the four tests (two for each function), explain what was being
tested and the expected result. For non-textual results (e.g. graphical or
auditory), you may describe in your own words what the expected result
should be or reference output files (e.g. images, audio files).

// TESTS WERE PERFORMED WITH s1.txt FOR THE PURPOSE OF CHECKING CUSTOM FUNCTIONS

1. (lines 307-309) printBoard Test 1 — testing to see if method correctly
prints out a formatted 9x9 Sudoku board, outputting data originally read from
.txt files to StdIn

2. (lines 310-313) isAccurate Test 1 — testing to see if method correctly
returns boolean "true" (since original sudoku board will have no immediate
conflicts)

3. (lines 317-319) printBoard Test 2 — testing to see if, after inputting
a value using updateBoard, the printBoard method will correctly output the
updated sudoku board

4. (lines 321-324) isAccurate Test 2 — testing to see if method correctly
returns boolean "false" (since mini-square in this board will now have a
conflict after updated)


/**********************************************************************
 * Citing Resources                                                   *
 **********************************************************************/

List below *EVERY* resource your project group looked at (bullet lists and
links suffice).

- https://introcs.cs.princeton.edu/java/11cheatsheet/
- http://lipas.uwasa.fi/~timan/sudoku/
- https://www.w3schools.com/java/java_try_catch.asp


Remember that you should *ALSO* be citing every resource that informed your
code at/near the line(s) of code that it informed.

Did you receive help from classmates, past COS 126 students, or anyone else?
If so, please list their names.  ("A Sunday lab TA" or "Office hours on
Thursday" is ok if you don't know their name.)
Yes or no?

no

Did you encounter any serious problems? If so, please describe.
Yes or no?

no


List any other comments here.



/**********************************************************************
 * Submission Checklist                                               *
 **********************************************************************/

Please mark that you’ve done all of the following steps:
[x] Created a project.zip file, unzipped its contents, and checked that our
    compile and run commands work on the unzipped contents. Ensure that the .zip
    file is under 50MB in size.
[x] Created and uploaded a Loom or YouTube video, set its thumbnail/starting
    frame to be an image of your program or a title slide, and checked that
    the video is viewable in an incognito browser.
[x] Uploaded all .java files to TigerFile.
[x] Uploaded project.zip file to TigerFile.

After you’ve submitted the above on TigerFile, remember to do the following:
[ ] Complete and upload readme.txt to TigerFile.
[ ] Complete and submit Google Form
    (https://forms.cs50.io/de2ccd26-d643-4b8a-8eaa-417487ba29ab).


/**********************************************************************
 * Partial Credit: Bug Report(s)                                      *
 **********************************************************************/

For partial credit for buggy features, you may include a bug report for at
most 4 bugs that your project group was not able to fix before the submission
deadline. For each bug report, copy and paste the following questions and
answer them in full. Your bug report should be detailed enough for the grader
to reproduce the bug. Note: if your code appears bug-free, you should not
submit any bug reports.

BUG REPORT #1:
1. Describe in a sentence or two the bug.




2. Describe in detail how to reproduce the bug (e.g. run commands, user input,
   etc.).




3. Describe the resulting effect of bug and provide evidence (e.g.
   copy-and-paste the buggy output, reference screenshot files and/or buggy
   output files, include a Loom video of reproducing and showing the effects of
   the bug, etc.).




4. Describe where in your program code you believe the bug occurs (e.g. line
   numbers).




5. Please describe what steps you tried to fix the bug.





/**********************************************************************
 * Extra Credit: Runtime Analysis                                     *
 **********************************************************************/

You may earn a small amount of extra credit by analyzing the efficiency of one
substantial component of your project. Please answer the following questions if
you would like to be considered for the extra credit for program analysis
(remember to copy and paste your answers to the following questions into the
Google form as well for credit).

Specify the scope of the component you are analyzing (e.g. function name,
starting and ending lines of specific .java file).




What is the estimated runtime (e.g. big-O complexity) of this component?
Provide justification for this runtime (i.e. explain in your own words why
you expect this component to have this runtime performance).




Provide experimental evidence in the form of timed analysis supporting this
runtime estimate. (Hint: you may find it helpful to use command-line
arguments/flags to run just the specified component being analyzed).





/**********************************************************************
 * Extra Credit: Packaging project as an executable .jar file         *
 **********************************************************************/

You may earn a small amount of extra credit by packaging your project as an
executable .jar file. Please answer the following question if you would like to
be considered for this extra credit opportunity (remember to copy and paste your
answers to the following questions into the Google form as well for credit).

Describe in detail how to execute your .jar application (e.g. what execution
command to use on the terminal). Include a few example execution commands and
the expected results of running your program. For non-textual outputs
(e.g. graphical or auditory), feel free to describe in words what the output
should be or reference output files (e.g. images, audio files) of the expected
output.



