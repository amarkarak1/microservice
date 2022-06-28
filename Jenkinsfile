
def gv
def aws_id
pipeline {
    agent none
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }
   
   
    stages {
        stage('init'){
            agent any
            steps{
                script{
                    gv = load "script.groovy"
                    aws_id = gv.returncreds()
                    
                }
            }
        }

       stage('Cleanup Workspace') {
            agent any
            steps {
                cleanWs()
                sh """
                echo "Cleaned Up Workspace For Project"
                """
            }
        }
        
        stage('Build') {
            agent any
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/prajwalyb/microservice.git'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean install"
                
               

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
         
        
        stage('SonarQube analysis') {
            agent any
                 steps{
    withSonarQubeEnv(installationName: 'SonarCloud') { // You can override the credential to be used
      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:2.6:sonar'
    }
                 }
        }
        
  
    // Building Docker images
    stage('Building image') {
        agent any
      steps{
        script {
            dockerImage = sh "sudo docker build -t microservice ."
        }
      }
    }
   
  
    

    }
}
