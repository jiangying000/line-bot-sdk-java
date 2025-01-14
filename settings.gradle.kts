/*
 * Copyright 2023 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

pluginManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://repo.spring.io/plugins-release") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}


include("line-bot-api-client")
include("line-bot-model")
include("line-bot-servlet")
include("line-bot-spring-boot")
include("line-bot-cli")
include("line-bot-parser")

// samples
include("sample-spring-boot-echo")
include("sample-spring-boot-echo-kotlin")
include("sample-spring-boot-kitchensink")
include("sample-manage-audience")
