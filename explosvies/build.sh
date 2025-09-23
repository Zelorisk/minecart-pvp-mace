#!/bin/bash

echo "Building ExplosiveDamage plugin for Paper..."

if ! command -v mvn &> /dev/null; then
    echo "Maven is not installed. Please install Maven to build this plugin."
    exit 1
fi

echo "Cleaning previous builds..."
mvn clean

echo "Compiling and packaging plugin..."
mvn package

if [ $? -eq 0 ]; then
    echo "Build successful!"
    echo "Plugin JAR file is located at: target/explosive-damage-1.0.0.jar"
    echo "Copy this JAR file to your Paper server's plugins directory."
else
    echo "Build failed. Please check the error messages above."
    exit 1
fi
