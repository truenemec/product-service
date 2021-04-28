pipeline {
  agent none
  stages {
    stage("Prepare container") {
      agent {
        docker {
          image 'openjdk:11.0.5-slim'
          args '-v $HOME/.m2:/root/.m2'
        }
      }
      stages {
        stage('Build') {
          steps {
            checkout scm
            sh 'chmod +x ./mvnw'
            sh './mvnw build-helper:parse-version versions:set   -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion}\${parsedVersion.qualifier?}'
            sh './mvnw clean compile'
          }
        }
        stage('Test') {
          steps {
            sh './mvnw test'
            junit '**/target/surefire-reports/TEST-*.xml'
          }
        }
        stage('Package') {
          steps {
            sh './mvnw package -DskipTests'
          }
        }
        stage('Commit new version') {
          steps {
            sh './mvnw package -DskipTests'
          }
        }
      }

    }
  }
}