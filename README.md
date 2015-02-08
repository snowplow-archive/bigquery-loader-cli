# BigQuery Loader CLI

[ ![Build Status] [travis-image] ] [travis] [ ![Release] [release-image] ] [releases] [ ![License] [license-image] ] [license]

## Overview

A prototype command-line app to upload Snowplow enriched events from local storage to **[Google BigQuery] [bigquery]**.

## Getting Started

### 1. Dependencies

You will need:

* Some Snowplow enriched events as typically archived in Amazon S3
* Java 7+ installed
* A Google BigQuery account

### 2. Installing

The app is hosted from Bintray:

```bash
> wget http://dl.bintray.com/snowplow/snowplow-generic/bigquery_loader_cli_0.1.0.zip
> unzip bigquery_loader_cli_0.1.0.zip
```

### 3. BigQuery setup

First, **[sign up] [bigquery-signup]** to BigQuery if you have not already done so, and enable billing.

Second, create a project, and make a note of the **Project Number** by clicking on the name of the project on the **[Google Developers Console] [google-developers-console]**.

Third, our command-line app will need credentials to access the BigQuery project:

1. Click on the **Consent screen* link in the **APIs and auth** section of the Developer Console, add an **Email address** and hit **Save** 
2. Click on the **Credentials** link in the **APIs and auth** section
3. Click on the **create new Client ID** button, selecting **Installed application** as the application type and **other** as the installed application type
4. Click **CreateClient Id** and then **Download JSON** to save the file
5. Save the `client_secrets` file to the same directory that you unzipped the command-line app
6. Rename the `client_secrets` file to `client_secrets_<projectId>.json`, where `<projectId>` is the Project Number obtained earlier

### 4. Downloading some Snowplow enriched events

Assuming that you are running the Snowplow Hadoop-based data pipeline with EmrEtlRunner, you can quickly retrieve January's enriched events using the following:

```bash
> aws --profile="xxx" s3 cp "s3://xxx-archive/enriched/good/" . --recursive \ 
    --exclude "*" --include "run=2015-01-*"
> find . -type f -execdir bash -c 'd="${PWD##*/}"; [[ "$1" != "$d-"* ]] && mv "$1" "../$d-$1"' - '{}' \;
> find . -type d -exec rm -d {} \;
```

### 5. Uploading a first batch of events

To upload your data you simply type the command:

```bash
> java -jar bigquery-loader-cli-0.1.0 --create-table \
    <projectId> <datasetId> <tableId> <dataLocation>
```

where:

* `<projectId>` is the Project Number obtained from the Google development console
* `<datasetId>` is the name of the dataset, which will be created if it doesn't already exist
* `<tableId>` is the name of the table, which will be created if it doesn't already exist
* `<dataLocation>` is the location of either a single file of Snowplow enriched events, or an un-nested folder of Snowplow enriched events

The first time you run this command, you will be prompted to go through Google's browser-based authentication process.

### 6. Uploading further batches of events

To append further data to the table simply run the command again, omitting the `--create-table` flag and changing `<dataLocation>` as appropriate.

**Warning: loads are not idempotent. Running the command twice against the same files will result in two copies of the events being added to the table.**

## Developer Quickstart

Assuming git, **[Vagrant] [vagrant-install]** and **[VirtualBox] [virtualbox-install]** installed:

```bash
 host> git clone https://github.com/snowplow/bigquery-loader-cli
 host> cd bigquery-loader-cli
 host> vagrant up && vagrant ssh
guest> cd /vagrant
guest> sbt test
```

## Copyright and license

Copyright 2015 Snowplow Analytics Ltd.

Licensed under the **[Apache License, Version 2.0] [license]** (the "License");
you may not use this software except in compliance with the License.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[travis]: https://travis-ci.org/snowplow/bigquery-loader-cli
[travis-image]: https://travis-ci.org/snowplow/bigquery-loader-cli.png?branch=master

[release-image]: http://img.shields.io/badge/release-0.1.0-blue.svg?style=flat
[releases]: https://github.com/snowplow/bigquery-loader-cli/releases

[license-image]: http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat
[license]: http://www.apache.org/licenses/LICENSE-2.0

[bigquery]: https://cloud.google.com/bigquery
[bigquery-signup]: https://cloud.google.com/bigquery/sign-up
[google-developers-console]: https://console.developers.google.com/project/

[vagrant-install]: http://docs.vagrantup.com/v2/installation/index.html
[virtualbox-install]: https://www.virtualbox.org/wiki/Downloads
