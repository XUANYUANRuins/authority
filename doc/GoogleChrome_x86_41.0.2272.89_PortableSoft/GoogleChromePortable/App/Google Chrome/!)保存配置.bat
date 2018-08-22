@echo off
echo 正在保存...
echo.
md "Profile\User Data"
xcopy "%USERPROFILE%\Local Settings\Application Data\Google\Chrome\User Data" "Profile\User Data" /e /c /y /q
echo.
echo 保存完毕 by 黯然过客
echo.
pause