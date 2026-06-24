# ── Étape 1 : Build ──────────────────────────────────────────────────────────
FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /src

# Copier uniquement le .csproj d'abord pour profiter du cache Docker sur restore
COPY LookAndSay.csproj ./
RUN dotnet restore

# Copier le reste et compiler en Release
COPY LookAndSay.cs ./
RUN dotnet publish -c Release -o /app/publish

# ── Étape 2 : Runtime (image plus légère, sans SDK) ──────────────────────────
FROM mcr.microsoft.com/dotnet/runtime:8.0 AS runtime
WORKDIR /app
COPY --from=build /app/publish .

ENTRYPOINT ["dotnet", "LookAndSay.dll"]
