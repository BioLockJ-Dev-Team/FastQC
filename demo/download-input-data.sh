#!/bin/bash

# See https://www.bioinformatics.babraham.ac.uk/projects/hicup/test_dataset/

URL=http://www.bioinformatics.babraham.ac.uk/projects/hicup
echo "Downloading data from $URL ..."

cd ../temp
echo "Working directory: $PWD"

wget $URL/test_dataset.tar.gz
tar -xvzf test_dataset.tar.gz
mv test_dataset/*.fastq ../output/. && rm -rf test_dataset/

echo "All done!"
