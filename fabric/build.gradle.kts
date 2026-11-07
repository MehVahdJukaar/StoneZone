plugins {
    id("com.possible-triangle.fabric")
}

fabric {
    dependOn(project(":common"))
    accessWidener(project(":common"))
}

val modId: String = property("mod_id").toString()
val modVersion: String = property("mod_version").toString()
tasks.named<Jar>("jar") {
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("fabric")
}
tasks.named<Jar>("sourcesJar") {
    from(sourceSets.main.get().allSource)
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("fabric-sources")
}

val localReposDir: String? = System.getenv("REPOS26_1_2")
dependencies {

//!! MOONLIGHT LIB (REQUIRED)  -------------------------------------------------------------------------------------- \\

    //- LOCAL
    if (findProperty("enable_moonlight_test").toString().toBoolean()) {
        val repos = requireNotNull(localReposDir) { "enable_moonlight_test is on but the REPOS26_1_2 env var is not set" }
        implementation(files("$repos\\Moonlight\\fabric\\build\\libs\\moonlight-${property("moonlight_testVersion")}-fabric.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:moonlight:${property("moonlight_version")}-fabric")
        else modApi("net.mehvahdjukaar:moonlight-fabric:${property("moonlight_version")}") { isTransitive = false }
    }

//!! EVERY COMPAT (REQUIRED) ---------------------------------------------------------------------------------------- \\

    //- LOCAL
    if (findProperty("enable_everycomp_test").toString().toBoolean()) {
        val repos = requireNotNull(localReposDir) { "enable_everycomp_test is on but the REPOS26_1_2 env var is not set" }
        implementation(files("$repos\\WoodGood\\fabric\\build\\libs\\everycomp-${property("everycomp_testVersion")}-fabric.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:every-compat:${property("everycomp_version")}-fabric")
        else modApi("net.mehvahdjukaar:everycomp-fabric:${property("everycomp_version")}:fabric") { isTransitive = false }
    }

//!! TOOLS  --------------------------------------------------------------------------------------------------------- \\

//    modRuntimeOnly("com.blamejared.crafttweaker:CraftTweaker-fabric-${property("minecraft_version")}:${property("crafttweaker_version")}") // Faux-Custom-Entity-Data
//    modRuntimeOnly("dev.emi:emi-fabric:${property("emi_version")}+${property("minecraft_version")}")
//    modRuntimeOnly("maven.modrinth:emi:${property("emi_version")}+${property("minecraft_version")}+fabric")
    modRuntimeOnly("mezz.jei:jei-${property("minecraft_version")}-fabric:${property("jei_version")}")
    modRuntimeOnly("maven.modrinth:jade:${property("jade_version")}+fabric")
//    modRuntimeOnly("curse.maven:worldedit-225608:5830452") // required loom 1.7.410

    modRuntimeOnly("curse.maven:configured-457570:7943179")

//!! ================================================= DEPENDENCIES ================================================= \\
    //- ONLY FOR TESTING - can be commented out or enabled
    modRuntimeOnly("curse.maven:faux-custom-entity-data-575305:5623810") // v13.0.1 | CraftTweaker //!! 1.21.1
//    modRuntimeOnly("curse.maven:terrablender-fabric-565956:6054948") // v4.1.0.8 | Biomes-O'-Plenty //!! 1.21.1
//    modRuntimeOnly("curse.maven:glitchcore-955399:5660741") // v2.1.0.0 | Biomes-O'-Plenty | required: Typetools, Night-Config //!! 1.21.1
//    modRuntimeOnly("curse.maven:yungs-api-421850:5769972") // v4.0.6 | NONE //!! 1.20
//    modRuntimeOnly("curse.maven:puzzles-lib-495476:6494828") // v21.1.36 | Stoneworks //!! 1.21.1
//    modRuntimeOnly("curse.maven:forge-config-api-port-fabric-547434:5982384") // v21.1.3 | Stoneworks //!! 1.21.1
//    modRuntimeOnly("com.terraformersmc.terraform-api:terraform-wood-api-v1:7.0.3") // Blockus //!! 1.20
//    modRuntimeOnly("curse.maven:fusion-connected-textures-854949:8479707") // v1.3.9 | Rechiseled //!! 1.21.1
//    modRuntimeOnly("curse.maven:supermartijn642s-config-lib-438332:5546988") // v1.1.8 | Rechiseled //!! 1.21.1
//    modRuntimeOnly("curse.maven:supermartijn642s-core-lib-454372:7783365") // v1.1.21 | Rechiseled //!! 1.21.1
//    modRuntimeOnly("curse.maven:bclib-495191:4971470") // v3.0.14 | BetterEnd //!! 1.20
//    modRuntimeOnly("com.github.quiqueck:BCLib:3.0.14") // BetterEnd //!! 1.20
//    modRuntimeOnly("com.github.quiqueck:WunderLib:1.1.5") // BetterEnd //!! 1.20
//    modRuntimeOnly("curse.maven:athena-841890:5176880") // Chipped //!! 1.20
//    modRuntimeOnly("curse.maven:resourceful-lib-570073:5973189")  // v3.0.12 | Chipped //!! 1.21.1
//    modRuntimeOnly("curse.maven:resourceful-config-714059:6467774") // v3.0.11 | Better-Archeology //!! 1.21.1
//    modRuntimeOnly("curse.maven:monolib-968432:6123964") // v2.0.0 | More-Beautiful-Torches //!! 1.20
//    modRuntimeOnly("curse.maven:architectury-api-419699:5786326") // v13.0.8 | Better-Archeology //!! 1.21.1
//    modRuntimeOnly("curse.maven:balm-fabric-500525:6841887") // v21.0.49 | Waystones //!! 1.21.1

    //- ~/fabric/mods LOCAL

    //- OTHER MAVENS - can be commented out or enabled
//    modRuntimeOnly("org.reflections:reflections:0.10.2") // Refurbished-Furniture
//    forgeRuntimeLibrary("com.teamresourceful:bytecodecs:1.0.2") // Chipped
//    modRuntimeOnly('net.jodah:typetools:0.6.3') // GlitchCore
//    modRuntimeOnly('com.electronwill.night-config:core:3.8.1') // GlitchCore
//    modRuntimeOnly('com.electronwill.night-config:toml:3.8.1') // GlitchCore

    //+ REQUIRED - The modules access libaries from below - Only in FABRIC
    modCompileOnly("curse.maven:framework-549225:7530359") // +Refurbished-Furniture, Mighty-Mail, Backpacked //!! 1.21.1
    modCompileOnly("curse.maven:owo-lib-532610:6446006") //!! 1.21.1

    //+ OTHER MAVENS
    modCompileOnly("dev.engine-room.flywheel:flywheel-fabric-1.21.1:${property("flywheel_fabric_version")}") // Create //!! 1.21.1
    modCompileOnly("net.createmod.ponder:Ponder-Fabric-1.21.1:${property("ponder_fabric_version")}") // Create //!! 1.21.1
    modCompileOnly("com.tterrag.registrate_fabric:Registrate:${property("registrate_fabric_version")}") // Create //!! 1.21.1
    modCompileOnly("curse.maven:supermartijn642s-core-lib-454372:7521829") // v1.1.20 | Rechiseled //!! 1.21.1

//!! =================================================== IMPORTS ==================================================== \\

    //- ONLY FOR TESTING - can be commented out or enabled

    //+ REQUIRED - The modules access libaries from below - ONLY IN FABRIC
    // MACAW's
    modCompileOnly("curse.maven:macaws-bridges-351725:7810029")
    modCompileOnly("curse.maven:macaws-fences-and-walls-453925:7818075")
    modCompileOnly("curse.maven:macaws-paths-and-pavings-629153:7818163")
    modCompileOnly("curse.maven:macaws-roofs-352039:7819132")
    modCompileOnly("curse.maven:macaws-windows-363569:7819133")
    modCompileOnly("curse.maven:macaws-stairs-1119394:7810438")

    //+ GENERAL
    modCompileOnly("curse.maven:create-fabric-624165:5982726") // Do not use for RunTime //@ 1.20 - It's abandoned and won't be updated to 1.21.1
//    modCompileOnly("curse.maven:create-deco-fabric-739872:5293979") //@ Create-Fabric is abandoned

    // OTHER MAVENS
    modCompileOnly("earth.terrarium.chipped:chipped-fabric-1.21.1:4.0.2") //INCLUDED: Athena, Resourceful-Lib //!! 1.21.1
//    modCompileOnly("com.simibubi.create:create-fabric-${minecraft_version}:${create_fabric_version}:slim") { isTransitive = false } // Registrate, Flywheel //!! maven is outdated & not available

    //+ MIRRORED FROM COMMON - Required because dependOn(common) compiles common sources with fabric classpath
    modCompileOnly("curse.maven:better-archeology-835687:9099892") // Resourceful-Config, Architectury-API //!! 1.21.1
    modCompileOnly("curse.maven:blockus-312289:7920575") // Terraformersmc's terraform-wood-api //!! 1.21.1
    modCompileOnly("curse.maven:building-but-better-989479:5382599") //!! 1.21.1
    modCompileOnly("curse.maven:decorative-blocks-reborn-1327768:7926190") //!! 1.21.1
    modCompileOnly("curse.maven:fabric-waystones-410902:5798771") //!! 1.21.1
    modCompileOnly("curse.maven:handcrafted-538214:6330032") // Resourceful-Lib //!! 1.21.1
    modCompileOnly("curse.maven:lets-do-candlelight-farm-charm-compat-1038117:9150312") //+[Let's-Do]-Farm-&-Charm //!! 1.21.1
    modCompileOnly("curse.maven:lets-do-farm-charm-1038103:9203719") // Architectury-API, Cloth-Config-API //!! 1.21.1
    modCompileOnly("curse.maven:more-beautiful-torches-860325:5609745") // MonoLib //!! MERGED //!! 1.21.1
    modCompileOnly("curse.maven:rechiseled-558998:8301622") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ] //!! 1.21.1
    modCompileOnly("curse.maven:refurbished-furniture-897116:7473565") // Framework //!! 1.21.1
    modCompileOnly("curse.maven:stoneworks-852663:5731745") // puzzles-lib, forge-config-api-port //!! 1.21.1
    modCompileOnly("curse.maven:twigs-496913:6782788") //!! 1.21.1
    modCompileOnly("curse.maven:waystones-245755:8450086") // Balm //!! 1.21.1

    // ========== LOCAL (NOTE: JARJAR meant the mod is embedded in the other mod)
    modCompileOnly("local-mod_foundation:foundation-fabric-1.0.7") //JARJAR - [Let's Do]-Farm&Charms //!! 1.21.1

//!! ================================================= FOR TESTING ================================================== \\

//!! Need to find a stone mod, I cannot find a simple mod, ugh!
//    modRuntimeOnly("curse.maven:betterend-413596:5680728") // BCLib, WunderLib, WorldWeaver  //!! ALPHA
//    modRuntimeOnly("curse.maven:mwtis-stone-expansion-907720:4767668") //!! 1.20

}