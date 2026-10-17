| **Legends**                                                                                                                       |
|-----------------------------------------------------------------------------------------------------------------------------------|
| \- **(C)**: FORGE & FABRIC                                                                                                        |
| - **(FB)**: FABRIC                                                                                                                |
| - **(NF)**: NEOFORGE                                                                                                              |
| - **(IT)**: Included Texture — added the ResourceLocation of the missing textures required for blocks or generating a new texture |
| - **(TEX)**: hand-made textures to improve the way a block looks                                                                  |
| - **(COMPAT)**: Create an exception for a compat mod. EveryCompat won't include the Supported Mod and the Wood Mod                |
| - **(INCLUDED)**: The block is not generated because a Wood Mod already has the same block as the supported mod will be generated |
| - **(EXCLUDED)**: The block is generated BUT it shouldn't be generated for a reason                                               |
| - **(UDBT)**: Undetected BlockTypes will be manually added                                                                        |
|                                                                                                                                   |

---
## v2.11.19

### FIXES:
- **Macaw's Bridge** (NF): Updated Neoforge side's outdated Id for CreativeTab  

---

## v2.11.18

### CHANGES:
- **Macaw's Bridges** (C): ported from 1.20.1 with the updated code for the following: 
  - Fixed the incorrect class used for `stone_brick_bridge`, `mossy_stone_brick_bridge`, `andesite_bridge` - [#206](https://github.com/MehVahdJukaar/StoneZone/issues/206)
  - Added a required children to BRIDGE_PIER (BRIDGE_SUPPORT) to exclude some StoneType that don't have the required children
- **Decorative Blocks Reborn** (C): Aka **Decorative Blocks** is re-enabled and Updated for 1.21.1
- **\[Let's Do\] Candlelight** (C): Updated for v2.1.14 due to the mod having a major changes - [#212](https://github.com/MehVahdJukaar/StoneZone/issues/212)
- **Stone Zone** (C): StoneType Detection did not detect Stones from **TerraFirmaCraft** (TFC)/**ArborFirmaCraft** (AFC) - [#205](https://github.com/MehVahdJukaar/StoneZone/issues/205)
- **Rechiseled** (C): Merged 3 class into 1 class reducing file size from 93.87kB to 30.63kB

### ADDED:
- **Gregtech CEu Modern** (UDBT): `red_granite`, `light_concrete`, & `dark_concrete` as StoneType - [#169](https://github.com/MehVahdJukaar/StoneZone/issues/169)
- **Oh The Biomes We've Gone** (UDBT): `white_dacite` - [#189](https://github.com/MehVahdJukaar/StoneZone/issues/189)
- **DecoFirmaCraft** (UDBT): Added into StoneType Detection so stones can be detected along with TFC and AFC
- **TerraFirmaCraft** (UDBT): [#205](https://github.com/MehVahdJukaar/StoneZone/issues/205)f
  - 7 sandstones & 16 alabasters as StoneTypes
  - 4 mud as MudTypes

### NEW: 
- **Refurbished Furniture** (C): MrCrayFish's mod - `stepping_stones`
- **Create bits N' Bobs** (NF)

### CREATE (NF) - Ported codes from Gems Realm 1.21.1 to add new blocks
- **CHANGES**: Updated the recipe generation to included **Bits N' Bobs**' tiles in stonecutting recipe 
- **Added** 10 new blocks & 1 item
  - encased_shaft
  - encased_cogwheel
  - encased_large_cogwheel
  - funnel
  - belt_funnel
  - alloy (required for crafting below)
  - door
  - ladder
  - bars
  - scaffolding
  - table_cloth
- **CHANGES**: All of Gems Realm's blocks for valve_handle, casing, table_cloth, funnel will show "W" when you hover the item so you can play StoryBoard
- **FIXES**: Finally fixed the SHINGLES & TILES' issue with top connected texture where they don't show "X"
- **NOTES**:
  - CASING can be not be applied to belt block because there is limitation in the code that prevent compatibility with Gems Realm - I've requested for a small changes in the Create's Source Code. I do not know when the changes will be applied. Too bad Create's github do not have suggestion issue I could create. Discord suggestion tend to get ignored or forgetten.
  - TUNNEL cannot be added for same reason as casing not being applied to belt block


---

## v2.11.17

### ADDED:
- **Waystones** (NF): Support the recipe from **Create: Waystones Recipe** only if it's installed
  - NOTE: the required children is BRICKS for StoneZone's waystones to be registered & recipe to be generated
- **Quark** (NF): Changed the PILLARS' texture generation to use POLISHED's texture instead of STONE's - [#207](https://github.com/MehVahdJukaar/StoneZone/issues/207) 

---

## v2.11.16

### CHANGES:
- **Chipped** (C): Excluded `pointed_dripstone` from recipe generation & Added `#chipped:pointed_dripstone` tag so it can be crafted via masonstone table

### FIXES: 
- **Macaw's Fences & Walls** (FB): Fixes the crash when opening inventory by corrected the incorrect Creative Tab Key for FABRIC side - [EveryCompat#1207](https://github.com/MehVahdJukaar/WoodGood/issues/1207)

---

## v2.11.15

### CHANGES: 
- **Stoneworks** (C): Updated the outdated ResourceLocation for tab - [#185](https://github.com/MehVahdJukaar/StoneZone/issues/185)

---

## v2.11.14

### REQUIRED:
- **Every Compat v2.11.32 or newer** - REASON: the code related to 2 configs are removed

### CHANGES:
- **Stone Zone** (C): Removed 2 configs due to a misunderstood request

---

## v2.11.13

### REQUIRED: 
- **Every Compat v2.11.31** - REASON: 2 new configs and new codes added responsible for Creative Tab stuff.

### CHANGES: 
- **Stone Zone** (C): Added 2 new configs - [Every Compat#1203](https://github.com/MehVahdJukaar/WoodGood/issues/1203)
  - `DISABLE_CYCLE_ITEM_RENDERER` - disable creative-tab from showing the iteration of every item from Gems Realm
  - `CREATIVE_TAB_ICON` - Choose one item (can be from Gems Realm or Minecraft) to replace the icon instead of iterating every item from Gems Realm

---

## v2.11.12

### CHANGES: 
- **Stone Zone** (C): 
  - Added a config to enable/disable item_search or search bar in Creative Inventory via StoneZone's tab
  - Fixed the incorrect logic responsible for blacklist a prismarine_waystone (it did not get excluded) - [#180](https://github.com/MehVahdJukaar/StoneZone/issues/180)
- **Rechiseled** (C): - [#181](https://github.com/MehVahdJukaar/StoneZone/issues/181)
  - Updated to support v1.2.0+ & The module class have been moved to COMMON
  - Updated the recipe generation

### FIXES:
- **Stone Zone** (C): Fixed the blacklist config, `stonezone-hazardous.toml` not excluding `prismarine` - [#180](https://github.com/MehVahdJukaar/StoneZone/issues/180)

---

## v2.11.11

### UPDATED: 
- **Quark** (IT): Added missing textures for `quark:myalite` - [#1182](https://github.com/MehVahdJukaar/WoodGood/issues/1182)
- **TEXTURES**: Added missing mask textures for: 
  - **Macaw's Paths & Pavings**
  - **Wraith Waystone**

---

## v2.11.10

### UPDATED: 
- **Quark** (NF): Fixed the crash when opening inventory - [#179](https://github.com/MehVahdJukaar/StoneZone/issues/179)
- **LANG** (ja_jp): Updated & Fixed by @Hayakoh-WeldyAlin -

---

## v2.11.9

### UPDATED: 
- **Macaw's Paths And Pavings** (C): Updated to support `v1.1.1`, NOTE: older than `v1.1.0` won't be supported

---

## v2.11.8

### UPDATED: 
- **Stone Zone** (C): Updated to work with **Every Compat v2.11.25**

---

## v2.11.7

### UPDATED: 
- **Stone Zone** (C): Fixed the crash with **Every Compat v2.11.24** - There is a changed code in **Every Compat** - [#172](https://github.com/MehVahdJukaar/StoneZone/issues/172)
- **Stone Chest** (C): Updated to support v1.1.0 & Fixed the crash because `chest_stone` is no longer available, changed to `chest_andesite` - [#171](https://github.com/MehVahdJukaar/StoneZone/issues/171)

---

## v2.11.6

### UPDATED: 
- **Better Archeology** (C): Corrected the ResourceLocation for the recipe, cracked_mud_brick_slab

### NOTE: 
- **Moonlight Lib v2.28.2**: Added SmeltingRecipe to RecipeTemplate, so the smelting-recipe from **Better Archeology** can be generated

---

## v2.11.5

### REQUIREMENT: 
- **Every Compat** v2.11.10 or newer

### ADDED:
- **Abyssal Decor** (UD): blood_coral, jade

---

## v2.11.4

### UPDATED: 
- **Waystones** (C): Changed the baseBlock to deepslate to improve the generated texture
- **Stone Zone** (C): 
  - Fixed the missing textures for **Additional Lights** - [#151](https://github.com/MehVahdJukaar/StoneZone/issues/151)
  - Updated the deprecated method to use new method from EveryCompat 
  - Fixed crash - [#156](https://github.com/MehVahdJukaar/StoneZone/issues/156) 
  
### ADDED:
- **Thaumon** (IT): amber

---

## v2.11.3

### UPDATED: 
- **Additional Lights** (NF): StoneTypes from **Ars Nouveau** is EXCLUDED - see [#149](https://github.com/MehVahdJukaar/StoneZone/issues/149) for reasons

---

## v2.11.2

### REQUIRED:
- **EveryCompat v2.11.3**

### UPDATED: 
- **StoneZone** (C): 
  - Re-added the Marioverse's undetected StoneTypes
  - Changed the **Moonlight**'s required version to v2.23.0

### NEW:
- **Wraith Waystones** (C)
- **Handcrafted** (C)
- **\[Let's Do\] Candlelight** (C)

---

## v2.11.1

### UPDATED: 
- **Additional Lights** (NF): Outdated codes are updated and fixed the crash - [#147](https://github.com/MehVahdJukaar/StoneZone/issues/147) 
- **Chipped** (C): Fixed the missing textures

### NEW:
- **Waystones** (C)

---

## v2.11.0

### UPDATED:
- **StoneZone** (C): 
  - Updated to work with **EveryCompat v2.11.0+** and **Moonlight v2.23.0**
  - Major changes in the code

### ADDED:
- **Pokecube AOI** (UD): ultra_darkstone, dusk_dolerite, azure_sandstone, blackened_sandstone, crystallized_sandstone, meteorite
- **Koopa's Critter** (UD): kopje_granite
