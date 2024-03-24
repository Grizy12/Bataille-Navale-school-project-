clear
cd src
javac -d ../build -cp ../lib/junit.jar modele/*.java controleur/*.java vue/*.java test/*.java
cd ../build
java -cp ./:../lib/junit.jar test.TestClass
