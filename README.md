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
- Eshal Kashif: Developed `Player.java` — handled player attributes, hand management, scoring updates, and full class documentation. Implemented methods for test classes PlayerTest.java and DeckTest.java with full documentation. Illustrated the UML diagram to show representation between main classes. 
- Emma Wong: Created `Deck.java` — implemented card generation, shuffling, draw/discard piles, and reshuffle mechanics. Implemented test class UnoFlipTest.java. Illustrated the all sequence diagrams to represent events.
- Anita Gaffuri Kasbiy: Implemented `UnoFlip.java` — managed main game flow, turn logic, input handling, scoring system, and resultant-state output after each turn.
- Matthew Sanii: Implemented `Card.java` — designed card structure, enums for color and type, and description formatting for display.

## Class Descriptions & Explanations

### Card.java
- Responsibility. Represent a single card’s immutable attributes: color, type, rank; provide a human-readable description used by the console UI.
- Uses enums (colortype and cardtype) to represent card attributes effectively

### Deck.java
- Responsibility. Build and manage the draw pile and the discard pile (the discard and draw pile make up the deck, hence why they are in the same class), shuffle, draw, and expose the current top discard.

### Player.java
- Responsibility. Track a player’s identity, score, and hand.

### UnoFlip.java
- Responsibility. Orchestrate the entire game loop: setup, turn management, reading keyboard input, placement validation, action card effects, scoring, and printing the resultant state.

## Future Work (M2+)
- Implement Flip functionality (lightside and darkside of cards)
- GUI, implement MVC
