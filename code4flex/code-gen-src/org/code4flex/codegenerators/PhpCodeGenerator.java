package org.code4flex.codegenerators;

import java.io.File;

import org.code4flex.cgflexintegration.model.MySqlConnectionInformationModel;
import org.code4flex.cgflexintegration.model.PhpProjectInformationModel;
import org.code4flex.codegenerators.resourcexporter.UnzipResourceExporter;
import org.code4flex.codegenerators.utils.CopyDirectory;
import org.code4flex.codegenerators.velocity.php.MySqlConnectionClass;
import org.code4flex.codegenerators.velocity.php.PhpDaoClassCodeGenerator;
import org.code4flex.codegenerators.velocity.php.PhpDaoTesterCodeGenerator;
import org.code4flex.codegenerators.velocity.php.PhpEntityConverterClassCodeGenerator;
import org.code4flex.codegenerators.velocity.php.PhpModelVelocityCodeGenerator;
import org.code4flex.generators.PhpDaoScriptModelGenerator;
import org.code4flex.generators.PhpScriptModelGenerator;


public class PhpCodeGenerator extends CodeGenerator {
	
	//Es: A partir del modelo de la base generan la información para alimentar los templates de velocity
	//En: From a base model generates the information to feed the Generators (These generators where previous initialized in PhpCodeGenerator)
	private PhpScriptModelGenerator phpScriptModelGenerator;
	private PhpDaoScriptModelGenerator phpDaoScriptModelGenerator;
	
	//Es: Generación de codigo en Velocity recupera el template y genera el archivo.
	//En: Code Generation with Velocity Templates
	private PhpDaoClassCodeGenerator generatorPhpDao;
	private PhpEntityConverterClassCodeGenerator generatorEntityConveter;
	private PhpModelVelocityCodeGenerator generatorPhpModelVelocity;
	
	//Es: Template para la conexion al mysql
	//En: Template for the mysqlConnection
	private MySqlConnectionClass generatorConnection;
	
	//Es: Template para instalar el AMFPHP descomprime una carpeta en el proyecto
	//En: Template that unzip AMFPHP project into your php proyect
	private UnzipResourceExporter generatorAmfProyect;
	
	//Es: Templates para pruebas.
	//En: Templates for unit test.
	private PhpDaoTesterCodeGenerator generatorPhpDaoTester;

	
	
	@Override
	public void generate() {
		
		try {
			
			PhpProjectInformationModel phpModel =  this.model.getPhpProjectInformation();
			MySqlConnectionInformationModel mysqlModel = this.model.getConnectionInformation();
			this.phpScriptModelGenerator.setNameSpace(phpModel.getModelPackage());
			this.phpDaoScriptModelGenerator.setNameSpace(phpModel.getDaoPackage());
			this.phpScriptModelGenerator.init(this.model.getSelectedRows());
			this.phpScriptModelGenerator.generateModel();
			this.phpDaoScriptModelGenerator.init(this.model.getSelectedRows());
			this.phpDaoScriptModelGenerator.setModelGenerator(this.phpScriptModelGenerator);
			this.phpDaoScriptModelGenerator.generateModel();
			
			generatorAmfProyect.exportResource();
			
			
			generatorPhpModelVelocity.setProyectName(this.proyectName);
			generatorEntityConveter.setProyectName(this.proyectName);
			generatorPhpDao.setProyectName(this.proyectName);
			generatorPhpDaoTester.setProyectName(this.proyectName);
			generatorConnection.setProyectName(this.proyectName);
			
			generatorPhpModelVelocity.setClasses(this.phpScriptModelGenerator.getPHPClases());
			generatorPhpModelVelocity.generate();
			generatorEntityConveter.setServicesClasses(this.phpDaoScriptModelGenerator.getPhpDaoList());
			generatorEntityConveter.generate();
			generatorPhpDao.setServicesClasses(this.phpDaoScriptModelGenerator.getPhpDaoList());
			generatorPhpDao.generate();
			generatorPhpDaoTester.setServicesClasses(this.phpDaoScriptModelGenerator.getPhpDaoList());
			generatorPhpDaoTester.generate();
			generatorConnection.setNamespace(this.phpDaoScriptModelGenerator.getPhpDaoList().get(0).getNamespace());
			generatorConnection.generate(mysqlModel.getUserName(), mysqlModel.getPassword(),mysqlModel.getDbName(), mysqlModel.getHost());
			try {
				if(this.model.getPhpProjectInformation().isHasEnviromentInfo()){
					CopyDirectory.copyDirectory(new File(this.proyectDestPath), new File(this.model.getPhpProjectInformation().getLocalServerDirectory()));
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public UnzipResourceExporter getGeneratorAmfProyect() {
		return generatorAmfProyect;
	}
	public void setGeneratorAmfProyect(UnzipResourceExporter generatorAmfProyect) {
		this.generatorAmfProyect = generatorAmfProyect;
	}
	public PhpDaoClassCodeGenerator getGeneratorPhpDao() {
		return generatorPhpDao;
	}
	public void setGeneratorPhpDao(PhpDaoClassCodeGenerator generatorPhpDao) {
		this.generatorPhpDao = generatorPhpDao;
	}
	public PhpEntityConverterClassCodeGenerator getGeneratorEntityConveter() {
		return generatorEntityConveter;
	}
	public void setGeneratorEntityConveter(
			PhpEntityConverterClassCodeGenerator generatorEntityConveter) {
		this.generatorEntityConveter = generatorEntityConveter;
	}
	public PhpModelVelocityCodeGenerator getGeneratorPhpModelVelocity() {
		return generatorPhpModelVelocity;
	}
	public void setGeneratorPhpModelVelocity(
			PhpModelVelocityCodeGenerator generatorPhpModelVelocity) {
		this.generatorPhpModelVelocity = generatorPhpModelVelocity;
	}
	public PhpDaoTesterCodeGenerator getGeneratorPhpDaoTester() {
		return generatorPhpDaoTester;
	}
	public void setGeneratorPhpDaoTester(
			PhpDaoTesterCodeGenerator generatorPhpDaoTester) {
		this.generatorPhpDaoTester = generatorPhpDaoTester;
	}
	public MySqlConnectionClass getGeneratorConnection() {
		return generatorConnection;
	}
	public void setGeneratorConnection(MySqlConnectionClass generatorConnection) {
		this.generatorConnection = generatorConnection;
	}
	
	public PhpScriptModelGenerator getPhpScriptModelGenerator() {
		return phpScriptModelGenerator;
	}
	public void setPhpScriptModelGenerator(
			PhpScriptModelGenerator phpScriptModelGenerator) {
		this.phpScriptModelGenerator = phpScriptModelGenerator;
	}
	public PhpDaoScriptModelGenerator getPhpDaoScriptModelGenerator() {
		return phpDaoScriptModelGenerator;
	}
	public void setPhpDaoScriptModelGenerator(
			PhpDaoScriptModelGenerator phpDaoScriptModelGenerator) {
		this.phpDaoScriptModelGenerator = phpDaoScriptModelGenerator;
	}
	
}
