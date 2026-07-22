plugins {
    id("com.possible-triangle.neoforge")
}

neoforge {
    dependOn(project(":common"))
    accessWidener(project(":common"))
}

mod {
    additional.add("neoforge_version_range")
}

val modId: String = property("mod_id").toString()
val modVersion: String = property("mod_version").toString()
tasks.named<Jar>("jar") {
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("neoforge")
}
tasks.named<Jar>("sourcesJar") {
    from(sourceSets.main.get().allSource)
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("neoforge-sources")
}

val path = System.getenv("REPOS21_1").toString()
dependencies {

//!! MOONLIGHT LIB (REQUIRED)  -------------------------------------------------------------------------------------- \\
    //- LOCAL
    if (findProperty("enable_moonlight_test").toString().toBoolean()) {
        modApi(files(path + "\\Moonlight\\neoforge\\build\\libs\\moonlight-${property("moonlight_testVersion")}-neoforge.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:moonlight:${property("moonlight_version")}-neoforge")
        else modApi("net.mehvahdjukaar:moonlight-neoforge:${property("moonlight_version")}") { isTransitive = false }
    }
    accessTransformers("net.mehvahdjukaar:moonlight-neoforge:${property("moonlight_version")}")
//    implementation("net.mehvahdjukaar:codecui-neoforge:1.0.2")

//!! EVERY COMPAT (REQUIRED) ---------------------------------------------------------------------------------------- \\

    //- LOCAL
    if (findProperty("enable_everycomp_test").toString().toBoolean()) {
        modApi(files(path + "\\WoodGood\\neoforge\\build\\libs\\everycomp-${property("everycomp_testVersion")}-neoforge.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:every-compat:${property("everycomp_version")}-neoforge")
        else modApi("net.mehvahdjukaar:everycomp-neoforge:${property("everycomp_version")}:neoforge") { isTransitive = false }
    }

//!! TOOLS ---------------------------------------------------------------------------------------------------------- \\

    modRuntimeOnly("com.blamejared.crafttweaker:CraftTweaker-neoforge-${property("minecraft_version")}:${property("crafttweaker_version")}")
//    modRuntimeOnly("dev.emi:emi-neoforge:${property("emi_version")}+${property("minecraft_version")}")
    modRuntimeOnly("maven.modrinth:emi:${property("emi_version")}+${property("minecraft_version")}+neoforge")
    modRuntimeOnly("curse.maven:jei-238222:5846880")
    modRuntimeOnly("curse.maven:jade-324717:5884231") // v15.8.3
    modRuntimeOnly("curse.maven:configured-457570:5873783")
//    modRuntimeOnly("curse.maven:worldedit-225608:5830452")

//!! ================================================= DEPENDENCIES ================================================= \\
    //- ONLY FOR TESTING - can be commented out or enabled
    modRuntimeOnly("curse.maven:terrablender-neoforge-940057:6054947") // v4.1.0.8 | Biomes-O'-Plenty
    modRuntimeOnly("curse.maven:glitchcore-955399:5660740") // v2.1.0.0 | Biomes-O'-Plenty | required: Typetools, Night-Config
//    modRuntimeOnly("curse.maven:aeroblender-879879:5911923") // v1.0.0 | Aether
//    modRuntimeOnly("curse.maven:citadel-331936:6002521") // v2.6.1 | Alexs-Caves //!! 1.20
//    modRuntimeOnly("curse.maven:yungs-api-421850:6715462") // v5.1.6 | NONE //!! FORGE
//    modRuntimeOnly("curse.maven:patchouli-306770:4966125") // v84 | TFC //!! 1.20
//    modRuntimeOnly("curse.maven:puzzles-lib-495476:6494827") // v21.1.36 | Stoneworks
//    modRuntimeOnly("curse.maven:athena-841890:5176879") // v3.1.2 | Chipped //!! 1.20
//    modRuntimeOnly("curse.maven:resourceful-lib-570073:5973188") // v3.0.12 | Chipped, Handcrafted
//    modRuntimeOnly("curse.maven:resourceful-config-714059:6467772") // v3.0.11 | Better-Archeology
//    modRuntimeOnly("curse.maven:fusion-connected-textures-854949:7471474") // v1.2.12 | Rechiseled
//    modRuntimeOnly("curse.maven:mantle-74924:6041712") // v1.11.28 | Tinkers-Construct //!! 1.20
//    modRuntimeOnly("curse.maven:blueprint-382216:5292242") // v7.1.0 | The-Outer-End, Caverns-And-Chasms //!! 1.20
//    modRuntimeOnly("curse.maven:monolib-968432:6123976") // v2.0.0 | More-Beautiful-Torches //!! 1.20
//    modRuntimeOnly("curse.maven:architectury-api-419699:5786327") // v13.0.8 | Better-Archeology
//    modRuntimeOnly("curse.maven:balm-531761:8424824") // v21.0.63 | Waystones
//    modRuntimeOnly("curse.maven:curios-309927:6529130") // v9.5.1 | Ars-Nouveau

    //- ~/neoforge/mods LOCAL
//    modRuntimeOnly("quark-biolith:biolith-neoforge-3.0.10") // Quark

    //- OTHER MAVENs - can be commented out or enabled
//    forgeRuntimeLibrary("com.teamresourceful:bytecodecs:1.0.2") // Chipped
//    modRuntimeOnly("dev.engine-room.flywheel:flywheel-neoforge-${property("minecraft_version")}:${property("flywheel_neoforge_version")}") // Create
//    modRuntimeOnly("net.createmod.ponder:Ponder-NeoForge-${property("minecraft_version")}:${property("ponder_version")}") // Create
//    modRuntimeOnly("software.bernie.geckolib:geckolib-neoforge-${property("minecraft_version")}:4.7.7") // Oh-The-Biomes-We've-Gone, Ars-Nouveau

    //+ REQUIRED - The modules access libaries from below - Only IN NEOFORGE
    modCompileOnly("curse.maven:supermartijn642s-config-lib-438332:5546996") // v1.1.8 | Rechiseled
    modCompileOnly("curse.maven:supermartijn642s-core-lib-454372:7521894") // v1.1.20 | Rechiseled
    modCompileOnly("org.violetmoon.zeta:Zeta:1.1-40-SNAPSHOT") // Quark

    //+ OTHER MAVENS
    modCompileOnly("com.tterrag.registrate:Registrate:${property("registrate_version")}") // Create

//!! =================================================== IMPORTS ==================================================== \\
    //+ REQUIRED - The modules access libaries from below - ONLY IN NEOFORGE
    // MACAW's
    modCompileOnly("curse.maven:macaws-bridges-351725:5465228") //v3.0.0
    modCompileOnly("curse.maven:macaws-paths-and-pavings-629153:7029451") //v1.1.1
    modCompileOnly("curse.maven:macaws-windows-363569:5592081") //v2.3.0
    modCompileOnly("curse.maven:macaws-roofs-352039:5554896") //v2.3.1
    modCompileOnly("curse.maven:macaws-fences-and-walls-453925:5442175") //v1.1.2
    modCompileOnly("curse.maven:macaws-stairs-1119394:5802422") //v1.0.1

    //+ GENERAL
    modCompileOnly("curse.maven:additional-lights-384991:6841545")
    modCompileOnly("curse.maven:stone-chest-307052:7432348")

    // OTHER MAVENS
    modImplementation("com.simibubi.create:create-1.21.1:${property("create_version")}:slim") { isTransitive = false } // Registrate, Flywheel, Ponder
    modCompileOnly("org.violetmoon.quark:Quark:4.1-481-SNAPSHOT") // Zeta, Biolith @  https://maven.blamejared.com/org/violetmoon/quark/Quark/
    modCompileOnly("earth.terrarium.chipped:chipped-neoforge-${property("minecraft_version")}:4.0.2") //INCLUDED: Athena, Resourceful-Lib, REQUIRE: Bytecodecs

    //+ MIRRORED FROM COMMON - Required because dependOn(common) compiles common sources with neoforge classpath
    modCompileOnly("curse.maven:better-archeology-835687:8447628") // Resourceful-Config, Architectury-API
    modCompileOnly("curse.maven:building-but-better-989479:5362380") // v1.0.1 //!! 1.20
    modCompileOnly("curse.maven:handcrafted-538214:6330030") // Resourceful-Lib
    modCompileOnly("curse.maven:lets-do-candlelight-farm-charm-compat-1038117:6963407") //+[Let's-Do]-Farm-&-Charm
    modCompileOnly("curse.maven:lets-do-farm-charm-1038103:6962704") // Architectury-API
//    modCompileOnly("curse.maven:more-beautiful-torches-860325:5609745") // Monolib //!! MERGED
    modCompileOnly("curse.maven:rechiseled-558998:7687594") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ]
//    modCompileOnly("curse.maven:stoneworks-852663:5731747") // puzzles-lib
    modCompileOnly("curse.maven:twigs-496913:8191595")
    modCompileOnly("curse.maven:waystones-245755:8450087") // Balm


//!! ================================================= FOR TESTING ================================================== \\

    // STONETYPE ONLY
//    modRuntimeOnly("curse.maven:alexs-caves-924854:5848216") // Citadel //!! 1.20
//    modRuntimeOnly("curse.maven:strata-forge-edition-387296:4989643") //!! 1.20
//    modRuntimeOnly("curse.maven:spelunkery-790530:5727135") // Moonlight-Lib //!! 1.20
//    modRuntimeOnly("curse.maven:tinkers-construct-74072:6041763") // Mantle //!! 1.20
    modRuntimeOnly("curse.maven:thaumon-926511:5492714")
//    modRuntimeOnly("curse.maven:ars-nouveau-401955:6954892") // Curios-API, GeckoLib
//    modRuntimeOnly("curse.maven:gaia-dimension-302529:5773438")
//    modRuntimeOnly("curse.maven:what-is-stone-colorful-caves-853161:5175855") //!! 1.20
//    modRuntimeOnly("curse.maven:artsandcrafts-1034791:5992027") //!! 1.20
//    modRuntimeOnly("curse.maven:the-outer-end-430404:5812948") // Blueprint //!! 1.20
//    modRuntimeOnly("curse.maven:caverns-and-chasms-438005:6040957") // Blueprint //!! 1.20

    // MUDTYPE ONLY
    modRuntimeOnly("curse.maven:deeperdarker-659011:5908863")
//    modRuntimeOnly("curse.maven:oh-the-biomes-weve-gone-1070751:6808771") // CorgiLib, GeckoLib, Oh-The-Trees-You'll-Grow, Terrablender

    // Oh-The_Biomes-We've-Gone's Dependency
//    modRuntimeOnly("curse.maven:corgilib-693313:6808264") // v5.0.0.7 | Oh-The-Biomes-We've-Gone
//    modRuntimeOnly("curse.maven:oh-the-trees-youll-grow-962544:6607561") // v5.0.14 | Oh-The-Biomes-We've-Gone

    // STONETYPE & WOODTYPE
//    modRuntimeOnly("curse.maven:terrafirmacraft-302973:5943050") // patchouli //!! 1.20


    //@ Aether & Deep_Aether's dependency mods
//    modImplementation("io.wispforest:owo-lib-neoforge:0.12.15.1-beta.6+1.21")
//    modImplementation("io.wispforest:accessories-neoforge:1.1.0-beta.49+1.21.1")

//    forgeRuntimeLibrary("blue.endless:jankson:1.2.2")
//    forgeRuntimeLibrary("io.wispforest:endec:0.1.9")
//    forgeRuntimeLibrary("io.wispforest.endec:gson:0.1.5")
//    forgeRuntimeLibrary("io.wispforest.endec:jankson:0.1.6")
//    forgeRuntimeLibrary("io.wispforest.endec:netty:0.1.5")

}