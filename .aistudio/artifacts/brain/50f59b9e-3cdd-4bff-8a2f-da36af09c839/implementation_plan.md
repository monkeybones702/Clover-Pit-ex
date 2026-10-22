# Implementation Plan: The Devil's Payline

This plan outlines the development of a roguelike dungeon crawler fusing slot machine combat with tactical roguelike mechanics.

## Phase 1: Project & Architecture Setup
*   **Unique Configuration**: Finalize `applicationId` and define core project structure.
*   **Database**: Set up Room Database for local-only player progress and chip unlocks.

## Phase 2: Core Game Engine (ViewModel)
*   **Combat Engine**: Implement `GameEngineViewModel` to manage reel states, HP calculations, coin generation, and tariff mechanics.
*   **Room Progression**: Logic for evolving from 3-reel (Floor 1) to 5-reel (Floor 3) layouts.
*   **PCB System**: State management for equippable microchips with specific tactical modifiers.

## Phase 3: UI Construction (Jetpack Compose)
*   **Main Combat Screen**: Slot reel interface, boss stats, player resources.
*   **Intercom Layer**: Clover's dialogue system.
*   **PCB Modding Interface**: Interaction panel for customizing the rig.
*   **Transitions**: Implement Navigation Compose with simple fade transitions.

## Phase 4: Integration & Polish
*   **Asset Pipeline**: Generate custom UI icons and boss/intercom visuals.
*   **Progression Logic**: Finalize mechanics for tariff collection and blood-draw interactions.
*   **Final Polish**: Integrate Clover's personality lines based on game state.

---
Please provide feedback on this plan to proceed with implementation.
