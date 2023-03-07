



SYSTEM CONFIGURATION
============================
---------------------------


Windows  : Set - Up
============================

JDK Configuration
---
Steps :

	1) Download the latest version of the JDK from the Oracle website: https://www.oracle.com/java/technologies/downloads/
	2) Run the installer and follow the prompts to install the JDK on your system.
	3) After installation, open the Control Panel and navigate to System > Advanced system settings > Environment Variables.
    4) Under System Variables, click New and add a new variable named JAVA_HOME. Set its value to the directory where the JDK is installed (e.g. C:\Program Files\Java\jdk-17).
    5) Edit the Path variable and add %JAVA_HOME%\bin to the end of the list of values.
    6) Open a new command prompt window and type java -version to verify that the JDK is installed correctly.


Maven Configuration
--------
Steps :

    1) Download the latest version of Maven from the Apache website: https://maven.apache.org/download.cgi
    2) Extract the downloaded archive to a directory of your choice (e.g. C:\Program Files\Maven).
    3) Open the Control Panel and navigate to System > Advanced system settings > Environment Variables.
    4) Under System Variables, click New and add a new variable named MAVEN_HOME. Set its value to the directory where Maven is installed (e.g. C:\Program Files\Maven\apache-maven-3.8.4).
    5) Edit the Path variable and add %MAVEN_HOME%\bin to the end of the list of values.
    6) Open a new command prompt window and type mvn -version to verify that Maven is installed correctly.


MAC  : Set - Up
============================

JDK Configuration
---
Steps :

	1) Open a web browser and navigate to the Oracle JDK download page: https://www.oracle.com/java/technologies/downloads/
    2) Download the latest version of the JDK for Mac.
    3) Run the installer and follow the prompts to install the JDK on your system.
    4) Open a terminal window and run the following command to set the JAVA_HOME environment variable: export JAVA_HOME=$(/usr/libexec/java_home -v 17)
    5) Run the following command to add the JAVA_HOME environment variable to your shell profile (e.g. .bash_profile or .zshrc): 
        echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 17)' >> ~/.bash_profile
            or
        echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 17)' >> ~/.zshrc
    6) Close the terminal window and open a new one.
    7) Type java -version to verify that the JDK is installed correctly.


    

Maven Configuration
--------
Steps :

    1) Open a web browser and navigate to the Apache Maven download page: https://maven.apache.org/download.cgi
    2) Download the latest version of Maven for Mac
    3) Extract the downloaded archive to a directory of your choice (e.g. /usr/local/apache-maven).
    4) Open a terminal window and run the following command to set the MAVEN_HOME environment variable:
        export MAVEN_HOME=/usr/local/apache-maven/apache-maven-3.8.4
    5) Run the following command to add the MAVEN_HOME environment variable to your shell profile (e.g. .bash_profile or .zshrc):
        echo 'export MAVEN_HOME=/usr/local/apache-maven/apache-maven-3.8.4' >> ~/.bash_profile
            or 
        echo 'export MAVEN_HOME=/usr/local/apache-maven/apache-m


