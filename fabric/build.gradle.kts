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
//    modRuntimeOnly("curse.maven:worldedit-225608:8037379") // v7.4.3

    modRuntimeOnly("curse.maven:configured-457570:7943179")

//!! ================================================= DEPENDENCIES ================================================= \\
    //- ONLY FOR TESTING - can be commented out or enabled
    modRuntimeOnly("curse.maven:faux-custom-entity-data-575305:7917206") // v1.2.4 | CraftTweaker
//    modRuntimeOnly("curse.maven:terrablender-fabric-565956:8384530") // v26.1.2.0.3 | Biomes-O'-Plenty
//    modRuntimeOnly("curse.maven:glitchcore-955399:8109769") // v26.1.2.0.2 | Biomes-O'-Plenty | required: Typetools, Night-Config
//    modRuntimeOnly("curse.maven:puzzles-lib-495476:9129961") // v26.1.16 | Stoneworks
//    modRuntimeOnly("curse.maven:forge-config-api-port-fabric-547434:8218664") // v26.1.5 | Stoneworks
//    modRuntimeOnly("com.terraformersmc.terraform-api:terraform-wood-api-v1:7.0.3") // Blockus //!! 1.20
//    modRuntimeOnly("curse.maven:fusion-connected-textures-854949:9070329") // v1.3.16 | Rechiseled
//    modRuntimeOnly("curse.maven:supermartijn642s-config-lib-438332:9009442") // v1.1.8 | Rechiseled
//    modRuntimeOnly("curse.maven:supermartijn642s-core-lib-454372:9447110") // v.1.26b | Rechiseled
//    modRuntimeOnly("curse.maven:bclib-495191:9365613") // v26.101.5 | BetterEnd
//    modRuntimeOnly("org.betterx:bclib:26.101.5") // BetterEnd - Need a new maven url: maven { url 'https://maven.ambertation.de/releases' }
//    modRuntimeOnly("com.github.quiqueck:WunderLib:1.1.5") // BetterEnd //!! 1.20
//    modRuntimeOnly("curse.maven:athena-841890:7970443") // v4.7.3 | Chipped
//    modRuntimeOnly("curse.maven:resourceful-lib-570073:7927298")  // v4.0.1 | Chipped
//    modRuntimeOnly("curse.maven:resourceful-config-714059:7872555") // v4.0.1 | Better-Archeology
//    modRuntimeOnly("curse.maven:monolib-968432:9050772") // v.1.2 | More-Beautiful-Torches
//    modRuntimeOnly("curse.maven:architectury-api-419699:9111153") // v20.1.17 | Better-Archeology
//    modRuntimeOnly("curse.maven:shogi-1475746:8978485") // v26.1.2.12 | Waystones
//    modRuntimeOnly("curse.maven:balm-531761:8969764") // v26.1.2.15 | Waystones

    //- ~/fabric/mods LOCAL

    //- OTHER MAVENS - can be commented out or enabled
//    modRuntimeOnly("org.reflections:reflections:0.10.2") // Refurbished-Furniture
//    forgeRuntimeLibrary("com.teamresourceful:bytecodecs:1.0.2") // Chipped
//    modRuntimeOnly('net.jodah:typetools:0.6.3') // GlitchCore
//    modRuntimeOnly('com.electronwill.night-config:core:3.8.1') // GlitchCore
//    modRuntimeOnly('com.electronwill.night-config:toml:3.8.1') // GlitchCore

    //+ REQUIRED - The modules access libaries from below - Only in FABRIC
    modCompileOnly("curse.maven:framework-549225:7964503") // v0.13.23 +Refurbished-Furniture, Mighty-Mail, Backpacked //!! 1.21.1
    modCompileOnly("curse.maven:owo-lib-532610:8589921") // v0.13.1 | ???

    //+ OTHER MAVENS
    modCompileOnly("dev.engine-room.flywheel:flywheel-fabric-1.21.1:${property("flywheel_fabric_version")}") // Create //!! 1.21.1
    modCompileOnly("net.createmod.ponder:Ponder-Fabric-1.21.1:${property("ponder_fabric_version")}") // Create //!! 1.21.1
    modCompileOnly("com.tterrag.registrate_fabric:Registrate:${property("registrate_fabric_version")}") // Create //!! 1.21.1

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
    modCompileOnly("curse.maven:better-archeology-835687:9099958") // v1.3.9 | Resourceful-Config, Architectury-API
    modCompileOnly("curse.maven:blockus-312289:9560390") // v2.16.9 | Terraformersmc's terraform-wood-api
    modCompileOnly("curse.maven:building-but-better-989479:5382599") //!! 1.21.1
    modCompileOnly("curse.maven:decorative-blocks-reborn-1327768:8959320") // v8.0.0
    modCompileOnly("curse.maven:fabric-waystones-410902:8453356") // v3.3.6
    modCompileOnly("curse.maven:handcrafted-538214:6330032") // Resourceful-Lib //!! 1.21.1
    modCompileOnly("curse.maven:lets-do-candlelight-farm-charm-compat-1038117:9150312") //+[Let's-Do]-Farm-&-Charm //!! 1.21.1
    modCompileOnly("curse.maven:lets-do-farm-charm-1038103:9203719") // Architectury-API, Cloth-Config-API //!! 1.21.1
    modCompileOnly("curse.maven:more-beautiful-torches-860325:8988924") // v3.1.1 MonoLib //!! MERGED
    modCompileOnly("curse.maven:rechiseled-558998:8875881") // v1.2.6 | Fusion, supermartijn642s-[ Config-Lib, Core-Lib ]
    modCompileOnly("curse.maven:refurbished-furniture-897116:7943174") // Framework
    modCompileOnly("curse.maven:stone-chest-307052:8145945")
    modCompileOnly("curse.maven:stoneworks-852663:7995179") // v26.1.0 | puzzles-lib, forge-config-api-port
    modCompileOnly("curse.maven:twigs-496913:6782788") //!! 1.21.1
    modCompileOnly("curse.maven:waystones-245755:9406317") // Balm

    // ========== LOCAL (NOTE: JARJAR meant the mod is embedded in the other mod)
    modCompileOnly("local-mod_foundation:foundation-fabric-1.0.7") //JARJAR - [Let's Do]-Farm&Charms //!! 1.21.1

//!! ================================================= FOR TESTING ================================================== \\

//    modRuntimeOnly("curse.maven:betterend-413596:9365627") // v26.101.5 | BCLib, WunderLib, WorldWeaver
//    modRuntimeOnly("curse.maven:mwtis-stone-expansion-907720:4767668") //!! 1.20

}