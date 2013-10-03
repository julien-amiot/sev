import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build 
{
	val appName         = "sev"
	val appVersion      = "1.0-SNAPSHOT"
	val appDependencies = Seq(// Add your project dependencies here,
	)

	def customLessEntryPoints(base: File): PathFinder = ( 
	    (base / "app" / "assets" / "stylesheets" / "bootstrap.less") +++
	    (base / "app" / "assets" / "stylesheets" / "responsive.less") +++ 
	    (base / "app" / "assets" / "stylesheets" / "*.less")
	)
	
	val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
	  lessEntryPoints <<= baseDirectory(customLessEntryPoints)      
	)
}