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
					powershell 'mvn clean'
				}
			}
			
			//stage('Stage: Install'){
			//	steps{
			//		echo 'Install starting...'
			//		powershell 'mvn install'
			//	}
			//}

			stage('Stage: Test'){
			
				steps{
				
					echo 'Test starting...'
					powershell 'mvn test'
					echo 'Test ended...'
				}			 

			}		

		}
	}