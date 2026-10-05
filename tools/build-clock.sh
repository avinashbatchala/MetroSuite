#!/usr/bin/env bash
# Thin wrapper: builds MetroClock using its own Gradle wrapper.
# Usage: tools/build-clock.sh [extra gradle args]
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
CLOCK_DIR="$ROOT_DIR/apps/clock"

: "${ANDROID_HOME:=$HOME/Library/Android/sdk}"
export ANDROID_HOME

cd "$CLOCK_DIR"
./gradlew :app:assembleFossDebug --no-configuration-cache "$@"
echo "APK: $CLOCK_DIR/app/build/outputs/apk/foss/debug/"
