pipeline{

		tools{
			jdk 'JAVA'
			maven 'apache-maven-3.6.1'
		}

		agent any
		stages{
			stage('Stage: Clean'){
				steps{
					echo 'Clean starting...'
					powershell 'clean'
				}
			}

			stage('Stage: Test'){
			
				steps{
				
					echo 'Test starting...'
					powershell 'mvn surefire:test'
					echo 'Test ended...'
				}			 

			}		

		}
	}