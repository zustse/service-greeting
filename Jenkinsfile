pipeline {
    agent any
    
    options {
        
    }
    
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                script {
                    try {
                        sh './gradlew clean build --no-daemon' //run a gradle task
                    } finally {
                        junit '**/build/test-results/test/*.xml' //make the junit test results available in any case (success & failure)
                    }
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
