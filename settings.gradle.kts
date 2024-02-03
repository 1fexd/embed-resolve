rootProject.name = "embed-resolve"
include("lib")

if (System.getenv("JITPACK")?.toBooleanStrictOrNull() != false) {
    include("testing")
}
