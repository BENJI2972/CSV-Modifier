cd %CD%

for /r %%i in (*) do psql -h localhost -U postgres -d postgres -c "copy navigationdata from '%%i' DELIMITER ';' csv";