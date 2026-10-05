#!/usr/bin/env bash
# Thin wrapper: builds and installs MetroClock (foss debug) on the connected device.
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
CLOCK_DIR="$ROOT_DIR/apps/clock"

: "${ANDROID_HOME:=$HOME/Library/Android/sdk}"
ADB="${ADB:-$ANDROID_HOME/platform-tools/adb}"
export ANDROID_HOME

cd "$CLOCK_DIR"
./gradlew :app:assembleFossDebug --no-configuration-cache
APK="$(ls "$CLOCK_DIR"/app/build/outputs/apk/foss/debug/*.apk | head -1)"
"$ADB" install -r "$APK"
