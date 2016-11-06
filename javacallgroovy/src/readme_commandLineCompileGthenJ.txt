E:\Users\henryms\sw\ustodo\130707public\javacallgroovy\src


groovyc Script.groovy
-- rem JavaClassToTestPassingToG is just a container class
javac -cp .;groovy-all-2.0.7.jar  JavaClassToTestPassingToG.java
javac -cp .;groovy-all-2.0.7.jar  Main.java
java -cp .;groovy-all-2.0.7.jar Main

-- works but Main seems better
-- javac -cp .;groovy-all-2.0.7.jar  Runner.java
-- java -cp .;groovy-all-2.0.7.jar Runner

