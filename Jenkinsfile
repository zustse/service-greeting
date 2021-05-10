pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                script {
                    try {
                        sh 'java -version'
                        sh './gradlew clean' //run a gradle task
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
