# Simple console paint application

Prerequirements:
- installed JRE 1.8
- installed maven

To run the program please follow next steps:
- build it first with `mvn clean verify`
- change directory to target `cd target`
- run the program using following command - `java -jar drawmaster-with-dependencies.jar` 
- input commands & enjoy

List of allowed commands:
```
==============================================================================================
| Command        | Description                                                               |
==============================================================================================
| C w h         | Should create a new canvas of width w and height h.                        |
----------------------------------------------------------------------------------------------
| L x1 y1 x2 y2 | Should create a new line from (x1,y1) to (x2,y2). Currently only           |
|               | horizontal or vertical lines are supported. Horizontal and vertical lines  |
|               | will be drawn using the 'x' character.                                     |
----------------------------------------------------------------------------------------------
| R x1 y1 x2 y2 | Should create a new rectangle, whose upper left corner is (x1,y1) and      |
|               | lower right corner is (x2,y2). Horizontal and vertical lines will be drawn |
|               | using the 'x' character.                                                   |
----------------------------------------------------------------------------------------------
| B x y c       | Should fill the entire area connected to (x,y) with "colour" c. The        |
|               | behaviour of this is the same as that of the "bucket fill" tool in paint   |
|               | programs.                                                                  |
----------------------------------------------------------------------------------------------
| E             | Erase created canvas. Basically re-creates it.                             |
----------------------------------------------------------------------------------------------
| Q             | Should quit the program.                                                   |
==============================================================================================
```
