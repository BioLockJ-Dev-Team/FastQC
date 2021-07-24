!/bin/bash

# This is a set of sequence example-size files created by the H
# See https://github.com/biobakery/biobakery/wiki/metaphlan3#input-files

echo "Downloading data..."

URL=https://github.com/biobakery/biobakery/raw/master/demos/biobakery_demos/data/metaphlan3/input
wget ${URL}/SRS014476-Supragingival_plaque.fasta.gz -P ../output
wget ${URL}/SRS014494-Posterior_fornix.fasta.gz -P ../output
wget ${URL}/SRS014459-Stool.fasta.gz -P ../output
wget ${URL}/SRS014464-Anterior_nares.fasta.gz -P ../output
wget ${URL}/SRS014470-Tongue_dorsum.fasta.gz -P ../output
wget ${URL}/SRS014472-Buccal_mucosa.fasta.gz -P ../output

echo "All done!"
