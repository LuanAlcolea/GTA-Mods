# GTA III CLEO Mods

A collection of CLEO scripts for GTA III that restore and add mechanics from 
later GTA titles, built through reverse engineering of the game's internal 
memory and behavior.

## About

These mods were developed by reading and reverse engineering GTA III's memory 
layout, internal flags, and game logic — then writing CLEO scripts that interact 
directly with the game engine at runtime. Some scripts were ported from GTA Vice 
City or inspired by GTA San Andreas equivalents, requiring analysis of the 
original scripts and adaptation to GTA III's different memory structure and 
opcodes.

## Mods

### No Clip
A no-clip implementation for GTA III inspired by junior_djjr's GTA SA version.
Works by directly manipulating memory flags that control collision detection and 
gravity for the player and vehicles at runtime.
- Reads camera direction vectors from memory to calculate movement
- Writes physics and collision flags directly to entity structs
- Supports both on-foot and in-vehicle modes

*Source available in this repository.*

### Restore Icons
Dynamically renders radar icons for nearby locations (Pay 'n' Spray, 
Ammu-Nation, 8-Ball's Bomb Shop) by tracking entity positions relative to the 
player and toggling visibility accordingly.

*Source available in this repository.*

### Jump Out of Car
Allows the player to jump out of a moving vehicle, backporting behavior from 
later GTA titles. Triggers based on vehicle speed threshold.

[MixMods thread](https://forum.mixmods.com.br/f5-scripts-codigos/t5530-iii-pular-do-carro)

### Engine On/Off
Toggle the car engine by holding the exit button, replicating behavior from 
later entries in the series.

[MixMods thread](https://forum.mixmods.com.br/f5-scripts-codigos/t6942-iii-iv-engine-on-off)

### Hydraulics in Any Car
Activate hydraulics on any vehicle via cheat code input.

[MixMods thread](https://forum.mixmods.com.br/f5-scripts-codigos/t6714-iii-hydraulics-in-any-car)

### Radar Zoom
Adds radar zoom and displays current street and vehicle names on screen. 
Ported from spaceeinstein's GTA Vice City version — required reading the 
original script and rewriting it for GTA III's different memory layout and 
opcode set.

[MixMods thread](https://forum.mixmods.com.br/f5-scripts-codigos/t6689-iii-radarzoom__)

## Technical Notes

Scripts are written in CLEO's scripting language and interact with the game 
engine through:
- Direct memory reads/writes to entity structs (collision flags, physics flags)
- Camera matrix reads for directional movement calculation
- Runtime manipulation of internal game state

## Requirements

- GTA III (PC)
- [CLEO](https://cleo.li/)
- [Modloader](https://github.com/thelink2012/modloader) (recommended)
