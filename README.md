
## Before you begin

This project is an example of a [BioLockJ](https://github.com/BioLockJ-Dev-Team/BioLockJ) module. 

To build it, or even just to use it, you must have successfully installed and tested [BioLockJ](https://github.com/BioLockJ-Dev-Team/BioLockJ).
```
biolockj --version
biolockj ${BLJ}/templates/myFirstPipeline/myFirstPipeline.properties
```

For more information about how to create BioLockJ modules and for other examples, see [the BioLockJ external modules resource repository](https://github.com/BioLockJ-Dev-Team/blj_ext_modules)

This module is requires the [FastQC](https://www.bioinformatics.babraham.ac.uk/projects/fastqc/) program.
```
fastqc --version
```
If this program is not installed on your machine, install it (see [link](https://www.bioinformatics.babraham.ac.uk/projects/fastqc/)) OR add `-d` when calling biolockj so use a pre-made docker image.

### Use this module (the latest release)

See the [userguide pages for the modules in this project](mkdocs/docs/index.md).

Download the jar file to your external modules folder (`mods`), which you point to with the `--external-modules` argument.  In your config file, reference the Simple_FastQC module in your module run order using the `#BioModule` keyword.

Minimalist example:
```
PROJ=FastQC
URL=https://github.com/BioLockJ-Dev-Team/FastQC/releases/latest/download
CONFIG=simple-fastqc.config

mkdir ${PROJ}_Example
cd ${PROJ}_Example
wget $URL/${PROJ}.jar -P $PWD/mods
wget $URL/demo.zip
unzip demo.zip && rm demo.zip 
biolockj --external-modules $PWD/mods ./demo/$CONFIG
# biolockj -d --external-modules $PWD/mods ./demo/$CONFIG
```
The example above will create a minimalist pipeline demonstrating the use of the Simple_FastQC module from the ShowTestVar project.  

Add the `#BioModule` line for the Simple_FastQC module to any other pipeline.

### Build this project from the latest source code

Download the source code:
```
wget https://github.com/BioLockJ-Dev-Team/FastQC/archive/refs/heads/main.zip 
unzip main.zip && rm main.zip && mv FastQC-main ShowTestVar
```

Alternatively, use git:
```
# git clone https://github.com/BioLockJ-Dev-Team/FastQC.git
```

Note: The build file references the BioLockJ project using the `$BLJ` variable.
There is an option to use a relative path instead, see commented lines in the build.xml. 

Run the `ant` command from the project root directory.
```
cd FastQC
ant
```

_If you encounter build difficulties, try using the docker build process below._

Test that BioLockJ recognizes the module.
```
biolockj-api listModules --external-modules $PWD/dist
```
The output list should include "com.github.fodorlab.fastqc.Simple_FastQC".

Run a demo pipeline.
```
biolockj --external-modules $PWD/dist ./demo/simple-fastqc.config
```

Check the demo folder for more examples.

### Build the project and its documentation using docker
Confirm docker is running:
```
docker run --rm hello-world
```

This is the standard build process for BioLockJ modules.

Note: the code block below uses `$PWD` (path to current working directory), and should be run from the project root directory; the folder you downloaded in the previous section.

```
docker run --rm \
  -v $PWD:/project \
  -v $BLJ:/BioLockJ \
  -e BLJ=/BioLockJ \
  -w /project \
  biolockjdevteam/build_and_deploy \
  ant userguide
```

This process produces the jar file and the standardized [userguide pages](mkdocs/docs/index.md) for the modules in this project.

**IF** you are also building BioLockJ from source code, use the same docker image to build the BioLockJ project before building this project:
```
# git clone https://github.com/BioLockJ-Dev-Team/BioLockJ.git
# cd BioLockJ
# BLJ=$PWD
docker run --rm -v ${BLJ}:/biolockj biolockjdevteam/build_and_deploy
```
Using the same docker image for both builds reduces the chances of conflicts arising from different java/host versions.


#### Release process (if you are the maintainer of this project)

Build your jar file using the ant build process:
```
cd FastQC
ant jar
```

Compress your demo folder using:
```
cd FastQC
zip -r -X demo.zip demo
```

Create a release in github: go to tags, find option to create a new release.  
Upload the jar file and the demo.zip file as release artifacts.

Update the instructions in your README accordingly.
