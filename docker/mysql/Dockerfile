FROM mysql:5.7
COPY ./initdb.d/* /docker-entrypoint-initdb.d/
RUN touch /var/log/mysql/mysqld.log # 指定の場所にログを記録するファイルを作る