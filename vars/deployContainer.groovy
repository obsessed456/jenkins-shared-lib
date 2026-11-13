// vars/deployContainer.groovy
def call(Map config = [:]) {
    def image = config.image          // e.g. nodemain
    def port  = config.port           // host port, e.g. 3000
    def tag   = config.tag ?: 'v1.0'

    withCredentials([usernamePassword(credentialsId: 'dockerhub',
        usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
        sh "docker pull \$DH_USER/${image}:${tag}"
        sh "docker rm -f ${image} || true"
        sh "docker run -d --name ${image} -p ${port}:3000 \$DH_USER/${image}:${tag}"
    }
}
