#!/bin/sh

# Set MySQL server connection parameters
DB_HOST="localhost"
DB_USER="root"
DB_PASS="12345"
DB_NAME="sale_db"

# Path to your SQL file
SQL_FILE="./sale_db.sql"

echo "Wait for database"
#this check if the database is available now
while ! nc -z db 3306; do
 sleep 1
done

echo "Database is ready"
echo "Init database"

# Command to run the SQL file
mysql -h "$DB_HOST" -u "$DB_USER" -p"$DB_PASS" "$DB_NAME" < "$SQL_FILE"

echo "Starting server"

mvn exec:java
