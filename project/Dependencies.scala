/*
 * Copyright (c) 2015 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
import sbt._

object Dependencies {

  val resolutionRepos = Seq(
    // For user-agent-utils
    "google-api-services"                    at "https://google-api-client-libraries.appspot.com/mavenrepo",
    "Jackson stuff for json"                 at "http://repo2.maven.org/maven2/com/google/http-client/google-http-client-jackson/"
  )

  object V {
    // Java
    val googleApiClient      = "1.18.0-rc"
    val googleApis           = "v2-rev151-1.18.0-rc"
    val googleOauthClient    = "1.18.0-rc"
    val googleHttpClient     = "1.18.0-rc"
    val googleJackson        = "1.15.0-rc"
    // Scala
    val argot                = "1.0.1"
    val json4s               = "3.2.11"
    // Scala (test only)
    val specs2               = "2.2"
    val scalazSpecs2         = "0.1.2"
  }

  object Libraries {
    // Java
    val googleApis           = "com.google.apis"            % "google-api-services-bigquery"  % V.googleApis
    val googleOauthClient    = "com.google.oauth-client"    % "google-oauth-client"           % V.googleOauthClient
    val googleHttpClient     = "com.google.http-client"     % "google-http-client-jackson2"   % V.googleHttpClient
    val googleApiClient      = "com.google.api-client"      %  "google-api-client"            % V.googleApiClient
    val googleJackson        = "com.google.http-client"     % "google-http-client-jackson"    % V.googleJackson
    // Scala
    val argot                = "org.clapper"                %% "argot"                    % V.argot
    val json4sJackson        = "org.json4s"                 %% "json4s-jackson"           % V.json4s
    // Scala (test only)
    val specs2               = "org.specs2"                 %% "specs2"                   % V.specs2         % "test"
    val scalazSpecs2         = "org.typelevel"              %% "scalaz-specs2"            % V.scalazSpecs2   % "test"
  }
}
