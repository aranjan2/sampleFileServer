echo "Build Started"
./mvnw install -DskipTests -pl fileServer
cp fileServer/target/fileServer-*.jar app-fs.jar
echo "Build complete"
