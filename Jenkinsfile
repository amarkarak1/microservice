
def gv
def aws_id
pipeline {
    agent none
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }
    environment {
       
    }
   
    stages {
        stage('init'){
            agent any
            steps{
                script{
                    gv = load "script.groovy"
                    aws_id = gv.returncreds()
                    sh """echo ${aws_id["REPOSITORY_URI"]}"""
                }
            }
        }
       
        

    }
}
        
