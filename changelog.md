<div style="text-align: center; border: 1px solid yellow; padding: 10px;">
  <div style="text-align: center; margin-bottom: 10px;">
    <h3>LEGEND</h3>
  </div>
  <div style="text-align: left;">
    <ul style="list-style-type: disc; padding-left: 20px;">
      <li>(C): FORGE & FABRIC</li>
      <li>(NF): NEOFORGE</li>
      <li>(FB): FABRIC</li>
      <li>(IT): Included Texture: Added the ResourceLocation of the missing textures required for blocks or generating a new texture</li>
      <li>(UD): Undetected StoneType have been manually added.</li>
    </ul>
  </div>
</div>

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
