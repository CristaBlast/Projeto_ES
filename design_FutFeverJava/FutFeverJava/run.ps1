# Compila e executa FutFeverApp usando encoding UTF-8
# Uso: ./run.ps1

$src = "src"
$out = "out"
if (Test-Path $out) { Remove-Item -Recurse -Force $out }
New-Item -ItemType Directory -Path $out > $null

# Compilar com encoding UTF-8
javac -encoding UTF-8 -d $out -sourcepath $src (Get-ChildItem -Recurse -Filter "*.java" -Path $src | ForEach-Object { $_.FullName })
if ($LASTEXITCODE -ne 0) { Write-Error "Compilação falhou."; exit $LASTEXITCODE }

# Executar
java -cp $out futfever.FutFeverApp

