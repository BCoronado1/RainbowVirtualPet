#!/bin/bash

VERSION=1.0
mkdir -p dist

mvn install:install-file -Dfile=lib/JavaVirtualPet-1.0.jar -DgroupId=com.github.bcoronado1.javavirtualpet \
    -DartifactId=JavaVirtualPet -Dversion=1.0 -Dpackaging=jar

# Currently the maven repo has a rainbow-core artifact with a missing hotfix, here we install the artifact manually.
# This artifact was built from the v3.0-yellow tag found at https://github.com/cmu-able/rainbow/tree/v3.0-yellow
mvn install:install-file -Dfile=lib/rainbow-core-3.0.jar -DgroupId=rainbow \
    -DartifactId=rainbow-core -Dversion=3.0 -Dpackaging=jar

mvn clean
mvn install -X || exit 1

cp -v target/RainbowVirtualPet-${VERSION}-jar-with-dependencies.jar dist/RainbowVirtualPet.jar
cp -Rv targets dist
cp -v scripts/run_rainbow.sh dist