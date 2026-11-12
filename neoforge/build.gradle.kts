plugins {
    id("com.possible-triangle.neoforge")
}

neoforge {
    dependOn(project(":common"))
    accessWidener(project(":common"))
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

val localReposDir: String? = System.getenv("REPOS26_1_2")
dependencies {

//!! MOONLIGHT LIB (REQUIRED)  -------------------------------------------------------------------------------------- \\

    //- LOCAL
    if (findProperty("enable_moonlight_test").toString().toBoolean()) {
        val repos = requireNotNull(localReposDir) { "enable_moonlight_test is on but the REPOS26_1_2 env var is not set" }
        modApi(files("$repos/Moonlight/neoforge/build/libs/moonlight-${property("moonlight_testVersion")}-neoforge.jar"))
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
        val repos = requireNotNull(localReposDir) { "enable_everycomp_test is on but the REPOS26_1_2 env var is not set" }
        implementation(files("$repos/WoodGood/neoforge/build/libs/everycomp-${property("everycomp_testVersion")}-neoforge.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:every-compat:${property("everycomp_version")}-neoforge")
        else modApi("net.mehvahdjukaar:everycomp-neoforge:${property("everycomp_version")}:neoforge") { isTransitive = false }
    }

//!! TOOLS ---------------------------------------------------------------------------------------------------------- \\

//    modRuntimeOnly("com.blamejared.crafttweaker:CraftTweaker-neoforge-${property("minecraft_version")}:${property("crafttweaker_version")}")
//    modRuntimeOnly("dev.emi:emi-neoforge:${property("emi_version")}+${property("minecraft_version")}")
//    modRuntimeOnly("maven.modrinth:emi:${property("emi_version")}+${property("minecraft_version")}+neoforge")
    modRuntimeOnly("mezz.jei:jei-${property("minecraft_version")}-neoforge:${property("jei_version")}")
    modRuntimeOnly("maven.modrinth:jade:${property("jade_version")}+neoforge")
//    modRuntimeOnly("curse.maven:worldedit-225608:8037379") // v7.4.3

    //+ REQUIRED
    modImplementation("curse.maven:configured-457570:7943180")

//!! ================================================= DEPENDENCIES ================================================= \\
    //- ONLY FOR TESTING - can be commented out or enabled
    modRuntimeOnly("curse.maven:terrablender-neoforge-940057:8384529") // v26.1.2.0.3 | Biomes-O'-Plenty
    modRuntimeOnly("curse.maven:glitchcore-955399:8109770") // v26.1.2.0.2 | Biomes-O'-Plenty | required: Typetools, Night-Config //!! 1.21.1
//    modRuntimeOnly("curse.maven:aeroblender-879879:5911923") // v1.0.0 | Aether //!! 1.21.1
//    modRuntimeOnly("curse.maven:citadel-331936:6002521") // v2.6.1 | Alexs-Caves //!! 1.21.1
//    modRuntimeOnly("curse.maven:citadel-1-21-1-port-1415723:7982052") // v2.7.6 | Alexs-Caves //!! 1.21.1
//    modRuntimeOnly("curse.maven:yungs-api-421850:8850510") // v6.1.2 | NONE
//    modRuntimeOnly("curse.maven:patchouli-306770:8404543") // v94 | TFC //!! 1.21.1
//    modRuntimeOnly("curse.maven:puzzles-lib-495476:9129960") // v26.1.16 | Stoneworks
//    modRuntimeOnly("curse.maven:athena-841890:7970442") // v4.7.3 | Chipped
//    modRuntimeOnly("curse.maven:resourceful-lib-570073:9358032") // v4.0.2 | Chipped, Handcrafted
//    modRuntimeOnly("curse.maven:resourceful-config-714059:7872554") // v4.0.1 | Better-Archeology
//    modRuntimeOnly("curse.maven:fusion-connected-textures-854949:9070415") // v1.3.16 | Rechiseled
//    modRuntimeOnly("curse.maven:mantle-74924:6041712") // v1.11.28 | Tinkers-Construct //!! 1.21.1
//    modRuntimeOnly("curse.maven:blueprint-382216:5292242") // v7.1.0 | The-Outer-End, Caverns-And-Chasms //!! 1.21.1
//    modRuntimeOnly("curse.maven:monolib-968432:9050778") // v4.1.2 | More-Beautiful-Torches
//    modRuntimeOnly("curse.maven:architectury-api-419699:9111154") // v20.1.17 | Better-Archeology, [Let's do] Farm & Charm
    modRuntimeOnly("curse.maven:shogi-1475746:8978486") // v26.1.2.12 | Waystones
    modRuntimeOnly("curse.maven:balm-531761:8969765") // v26.1.2.15 | Waystones
//    modRuntimeOnly("curse.maven:curios-309927:8463519") // v15.0.0 | Ars-Nouveau
//    modRuntimeOnly("curse.maven:azimuth-api-1466331:9058513") // v1.4.10 | Create, Bits-N-Bobs //!! 1.21.1
//    modRuntimeOnly("curse.maven:strut-your-stuff-1486746:8812965") // v1.4.10 | Create, Bits-N-Bobs //!! 1.21.1
//    modRuntimeOnly("curse.maven:platform-997634:8075942") // v1.3.3 | Vanilla-Backport //!! 1.21.1

    //- ~/neoforge/mods LOCAL
//    modRuntimeOnly("quark-biolith:biolith-neoforge-3.0.10") // Quark

    //- OTHER MAVENs - can be commented out or enabled
//    forgeRuntimeLibrary("com.teamresourceful:bytecodecs:1.0.2") // Chipped
//    modRuntimeOnly("software.bernie.geckolib:geckolib-neoforge-${property("minecraft_version")}:4.7.7") // Oh-The-Biomes-We've-Gone, Ars-Nouveau

    //+ REQUIRED - The modules access libaries from below - Only IN NEOFORGE
    modCompileOnly("curse.maven:framework-549225:7964506") // v0.13.23 | +Refurbished-Furniture
    modCompileOnly("curse.maven:supermartijn642s-config-lib-438332:8306682") // v1.1.8 | Rechiseled
    modCompileOnly("curse.maven:supermartijn642s-core-lib-454372:9447187") // v1.1.26b | Rechiseled

    modCompileOnly("org.violetmoon.zeta:Zeta:1.1-40-SNAPSHOT") // Quark - @ https://maven.blamejared.com/org/violetmoon/zeta/Zeta/
//    modCompileOnly("curse.maven:zeta-968868:7640154") // v1.1-39 | TEMP BACKUP MAVEN

    //+ OTHER MAVENS
    modCompileOnly("com.tterrag.registrate:Registrate:${property("registrate_version")}") // Create
    modCompileOnly("net.createmod.ponder:ponder-neoforge:${property("ponder_version")}+mc1.21.1") // Create
    modCompileOnly("dev.engine-room.flywheel:flywheel-neoforge-1.21.1:${property("flywheel_neoforge_version")}") // Create

//!! =================================================== IMPORTS ==================================================== \\
    //+ REQUIRED - The modules access libaries from below - ONLY IN NEOFORGE
    // MACAW's
    modCompileOnly("curse.maven:macaws-bridges-351725:7809923")
    modCompileOnly("curse.maven:macaws-fences-and-walls-453925:7809939")
    modCompileOnly("curse.maven:macaws-lights-and-lamps-502372:7809968")
    modCompileOnly("curse.maven:macaws-paths-and-pavings-629153:7809974")
    modCompileOnly("curse.maven:macaws-roofs-352039:7809972")
    modCompileOnly("curse.maven:macaws-windows-363569:7809964")
    modCompileOnly("curse.maven:macaws-stairs-1119394:7809966")

    //+ GENERAL
    modCompileOnly("curse.maven:create-bits-n-bobs-1398383:8886545") // Create, Azimuth-API, Strut-Your-Stuff //!! 1.21.1
    modCompileOnly("curse.maven:additional-lights-384991:9128435")
    modCompileOnly("curse.maven:stone-chest-307052:8145831")

    //+ OTHER MAVENS
    modCompileOnly("com.simibubi.create:create-1.21.1:${property("create_version")}:slim") { isTransitive = false } // Registrate, Flywheel, Ponder
    modCompileOnly("earth.terrarium.chipped:chipped-neoforge-1.21.1:4.0.2") //INCLUDED: Athena, Resourceful-Lib, REQUIRE: Bytecodecs

    modCompileOnly("org.violetmoon.quark:Quark:4.1-482-SNAPSHOT") // Zeta, Biolith @  https://maven.blamejared.com/org/violetmoon/quark/Quark/ //!! 1.21.1
//    modCompileOnly("curse.maven:quark-243121:7640331") // v4.1.474 | TEMP BACKUP MAVEN

    //+ MIRRORED FROM COMMON - Required because dependOn(common) compiles common sources with neoforge classpath
    modCompileOnly("curse.maven:better-archeology-835687:9099959") // Resourceful-Config, Architectury-API
    modCompileOnly("curse.maven:building-but-better-989479:5362380") // v1.0.1 //!! 1.21.1
    modCompileOnly("curse.maven:decorative-blocks-reborn-1327768:8959324")
    modCompileOnly("curse.maven:handcrafted-538214:6330030") // Resourceful-Lib //!! 1.21.1
    modCompileOnly("curse.maven:lets-do-candlelight-farm-charm-compat-1038117:9150310") //+[Let's-Do]-Farm-&-Charm
    modCompileOnly("curse.maven:lets-do-farm-charm-1038103:9203717") // Architectury-API //!! 1.21.1
//    modCompileOnly("curse.maven:more-beautiful-torches-860325:8988924") // Monolib //!! MERGED
    modCompileOnly("curse.maven:rechiseled-558998:8875922") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ]
    modCompileOnly("curse.maven:refurbished-furniture-897116:7943178") // Framework
//    modCompileOnly("curse.maven:stoneworks-852663:7995178") // puzzles-lib
    modCompileOnly("curse.maven:twigs-496913:8191595") //!! 1.21.1
    modCompileOnly("curse.maven:waystones-245755:9406314") // Shogi

    // ========== LOCAL (NOTE: JARJAR meant the mod is embedded in the other mod)
    modCompileOnly("local-mod_foundation:foundation-neoforge-1.0.7") //JARJAR - [Let's Do]-Farm&Charms //!! 1.21.1

//!! ================================================= FOR TESTING ================================================== \\

    // STONETYPE ONLY
//    modRuntimeOnly("curse.maven:alexs-caves-924854:5848216") // Citadel //!! 1.21.1
//    modRuntimeOnly("curse.maven:alexs-caves-unofficial-port-1420348:7981977") // Citadel //!! 1.21.1
//    modRuntimeOnly("curse.maven:strata-forge-edition-387296:4989643") //!! 1.21.1
//    modRuntimeOnly("curse.maven:spelunkery-790530:5727135") // Moonlight-Lib //!! 1.21.1
//    modRuntimeOnly("curse.maven:tinkers-construct-74072:6041763") // Mantle //!! 1.21.1
    modRuntimeOnly("curse.maven:thaumon-926511:5492714") //!! 1.21.1
//    modRuntimeOnly("curse.maven:ars-nouveau-401955:6954892") // Curios-API, GeckoLib //!! 1.21.1
//    modRuntimeOnly("curse.maven:gaia-dimension-302529:8918019")
//    modRuntimeOnly("curse.maven:what-is-stone-colorful-caves-853161:5175855") //!! 1.21.1
//    modRuntimeOnly("curse.maven:artsandcrafts-1034791:9204111")
//    modRuntimeOnly("curse.maven:the-outer-end-430404:5812948") // Blueprint //!! 1.21.1
//    modRuntimeOnly("curse.maven:caverns-and-chasms-438005:6040957") // Blueprint //!! 1.21.1
//    modRuntimeOnly("curse.maven:vanillabackport-417430:8229724") // Platform //!! 1.21.1

    // MUDTYPE ONLY
    modRuntimeOnly("curse.maven:deeperdarker-659011:5908863") //!! 1.21.1
//    modRuntimeOnly("curse.maven:oh-the-biomes-weve-gone-1070751:6808771") // CorgiLib, GeckoLib, Oh-The-Trees-You'll-Grow, Terrablender //!! 1.21.1

    // Oh-The_Biomes-We've-Gone's Dependency
//    modRuntimeOnly("curse.maven:corgilib-693313:6808264") // v5.0.0.7 | Oh-The-Biomes-We've-Gone //!! 1.21.1
//    modRuntimeOnly("curse.maven:oh-the-trees-youll-grow-962544:9467839") // v5.0.14 | Oh-The-Biomes-We've-Gone

    // STONETYPE & WOODTYPE
//    modRuntimeOnly("curse.maven:terrafirmacraft-302973:5943050") // patchouli //!! 1.21.1


    //@ Aether & Deep_Aether's dependency mods
//    modImplementation("io.wispforest:owo-lib-neoforge:0.12.15.1-beta.6+1.21") //!! 1.21.1
//    modImplementation("io.wispforest:accessories-neoforge:1.1.0-beta.49+1.21.1") //!! 1.21.1

//    forgeRuntimeLibrary("blue.endless:jankson:1.2.2")
//    forgeRuntimeLibrary("io.wispforest:endec:0.1.9")
//    forgeRuntimeLibrary("io.wispforest.endec:gson:0.1.5")
//    forgeRuntimeLibrary("io.wispforest.endec:jankson:0.1.6")
//    forgeRuntimeLibrary("io.wispforest.endec:netty:0.1.5")

}