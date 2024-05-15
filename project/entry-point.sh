#!/bin/sh


# CONTAINER_ALREADY_STARTED="CONTAINER_ALREADY_STARTED_PLACEHOLDER"
# if [ ! -e $CONTAINER_ALREADY_STARTED ]; then
#     touch $CONTAINER_ALREADY_STARTED
#     echo "-- First container startup --"
#     # YOUR_JUST_ONCE_LOGIC_HERE
#     RUN mvn clean install -DskipTests
# else
#     echo "-- Not first container startup --"
# fi

# Set MySQL server connection parameters
DB_HOST="localhost"
DB_USER="root"
DB_PASS="12345"
DB_NAME="sale_db"

# Path to your SQL file
SQL_FILE="/app/sales_db.sql"

echo "Wait for database"
#this check if the database is available now
while ! nc -z mysql 3306; do
 sleep 1
done

echo "Database is ready"
echo "Init database"

# Command to run the SQL file
mysql -h "$DB_HOST" -u "$DB_USER" -p"$DB_PASS" "$DB_NAME" < "$SQL_FILE"

echo "Starting server"