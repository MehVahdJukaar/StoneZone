<div style="text-align: center; border: 1px solid yellow; padding: 10px;">

<div style="text-align: center; margin-bottom: 10px;">

### LEGEND

</div>

<div style="text-align: left;">

* **(C)**: FORGE & FABRIC
* **(FB)**: FABRIC
* **(F)**: FORGE
* **(IT)**: Included Texture: Added the ResourceLocation of the missing textures required for blocks or generating a new texture
* **(UDS)**: Undetected StoneType have been manually added.
* **(COMPAT)**: Create an exception for a compat mod. EveryCompat won't included for the Supported Mod and the Wood Mod
* **(INCLUDED)**: The block is not generated because a Wood Mod already have the same block as the supported mod will be generated
* **(EXCLUDED)**: The block is generated BUT it shouldn't be generated for a reason

</div>

</div>

Merry Christmas - Xel'Bayria

### UPDATED: 
- **Stone Zone** (C): Updated to use **Every Compat v2.9.13** & Fixed the crash - [#1151](https://github.com/MehVahdJukaar/WoodGood/issues/1151)
- **Create** (FB): Updated to support Create-Fabric v6.0+
- **Blast From The Past** (IT): permafrost & permafrost_bricks to correct the texture generation - [#170](https://github.com/MehVahdJukaar/StoneZone/issues/170)
- **LANG** (la_jp): Updated & Fixed by @Hayakoh-WeldyAlin - [PR#163](https://github.com/MehVahdJukaar/StoneZone/pull/163), [PR#165](https://github.com/MehVahdJukaar/StoneZone/pull/165)

---

## v2.9.7

### REQUIRED:
- **Every Compat** (C): v2.9.9 or newer version

### UPDATED:
- **Stone Zone** (C): Updated libraries from **Every Compat**
- **LANG** (en_us): Removed the wetland_whimsy:limestone, so it is no longer using "lemonstone", it's now using "limestone"
- **Koopa's Critters** (UDS): Fixed `kopje_granite`'s undetected polished 

### DEV:
- **Every Compat** (C):
    - Utility Classes have been moved to net.mehvahdjukaar.every_compat.misc
    - `TextureUtility` are renamed to `UtilityTexture`
    - `TagUtility` are renamed to `UtilityTag`
    - `RecipeUtility` are renamed to `UtilityRecipe`
    - `Utility` are renamed to `UtilityMisc`

---

## v2.9.6

### ADDED:
- **Abyssal Decor** (UD): blood_coral, jade - [#154](https://github.com/MehVahdJukaar/StoneZone/issues/154)

---

## v2.9.5

### UPDATED: 
- **Rechiseled** (C): Fixed the crash with FORGE

---

## v2.9.4

### UPDATED: 
- **Rechiseled** (C): 
  - Fixed the missing recipes
  - Moved to COMMON
- **Stone Zone** (C): Updated deprecated methods in Modules (Supported mods) to use the new method (backported from 1.21.1)

---

## v2.9.3

### UPDATED: 
- **Stone Zone** (C): Fixed the placed blocks' missing texture and its shape

---

## v2.9.2

## UPDATED: 
- **Enlightened End** (UDS): `chorloam` as MudType but is blacklisted as StoneType
- **Stone Zone** (C): Updated to correct the modification of models' content - [#141](https://github.com/MehVahdJukaar/StoneZone/issues/141)
- **Macaw's Fences & Walls** (C): Fixed the wrong textures on modern_[StoneType\]_brick_wall and railing_\[StoneType\]_brick_wall - This is just TEMP FIX
  - This is an issue on Macaw's side and this is already reported to the DEV to fix few typos. 
- **Pokecube AIO** (UDS): ultra_darkstone, dusk_dolerite, azure_sandstone, blackened_sandstone, crystallized_sandstone, meteorite
- **Koopa's Critters** (UDS): kopje_granite
- **Ars Nouveau** (UDS): sourcestone
  - sconce is blacklisted

---

## v2.9.1

### UPDATED: 
- **Stone Zone** (C): Fixed children not being detected - [#137](https://github.com/MehVahdJukaar/StoneZone/issues/137)
- **Quark** (IT): Added a custom texture for a tinted StoneType: Myalite's stone & bricks - [#140](https://github.com/MehVahdJukaar/StoneZone/issues/140)

---

## v2.9.0

### UPDATED: 
- **StoneZone** (COMMON): 
  - CHANGED CODES to work properly with Every Compat v2.9.0
  - Fixed Flat Missing Texture - [#135](https://github.com/MehVahdJukaar/StoneZone/issues/135)
  - Blacklisted 2 StoneTypes: **sullysmod:amber** & **endergetic:eumus** - [#133](https://github.com/MehVahdJukaar/StoneZone/issues/133)