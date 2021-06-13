echo "Build Started"
mvnw install -DskipTests -pl fileServer
copy fileServer/target/fileServer-*.jar app-fs.jar
echo "Build complete"
