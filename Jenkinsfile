def githubCredentialsId='bb23ce6e-aab2-4273-bf83-5e639f6a2324'
def nexusCredentialsId='neitNexus'

pipeline {
    agent {
        label 'jnlp_slave'
    }
    stages {
        stage('Publish release') {
            steps {
                withCredentials([usernamePassword(credentialsId: nexusCredentialsId, usernameVariable: 'username', passwordVariable: 'password')]){
                    sh './gradlew publish -PneitNexusUser=${username} -PneitNexusPassword=${password}'
                }
            }
        }
        stage('Publish release') {
                    steps {
                        withCredentials([usernamePassword(credentialsId: nexusCredentialsId, usernameVariable: 'username', passwordVariable: 'password')]){
                            sh './gradlew publish -PneitNexusUser=${username} -PneitNexusPassword=${password} -Pdisl_neit.doSnapshot=true'
                        }
                    }
                }
    }
}