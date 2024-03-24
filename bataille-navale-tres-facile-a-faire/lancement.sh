clear
cd src
javac -d ../build modele/*.java controleur/*.java vue/*.java
cd ../build
java controleur.Orchestrator
