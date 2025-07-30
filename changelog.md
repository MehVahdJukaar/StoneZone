<div style="text-align: center; border: 1px solid yellow; padding: 10px;">

<div style="text-align: center; margin-bottom: 10px;">

### LEGEND

</div>

<div style="text-align: left;">

* **(COMMON)**: FORGE & FABRIC
* **(FB)**: FABRIC
* **(F)**: FORGE
* **(IT)**: Included Texture: Added the ResourceLocation of the missing textures required for blocks or generating a new texture
* **(UDS)**: Undetected StoneType have been manually added.
* **(COMPAT)**: Create an exception for a compat mod. EveryCompat won't included for the Supported Mod and the Wood Mod
* **(INCLUDED)**: The block is not generated because a Wood Mod already have the same block as the supported mod will be generated
* **(EXCLUDED)**: The block is generated BUT it shouldn't be generated for a reason

</div>

</div>

### UPDATED: 
- **Stone Zone** (COMMON): Fixed the SERVER crash related to `stonezone-hazardous.toml` config - [#1006](https://github.com/MehVahdJukaar/WoodGood/issues/1006)

---

## v2.8.14

### REQUIRED VERSION:
- **Every Compat v2.8.14** - Same verison is applied to **Library Section**

### UPDATED: 
- **Stone Zone** (COMMON): 
  - Added a config to disable the visibility of SearchBar in GemsRealm' tab
  - Added new config to disable EntrySet, BlockTypes, or Module

---

## v2.8.13

### UPDATED: 
- **LANG**: 
  - zh_cn for **Blocks you need**, **\[Let's Do\] Candlelight**, **Waystones** - @libu2333
  - en_us for **Wetland Whimsy**'s limestone to use "Lemonstone"
- **Chipped** (COMMON): Corrected the blocks' class & see the link for more details - [#126](https://github.com/MehVahdJukaar/WoodGood/issues/126)
- **Stone Zone** (COMMON): Corrected the detection system to detect StoneType's children
- **(INCLUDED)**: Included block with "pillar" from:
  - **Create**
  - **Quark**
  - **Decorative Blocks**

---

## v2.8.12

### UPDATED: 
- **Blocks You Need (Luna)** (F): Fixed the crash 

---

## v2.8.11

### UPDATED: 
- **LANG**: zh_cn for Builder's Crafts & Addition, Wraith-Waystones, Decorative-Blocks, Handcrafted - @libu2333
- **Blockus** (COMMON): Corrected the baseBlock for tile_stairs
- **Stone Zone** (COMMON): 
  - Updated Spritehelper to use method from **Every Compat** so the (IT) can work properly
  - Fixed the system detection to detect "bricks" of any StoneTypes

### NEW SUPPORTED:
- <span style="color: YELLOW;">**Waystones** (COMMON)</span> - Yup, Waystones is supported and prismarine_waystone is included, too.
- **\[Let's Do\] Candlelight** (COMMON)
- **Handcrafted** (COMMON)
- **Decorative Blocks** (COMMON)
- **Wraith Waystone** (FB) - Can be used with Sinytra-Connector for FORGE & NOTE: the recipe for `stone_brick_waystone` will be different
- **Blocks You Need (Luna)** (F)

---

## v2.8.10

### UPDATED: 
- **Stone Zone** (COMMON): 
  - required Moonlight v2.14.12 from now
  - Improved the detection system to ensure that a type (woodtype, stonetype, so on) is not added to other blocktypes.
    - This applied to GemsRealm's types, too. 
- **Twilight Forest** (UDS): mazestone & deadrock

---

## v2.8.9


### UPDATED: 
- **Stone Zone** (COMMON): Blacklisted **Blue Skies'** brumble blc it's not a StoneType, more likely to be WoodType - [#120](https://github.com/MehVahdJukaar/StoneZone/issues/120)

### NEW SUPPORTED MOD:
- **Builder's Crafts & Additions** (FG)

---

## v2.8.8

### ADDED: 
- **Nature's Spirit** (UD): Kaolin Color Variations - [#115](https://github.com/MehVahdJukaar/StoneZone/issues/115)
- **StoneZone** (COMMON): Added the Tooltip where it will now show where blocks are from: "Stone Type" or "Mud Type"

---

## v2.8.7

### UPDATED: 
- **Stone Zone** (COMMON): Blacklisted **RGB Blocks**

---

## v2.8.6

### UPDATED: 
- **Stone Zone** (COMMON): Updated to use **Every Compat** v2.8.6

---

## v2.8.5

### UPDATED: 
- **BlocksPlus** (FORGE): Improved furance's texture 
- **Stone Zone** (COMMON): Fixed **Building But Better**'s mod-id - @igalaxy 
  - <span style="color: RED;">WARNING: If you have **Building But Better** installed, then backup your world because the blocks will be removed from the world due to change of blocks' ID</span>

### ADDED:
- **Galesphere** (UDS): pink_salt, rose_pink_salt, pastel_pink_salt

---

## v2.8.4

### UPDATED:
- **Macaw's Bridge** (FORGE): Re-enable the `requiresChildren()` - Forgot to do that and what it does is ensure the StoneType has the children (polished, bricks, so on) for textures or recipes
  - <span style="color: RED;">WARNING: Ensure you backup your world or replace your blocks (from StoneZone with Macaw's Bridges before you update StoneZone</span>

### ADDED:
- **EN_US**: stone_bridge from **Macaw's Bridge** (missed that one) - [#110](https://github.com/MehVahdJukaar/StoneZone/issues/110)

---

## v2.8.3

### UPDATED: 
- **Stone Chest** (FORGE): Fixed the chests' missing textures in inventory - [#108](https://github.com/MehVahdJukaar/StoneZone/issues/108)

---

## v2.8.2

### UPDATED:
- **Stone Zone** (COMMON): Finished the system to fix the Tinted Index for blocks' parts that shouldn't be tinted

### ADDED:
- **Aerial Hell** (UD): 7 StoneTypes - [#105](https://github.com/MehVahdJukaar/StoneZone/issues/105)
  - glaucophanite
  - lunatic_stone
  - volucite_stone
  - dark_lunatic_stone
  - slippery_sand_stone
  - smoky_quartz
  - aerial_netherack 
    - children: golden_nether_bricks
