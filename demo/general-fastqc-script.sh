#!/bin/bash

# This script is meant to be run as a GenMod module in BioLockJ

inFile=$1

echo "FastQC version:"
fastqc --version
echo ""

echo "Running fastqc on file: $inFile "
echo ""

fastqc --outdir ../output $inFile

echo ""
echo "Done!"
