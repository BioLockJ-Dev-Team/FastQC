# Simple_FastQC
Add to module run order:                    
`#BioModule com.github.fodorlab.fastqc.Simple_FastQC`

## Description 
Run [FastQC](https://www.bioinformatics.babraham.ac.uk/projects/fastqc/) on sequence files.

## Properties 
*Properties are the `name=value` pairs in the configuration file.*                   

### Simple_FastQC properties: 
| Property| Description |
| :--- | :--- |
| *fastqc.param* | _string_ <br>Parameters used with FastQC; not validated in any way. Avoid using '-o/--outdir', this is automatically set to the module's output folder.<br>*default:*  *null* |

### General properties applicable to this module: 
| Property| Description |
| :--- | :--- |
| *cluster.batchCommand* | _string_ <br>Terminal command used to submit jobs on the cluster<br>*default:*  *null* |
| *cluster.jobHeader* | _string_ <br>Header written at top of worker scripts<br>*default:*  *null* |
| *cluster.modules* | _list_ <br>List of cluster modules to load at start of worker scripts<br>*default:*  *null* |
| *cluster.prologue* | _string_ <br>To run at the start of every script after loading cluster modules (if any)<br>*default:*  *null* |
| *cluster.statusCommand* | _string_ <br>Terminal command used to check the status of jobs on the cluster<br>*default:*  *null* |
| *docker.saveContainerOnExit* | _boolean_ <br>If Y, docker run command will NOT include the --rm flag<br>*default:*  *null* |
| *docker.verifyImage* | _boolean_ <br>In check dependencies, run a test to verify the docker image.<br>*default:*  *null* |
| *script.defaultHeader* | _string_ <br>Store default script header for MAIN script and locally run WORKER scripts.<br>*default:*  #!/bin/bash |
| *script.numThreads* | _integer_ <br>Used to reserve cluster resources and passed to any external application call that accepts a numThreads parameter.<br>*default:*  8 |
| *script.numWorkers* | _integer_ <br>Set number of samples to process per script (if parallel processing)<br>*default:*  1 |
| *script.permissions* | _string_ <br>Used as chmod permission parameter (ex: 774)<br>*default:*  770 |
| *script.timeout* | _integer_ <br>Sets # of minutes before worker scripts times out.<br>*default:*  *null* |

## Details 
_version: 0.0.0_ 
*none*

## Adds modules 
**pre-requisite modules**                    
*none found*                   
**post-requisite modules**                    
*none found*                   

## Docker 
If running in docker, this module will run in a docker container from this image:<br>
```
biocontainers/fastqc:v0.11.9_cv8
```
This can be modified using the following properties:<br>
`Simple_FastQC.imageOwner`<br>
`Simple_FastQC.imageName`<br>
`Simple_FastQC.imageTag`<br>

## Citation 
Module developed by Ivory Blakley to facilitate using FastQC with BioLockJ.                   
[FastQC](https://www.bioinformatics.babraham.ac.uk/projects/fastqc/) is produced by [Babraham Bioinformatics](https://www.bioinformatics.babraham.ac.uk/index.html)

