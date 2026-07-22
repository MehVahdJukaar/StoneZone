plugins {
    id("com.possible-triangle.fabric")
}

fabric {
    dependOn(project(":common"))
    accessWidener(project(":common"))
}

val modId: String = property("mod_id").toString()
val modVersion: String = property("mod_version").toString()
tasks.remapJar {
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("fabric")
}
tasks.remapSourcesJar {
    from(sourceSets.main.get().allSource)
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("fabric-sources")
}

val path = System.getenv("REPOS21_1").toString()
dependencies {

//!! MOONLIGHT LIB (REQUIRED)  -------------------------------------------------------------------------------------- \\
    //- LOCAL
    if (findProperty("enable_moonlight_test").toString().toBoolean()) {
        modApi(files(path + "\\Moonlight\\fabric\\build\\libs\\moonlight-${property("moonlight_testVersion")}-fabric.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:moonlight:${property("moonlight_version")}-fabric")
        else modApi("net.mehvahdjukaar:moonlight-fabric:${property("moonlight_version")}") { isTransitive = false }
    }

//!! EVERY COMPAT (REQUIRED) ---------------------------------------------------------------------------------------- \\

    //- LOCAL
    if (findProperty("enable_everycomp_test").toString().toBoolean()) {
        modApi(files(path + "\\WoodGood\\fabric\\build\\libs\\everycomp-${property("everycomp_testVersion")}-fabric.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:every-compat:${property("everycomp_version")}-fabric")
        else modApi("net.mehvahdjukaar:everycomp-fabric:${property("everycomp_version")}:fabric") { isTransitive = false }
    }

//!! TOOLS  --------------------------------------------------------------------------------------------------------- \\
    modRuntimeOnly("com.blamejared.crafttweaker:CraftTweaker-fabric-${property("minecraft_version")}:${property("crafttweaker_version")}") // Faux-Custom-Entity-Data
//    modRuntimeOnly("dev.emi:emi-fabric:${property("emi_version")}+${property("minecraft_version")}")
    modRuntimeOnly("maven.modrinth:emi:${property("emi_version")}+${property("minecraft_version")}+fabric")
    modRuntimeOnly("curse.maven:jei-238222:5846878")
    modRuntimeOnly("curse.maven:jade-324717:5884237") // v15.8.3
//    modRuntimeOnly("curse.maven:worldedit-225608:5830452") // required loom 1.7.410

//!! ================================================= DEPENDENCIES ================================================= \\
    //- ONLY FOR TESTING - can be commented out or enabled
    modRuntimeOnly("curse.maven:faux-custom-entity-data-575305:5623810") // v13.0.1 | CraftTweaker
//    modRuntimeOnly("curse.maven:terrablender-fabric-565956:6054948") // v4.1.0.8 | Biomes-O'-Plenty
//    modRuntimeOnly("curse.maven:glitchcore-955399:5660741") // v2.1.0.0 | Biomes-O'-Plenty | required: Typetools, Night-Config
//    modRuntimeOnly("curse.maven:yungs-api-421850:5769972") // v4.0.6 | NONE //!! 1.20
//    modRuntimeOnly("curse.maven:puzzles-lib-495476:6494828") // v21.1.36 | Stoneworks
//    modRuntimeOnly("curse.maven:forge-config-api-port-fabric-547434:5982384") // v21.1.3 | Stoneworks
//    modRuntimeOnly("com.terraformersmc.terraform-api:terraform-wood-api-v1:7.0.3") // Blockus //!! 1.20
//    modRuntimeOnly("curse.maven:fusion-connected-textures-854949:5129312") // v1.1.1 | Rechiseled //!! 1.20
//    modRuntimeOnly("curse.maven:supermartijn642s-config-lib-438332:5546988") // v1.1.8 | Rechiseled
//    modRuntimeOnly("curse.maven:bclib-495191:4971470") // v3.0.14 | BetterEnd //!! 1.20
//    modRuntimeOnly("com.github.quiqueck:BCLib:3.0.14") // BetterEnd //!! 1.20
//    modRuntimeOnly("com.github.quiqueck:WunderLib:1.1.5") // BetterEnd //!! 1.20
//    modRuntimeOnly("curse.maven:athena-841890:5176880") // Chipped //!! 1.20
//    modRuntimeOnly("curse.maven:resourceful-lib-570073:5973189")  // v3.0.12 | Chipped
//    modRuntimeOnly("curse.maven:resourceful-config-714059:6467774") // v3.0.11 | Better-Archeology
//    modRuntimeOnly("curse.maven:monolib-968432:6123964") // v2.0.0 | More-Beautiful-Torches //!! 1.20
//    modRuntimeOnly("curse.maven:architectury-api-419699:5786326") // v13.0.8 | Better-Archeology
//    modRuntimeOnly("curse.maven:balm-fabric-500525:6841887") // v21.0.49 | Waystones

    //- ~/fabric/mods LOCAL@

    //- OTHER MAVENS - can be commented out or enabled
//    modRuntimeOnly("com.jozufozu.flywheel:flywheel-fabric-${minecraft_version}:${flywheel_neoforge_version}") // Create
//    forgeRuntimeLibrary("com.teamresourceful:bytecodecs:1.0.2") // Chipped
//    modRuntimeOnly('net.jodah:typetools:0.6.3') // GlitchCore
//    modRuntimeOnly('com.electronwill.night-config:core:3.8.1') // GlitchCore
//    modRuntimeOnly('com.electronwill.night-config:toml:3.8.1') // GlitchCore

    //+ REQUIRED - The modules access libaries from below - Only in FABRIC
    modCompileOnly("curse.maven:owo-lib-532610:6446006")

    // OTHER MAVENS
    modCompileOnly("com.tterrag.registrate_fabric:Registrate:${property("registrate_fabric_version")}") // Create
    modCompileOnly("curse.maven:supermartijn642s-core-lib-454372:7521829") // v1.1.20 | Rechiseled

//!! =================================================== IMPORTS ==================================================== \\

    //- ONLY FOR TESTING - can be commented out or enabled

    //+ REQUIRED - The modules access libaries from below - ONLY IN FABRIC
    // MACAW's
    modCompileOnly("curse.maven:macaws-bridges-351725:5465222") //v3.0.0
    modCompileOnly("curse.maven:macaws-paths-and-pavings-629153:7029506") //v1.1.1
    modCompileOnly("curse.maven:macaws-windows-363569:5592083") //v2.3.0
    modCompileOnly("curse.maven:macaws-roofs-352039:5554934") //v2.3.1
    modCompileOnly("curse.maven:macaws-fences-and-walls-453925:5442197") //v1.1.2
    modCompileOnly("curse.maven:macaws-stairs-1119394:5802484") //v1.0.1

    //+ GENERAL
    modCompileOnly("curse.maven:create-fabric-624165:5982726") // Do not use for RunTime //!! 1.20

    // OTHER MAVENS
    modCompileOnly("earth.terrarium.chipped:chipped-fabric-${property("minecraft_version")}:4.0.2") //INCLUDED: Athena, Resourceful-Lib
//    modCompileOnly("com.simibubi.create:create-fabric-${minecraft_version}:${create_fabric_version}:slim") { isTransitive = false } // Registrate, Flywheel //!! maven is outdated & not available

    //+ MIRRORED FROM COMMON - Required because dependOn(common) compiles common sources with fabric classpath
    modCompileOnly("curse.maven:better-archeology-835687:8447627") // Resourceful-Config, Architectury-API
    modCompileOnly("curse.maven:blockus-312289:7920575") // Terraformersmc's terraform-wood-api
    modCompileOnly("curse.maven:building-but-better-989479:5382599") //!! 1.20
    modCompileOnly("curse.maven:fabric-waystones-410902:5798771")
    modCompileOnly("curse.maven:handcrafted-538214:6330032") // Resourceful-Lib
    modCompileOnly("curse.maven:lets-do-candlelight-farm-charm-compat-1038117:6963399") //+[Let's-Do]-Farm-&-Charm
    modCompileOnly("curse.maven:lets-do-farm-charm-1038103:6962195") // Architectury-API, Cloth-Config-API
    modCompileOnly("curse.maven:more-beautiful-torches-860325:5609745") // MonoLib //!! MERGED
    modCompileOnly("curse.maven:rechiseled-558998:7687483") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ]
    modCompileOnly("curse.maven:stoneworks-852663:5731745") // puzzles-lib, forge-config-api-port
    modCompileOnly("curse.maven:twigs-496913:6782788")
    modCompileOnly("curse.maven:waystones-245755:8450086") // Balm

//!! ================================================= FOR TESTING ================================================== \\

//!! Need to find a stone mod, I cannot find a simple mod, ugh!
//    modRuntimeOnly("curse.maven:betterend-413596:5680728") // BCLib, WunderLib, WorldWeaver  //!! ALPHA
//    modRuntimeOnly("curse.maven:mwtis-stone-expansion-907720:4767668") //!! 1.20

}