<div style="text-align: center; border: 1px solid yellow; padding: 10px;">
  <div style="text-align: center; margin-bottom: 10px;">
    <h3>LEGEND</h3>
  </div>
  <div style="text-align: left;">
    <ul style="list-style-type: disc; padding-left: 20px;">
      <li>(COMMON): FORGE & FABRIC</li>
      <li>(IT): Included Texture: Added the ResourceLocation of the missing textures required for blocks or generating a new texture</li>
      <li>(UD): Undetected StoneType have been manually added.</li>
    </ul>
  </div>
</div>

---

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
