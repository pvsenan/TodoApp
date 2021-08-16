pipeline {
    agent any
    environment {
      AWS_ACCESS_KEY_ID=credentials('AWS_ACCESS')
      AWS_SECRET_ACCESS_KEY=credentials('AWS_SECRET')
      APP_NAME='TodoApp'
    }
    stages {
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew build'
            }
        }
        stage('Test') {
             steps {
               sh 'chmod +x gradlew'
               sh './gradlew test'
             }
         }

        stage('Deploy') {
            input {
                message "Deploy app. Please confirm ?"
             }
             steps {
               sh 'mkdir -p artifacts'
               sh 'mv build/libs/TodoApp-0.0.1-SNAPSHOT.jar artifacts/$APP_NAME.jar'
               sh 'cd artifacts'
               sh "ls -l"
               sh 'eb init $APP_NAME  --region us-east-1'
               sh 'eb deploy $APP_NAME --region eu-north-1 --timeout 40'
             }
         }
    }
}
