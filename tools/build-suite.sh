#!/usr/bin/env bash
# Thin wrapper: builds each MetroSuite app with its own Gradle wrapper.
# Usage: tools/build-suite.sh [launcher|weather|clock ...]
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
: "${ANDROID_HOME:=$HOME/Library/Android/sdk}"
export ANDROID_HOME

TARGETS=("$@")
if [ ${#TARGETS[@]} -eq 0 ]; then
  TARGETS=(launcher weather clock)
fi

build_app() {
  local name="$1"
  local dir="$ROOT_DIR/apps/$name"
  [ -d "$dir" ] || { echo "skip: $name (not present)"; return; }
  echo "==> building $name"
  case "$name" in
    clock) (cd "$dir" && ./gradlew :app:assembleFossDebug --no-configuration-cache) ;;
    *)     (cd "$dir" && ./gradlew :app:assembleDebug --no-configuration-cache) ;;
  esac
}

for app in "${TARGETS[@]}"; do
  build_app "$app"
done
