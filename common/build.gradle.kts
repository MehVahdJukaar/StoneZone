plugins {
    id("com.possible-triangle.common")
}

common {
    neoformVersion = "26.1.2-1"
    accessWidener()
}

val modId: String = property("mod_id").toString()
val modVersion: String = property("mod_version").toString()
tasks.named<Jar>("jar") {
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("common")
}
tasks.named<Jar>("sourcesJar") {
    from(sourceSets.main.get().allSource)
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("common-sources")
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
        else modApi("net.mehvahdjukaar:moonlight-common:${property("moonlight_version")}") { isTransitive = false }
    }
    accessTransformers("net.mehvahdjukaar:moonlight-common:${property("moonlight_version")}")

//!! EVERY COMPAT (REQUIRED) ---------------------------------------------------------------------------------------- \\

    //- LOCAL
    if (findProperty("enable_everycomp_test").toString().toBoolean()) {
        val repos = requireNotNull(localReposDir) { "enable_everycomp_test is on but the REPOS26_1_2 env var is not set" }
        compileOnly(files("$repos/WoodGood/common/build/libs/everycomp-${property("everycomp_testVersion")}-common.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:every-compat:${property("everycomp_version")}-neoforge")
        else modApi("net.mehvahdjukaar:everycomp-common:${property("everycomp_version")}") { isTransitive = false }
    }

//!! ================================================= DEPENDENCIES ================================================= \\
    //- OTHER MAVENs - can be commented out or enabled

    //+ REQUIRED - The modules access libaries from below - Only in NEOFORGE
    modCompileOnly("curse.maven:framework-549225:7964506") // v0.13.23 | +Refurbished-Furniture, Mighty-Mail, Backpacked
    modCompileOnly("curse.maven:supermartijn642s-config-lib-438332:8306682") // v1.1.8 | Rechiseled
    modCompileOnly("curse.maven:supermartijn642s-core-lib-454372:9447187") // v1.1.26b | Rechiseled
//    modCompileOnly("curse.maven:balm-531761:8969765") // v26.1.2.15 | Waystones

    //+ OTHER MAVENS
    modCompileOnly("org.violetmoon.zeta:Zeta:1.1-40-SNAPSHOT") // Quark //!! 1.21.1
    modCompileOnly("com.tterrag.registrate:Registrate:${property("registrate_version")}") // Create //!! 1.21.1
    modCompileOnly("net.createmod.ponder:ponder-neoforge:${property("ponder_version")}+mc1.21.1") // Create //!! 1.21.1
    modCompileOnly("dev.engine-room.flywheel:flywheel-neoforge-1.21.1:${property("flywheel_neoforge_version")}") // Create //!! 1.21.1

    // ========== LOCAL (NOTE: JARJAR meant the mod is embedded in the other mod)
    modCompileOnly("local-mod_foundation:foundation-neoforge-1.0.7") //JARJAR - [Let's Do]-Farm&Charms //!! 1.21.1

//!! =================================================== IMPORTS ==================================================== \\
    //+ REQUIRED - The modules access libaries from below - ONLY IN FORGE
    // MACAW's
    modCompileOnly("curse.maven:macaws-bridges-351725:7809923") //v3.1.2
    modCompileOnly("curse.maven:macaws-paths-and-pavings-629153:7809974") //v1.1.1
    modCompileOnly("curse.maven:macaws-windows-363569:7809964") //v2.4.2
    modCompileOnly("curse.maven:macaws-roofs-352039:7809972") //v2.3.2
    modCompileOnly("curse.maven:macaws-fences-and-walls-453925:7809939") //v1.2.1
    modCompileOnly("curse.maven:macaws-stairs-1119394:7809966") //v1.0.2

    //+ GENERAL
    modCompileOnly("curse.maven:additional-lights-384991:9128435") // v3.1.4
    modCompileOnly("curse.maven:better-archeology-835687:9099959") // v1.3.9 | Resourceful-Config, Architectury-API
    modCompileOnly("curse.maven:building-but-better-989479:5362380") // v1.0.1 //!! 1.21.1
    modCompileOnly("curse.maven:decorative-blocks-reborn-1327768:8959324") // v8.0.0
    modCompileOnly("curse.maven:handcrafted-538214:6330030") // Resourceful-Lib //!! 1.21.1
    modCompileOnly("curse.maven:lets-do-candlelight-farm-charm-compat-1038117:9150310") //+[Let's-Do]-Farm-&-Charm //!! 1.21.1
    modCompileOnly("curse.maven:lets-do-farm-charm-1038103:9203717") // Architectury-API //!! 1.21.1
    modCompileOnly("curse.maven:more-beautiful-torches-860325:8988924") // Monolib //!! MERGED
    modCompileOnly("curse.maven:rechiseled-558998:8875922") // v1.2.6 | Fusion, supermartijn642s-[ Config-Lib, Core-Lib ]
    modCompileOnly("curse.maven:refurbished-furniture-897116:7943178") // Framework
    modCompileOnly("curse.maven:stone-chest-307052:8145831")
    modCompileOnly("curse.maven:stoneworks-852663:7995178") // v26.1.0 | puzzles-lib
    modCompileOnly("curse.maven:twigs-496913:8191595") //!! 1.21.1
    modCompileOnly("curse.maven:waystones-245755:9406314") // Shogi

    //+ OTHER MAVENS
    modCompileOnly("com.simibubi.create:create-1.21.1:${property("create_version")}:slim") { isTransitive = false } // Registrate, Flywheel, Ponder //!! 1.21.1
    modCompileOnly("org.violetmoon.quark:Quark:4.1-481-SNAPSHOT") // Zeta, Biolith @  https://maven.blamejared.com/org/violetmoon/quark/Quark/ //!! 1.21.1
    modCompileOnly("earth.terrarium.chipped:chipped-neoforge-1.21.1:4.0.2") //INCLUDED: Athena, Resourceful-Lib, REQUIRE: Bytecodecs //!! 1.21.1

}

tasks.named("copyAccessTransformersPublications") {
    dependsOn(":common:transformAccessWidener")
}