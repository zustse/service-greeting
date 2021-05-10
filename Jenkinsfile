pipeline {
    agent any
    
    stages {
        stage('Build') {
            tools {
               jdk "Corretto 11"
            }
            steps {
                echo 'Building...'
                script {
                    sh './gradlew clean build' //run a gradle task
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
