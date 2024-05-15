FROM mysql:8.0.0

COPY /project-db-init/sales_db.sql /docker-entrypoint-initdb.d/init.sql

EXPOSE 3306