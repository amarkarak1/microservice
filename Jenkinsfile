



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
        stage('Logging into AWS ECR') {
            agent any
            steps {
                
                script {
                sh "aws ecr get-login-password --region ${aws_id["AWS_DEFAULT_REGION"]} | docker login --username AWS --password-stdin ${aws_id["AWS_ACCOUNT_ID"]}.dkr.ecr.${aws_id["AWS_DEFAULT_REGION"]}.amazonaws.com"
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
        stage('git')
        {
            agent any
            steps{
                git credentialsId: '63d5ff9f-a87e-4c3c-9d84-09f7f55aa496', url: 'https://github.com/prajwalyb/microservice.git'
                
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
      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
    }
                 }
        }
        
  
    // Building Docker images
    stage('Building image') {
        agent any
      steps{
        script {
            dockerImage = docker.build "${aws_id["IMAGE_REPO_NAME"]}:${env.BUILD_ID}"
        }
      }
    }
   
    // Uploading Docker images into AWS ECR
    stage('Pushing to ECR') {
        agent any
     steps{  
         script {
             sh "docker tag ${aws_id["IMAGE_REPO_NAME"]}:${env.BUILD_ID} ${aws_id["REPOSITORY_URI"]}:${env.BUILD_ID}"
             sh "docker push ${aws_id["REPOSITORY_URI"]}:${env.BUILD_ID}"
         }
        }
      }
        

    }
}

