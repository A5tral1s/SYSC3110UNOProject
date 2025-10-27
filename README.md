# SYSC3110UNOProject - Milestone 1

## Deliverables 
This milestone implements the core functionality of the UNO Flip game in Java using object-oriented design.  
The program allows 2–4 players to play a text-based version of UNO Flip that enforces valid moves, action cards, and scoring.

Key features implemented:
- Player setup and input validation (2–4 players)
- Deck creation and shuffling (includes number, action, and wild cards)
- Turn management and direction control (clockwise/counter-clockwise)
- Handling of action cards: SKIP, REVERSE, DRAW ONE, WILD, WILD DRAW TWO
- Card placement validation based on color, number, or action type
- Scoring system awarding points for each card played
- Display of the resultant state after each turn (top discard + next player’s hand)

## Team Member Contributions
- Eshal Kashif: Developed `Player.java` — handled player attributes, hand management, scoring updates, and full class documentation. |
- Emma Wong: Created `Deck.java` — implemented card generation, shuffling, draw/discard piles, and reshuffle mechanics. 
- Anita Gaffuri Kasbiy: Implemented `UnoFlip.java` — managed main game flow, turn logic, input handling, scoring system, and resultant-state output after each turn.
- Matthew Sanii: Implemented `Card.java` — designed card structure, enums for color and type, and description formatting for display. |

## Known Issues
- Input must match prompts exactly (case-sensitive for colors)
- GUI and Flip-side (dark deck) functionality not yet implemented
- Scores reset between sessions (no persistent storage)
