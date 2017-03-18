# Main-Library
Primary Library for Agile Rocket Android Projects

To add library to project(s):

-- In the build.gradle (Project: XYZ) --

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
-- In the build.gradle (Module: app) --
  
dependencies {
        compile 'com.github.SBKeith:Main-Library:Version'
   }
