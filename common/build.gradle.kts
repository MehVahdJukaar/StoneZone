plugins {
    id("com.possible-triangle.common")
}

common {
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
        else modApi("net.mehvahdjukaar:moonlight-common:${property("moonlight_version")}") { isTransitive = false }
    }
    accessTransformers("net.mehvahdjukaar:moonlight-common:${property("moonlight_version")}")

//!! EVERY COMPAT (REQUIRED) ---------------------------------------------------------------------------------------- \\

    //- LOCAL
    if (findProperty("enable_everycomp_test").toString().toBoolean()) {
        modApi(files(path + "\\WoodGood\\neoforge\\build\\libs\\everycomp-${property("everycomp_testVersion")}-neoforge.jar"))
    }
    //+ MAVEN
    else {
        if (findProperty("maven_backup").toString().toBoolean()) modApi("maven.modrinth:every-compat:${property("everycomp_version")}-neoforge")
        else modApi("net.mehvahdjukaar:everycomp-common:${property("everycomp_version")}") { isTransitive = false }
    }

//!! ================================================= DEPENDENCIES ================================================= \\
    //- OTHER MAVENs - can be commented out or enabled
    modCompileOnly("dev.engine-room.flywheel:flywheel-neoforge-${property("minecraft_version")}:${property("flywheel_neoforge_version")}") // Create
    modCompileOnly("net.createmod.ponder:Ponder-NeoForge-${property("minecraft_version")}:${property("ponder_version")}") // Create

    //+ REQUIRED - The modules access libaries from below - Only in FORGE
    modCompileOnly("org.violetmoon.zeta:Zeta:1.1-40-SNAPSHOT") // Quark
    modCompileOnly("curse.maven:supermartijn642s-config-lib-438332:5546996") // v1.1.8 | Rechiseled
    modCompileOnly("curse.maven:supermartijn642s-core-lib-454372:7521894") // v1.1.20 | Rechiseled

    //+ OTHER MAVENS
    modCompileOnly("com.tterrag.registrate:Registrate:${property("registrate_version")}") // Create

//!! =================================================== IMPORTS ==================================================== \\
    //+ REQUIRED - The modules access libaries from below - ONLY IN FORGE
    // MACAW's
    modCompileOnly("curse.maven:macaws-bridges-351725:5465228") //v3.0.0
    modCompileOnly("curse.maven:macaws-paths-and-pavings-629153:7029451") //v1.1.1
    modCompileOnly("curse.maven:macaws-windows-363569:5592081") //v2.3.0
    modCompileOnly("curse.maven:macaws-roofs-352039:5554896") //v2.3.1
    modCompileOnly("curse.maven:macaws-fences-and-walls-453925:5442175") //v1.1.2
    modCompileOnly("curse.maven:macaws-stairs-1119394:5802422") //v1.0.1

    //+ GENERAL
    modCompileOnly("curse.maven:additional-lights-384991:6841545")
    modCompileOnly("curse.maven:better-archeology-835687:8447628") // Resourceful-Config, Architectury-API
    modCompileOnly("curse.maven:building-but-better-989479:5362380") // v1.0.1 //!! 1.20
    modCompileOnly("curse.maven:handcrafted-538214:6330030") // Resourceful-Lib
    modCompileOnly("curse.maven:lets-do-candlelight-farm-charm-compat-1038117:6963407") //+[Let's-Do]-Farm-&-Charm
    modCompileOnly("curse.maven:lets-do-farm-charm-1038103:6962704") // Architectury-API
    modCompileOnly("curse.maven:more-beautiful-torches-860325:5609745") // Monolib //!! MERGED
    modCompileOnly("curse.maven:rechiseled-558998:8301793") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ]
    modCompileOnly("curse.maven:stone-chest-307052:7432348")
    modCompileOnly("curse.maven:stoneworks-852663:5731747") // puzzles-lib
    modCompileOnly("curse.maven:twigs-496913:8191595")
    modCompileOnly("curse.maven:waystones-245755:8450087") // Balm

    // OTHER MAVENS
    modCompileOnly("com.simibubi.create:create-1.21.1:${property("create_version")}:slim") { isTransitive = false } // Registrate, Flywheel, Ponder
    modCompileOnly("org.violetmoon.quark:Quark:4.1-481-SNAPSHOT") // Zeta, Biolith @  https://maven.blamejared.com/org/violetmoon/quark/Quark/
    modCompileOnly("earth.terrarium.chipped:chipped-neoforge-${property("minecraft_version")}:4.0.2") //INCLUDED: Athena, Resourceful-Lib, REQUIRE: Bytecodecs

}

tasks.named("copyAccessTransformersPublications") {
    dependsOn(":common:transformAccessWidener")
}