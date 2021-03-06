package generated

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.freeDiskSpace
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.swabra
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dockerCommand
import common.TeamCityDockerImagesRepo.TeamCityDockerImagesRepo

object push_local_windows_2004 : BuildType({
name = "Build and push windows 2004"
buildNumberPattern="%dockerImage.teamcity.buildNumber%-%build.counter%"
description  = "teamcity-server:2020.2.1-nanoserver-2004,latest,2020.2.1 teamcity-minimal-agent:2020.2.1-nanoserver-2004,latest,2020.2.1 teamcity-agent:2020.2.1-windowsservercore-2004,2020.2.1-windowsservercore,latest-windowsservercore:2020.2.1-nanoserver-2004,latest,2020.2.1"
vcs {root(TeamCityDockerImagesRepo)}
steps {
dockerCommand {
name = "pull mcr.microsoft.com/powershell:nanoserver-2004"
commandType = other {
subCommand = "pull"
commandArgs = "mcr.microsoft.com/powershell:nanoserver-2004"
}
}

dockerCommand {
name = "pull mcr.microsoft.com/windows/nanoserver:2004"
commandType = other {
subCommand = "pull"
commandArgs = "mcr.microsoft.com/windows/nanoserver:2004"
}
}

dockerCommand {
name = "pull mcr.microsoft.com/dotnet/framework/sdk:4.8-windowsservercore-2004"
commandType = other {
subCommand = "pull"
commandArgs = "mcr.microsoft.com/dotnet/framework/sdk:4.8-windowsservercore-2004"
}
}

script {
name = "context teamcity-server:2020.2.1-nanoserver-2004"
scriptContent = """
echo 2> context/.dockerignore
echo TeamCity/buildAgent >> context/.dockerignore
echo TeamCity/temp >> context/.dockerignore
""".trimIndent()
}

dockerCommand {
name = "build teamcity-server:2020.2.1-nanoserver-2004"
commandType = build {
source = file {
path = """context/generated/windows/Server/nanoserver/2004/Dockerfile"""
}
contextDir = "context"
namesAndTags = """
teamcity-server:2020.2.1-nanoserver-2004
""".trimIndent()
}
param("dockerImage.platform", "windows")
}

script {
name = "context teamcity-minimal-agent:2020.2.1-nanoserver-2004"
scriptContent = """
echo 2> context/.dockerignore
echo TeamCity/webapps >> context/.dockerignore
echo TeamCity/devPackage >> context/.dockerignore
echo TeamCity/lib >> context/.dockerignore
""".trimIndent()
}

dockerCommand {
name = "build teamcity-minimal-agent:2020.2.1-nanoserver-2004"
commandType = build {
source = file {
path = """context/generated/windows/MinimalAgent/nanoserver/2004/Dockerfile"""
}
contextDir = "context"
namesAndTags = """
teamcity-minimal-agent:2020.2.1-nanoserver-2004
""".trimIndent()
}
param("dockerImage.platform", "windows")
}

script {
name = "context teamcity-agent:2020.2.1-windowsservercore-2004"
scriptContent = """
echo 2> context/.dockerignore
echo TeamCity/webapps >> context/.dockerignore
echo TeamCity/devPackage >> context/.dockerignore
echo TeamCity/lib >> context/.dockerignore
""".trimIndent()
}

dockerCommand {
name = "build teamcity-agent:2020.2.1-windowsservercore-2004"
commandType = build {
source = file {
path = """context/generated/windows/Agent/windowsservercore/2004/Dockerfile"""
}
contextDir = "context"
namesAndTags = """
teamcity-agent:2020.2.1-windowsservercore-2004
""".trimIndent()
}
param("dockerImage.platform", "windows")
}

script {
name = "context teamcity-agent:2020.2.1-nanoserver-2004"
scriptContent = """
echo 2> context/.dockerignore
echo TeamCity/webapps >> context/.dockerignore
echo TeamCity/devPackage >> context/.dockerignore
echo TeamCity/lib >> context/.dockerignore
""".trimIndent()
}

dockerCommand {
name = "build teamcity-agent:2020.2.1-nanoserver-2004"
commandType = build {
source = file {
path = """context/generated/windows/Agent/nanoserver/2004/Dockerfile"""
}
contextDir = "context"
namesAndTags = """
teamcity-agent:2020.2.1-nanoserver-2004
""".trimIndent()
}
param("dockerImage.platform", "windows")
}

dockerCommand {
name = "tag teamcity-server:2020.2.1-nanoserver-2004"
commandType = other {
subCommand = "tag"
commandArgs = "teamcity-server:2020.2.1-nanoserver-2004 %docker.buildRepository%teamcity-server%docker.buildImagePostfix%:2020.2.1-nanoserver-2004"
}
}

dockerCommand {
name = "tag teamcity-minimal-agent:2020.2.1-nanoserver-2004"
commandType = other {
subCommand = "tag"
commandArgs = "teamcity-minimal-agent:2020.2.1-nanoserver-2004 %docker.buildRepository%teamcity-minimal-agent%docker.buildImagePostfix%:2020.2.1-nanoserver-2004"
}
}

dockerCommand {
name = "tag teamcity-agent:2020.2.1-windowsservercore-2004"
commandType = other {
subCommand = "tag"
commandArgs = "teamcity-agent:2020.2.1-windowsservercore-2004 %docker.buildRepository%teamcity-agent%docker.buildImagePostfix%:2020.2.1-windowsservercore-2004"
}
}

dockerCommand {
name = "tag teamcity-agent:2020.2.1-nanoserver-2004"
commandType = other {
subCommand = "tag"
commandArgs = "teamcity-agent:2020.2.1-nanoserver-2004 %docker.buildRepository%teamcity-agent%docker.buildImagePostfix%:2020.2.1-nanoserver-2004"
}
}

dockerCommand {
name = "push teamcity-server:2020.2.1-nanoserver-2004"
commandType = push {
namesAndTags = """
%docker.buildRepository%teamcity-server%docker.buildImagePostfix%:2020.2.1-nanoserver-2004
""".trimIndent()
removeImageAfterPush = false
}
}

dockerCommand {
name = "push teamcity-minimal-agent:2020.2.1-nanoserver-2004"
commandType = push {
namesAndTags = """
%docker.buildRepository%teamcity-minimal-agent%docker.buildImagePostfix%:2020.2.1-nanoserver-2004
""".trimIndent()
removeImageAfterPush = false
}
}

dockerCommand {
name = "push teamcity-agent:2020.2.1-windowsservercore-2004"
commandType = push {
namesAndTags = """
%docker.buildRepository%teamcity-agent%docker.buildImagePostfix%:2020.2.1-windowsservercore-2004
""".trimIndent()
removeImageAfterPush = false
}
}

dockerCommand {
name = "push teamcity-agent:2020.2.1-nanoserver-2004"
commandType = push {
namesAndTags = """
%docker.buildRepository%teamcity-agent%docker.buildImagePostfix%:2020.2.1-nanoserver-2004
""".trimIndent()
removeImageAfterPush = false
}
}

}
features {
freeDiskSpace {
requiredSpace = "38gb"
failBuild = true
}
dockerSupport {
cleanupPushedImages = true
loginToRegistry = on {
dockerRegistryId = "PROJECT_EXT_4022"
}
}
swabra {
forceCleanCheckout = true
}
}
dependencies {
dependency(AbsoluteId("TC_Trunk_BuildDistDocker")) {
snapshot { onDependencyFailure = FailureAction.FAIL_TO_START
reuseBuilds = ReuseBuilds.ANY
synchronizeRevisions = false }
artifacts {
artifactRules = "TeamCity.zip!/**=>context/TeamCity"
}
}
}
params {
param("system.teamcity.agent.ensure.free.space", "38gb")
}
})

