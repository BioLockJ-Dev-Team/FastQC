package com.github.fodorlab.fastqc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import biolockj.Config;
import biolockj.Log;
import biolockj.Properties;
import biolockj.api.ApiModule;
import biolockj.exception.SpecialPropertiesException;
import biolockj.module.BioModule;
import biolockj.module.ScriptModuleImpl;
import biolockj.module.SeqModule;
import biolockj.module.diy.GenMod;

public class Simple_FastQC extends ScriptModuleImpl implements ApiModule {

	private static final String PARAM = "fastqc.param";
	
	private static final String MAIN_FUNCTION = "runFastQC";
	
	private static final String SHOW_INFO = "FastqcInfo";
	
	public Simple_FastQC() {
		addNewProperty( PARAM, Properties.STRING_TYPE, "Parameters used with FastQC; not validated in any way. Avoid using '-o/--outdir', this is automatically set to the module's output folder." );
	}

	@Override
	public List<List<String>> buildScript( List<File> files ) throws Exception {
		List<List<String>> outer = new ArrayList<>();
		List<String> once =  new ArrayList<>();
		once.add( SHOW_INFO );
		outer.add( once );
		for (File file : files) {
			List<String> inner =  new ArrayList<>();
			inner.add( MAIN_FUNCTION + " " + file.getAbsolutePath() );
			outer.add( inner );
		}
		return outer;
	}
	
	@Override
	public List<String> getWorkerScriptFunctions() throws Exception {
		// This function is run once for each file.
		List<String> lines = new ArrayList<>();
		lines.add( "function " + MAIN_FUNCTION + "(){" );
		lines.add( "FILE=$1" );
		lines.add( fastqc() + getFastqcParams() + " --outdir " + getOutputDir().getAbsolutePath() + " $FILE" );
		lines.add( "}" );
		lines.add( "" );
		// The function below will be run once.  
		String infoPath = (new File(getLogDir(), "fastqc_info.txt")).getAbsolutePath();
		Log.debug(getClass(), "The output of 'fastqc --help' will be saved to: " + infoPath);
		lines.add( "function " + SHOW_INFO + "(){" );
		lines.add( fastqc() + " --version > " + infoPath );//create file
		lines.add( "echo '' >> " + infoPath );//append file
		lines.add( fastqc() + " --help >> " + infoPath );//append file
		lines.add( "}" );
		lines.add( "" );
		return lines;
	}
	
	private String getFastqcParams() {
//		String base = Config.getString( this, PARAM, "" );
//		if (base.trim().isEmpty()) return "";
//		else return " " + base.trim();
		return "";
	}
	
	private String fastqc() throws SpecialPropertiesException {
		return Config.getExe( this, "exe.fastqc" );
	}

	/**
	 * Accept sequence files as input, by only using sequence module output as input.
	 */
	@Override
	public boolean isValidInputModule( BioModule module ) {
		return module instanceof SeqModule || module instanceof GenMod;
	}
	
	
	@Override
	public String getDockerImageName() {
		return "fastqc";
	}
	
	@Override
	public String getDockerImageOwner() {
		return "biocontainers";
	}
	
	@Override
	public String getDockerImageTag() {
		// lastest as of 24 July 2021
		return "v0.11.9_cv8"; 
	}

	@Override
	public String getDescription() {
		return "Run [FastQC](https://www.bioinformatics.babraham.ac.uk/projects/fastqc/) on sequence files.";
	}

	@Override
	public String getCitationString() {
		return "Module developed by Ivory Blakley to facilitate using FastQC with BioLockJ." + System.lineSeparator() +
						"[FastQC](https://www.bioinformatics.babraham.ac.uk/projects/fastqc/) is produced by [Babraham Bioinformatics](https://www.bioinformatics.babraham.ac.uk/index.html)";
	}
}
