# Cellular Automata Kotlin

Basic Visualization of Wolfram's cellular automata.  Core module is written in pure Kotlin and utilizes 
multiplatform support to allow Android, CLI, and iOS(not implemented yet).  Core engine can general all rulesets (1-256).
High-level overview is below.  For more detail, view the JavaDocs.

Generations are stored as an `BooleanArray`.  On the JVM, kotlin compiles this to a `boolean[]`, which uses less memory than `Boolean[]` or `List<Boolean>`.
Initially I planned to use Bitsets for each generation, however, Kotlin does not offer a Bitset.  Using a native Android bitmap to store the generations, and then drawing the bitmap to canvas would be a more performant solution.

## Modules

__common__ - Pure Kolin module which contains the CellularGenerator class for generating Cellular Automata.  Unit tests are contained here, but must be run from a platform implementation module.

__android__ - Android app showing cellular automata with options for ruleset and block size. Standard Android setup.  
                Visualization is completed with `CellularView`, a custom Android view that displays the automata as scrolling up on the screen.  
                CellularView maintains a `List<BooleanArray>` which is a history of all the generations.  In `onDraw()` canvas.setPixel is used to draw the black pixels.

__jvm__ - JVM CLI implementation using text output.  The ruleset can be specified as an argumentCan be run using gradle with:

            ./gradlew jvm:run -PappArgs="['90']"


## Unit Tests

Unit tests for the common module can be run with:

            ./gradlew jvm:build
    
Test report are placed in `jvm/build/reports/tests/test/index.html`

Rule 30 CLI output:


                                        █
                                       ███
                                      ██  █
                                     ██ ████
                                    ██  █   █
                                   ██ ████ ███
                                  ██  █    █  █
                                 ██ ████  ██████
                                ██  █   ███     █
                               ██ ████ ██  █   ███
                              ██  █    █ ████ ██  █
                             ██ ████  ██ █    █ ████
                            ██  █   ███  ██  ██ █   █
                           ██ ████ ██  ███ ███  ██ ███
                          ██  █    █ ███   █  ███  █  █
                         ██ ████  ██ █  █ █████  ███████
                        ██  █   ███  ████ █    ███      █
                       ██ ████ ██  ███    ██  ██  █    ███
                      ██  █    █ ███  █  ██ ███ ████  ██  █
                     ██ ████  ██ █  ██████  █   █   ███ ████
                    ██  █   ███  ████     ████ ███ ██   █   █
                   ██ ████ ██  ███   █   ██    █   █ █ ███ ███
                  ██  █    █ ███  █ ███ ██ █  ███ ██ █ █   █  █
                 ██ ████  ██ █  ███ █   █  ████   █  █ ██ ██████
                ██  █   ███  ████   ██ █████   █ █████ █  █     █
               ██ ████ ██  ███   █ ██  █    █ ██ █     █████   ███
              ██  █    █ ███  █ ██ █ ████  ██ █  ██   ██    █ ██  █
             ██ ████  ██ █  ███ █  █ █   ███  ████ █ ██ █  ██ █ ████
            ██  █   ███  ████   ████ ██ ██  ███    █ █  ████  █ █   █
           ██ ████ ██  ███   █ ██    █  █ ███  █  ██ ████   ███ ██ ███
          ██  █    █ ███  █ ██ █ █  █████ █  ██████  █   █ ██   █  █  █
         ██ ████  ██ █  ███ █  █ ████     ████     ████ ██ █ █ █████████
        ██  █   ███  ████   ████ █   █   ██   █   ██    █  █ █ █        █
       ██ ████ ██  ███   █ ██    ██ ███ ██ █ ███ ██ █  █████ █ ██      ███
      ██  █    █ ███  █ ██ █ █  ██  █   █  █ █   █  ████     █ █ █    ██  █
     ██ ████  ██ █  ███ █  █ ████ ████ █████ ██ █████   █   ██ █ ██  ██ ████
    ██  █   ███  ████   ████ █    █    █     █  █    █ ███ ██  █ █ ███  █   █



Android Rule 30 output:

![Android rule 30](https://raw.githubusercontent.com/patjackson52/CellularAutomataKolin/master/Screenshot_1537188061.png)