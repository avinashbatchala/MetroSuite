# MetroSuite

A monorepo of Windows 10 Mobile / Windows Phone style Android apps that share one
design system, while each forked app keeps its upstream separated so it can be rebased.

## Layout

```
MetroSuite/
├─ design/            # :metro-ui — the shared Windows Metro design system (Apache-2.0)
├─ shared/
│  └─ live-tile-contract/  # :live-tile-contract — versioned cross-APK live-tile protocol
├─ apps/
│  ├─ launcher/       # submodule → github.com/avinashbatchala/win10
│  ├─ weather/        # submodule → github.com/avinashbatchala/MetroWeather (fork of PranshulGG/WeatherMaster)
│  ├─ clock/          # submodule → github.com/avinashbatchala/MetroClock (fork of FossifyOrg/Clock)
│  ├─ music/          # (planned) fork of ViMusic
│  └─ photos/ dialer/ messages/ contacts/   # (planned) forks of the Fossify suite
└─ .gitmodules
```

Each app keeps its **own Gradle build** and consumes `:metro-ui` (and the live-tile
contract where relevant) through Gradle **composite builds**:

```kotlin
// apps/<app>/settings.gradle.kts
includeBuild("../../design")
includeBuild("../../shared/live-tile-contract")
```
```kotlin
// apps/<app>/app/build.gradle.kts
implementation("com.metro:metro-ui")
implementation("com.metro:live-tile-contract")
```

This keeps upstream build files untouched (easier rebases) while sharing one theme,
control kit, pivot, icon set and cross-app live-tile protocol.

## Toolchain baseline

`design/` and the apps we author (launcher, weather) align on: **AGP 9.3.1, Kotlin
2.4.20, Compose BOM 2026.09.00, compileSdk 37, minSdk 24**. Forked apps that keep their
own build (e.g. `apps/clock`, a Fossify fork) preserve their upstream toolchain and only
consume the shared libraries through composite builds; `design/` and
`shared/live-tile-contract/` use conservative API/Kotlin settings so they compile for any
consumer.

## Clone

```bash
git clone --recurse-submodules git@github.com:avinashbatchala/MetroSuite.git
# or, after a normal clone:
git submodule update --init --recursive
```

## Rebase a forked app from upstream

Each app submodule is your fork; the original project is configured as `upstream`
inside it (e.g. `apps/weather` → `PranshulGG/WeatherMaster`).

```bash
cd apps/weather
git remote -v                     # origin = your fork, upstream = original
git fetch upstream
git rebase upstream/main          # replay our theming commits on top
# resolve conflicts (our changes are kept intentionally small/surgical), then:
git push --force-with-lease origin main

cd ../..
git add apps/weather              # record the new submodule commit
git commit -m "chore: bump weather to upstream <sha>"
```

To keep rebases cheap, confine our per-app edits to a small surface: the theme entry
point (delegating to `MetroTheme`), removal of Material-looking chrome, and feature
screens. Prefer contributing generic fixes upstream.

## Development signing (same-signature IPC)

MetroSuite apps communicate over a signature-protected live-tile contract, so their debug
builds must share one signing identity. `tools/metro-debug.keystore` is a **debug** key
(non-sensitive, the same class of key Android generates automatically) that
`apps/launcher` and `apps/clock` prefer when built inside the suite, falling back to the
local/default debug keystore otherwise. Release signing is never affected, and no release
key is committed.

## Helper scripts

Thin wrappers that call each app's own Gradle wrapper (no monolithic Gradle invocation):

```bash
tools/build-clock.sh          # assembleFossDebug
tools/install-clock.sh        # build + adb install
tools/build-suite.sh          # launcher + weather + clock
```

## Licenses

- `design/` (`:metro-ui`): Apache-2.0 (compatible with the GPL-3.0 apps).
- Each app keeps its upstream license (WeatherMaster, ViMusic and the Fossify apps are
  GPL-3.0; AOSP-derived apps are Apache-2.0).
