pipeline {
    agent any
    stages {
        stage('Publish') {
            steps {
                sh './gradlew build'
            }
        }
    }
}
