!/bin/bash

# See https://www.bioinformatics.babraham.ac.uk/projects/hicup/test_dataset/

echo "Downloading data..."

wget http://www.bioinformatics.babraham.ac.uk/projects/hicup/test_dataset.tar.gz -P ../temp
tar -xvzf ../temp/test_dataset.tar.gz
mv test_dataset/*.fastq ../output/. && rm -rf test_dataset/

echo "All done!"
