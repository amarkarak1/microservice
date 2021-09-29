
//checking changes 13


pipeline {
    agent any
     environment {
        AWS_ACCOUNT_ID="653709203391"
        AWS_DEFAULT_REGION="us-east-2" 
        IMAGE_REPO_NAME="samplerepo"
        IMAGE_TAG="first image"
        REPOSITORY_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
    }
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }

    stages {
         stage('Logging into AWS ECR') {
            steps {
                script {
                sh "aws ecr get-login-password --region ${AWS_DEFAULT_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com"
                }
                 
            }
        }
         stage('Cleanup Workspace') {
            steps {
                cleanWs()
                sh """
                echo "Cleaned Up Workspace For Project"
                """
            }
        }
        stage('Build') {
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
                 steps{
    withSonarQubeEnv(installationName: 'SonarCloud') { // You can override the credential to be used
      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
    }
                 }
  }
        stage('Deploy')
        {
            steps{
                script{docker.withRegistry('https://448947842740.dkr.ecr.448947842740.dkr.ecr.us-east-2.amazonaws.com','ecr:448947842740.dkr.ecr.us-east-2:myawscreds'){
                def myImage = docker build 'samplerepo'
                myImage.push('First image')
                }}}}
           


        }
    }

