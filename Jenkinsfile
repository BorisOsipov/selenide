pipeline {
  agent {
    docker { image 'atlassianlabs/docker-node-jdk-chrome-firefox' }
  }
  stages {
    stage('Test') {
      steps {
        sh './gradlew check --no-daemon --console=plain'
      }
    }

    stage('Chrome') {
      steps {
        sh './gradlew chrome_headless --no-daemon --console=plain'
      }
    }
  }
}
