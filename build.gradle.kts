plugins {
    id("com.possible-triangle.core") version "1.4.213"
    id("com.possible-triangle.common") version "1.4.213" apply false
    id("com.possible-triangle.fabric") version "1.4.213" apply false
    id("com.possible-triangle.neoforge") version "1.4.213" apply false
    id("net.mehvahdjukaar.candlelight") version "1.2.1" apply false
}

mod {
    additional.add("mod_description")
    additional.add("mod_credits")
    additional.add("mod_license")
    additional.add("mod_homepage")
    additional.add("mod_authors")
    additional.add("mod_github")
    additional.add("moonlight_required_version")
    additional.add("everycomp_required_version")
}


subprojects {

    pluginManager.apply("com.possible-triangle.core")
    pluginManager.apply("net.mehvahdjukaar.candlelight")
    pluginManager.apply("maven-publish")

    dependencies {
        compileOnly("net.mehvahdjukaar:candlelight:1.2.1")
    }


    tasks.withType<GenerateModuleMetadata> {
        enabled = true
    }

    repositories {
        nexus()
    }

    upload {
        var modLoader = name
        curseforge {
            dependencies {
                //!! COMMON
                required("selene")
                required("every-compat")
                optional("gems-realm")
                optional("sawmill")

                optional("macaws-bridges")
                optional("macaws-fences-and-walls")
                optional("macaws-paths-and-pavings")
                optional("macaws-roofs")
                optional("macaws-windows")
                optional("macaws-stairs")

                //!! FABRIC
                if (modLoader == "fabric") {
                    required("fabric-api")
                    optional("additional-lights")
                    optional("building-but-better")
                    optional("better-archeology")
                    optional("create-fabric")
                    optional("decorative-blocks")
                    optional("mighty-mail-fabric")
                    optional("more-beautiful-torches")
                    optional("stoneworks")
                    optional("twigs")
                    optional("rechiseled")

                }
                //!! NEOFORGE
                else {
                    optional("additional-lights")
                    optional("better-archeology")
                    optional("blockus")
//                        optional("buildersaddition")
                    optional("building-but-better")
//                        optional("corail-pillar")
                    optional("create")
//                        optional("decorative-blocks")
//                        optional("mighty-mail")
                    optional("more-beautiful-torches")
                    optional("quark")
                    optional("stone-chest")
                    optional("stoneworks")
                    optional("twigs")
                    optional("rechiseled")
                }
            }
        }
        modrinth {
            dependencies {
                required("moonlight")
                required("every-compat")
                optional("gems-realm")
                optional("sawmill")

                optional("macaws-bridges")
                optional("macaws-fences-and-walls")
                optional("macaws-paths-and-pavings")
                optional("macaws-roofs")
                optional("macaws-windows")
                optional("macaws-stairs")


                if (modLoader == "fabric") {
                    required("fabric-api")
                    optional("additional-lights")
                    optional("better-archeology")
                    optional("building-but-better")
                    optional("chipped")
                    optional("create-fabric")
                    optional("decorative-blocks")
                    optional("mighty-mail-fabric")
                    optional("more-beautiful-torches")
                    optional("rechiseled")
                    optional("stoneworks")
                    optional("twigs")
    //                optional("corail-pillar") // Not-Available on Modrinth
                } else { //!! NEOFORGE
                    optional("additional-lights")
                    optional("better-archeology")
                    optional("building-but-better")
                    optional("create-fabric")
                    optional("decorative-blocks")
                    optional("mighty-mail-fabric")
                    optional("more-beautiful-torches")
                    optional("rechiseled")
                    optional("stoneworks")
                    optional("twigs")
                }
            }
        }
        forEach {
            changelog = rootProject.file("changelog.md").readText()
            versionName = "${mod.id.get()}-${mod.version.get()}-${name}"
        }
        maven {
            nexus()
        }
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.addAll(listOf("-Xmaxerrs", "4000"))
    }

    repositories {
        // Standard repositories
        mavenLocal()
        mavenCentral()

        // Flat directory for local mods
        flatDir { dirs("mods") }

        // Our publishing repo
        maven("https://api.modrinth.com/maven")
        maven("https://www.cursemaven.com")
        maven("https://jitpack.io")

//        maven("https://maven.neoforged.net/releases")
        maven("https://maven.architectury.dev")
        maven("https://maven.parchmentmc.org")

        maven("https://maven.createmod.net") // Create Mod, Ponder, Flywheel
        maven("https://maven.blamejared.com") // JEI, Vazkii's Mods
        maven("https://maven.ladysnake.org/releases") // Ladysnake mods
        maven("https://maven.tterrag.com/") // Flywheel, EnderIO
        maven("https://maven.ithundxr.dev/snapshots") // Registrate
        maven("https://mvn.devos.one/releases/") // Registrate, Porting Lib (releases)
        maven("https://mvn.devos.one/snapshots/") // Registrate, Porting Lib (snapshots)
//        maven("https://maven.terraformersmc.com/") // EMI - currently down
        maven("https://maven.saps.dev/releases") // FTB Mods

        maven("https://maven.theillusivec4.top/") // Curios API
        maven("https://maven.squiddev.cc") // CC: Tweaked
        maven("https://maven.su5ed.dev/releases") // SU5ED mods
        maven("https://harleyoconnor.com/maven") // Dynamic Trees
        maven("https://maven.misterpemodder.com/libs-release/") // ShulkerBoxTooltip
        maven("https://maven.firstdarkdev.xyz/snapshots") // FirstDarkDev (snapshots)
        maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven") // NeoForge-config-API-port

        maven("https://maven.shedaniel.me/") // Cloth Config
        maven("https://squiddev.cc/maven") // CC: Tweaked
        maven("https://maven.teamresourceful.com/repository/maven-public/") // Chipped
        maven("https://raw.githubusercontent.com/moddingplayground/maven/main/")
        maven("https://maven.resourcefulbees.com/repository/maven-public/") // Resourceful-Lib
        maven("https://maven.blamejared.com/") // JEI, CraftTweaker
        maven("https://modmaven.dev") // Botania & FALLBACK for JEI
        maven("https://maven.isxander.dev/releases") // Yet-Another-Config-Lib

        maven { // Reach Entity Attributes
            url = uri("https://maven.jamieswhiteshirt.com/libs-release")
            content { includeGroup(("com.jamieswhiteshirt")) }
        }
//        maven("https://maven.muon.rip/releases") // Possible-Triangle Plugins
    }
}