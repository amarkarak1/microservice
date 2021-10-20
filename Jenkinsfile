
def gv
def aws_id
pipeline {
    agent none
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }
    environment {
        AWS_ACCOUNT_ID=653709203391
        AWS_DEFAULT_REGION="us-east-1" 
        IMAGE_REPO_NAME="jenkinspipleine"
        
        REPOSITORY_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
    }
   
    stages {
        stage('init'){
            agent any
            steps{
                script{
                    gv = load "script.groovy"
                    aws_id = gv.returncreds()
                    sh """echo ${aws_id["AWS_ACCOUNT_ID"]}"""
                }
            }
        }
    }
}
        
