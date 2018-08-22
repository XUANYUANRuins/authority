@echo off
echo 正在恢复...
echo.
md "%USERPROFILE%\Local Settings\Application Data\Google\Chrome\User Data"
xcopy "Profile\User Data" "%USERPROFILE%\Local Settings\Application Data\Google\Chrome\User Data" /e /c /y /q
echo.
echo 恢复完毕 by 黯然过客
echo.
pause