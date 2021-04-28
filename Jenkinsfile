pipeline {
  agent none
  stages {
    stage("Prepare container") {
      environment {
        IMAGE_BASE = 'anshelen/microservices-backend'
        IMAGE_TAG = "v$BUILD_NUMBER"
        IMAGE_NAME = "${env.IMAGE_BASE}:${env.IMAGE_TAG}"
        IMAGE_NAME_LATEST = "${env.IMAGE_BASE}:latest"
        DOCKERFILE_NAME = "Dockerfile-packaged"
      }
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
      stage('Push images') {
        agent any
        when {
          branch 'main'
        }
        steps {
//             sh './mvnw build-image'
            IMAGE = readMavenPom().getArtifactId()
            VERSION = readMavenPom().getVersion()
            echo "${IMAGE}"
            echo "${VERSION}"
//             sh "docker tag --all-tags ${myImg.imageName()}"
//              git commit -b 'increment version'
        }
      }
    }
  }
}