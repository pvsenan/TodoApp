pipeline {
    agent any
    stages {
        stage('Publish') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew build'
            }
        }
    }
}
