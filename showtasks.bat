call gradlew build
if "%ERRORLEVEL%" == "0" goto runapp
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail


:runapp
call %CATALINA_HOME%\bin\startup.bat
goto startchrome
echo.


:startchrome
start chrome "http://localhost:8080/crud/v1/task/getTasks"
goto end


:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.