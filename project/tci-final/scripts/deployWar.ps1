Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
$username = "tci"
$password = "tci123123"
$filePath = "../api/out/tci.war"
$apiUrl = "https://tci.scm.azurewebsites.net/api/wardeploy"
$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("{0}:{1}" -f $username, $password)))
Invoke-RestMethod -Uri $apiUrl -Headers @{Authorization=("Basic {0}" -f $base64AuthInfo)} -Method POST -InFile $filePath -ContentType "application/octet-stream"