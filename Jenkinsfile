def githubCredentialsId='bb23ce6e-aab2-4273-bf83-5e639f6a2324'
def nexusCredentialsId='neitNexus'

 pipeline {
     agent any
     stages {
         stage('Upload archives') {
             steps {
                  withCredentials([usernamePassword(credentialsId: nexusCredentialsId, usernameVariable: 'username', passwordVariable: 'password')]){
                    sh './gradlew uploadArchives -PneitNexusUser=${username} -PneitNexusPassword=${password}'
                 }
             }
         }
     }
 }