node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/lzipport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/lzipport.git'), string(name: 'PORT_DESCRIPTION', value: 'Lzip is a lossless data compressor with a user interface similar to the one of gzip or bzip2' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
